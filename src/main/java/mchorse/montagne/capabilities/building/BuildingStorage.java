package mchorse.montagne.capabilities.building;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**
 * Morphing storage
 *
 * This class is responsible for saving IMorphing capability to... Hey, Houston,
 * where these data are getting saved? Basically, I don't know.
 *
 * Further research in Minecraft sources shows that capabilities are stored
 * in target's NBT (i.e. ItemStack's, TE's or Entity's NBT) in field "ForgeCaps."
 */
public class BuildingStorage implements IStorage<IBuilding>
{
    @Override
    public NBTBase writeNBT(Capability<IBuilding> capability, IBuilding instance, EnumFacing side)
    {
        NBTTagCompound tag = new NBTTagCompound();

        instance.toNBT(tag);

        return tag;
    }

    @Override
    public void readNBT(Capability<IBuilding> capability, IBuilding instance, EnumFacing side, NBTBase nbt)
    {
        if (nbt instanceof NBTTagCompound)
        {
            instance.fromNBT((NBTTagCompound) nbt);
        }
    }
}
