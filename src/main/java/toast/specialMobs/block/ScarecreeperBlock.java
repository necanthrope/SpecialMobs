package toast.specialMobs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import toast.specialMobs._SpecialMobs;
import toast.specialMobs.client.ClientProxy;

import java.util.Random;

/**
 * Created by jtidwell on 2/2/2016.
 */
public class ScarecreeperBlock extends BlockContainer {

    public IIcon[] icons = new IIcon[6];

    public ScarecreeperBlock(String unlocalizedName, Material material) {
        super(material);
        this.setBlockName(unlocalizedName);
        //this.setBlockTextureName(_SpecialMobs.MODID + ":" + unlocalizedName);
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
    public void registerBlockIcons(IIconRegister reg) {



    }

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
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

        ScarecreeperTileEntity te = (ScarecreeperTileEntity) par1World.getTileEntity(par2, par3, par4);
        te.setRotation(MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw)));

    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World pWorld, int pWorldX, int pWorldY, int pWorldZ) {
        return ClientProxy.scarecreeperItem;
    }

    @SideOnly(Side.CLIENT)
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return ClientProxy.scarecreeperItem;
    }

}
