package mchorse.montagne.network.common;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketToggleBuildingMode implements IMessage
{
    public boolean buildingMode;

    public PacketToggleBuildingMode()
    {}

    public PacketToggleBuildingMode(boolean buildingMode)
    {
        this.buildingMode = buildingMode;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.buildingMode = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(this.buildingMode);
    }
}