package mchorse.montagne;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Montagne mod
 */
@Mod(modid = Montagne.MODID, name = Montagne.MODNAME, version = Montagne.VERSION)
public class Montagne
{
    /* Metadata fields */
    public static final String MODID = "montagne";
    public static final String MODNAME = "Monagne";
    public static final String VERSION = "1.0";

    public static final String CLIENT_PROXY = "mchorse.montagne.ClientProxy";
    public static final String SERVER_PROXY = "mchorse.montagne.CommonProxy";

    /* Forge stuff classes */
    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static Montagne instance;

    /* Events */
    @EventHandler
    public void preLoad(FMLPreInitializationEvent event)
    {
        proxy.preLoad(event);
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        proxy.load(event);
    }
}