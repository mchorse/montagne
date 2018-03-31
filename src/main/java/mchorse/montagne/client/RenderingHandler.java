package mchorse.montagne.client;

import org.lwjgl.opengl.GL11;

import mchorse.montagne.api.Region;
import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.IBuilding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderingHandler
{
    public static ResourceLocation ICONS = new ResourceLocation("montagne:textures/gui/icons.png");
    public static RayTraceResult result;

    private Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public void onHudRender(RenderGameOverlayEvent.Post event)
    {
        ScaledResolution resolution = event.getResolution();

        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL)
        {
            IBuilding building = Building.get(this.mc.thePlayer);

            if (building.isBuildingMode())
            {
                this.mc.renderEngine.bindTexture(ICONS);
                Gui.drawModalRectWithCustomSizedTexture(resolution.getScaledWidth() - 18, 2, 0, 0, 16, 16, 256, 256);
            }
        }
    }

    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent event)
    {
        IBuilding cap = Building.get(this.mc.thePlayer);

        if (cap != null && cap.isBuildingMode())
        {
            this.renderBuildingMode(cap, event.getPartialTicks());
            this.renderMirrorAxes(cap, event.getPartialTicks());
        }
    }

    private void renderBuildingMode(IBuilding cap, float partialTicks)
    {
        RayTraceResult over = this.mc.objectMouseOver;

        if (over != null && over.typeOfHit == Type.MISS)
        {
            result = this.mc.thePlayer.rayTrace(4, partialTicks);

            if (result != null)
            {
                BlockPos pos = result.getBlockPos();
                Entity render = this.mc.getRenderViewEntity();

                float renderX = (float) (render.prevPosX + (render.posX - render.prevPosX) * partialTicks);
                float renderY = (float) (render.prevPosY + (render.posY - render.prevPosY) * partialTicks);
                float renderZ = (float) (render.prevPosZ + (render.posZ - render.prevPosZ) * partialTicks);

                GlStateManager.enableBlend();
                GlStateManager.color(0, 0, 0, 0.4F);
                GlStateManager.disableTexture2D();

                float x = pos.getX() - renderX;
                float y = pos.getY() - renderY;
                float z = pos.getZ() - renderZ;

                GL11.glBegin(GL11.GL_LINES);

                /* Pillars */
                GL11.glVertex3f(x, y, z);
                GL11.glVertex3f(x, y + 1, z);

                GL11.glVertex3f(x + 1, y, z);
                GL11.glVertex3f(x + 1, y + 1, z);

                GL11.glVertex3f(x + 1, y, z + 1);
                GL11.glVertex3f(x + 1, y + 1, z + 1);

                GL11.glVertex3f(x, y, z + 1);
                GL11.glVertex3f(x, y + 1, z + 1);

                /* Bottom */
                GL11.glVertex3f(x, y, z);
                GL11.glVertex3f(x + 1, y, z);

                GL11.glVertex3f(x, y, z);
                GL11.glVertex3f(x, y, z + 1);

                GL11.glVertex3f(x + 1, y, z + 1);
                GL11.glVertex3f(x + 1, y, z);

                GL11.glVertex3f(x + 1, y, z + 1);
                GL11.glVertex3f(x, y, z + 1);

                /* Top */
                GL11.glVertex3f(x, y + 1, z);
                GL11.glVertex3f(x + 1, y + 1, z);

                GL11.glVertex3f(x, y + 1, z);
                GL11.glVertex3f(x, y + 1, z + 1);

                GL11.glVertex3f(x + 1, y + 1, z + 1);
                GL11.glVertex3f(x + 1, y + 1, z);

                GL11.glVertex3f(x + 1, y + 1, z + 1);
                GL11.glVertex3f(x, y + 1, z + 1);

                GL11.glEnd();

                if (this.mc.gameSettings.showDebugInfo && !this.mc.gameSettings.hideGUI)
                {
                    float hx = (float) (result.hitVec.xCoord - renderX);
                    float hy = (float) (result.hitVec.yCoord - renderY);
                    float hz = (float) (result.hitVec.zCoord - renderZ);

                    /* Draw hit vector */
                    GlStateManager.color(1, 0.7F, 0);
                    GL11.glPointSize(6);
                    GL11.glBegin(GL11.GL_POINTS);
                    GL11.glVertex3f(hx, hy, hz);
                    GL11.glEnd();

                    GlStateManager.color(0.9F, 0.6F, 0);
                    GL11.glBegin(GL11.GL_LINES);
                    GL11.glVertex3f(x, hy, hz);
                    GL11.glVertex3f(x + 1, hy, hz);

                    GL11.glVertex3f(hx, y, hz);
                    GL11.glVertex3f(hx, y + 1, hz);

                    GL11.glVertex3f(hx, hy, z);
                    GL11.glVertex3f(hx, hy, z + 1);
                    GL11.glEnd();

                    GlStateManager.disableBlend();
                    GlStateManager.enableTexture2D();
                    GlStateManager.color(1, 1, 1);
                }
            }
        }
    }

    private void renderMirrorAxes(IBuilding cap, float partialTicks)
    {
        Entity render = this.mc.getRenderViewEntity();

        float renderX = (float) (render.prevPosX + (render.posX - render.prevPosX) * partialTicks);
        float renderY = (float) (render.prevPosY + (render.posY - render.prevPosY) * partialTicks);
        float renderZ = (float) (render.prevPosZ + (render.posZ - render.prevPosZ) * partialTicks);

        GlStateManager.enableBlend();
        GlStateManager.glLineWidth(3);
        GlStateManager.color(1, 0.85F, 0, 0.75F);
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();

        for (Region region : cap.getRegions())
        {
            float org_x = region.origin.getX() - renderX;
            float org_y = region.origin.getY() - renderY;
            float org_z = region.origin.getZ() - renderZ;

            float max_x = org_x + region.dimension.getX();
            float max_y = org_y + region.dimension.getY();
            float max_z = org_z + region.dimension.getZ();

            GL11.glBegin(GL11.GL_LINES);

            /* Pillars */
            GL11.glVertex3f(org_x, org_y, org_z);
            GL11.glVertex3f(org_x, max_y, org_z);

            GL11.glVertex3f(max_x, org_y, org_z);
            GL11.glVertex3f(max_x, max_y, org_z);

            GL11.glVertex3f(org_x, org_y, max_z);
            GL11.glVertex3f(org_x, max_y, max_z);

            GL11.glVertex3f(max_x, org_y, max_z);
            GL11.glVertex3f(max_x, max_y, max_z);

            /* Top */
            GL11.glVertex3f(org_x, max_y, org_z);
            GL11.glVertex3f(max_x, max_y, org_z);

            GL11.glVertex3f(org_x, max_y, org_z);
            GL11.glVertex3f(org_x, max_y, max_z);

            GL11.glVertex3f(max_x, max_y, max_z);
            GL11.glVertex3f(max_x, max_y, org_z);

            GL11.glVertex3f(max_x, max_y, max_z);
            GL11.glVertex3f(org_x, max_y, max_z);

            /* Bottom */
            GL11.glVertex3f(org_x, org_y, org_z);
            GL11.glVertex3f(max_x, org_y, org_z);

            GL11.glVertex3f(org_x, org_y, org_z);
            GL11.glVertex3f(org_x, org_y, max_z);

            GL11.glVertex3f(max_x, org_y, max_z);
            GL11.glVertex3f(max_x, org_y, org_z);

            GL11.glVertex3f(max_x, org_y, max_z);
            GL11.glVertex3f(org_x, org_y, max_z);

            GL11.glEnd();
        }

        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.glLineWidth(1);
        GlStateManager.color(1, 1, 1);
        GlStateManager.enableTexture2D();
    }
}