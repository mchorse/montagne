package mchorse.montagne;

import mchorse.montagne.capabilities.CapabilityHandler;
import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.BuildingStorage;
import mchorse.montagne.capabilities.building.IBuilding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy
{
    public void preLoad(FMLPreInitializationEvent event)
    {}

    public void load(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());

        CapabilityManager.INSTANCE.register(IBuilding.class, new BuildingStorage(), Building.class);
    }
}