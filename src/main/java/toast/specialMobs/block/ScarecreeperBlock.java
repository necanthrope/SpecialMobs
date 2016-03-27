package toast.specialMobs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import toast.specialMobs._SpecialMobs;

/**
 * Created by jtidwell on 2/2/2016.
 */
public class ScarecreeperBlock extends BlockContainer {

    public IIcon[] icons = new IIcon[6];

    public ScarecreeperBlock(String unlocalizedName, Material material) {
        super(material);
        this.setBlockName(unlocalizedName);
        this.setBlockTextureName(_SpecialMobs.MODID + ":" + unlocalizedName);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.setHardness(2.0F);
        this.setResistance(6.0F);
        this.setStepSound(soundTypeGravel);
        this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);

    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        //return this.icons[side];
        if (side == 0 || side == 1)
            return this.icons[side];

        if (side == meta)
            return this.icons[2];

        return this.icons[3];
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {}
    /**
     * Gets the block's texture. Args: side, meta
     */
    /*
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return Blocks.soul_sand.getBlockTextureFromSide(p_149691_1_);
    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
        this.icons[0] = reg.registerIcon(this.textureName + "_bottom");
        this.icons[1] = reg.registerIcon(this.textureName + "_top");
        this.icons[2] = reg.registerIcon(this.textureName + "_front");
        this.icons[3] = reg.registerIcon(this.textureName + "_back");
        this.icons[4] = reg.registerIcon(this.textureName + "_right");
        this.icons[5] = reg.registerIcon(this.textureName + "_left");
    }

    */

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
    {
        return new ScarecreeperTileEntity();
    }

    //You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
        return -1;
    }

    //It's not an opaque cube, so you need this.
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    //It's not a normal block, so you need this too.
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
                                EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;



        if (l == 0)
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);

        if (l == 1)
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);

        if (l == 2)
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);

        if (l == 3)
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);

    }

}
