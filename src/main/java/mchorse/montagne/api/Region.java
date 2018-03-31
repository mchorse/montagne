package mchorse.montagne.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.math.BlockPos.MutableBlockPos;

public class Region
{
    /**
     * The minimum point of the region 
     */
    public MutableBlockPos origin = new MutableBlockPos();

    /**
     * The dimension of the region (maximum is formed by adding origin 
     * to dimension) 
     */
    public MutableBlockPos dimension = new MutableBlockPos();

    /**
     * List of mirror axes 
     */
    public List<MirrorAxis> axes = new ArrayList<MirrorAxis>();

    /**
     * Does this region builds
     */
    public boolean isMirroring;
}