package mchorse.montagne.capabilities;

import mchorse.montagne.Montagne;
import mchorse.montagne.capabilities.building.BuildingProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
     * Attach capabilities (well, only one, right now)
     */
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent.Entity event)
    {
        if (!(event.getEntity() instanceof EntityPlayer)) return;

        event.addCapability(BUILDING_CAP, new BuildingProvider());
    }
}