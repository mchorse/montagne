package mchorse.montagne.client;

import org.lwjgl.input.Keyboard;

import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.IBuilding;
import mchorse.montagne.network.Dispatcher;
import mchorse.montagne.network.common.PacketPlaceMidAirBlock;
import mchorse.montagne.network.common.PacketToggleBuildingMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public class KeyboardHandler
{
    public KeyBinding toggleBuildingMode;

    private Minecraft mc = Minecraft.getMinecraft();
    private int delay;

    public KeyboardHandler()
    {
        this.toggleBuildingMode = new KeyBinding("montagne.keys.toggle_build_mode", Keyboard.KEY_B, "montagne.keys.title");

        ClientRegistry.registerKeyBinding(this.toggleBuildingMode);
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event)
    {
        IBuilding building = Building.get(this.mc.thePlayer);

        if (this.toggleBuildingMode.isPressed())
        {
            Dispatcher.sendToServer(new PacketToggleBuildingMode(!building.isBuildingMode()));
        }
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event)
    {
        if (this.mc.thePlayer == null || event.phase == Phase.END)
        {
            return;
        }

        IBuilding building = Building.get(this.mc.thePlayer);

        if (building.isBuildingMode())
        {
            RayTraceResult over = this.mc.objectMouseOver;
            RayTraceResult result = RenderingHandler.result;

            if (this.mc.gameSettings.keyBindUseItem.isKeyDown() && result != null && over != null && over.typeOfHit == Type.MISS)
            {
                if (this.delay <= 0)
                {
                    ItemStack item = this.mc.thePlayer.getHeldItemMainhand();
                    EnumHand hand = EnumHand.MAIN_HAND;

                    if (item == null)
                    {
                        item = this.mc.thePlayer.getHeldItemOffhand();
                        hand = EnumHand.OFF_HAND;
                    }

                    if (item != null)
                    {
                        BlockPos pos = result.getBlockPos().offset(result.sideHit);
                        float hitX = (float) result.hitVec.xCoord - pos.getX();
                        float hitY = (float) result.hitVec.yCoord - pos.getY();
                        float hitZ = (float) result.hitVec.zCoord - pos.getZ();

                        Dispatcher.sendToServer(new PacketPlaceMidAirBlock(pos, hand, EnumFacing.EAST, hitX, hitY, hitZ));
                        this.mc.thePlayer.swingArm(hand);
                    }

                    this.delay = 2;
                }
            }

            this.delay--;
        }
    }
}