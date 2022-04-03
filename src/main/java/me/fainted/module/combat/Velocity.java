package me.fainted.module.combat;

import me.fainted.Tutorial;
import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.settings.Setting;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Velocity extends Module{

	public Velocity() {
		super("Velocity", "Reduces Knockback", Category.COMBAT);
		Tutorial.instance.settingsManager.rSetting(new Setting("Horizontal", this, 100, 0, 100, true));
		Tutorial.instance.settingsManager.rSetting(new Setting("Vertical", this, 100, 0, 100, true));
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent e) {
		if (mc.thePlayer == null) {
			return;
		}
		
		float horizontal = (float) Tutorial.instance.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
		float vertical = (float) Tutorial.instance.settingsManager.getSettingByName(this, "Vertical").getValDouble();
		
		if (mc.thePlayer.hurtTime == mc.thePlayer.maxHurtTime && mc.thePlayer.maxHurtTime > 0) {
			mc.thePlayer.motionX *= (float) horizontal / 100;
			mc.thePlayer.motionY *= (float) vertical / 100;
			mc.thePlayer.motionZ *= (float) horizontal / 100;
		}
	}
}
