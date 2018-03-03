package mchorse.montagne.capabilities.building;

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
}