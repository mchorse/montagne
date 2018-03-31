package mchorse.montagne.capabilities.building;

import java.util.ArrayList;
import java.util.List;

import mchorse.montagne.api.Region;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
     * List of regions 
     */
    private List<Region> regions = new ArrayList<Region>();

    public static IBuilding get(EntityPlayer player)
    {
        return player.getCapability(BuildingProvider.BUILDING_CAP, null);
    }

    public Building()
    {
        Region region = new Region();

        region.origin.setPos(883, 4, 1263);
        region.dimension.setPos(6, 24, 6);

        this.regions.add(region);
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
    public List<Region> getRegions()
    {
        return this.regions;
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

        /* TODO: implement region block placement */
    }

    @Override
    public void breakBlock(BreakEvent event)
    {
        BlockPos pos = event.getPos();
        BlockPos newPos;

        /* TODO: implement region block breaking */
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