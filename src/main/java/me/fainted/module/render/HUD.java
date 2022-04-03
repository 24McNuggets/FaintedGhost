package me.fainted.module.render;

import me.fainted.Tutorial;
import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HUD extends Module {

	public HUD() {
		super("HUD", "Draws the module list on your screen", Category.RENDER);
	}
	
	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent egoe) {
		if (!egoe.type.equals(egoe.type.CROSSHAIRS)) {
			return;
		}
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int y = 2;
		for (Module mod : Tutorial.instance.moduleManager.getModuleList()) {
			if (!mod.getName().equalsIgnoreCase("HUD") && mod.isToggled() && mod.visible) {
				FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
				fr.drawString(mod.getName(), sr.getScaledWidth() - fr.getStringWidth(mod.getName()) - 1, y, 0xFFFFFF);
				y += fr.FONT_HEIGHT;
			}
		}
	}
}
