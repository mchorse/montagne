package mchorse.montagne.capabilities.building;

import java.util.List;

import mchorse.montagne.api.Region;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

/**
 * Morphing interface
 *
 * This interface is responsible for morphing. See {@link Building} class for
 * default implementation.
 */
public interface IBuilding
{
    public boolean isBuildingMode();

    public void setBuildingMode(boolean mode);

    public List<Region> getRegions();

    public void placeBlock(RightClickBlock event);

    public void breakBlock(BreakEvent event);

    /**
     * Fill given NBT tag with this capability's data
     */
    public void toNBT(NBTTagCompound tag);

    /**
     * Fill this capability's fields from NBT tag 
     */
    public void fromNBT(NBTTagCompound tag);
}