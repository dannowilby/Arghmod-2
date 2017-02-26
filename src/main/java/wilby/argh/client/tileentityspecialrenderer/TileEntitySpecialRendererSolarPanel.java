package wilby.argh.client.tileentityspecialrenderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import wilby.argh.common.tileentity.TileEntityQuarry;
import wilby.argh.common.tileentity.TileEntitySolarPanel;

public class TileEntitySpecialRendererSolarPanel extends TileEntitySpecialRenderer<TileEntitySolarPanel>
{
	
	public static final ResourceLocation TEXTURE_BEACON_BEAM = new ResourceLocation("textures/entity/beacon_beam.png");
	
	public void renderTileEntityAt(TileEntitySolarPanel te, double x, double y, double z, float partialTicks, int destroyStage)
    {   
    	this.render(x, y, z, (double)partialTicks, te.getSunAngle());
    }
	
	private void render(double x, double y, double z, double partialTicks, double sunAngle) 
	{
		GlStateManager.alphaFunc(516, 0.1F);
        this.bindTexture(TEXTURE_BEACON_BEAM);
        GlStateManager.disableFog();
        GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        vertexbuffer.pos(x+.75,y+1,z+.75).tex(1d, 0d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+2,z+.25).tex(0d, 1d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+2,z+.25).tex(0d, 1d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+1,z+.75).tex(1d, 0d).color(255, 0, 0, 255).endVertex();
        
        vertexbuffer.pos(x+.75,y+1,z+.75).tex(1d, 0d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+2,z+.25).tex(0d, 1d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+2,z+.25).tex(0d, 1d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+1,z+.75).tex(1d, 0d).color(255, 0, 0, 255).endVertex();

        vertexbuffer.pos(x+.75,y+1,z+.75).tex(1d, 0d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+2,z+.25).tex(0d, 1d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+2,z+.25).tex(0d, 1d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+1,z+.75).tex(1d, 0d).color(255, 0, 0, 255).endVertex();

        vertexbuffer.pos(x+.75,y+1,z+.75).tex(1d, 0d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+2,z+.25).tex(0d, 1d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+2,z+.25).tex(0d, 1d).color(255, 0, 0, 255).endVertex();
        vertexbuffer.pos(x+.75,y+1,z+.75).tex(1d, 0d).color(255, 0, 0, 255).endVertex();
        tessellator.draw();
        
        GlStateManager.enableBlend();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
        
        GlStateManager.enableFog();
	}
	
}
