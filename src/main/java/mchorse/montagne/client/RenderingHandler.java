package mchorse.montagne.client;

import mchorse.montagne.capabilities.building.Building;
import mchorse.montagne.capabilities.building.IBuilding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderingHandler
{
    public static ResourceLocation ICONS = new ResourceLocation("montagne:textures/gui/icons.png");

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
    {}
}