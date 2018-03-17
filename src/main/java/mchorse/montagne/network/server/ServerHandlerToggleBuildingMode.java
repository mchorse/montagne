package mchorse.montagne.network.server;

import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.IBuilding;
import mchorse.montagne.network.Dispatcher;
import mchorse.montagne.network.common.PacketToggleBuildingMode;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerHandlerToggleBuildingMode extends ServerMessageHandler<PacketToggleBuildingMode>
{
    @Override
    public void run(EntityPlayerMP player, PacketToggleBuildingMode message)
    {
        IBuilding building = Building.get(player);

        if (building != null)
        {
            boolean old = building.isBuildingMode();

            building.setBuildingMode(message.buildingMode);

            if (old != message.buildingMode)
            {
                Dispatcher.sendTo(message, player);
            }
        }
    }
}