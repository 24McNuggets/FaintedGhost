package me.fainted.utils;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class Utils {

	public static final Minecraft mc = Minecraft.getMinecraft();
	
	public static boolean isPlayerInGame() {
        return mc.thePlayer != null && mc.theWorld != null;
     }
	
	public static double fovFromEntity(Entity en) {
        return ((double)(mc.thePlayer.rotationYaw - fovToEntity(en)) % 360.0D + 540.0D) % 360.0D - 180.0D;
     }
	
	
	
	public static float fovToEntity(Entity ent) {
        double x = ent.posX - mc.thePlayer.posX;
        double z = ent.posZ - mc.thePlayer.posZ;
        double yaw = Math.atan2(x, z) * 57.2957795D;
        return (float)(yaw * -1.0D);
     }
	
	public static double ranModuleVal(Double a, Double b) {
		Random ra = new Random(1);
		Double r = 0.0;
		r = ra.nextDouble();
        return a == b ? a : a + r * (b - a);
     }

   
}
