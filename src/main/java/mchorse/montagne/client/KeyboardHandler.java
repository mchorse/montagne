package mchorse.montagne.client;

import org.lwjgl.input.Keyboard;

import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.IBuilding;
import mchorse.montagne.network.Dispatcher;
import mchorse.montagne.network.common.PacketToggleBuildingMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyboardHandler
{
    public KeyBinding toggleBuildingMode;

    public KeyboardHandler()
    {
        this.toggleBuildingMode = new KeyBinding("montagne.keys.toggle_build_mode", Keyboard.KEY_B, "montagne.keys.title");

        ClientRegistry.registerKeyBinding(this.toggleBuildingMode);
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event)
    {
        if (this.toggleBuildingMode.isPressed())
        {
            IBuilding building = Building.get(Minecraft.getMinecraft().thePlayer);

            Dispatcher.sendToServer(new PacketToggleBuildingMode(!building.isBuildingMode()));
        }
    }
}