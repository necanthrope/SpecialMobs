package toast.specialMobs.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
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
    private World world = Minecraft.getMinecraft().theWorld;

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

        int meta = 1; //pTileEntity.getBlockMetadata();

        this.bindTexture(creeperTexture);

        //float rotation = (float)(pTileEntity.getRotation() * 360) / 16.0F;
        float rotation = pTileEntity.getRotation();

        GL11.glTranslatef((float)pX + 0.5F, (float)pY, (float)pZ + 0.5F);

        float f4 = 0.0625F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(-1.0F, -1.0F, 1.0F);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        model.render((Entity) null, 0.0F, 0.0F, 0.0F, rotation, 12.0F, f4);
        GL11.glPopMatrix();

    }

    /*
    private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
        int meta = world.getBlockMetadata(x, y, z);
        GL11.glPushMatrix();
        GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
        GL11.glPopMatrix();
    }

    //Set the lighting stuff, so it changes it's brightness properly.
    private void adjustLightFixture(World world, int i, int j, int k, Block block) {
        Tessellator tess = Tessellator.instance;
        //float brightness = block.getBlockBrightness(world, i, j, k);
        //As of MC 1.7+ block.getBlockBrightness() has become block.getLightValue():
        float brightness = block.getLightValue(world, i, j, k);
        int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int modulousModifier = skyLight % 65536;
        int divModifier = skyLight / 65536;
        tess.setColorOpaque_F(brightness, brightness, brightness);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
    }
    */

}
