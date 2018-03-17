package mchorse.montagne.capabilities.building;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Default implementation of {@link IBuilding} interface.
 *
 * This class is responsible for storing current morphing, setting and retrieval
 * of current morphing.
 */
public class Building implements IBuilding
{
    private boolean mode;

    public static IBuilding get(EntityPlayer player)
    {
        return player.getCapability(BuildingProvider.BUILDING_CAP, null);
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
}