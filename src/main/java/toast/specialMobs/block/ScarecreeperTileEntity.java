package toast.specialMobs.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by jtidwell on 2/7/2016.
 */
public class ScarecreeperTileEntity extends TileEntity {

    private int rotation;
    private static final String __OBFID = "CL_00010364";

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("Rot", this.rotation);
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        this.rotation = nbtTagCompound.getInteger("Rot");

    }

    /**
     * Stolen from TileEntitySign
     */
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 4, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound nbtTagCompound = pkt.func_148857_g();
        this.readFromNBT(pkt.func_148857_g());
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }

    public int getRotation()
    {
        return this.rotation;
    }

}
