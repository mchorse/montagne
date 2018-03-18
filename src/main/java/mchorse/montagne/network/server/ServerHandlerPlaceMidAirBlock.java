package mchorse.montagne.network.server;

import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.IBuilding;
import mchorse.montagne.network.common.PacketPlaceMidAirBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class ServerHandlerPlaceMidAirBlock extends ServerMessageHandler<PacketPlaceMidAirBlock>
{
    @Override
    public void run(EntityPlayerMP player, PacketPlaceMidAirBlock message)
    {
        IBuilding building = Building.get(player);

        if (building != null && building.isBuildingMode())
        {
            ItemStack stack = player.getHeldItem(message.hand);

            if (stack != null)
            {
                player.interactionManager.processRightClickBlock(player, player.worldObj, stack, message.hand, message.pos, message.facing, message.hitX, message.hitY, message.hitZ);
            }
        }
    }
}