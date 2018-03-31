package mchorse.montagne.capabilities.building;

import java.util.ArrayList;
import java.util.List;

import mchorse.montagne.api.MirrorAxis;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

/**
 * Default implementation of {@link IBuilding} interface.
 *
 * This class is responsible for storing current morphing, setting and retrieval
 * of current morphing.
 */
public class Building implements IBuilding
{
    /**
     * Building mode 
     */
    private boolean mode;

    /**
     * List of mirror axes 
     */
    private List<MirrorAxis> mirrors = new ArrayList<MirrorAxis>();

    public static IBuilding get(EntityPlayer player)
    {
        return player.getCapability(BuildingProvider.BUILDING_CAP, null);
    }

    public Building()
    {
        this.mirrors.add(new MirrorAxis(905, 10, 1277, Axis.Y));
    }

    @Override
    public boolean isBuildingMode()
    {
        return this.mode;
    }

    @Override
    public void setBuildingMode(boolean mode)
    {
        this.mode = mode;
    }

    @Override
    public List<MirrorAxis> getMirrorAxes()
    {
        return this.mirrors;
    }

    @Override
    public void placeBlock(RightClickBlock event)
    {
        BlockPos pos = event.getPos().offset(event.getFace());
        BlockPos newPos;

        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = event.getItemStack();
        Vec3d hit = event.getHitVec();

        if (stack == null)
        {
            return;
        }

        for (MirrorAxis axis : this.mirrors)
        {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            x -= axis.origin.getX();
            y -= axis.origin.getY();
            z -= axis.origin.getZ();

            System.out.println("Diff: " + x + ", " + y + ", " + z);

            x = axis.origin.getX() + (axis.axis == Axis.X ? -x : x);
            y = axis.origin.getY() + (axis.axis == Axis.Y ? -(y + 1) : y);
            z = axis.origin.getZ() + (axis.axis == Axis.Z ? -z : z);

            System.out.println("Placing: " + x + ", " + y + ", " + z + " from " + pos);

            newPos = new BlockPos(x, y, z);

            int j = stack.getMetadata();
            int i = stack.stackSize;
            stack.onItemUse(player, player.worldObj, newPos, event.getHand(), event.getFace(), (float) hit.xCoord, (float) hit.yCoord, (float) hit.zCoord);
            stack.setItemDamage(j);
            stack.stackSize = i;
        }
    }

    @Override
    public void breakBlock(BreakEvent event)
    {
        BlockPos pos = event.getPos();
        BlockPos newPos;

        for (MirrorAxis axis : this.mirrors)
        {
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();

            x -= axis.origin.getX();
            y -= axis.origin.getY();
            z -= axis.origin.getZ();

            x = axis.origin.getX() + (axis.axis == Axis.X ? -x : x);
            y = axis.origin.getY() + (axis.axis == Axis.Y ? -(y + 1) : y);
            z = axis.origin.getZ() + (axis.axis == Axis.Z ? -z : z);

            newPos = new BlockPos(x, y, z);
            event.getWorld().destroyBlock(newPos, false);
        }
    }

    @Override
    public void toNBT(NBTTagCompound tag)
    {
        tag.setBoolean("BuildingMode", this.mode);
    }

    @Override
    public void fromNBT(NBTTagCompound tag)
    {
        this.mode = tag.getBoolean("BuildingMode");
    }
}