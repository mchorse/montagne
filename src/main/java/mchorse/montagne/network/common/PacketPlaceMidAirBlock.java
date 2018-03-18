package mchorse.montagne.network.common;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketPlaceMidAirBlock implements IMessage
{
    public BlockPos pos;
    public EnumHand hand;
    public EnumFacing facing;

    public float hitX;
    public float hitY;
    public float hitZ;

    public PacketPlaceMidAirBlock()
    {}

    public PacketPlaceMidAirBlock(BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        this.pos = pos;
        this.hand = hand;
        this.facing = facing;
        this.hitX = hitX;
        this.hitY = hitY;
        this.hitZ = hitZ;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
        this.hand = buf.readBoolean() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
        this.facing = EnumFacing.values()[buf.readByte()];
        this.hitX = buf.readFloat();
        this.hitY = buf.readFloat();
        this.hitZ = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(this.pos.getX());
        buf.writeInt(this.pos.getY());
        buf.writeInt(this.pos.getZ());
        buf.writeBoolean(this.hand == EnumHand.MAIN_HAND);
        buf.writeByte(this.facing.ordinal());
        buf.writeFloat(this.hitX);
        buf.writeFloat(this.hitY);
        buf.writeFloat(this.hitZ);
    }
}