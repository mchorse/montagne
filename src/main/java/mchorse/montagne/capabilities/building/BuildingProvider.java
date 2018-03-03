package mchorse.montagne.capabilities.building;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

/**
 * Morhping capability provider
 *
 * Now that I understand capability system, it seems pretty easy to use!
 */
public class BuildingProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IBuilding.class)
    public static final Capability<IBuilding> BUILDING_CAP = null;

    private IBuilding instance = BUILDING_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == BUILDING_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == BUILDING_CAP ? BUILDING_CAP.<T>cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return BUILDING_CAP.getStorage().writeNBT(BUILDING_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        BUILDING_CAP.getStorage().readNBT(BUILDING_CAP, this.instance, null, nbt);
    }
}