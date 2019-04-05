package liznet.bopadditions.renderers;

import java.util.List;

import liznet.bopadditions.proxy.ClientProxy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class ModRenderItem extends RenderItem
{
    private static ResourceLocation RES_ITEM_GLINT = new ResourceLocation("minecraft", "textures/misc/enchanted_item_glint.png");
    private final TextureManager textureManager;
    private final ItemColors itemColors;
	private final ItemModelMesher itemModelMesher;

    public ModRenderItem(TextureManager parTextureManager, ModelManager parModelManager, ItemColors parItemColors, ItemModelMesher parItemModelMesher)
    {
        super(parTextureManager, parModelManager, parItemColors);
        this.textureManager = parTextureManager;
        this.itemModelMesher = parItemModelMesher;
        this.itemColors = parItemColors;
    }
    
    @Override
    public ItemModelMesher getItemModelMesher()
    {
        return this.itemModelMesher;
    }
    
    @Override
    public void renderItem(ItemStack stack, IBakedModel model)
    {
        if (!stack.isEmpty())
        {
            GlStateManager.pushMatrix();
            GlStateManager.translate(-0.5F, -0.5F, -0.5F);

            if (model.isBuiltInRenderer())
            {
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.enableRescaleNormal();
                stack.getItem().getTileEntityItemStackRenderer().renderByItem(stack);
            }
            else
            {
                this.renderModel(model, stack);

                if (stack.hasEffect())
                {
                    this.renderEffect(model, ClientProxy.getEffectColor(stack));
                }
            }

            GlStateManager.popMatrix();
        }
    }
    
    @Override
    public void renderQuads(BufferBuilder renderer, List<BakedQuad> quads, int color, ItemStack stack)
    {
        boolean flag = color == -1 && !stack.isEmpty();
        int i = 0;

        for (int j = quads.size(); i < j; ++i)
        {
            BakedQuad bakedquad = quads.get(i);
            int k = color;

            if (flag && bakedquad.hasTintIndex())
            {
                k = this.itemColors.colorMultiplier(stack, bakedquad.getTintIndex());

                if (EntityRenderer.anaglyphEnable)
                {
                    k = TextureUtil.anaglyphColor(k);
                }

                k = k | -16777216;
            }

            net.minecraftforge.client.model.pipeline.LightUtil.renderQuadColor(renderer, bakedquad, k);
        }
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager)
    {
        this.itemModelMesher.rebuildCache();
    }
    
    private void renderEffect(IBakedModel model, int color)
    {
        GlStateManager.depthMask(false);
        GlStateManager.depthFunc(514);
        GlStateManager.disableLighting();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
        this.textureManager.bindTexture(RES_ITEM_GLINT);
        GlStateManager.matrixMode(5890);
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f = (float)(Minecraft.getSystemTime() % 3000L) / 3000.0F / 8.0F;
        GlStateManager.translate(f, 0.0F, 0.0F);
        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
        this.renderModel(model, color); // original color: -8372020
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scale(8.0F, 8.0F, 8.0F);
        float f1 = (float)(Minecraft.getSystemTime() % 4873L) / 4873.0F / 8.0F;
        GlStateManager.translate(-f1, 0.0F, 0.0F);
        GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
        this.renderModel(model, color); // original color: -8372020
        GlStateManager.popMatrix();
        GlStateManager.matrixMode(5888);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableLighting();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        this.textureManager.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
    }

    private void renderModel(IBakedModel model, ItemStack stack)
    {
        this.renderModel(model, -1, stack);
    }

    private void renderModel(IBakedModel model, int color)
    {
        this.renderModel(model, color, ItemStack.EMPTY);
    }

    private void renderModel(IBakedModel model, int color, ItemStack stack)
    {
        if (net.minecraftforge.common.ForgeModContainer.allowEmissiveItems)
        {
            net.minecraftforge.client.ForgeHooksClient.renderLitItem(this, model, color, stack);
            return;
        }
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.ITEM);

        for (EnumFacing enumfacing : EnumFacing.values())
        {
            this.renderQuads(bufferbuilder, model.getQuads((IBlockState)null, enumfacing, 0L), color, stack);
        }

        this.renderQuads(bufferbuilder, model.getQuads((IBlockState)null, (EnumFacing)null, 0L), color, stack);
        tessellator.draw();
    }

}
