package mchorse.montagne.capabilities;

import mchorse.montagne.Montagne;
import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.BuildingProvider;
import mchorse.montagne.capabilities.building.IBuilding;
import mchorse.montagne.network.Dispatcher;
import mchorse.montagne.network.common.PacketToggleBuildingMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

/**
 * Capability handler class
 *
 * This class is responsible for managing capabilities, i.e. attaching
 * capabilities and syncing values on the client.
 */
public class CapabilityHandler
{
    public static final ResourceLocation BUILDING_CAP = new ResourceLocation(Montagne.MODID, "building_capability");

    /**
     * Attach capability
     */
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof EntityPlayer)
        {
            event.addCapability(BUILDING_CAP, new BuildingProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerLoggedInEvent event)
    {
        EntityPlayer player = event.player;
        IBuilding cap = Building.get(player);

        if (cap != null)
        {
            Dispatcher.sendTo(new PacketToggleBuildingMode(cap.isBuildingMode()), (EntityPlayerMP) player);
        }
    }

    @SubscribeEvent
    public void onPlayerUseItem(RightClickBlock event)
    {
        EntityPlayer player = event.getEntityPlayer();
        IBuilding cap = Building.get(player);

        if (cap != null && cap.isBuildingMode())
        {
            cap.placeBlock(event);
        }
    }

    @SubscribeEvent
    public void onPlayerDestroyBlock(BreakEvent event)
    {
        EntityPlayer player = event.getPlayer();
        IBuilding cap = Building.get(player);

        if (cap != null && cap.isBuildingMode())
        {
            cap.breakBlock(event);
        }
    }
}