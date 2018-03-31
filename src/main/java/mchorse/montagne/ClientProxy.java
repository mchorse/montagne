package mchorse.montagne;

import mchorse.montagne.client.GuiBuilding;
import mchorse.montagne.client.KeyboardHandler;
import mchorse.montagne.client.RenderingHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    public static GuiBuilding GUI;

    @Override
    public void preLoad(FMLPreInitializationEvent event)
    {
        super.preLoad(event);

        GUI = new GuiBuilding();
    }

    @Override
    public void load(FMLInitializationEvent event)
    {
        super.load(event);

        MinecraftForge.EVENT_BUS.register(new KeyboardHandler());
        MinecraftForge.EVENT_BUS.register(new RenderingHandler());
    }
}