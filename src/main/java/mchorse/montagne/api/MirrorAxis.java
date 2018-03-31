package mchorse.montagne.api;

import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;

/**
 * Reference axis class
 * 
 * This class represents a mirror axis which allows to place block on 
 * the opposite side of the axis.
 */
public class MirrorAxis
{
    /**
     * Origin point of this axis 
     */
    public BlockPos.MutableBlockPos origin = new BlockPos.MutableBlockPos();

    /**
     * Axis in which direction player will be able to place blocks 
     */
    public Axis axis = Axis.Z;

    public MirrorAxis(int x, int y, int z, Axis axis)
    {
        this.origin.setPos(x, y, z);
        this.axis = axis;
    }
}