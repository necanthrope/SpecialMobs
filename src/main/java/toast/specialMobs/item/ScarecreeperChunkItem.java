package toast.specialMobs.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import toast.specialMobs._SpecialMobs;
import toast.specialMobs.block.ScarecreeperTileEntity;

/**
 * Created by jtidwell on 3/27/2016.
 */
@SideOnly(Side.CLIENT)
public class ScarecreeperChunkItem extends Item {

    public ScarecreeperChunkItem() {

        this.setCreativeTab(CreativeTabs.tabDecorations);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setTextureName(_SpecialMobs.MODID + ":" + "scarecreeper_icon");
        this.setUnlocalizedName(_SpecialMobs.MODID + "_scarecreeperItem");

    }

    @Override
    public IIcon getIcon(ItemStack stack, int pass) {
        return itemIcon;
    }

    @Override
    public IIcon getIconIndex(ItemStack stack) {
        return this.getIcon(stack,0);
    }

    /*
    public boolean onItemUse(ItemStack pItemStack, EntityPlayer pEntityPlayer, World pWorld,
                             int pWorldX, int pWorldY, int pWorldZ, int p_77648_7_,
                             float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {

        if(!pWorld.isRemote) {
            pWorld.setBlock(pWorldX, pWorldY, pWorldZ, _SpecialMobs.scarecreeperBlock, p_77648_7_, 2);
            int rotation = 0;

            if (p_77648_7_ == 1) {
                rotation = MathHelper.floor_double((double) (pEntityPlayer.rotationYaw * 16.0F / 360.0F) + 0.5D) & 15;
            }

            TileEntity tileentity = pWorld.getTileEntity(pWorldX, pWorldY, pWorldZ);

            ((ScarecreeperTileEntity) tileentity).setRotation(rotation);
        }

        return true;
    }
    */

    @Override
    public void registerIcons(IIconRegister iIconRegister) {
        itemIcon = iIconRegister.registerIcon(_SpecialMobs.MODID + ":" + "scarecreeper_icon");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int p_77647_1_)
    {
        return p_77647_1_;
    }
}
