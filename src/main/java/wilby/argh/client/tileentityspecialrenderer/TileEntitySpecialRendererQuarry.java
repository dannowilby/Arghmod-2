package wilby.argh.client.tileentityspecialrenderer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import wilby.argh.common.tileentity.TileEntityQuarry;

public class TileEntitySpecialRendererQuarry extends TileEntitySpecialRenderer<TileEntityQuarry>
{
	public static final ResourceLocation TEXTURE_BEACON_BEAM = new ResourceLocation("textures/entity/beacon_beam.png");
	
    public void renderTileEntityAt(TileEntityQuarry te, double x, double y, double z, float partialTicks, int destroyStage)
    {   
    	if(te.isMining())
    		this.renderLaser(x, y, z, (double)partialTicks, te.getNextBlock());
    }

	private void renderLaser(double x, double y, double z, double partialTicks, int[] nextPos) 
	{
		GlStateManager.alphaFunc(516, 0.1F);
        
        GlStateManager.disableFog();
        
        GlStateManager.glTexParameteri(3553, 10242, 10497);
        GlStateManager.glTexParameteri(3553, 10243, 10497);
        GlStateManager.disableTexture2D();
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        double x1 = x;
        double x2 = nextPos[0];
        double y1 = y;
        double y2 = nextPos[1];
        double z1 = z;
        double z2 = nextPos[2];
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos(x1+.5, y1, z1+.5).color(0, 0, 0, 255).endVertex();
        vertexbuffer.pos((x1-x2) + .5, (y1-y2)+1, (z1-z2) + .5).color(0, 0, 0, 255).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        
        GlStateManager.enableFog();
	}
	
	public boolean isGlobalRenderer(TileEntityQuarry te)
    {
        return true;
    }
}
