package me.fainted.module.movement;

import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.utils.listeners.UpdateListener;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Speed extends Module implements UpdateListener
{
	public Speed() {
		super("Speed", "Increases player speed 'legit'", Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEnable()
	{
		super.onEnable();
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.RenderTickEvent e)
	{
		// return if sneaking or not walking
		if(mc.thePlayer.isSneaking()
			|| mc.thePlayer.moveForward == 0 && mc.thePlayer.moveStrafing == 0)
			return;
		
		// activate sprint if walking forward
		if(mc.thePlayer.moveForward > 0 && !mc.thePlayer.isCollidedHorizontally)
			mc.thePlayer.setSprinting(true);
		
		// activate mini jump if on ground
		if(mc.thePlayer.onGround)
		{
			mc.thePlayer.motionY += 0.1;
			mc.thePlayer.motionX *= 1.8;
			mc.thePlayer.motionZ *= 1.8;
			double currentSpeed = Math.sqrt(Math.pow(mc.thePlayer.motionX, 2)
				+ Math.pow(mc.thePlayer.motionZ, 2));
			
			// limit speed to highest value that works on NoCheat+ version
			// 3.13.0-BETA-sMD5NET-b878
			// UPDATE: Patched in NoCheat+ version 3.13.2-SNAPSHOT-sMD5NET-b888
			double maxSpeed = 0.66F;
			if(currentSpeed > maxSpeed)
			{
				mc.thePlayer.motionX = mc.thePlayer.motionX / currentSpeed * maxSpeed;
				mc.thePlayer.motionZ = mc.thePlayer.motionZ / currentSpeed * maxSpeed;
			}
		}
	}
	
	@Override
	public void onDisable()
	{
		super.onDisable();
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}
}