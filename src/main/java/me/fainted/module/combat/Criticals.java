package me.fainted.module.combat;

import org.lwjgl.input.Mouse;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;


public class Criticals extends Module
{
	public Criticals()
	{
		super(
			"Criticals",
			"Changes all your hits to critical hits.",
			Category.COMBAT);
	}

	@SubscribeEvent
	public void onTick(TickEvent.RenderTickEvent e) {
		if (Mouse.isButtonDown(0)) {
			onLeftClick();
		}
	}
	
	public void onLeftClick()
	{
		
		doCritical();
	}

	public static void doCritical()
	{
		if(!Minecraft.getMinecraft().thePlayer.isInWater() && !Minecraft.getMinecraft().thePlayer.isInsideOfMaterial(Material.lava) && Minecraft.getMinecraft().thePlayer.onGround)
		{
			Minecraft.getMinecraft().thePlayer.motionY = 0.1F;
			Minecraft.getMinecraft().thePlayer.fallDistance = 0.1F;
			Minecraft.getMinecraft().thePlayer.onGround = false;
		}
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
}