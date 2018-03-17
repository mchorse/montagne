package mchorse.montagne.network;

import mchorse.montagne.Montagne;
import mchorse.montagne.network.client.ClientHandlerToggleBuildingMode;
import mchorse.montagne.network.common.PacketToggleBuildingMode;
import mchorse.montagne.network.server.ServerHandlerToggleBuildingMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Network dispatcher
 *
 * @author Ernio (Ernest Sadowski)
 */
public class Dispatcher
{
    private static final SimpleNetworkWrapper DISPATCHER = NetworkRegistry.INSTANCE.newSimpleChannel(Montagne.MODID);
    private static byte PACKET_ID;

    public static SimpleNetworkWrapper get()
    {
        return DISPATCHER;
    }

    public static void updateTrackers(Entity entity, IMessage message)
    {
        EntityTracker et = ((WorldServer) entity.worldObj).getEntityTracker();

        for (EntityPlayer player : et.getTrackingPlayers(entity))
        {
            DISPATCHER.sendTo(message, (EntityPlayerMP) player);
        }
    }

    public static void sendTo(IMessage message, EntityPlayerMP player)
    {
        DISPATCHER.sendTo(message, player);
    }

    public static void sendToServer(IMessage message)
    {
        DISPATCHER.sendToServer(message);
    }

    /**
     * Register all the networking messages and message handlers
     */
    public static void register()
    {
        register(PacketToggleBuildingMode.class, ClientHandlerToggleBuildingMode.class, Side.CLIENT);
        register(PacketToggleBuildingMode.class, ServerHandlerToggleBuildingMode.class, Side.SERVER);
    }

    private static <REQ extends IMessage, REPLY extends IMessage> void register(Class<REQ> message, Class<? extends IMessageHandler<REQ, REPLY>> handler, Side side)
    {
        DISPATCHER.registerMessage(handler, message, PACKET_ID++, side);
    }
}