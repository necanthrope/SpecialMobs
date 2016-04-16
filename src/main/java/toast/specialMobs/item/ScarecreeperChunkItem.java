package toast.specialMobs.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import toast.specialMobs._SpecialMobs;

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

    @Override
    public void registerIcons(IIconRegister iIconRegister) {
        itemIcon = iIconRegister.registerIcon(_SpecialMobs.MODID + ":" + "scarecreeper_chunk_icon");
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int p_77647_1_)
    {
        return p_77647_1_;
    }
}
