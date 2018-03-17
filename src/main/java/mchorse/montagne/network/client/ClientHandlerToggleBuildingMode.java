package mchorse.montagne.network.client;

import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.IBuilding;
import mchorse.montagne.network.common.PacketToggleBuildingMode;
import net.minecraft.client.entity.EntityPlayerSP;

public class ClientHandlerToggleBuildingMode extends ClientMessageHandler<PacketToggleBuildingMode>
{
    @Override
    public void run(EntityPlayerSP player, PacketToggleBuildingMode message)
    {
        IBuilding building = Building.get(player);

        if (building != null)
        {
            building.setBuildingMode(message.buildingMode);
        }
    }
}