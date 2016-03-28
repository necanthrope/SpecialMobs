package toast.specialMobs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import toast.specialMobs._SpecialMobs;

/**
 * Created by jtidwell on 2/7/2016.
 */
@SideOnly(Side.CLIENT)
public class ScarecreeperTileEntitySpecialRenderer extends TileEntitySpecialRenderer {

    private static final ResourceLocation creeperTexture = new ResourceLocation("textures/entity/creeper/creeper.png");
    private static final ResourceLocation customTexture = new ResourceLocation(_SpecialMobs.BLOCK_TEXTURE_PATH.concat("scarecreeper_block.png"));
    public static ScarecreeperTileEntitySpecialRenderer field_147536_b;
    private final ModelSkeletonHead model = new ModelSkeletonHead(0, 0, 64, 32);

    public void renderTileEntityAt(TileEntity pTileEntity, double pX, double pY, double pZ, float pScale) {
        renderTileEntityAt((ScarecreeperTileEntity)pTileEntity, pX, pY, pZ, pScale);
    }

    public void func_147497_a(TileEntityRendererDispatcher p_147497_1_)
    {
        super.func_147497_a(p_147497_1_);
        field_147536_b = this;
    }

    public void renderTileEntityAt(ScarecreeperTileEntity pTileEntity, double pX, double pY, double pZ, float pScale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);

        int meta = pTileEntity.getWorldObj().getBlockMetadata(pTileEntity.xCoord, pTileEntity.yCoord, pTileEntity.zCoord);

        this.bindTexture(customTexture);

        float rotation = ((Integer) pTileEntity.getRotation()).floatValue();

        GL11.glTranslatef((float) pX + 0.5F, (float) pY, (float) pZ + 0.5F);

        float f4 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glRotatef(meta, 0F, 1F, 0F);
        model.render((Entity) null, 0.0F, 0.0F, 0.0F, rotation, 0.0F, f4);
        GL11.glPopMatrix();

    }

}
