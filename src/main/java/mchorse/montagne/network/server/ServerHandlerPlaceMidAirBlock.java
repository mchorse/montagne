package mchorse.montagne.network.server;

import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.IBuilding;
import mchorse.montagne.network.common.PacketPlaceMidAirBlock;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

public class ServerHandlerPlaceMidAirBlock extends ServerMessageHandler<PacketPlaceMidAirBlock>
{
    @Override
    public void run(EntityPlayerMP player, PacketPlaceMidAirBlock message)
    {
        IBuilding cap = Building.get(player);

        if (cap.isBuildingMode())
        {
            ItemStack stack = player.getHeldItem(message.hand);

            if (stack != null)
            {
                int j = stack.getMetadata();
                int i = stack.stackSize;
                stack.onItemUse(player, player.worldObj, message.pos, message.hand, message.facing, message.hitX, message.hitY, message.hitZ);
                stack.setItemDamage(j);
                stack.stackSize = i;

                cap.placeBlock(new RightClickBlock(player, message.hand, stack, message.pos.offset(message.facing, -1), message.facing, new Vec3d(message.hitX, message.hitY, message.hitZ)));
            }
        }
    }
}