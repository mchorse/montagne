package mchorse.montagne.api;

import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;

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

    /**
     * How many times this axis stacks (positive value will stack the 
     * axis into positive direction, negative value will stack the axis 
     * into negative direction) 
     */
    public int stack = 0;

    /**
     * Equal distance between stacks 
     */
    public int stackStep = 1;

    /**
     * How long away from the origin or stacked end can this axis affect 
     * block placing 
     */
    public int limit = 16;

    public MirrorAxis(int x, int y, int z, Axis axis)
    {
        this.origin.setPos(x, y, z);
        this.axis = axis;
    }

    /**
     * Calculate points for the plane 
     */
    public void calculatePlane(MutableBlockPos min, MutableBlockPos max)
    {
        float mnx = this.origin.getX() - (this.axis != Axis.Z ? this.limit : 0);
        float mny = this.origin.getY() - (this.axis != Axis.Y ? this.limit : 0);
        float mnz = this.origin.getZ() - (this.axis != Axis.X ? this.limit : 0);

        float mxx = this.origin.getX() + (this.axis != Axis.Z ? this.limit : 0);
        float mxy = this.origin.getY() + (this.axis != Axis.Y ? this.limit : 0);
        float mxz = this.origin.getZ() + (this.axis != Axis.X ? this.limit : 0);

        min.setPos(mnx, mny, mnz);
        max.setPos(mxx, mxy, mxz);
    }
}