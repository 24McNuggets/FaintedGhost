package me.fainted.module.movement;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class Sprint extends Module {

	public Sprint() {
		super("Sprint", "Always holds down the sprint key", Category.MOVEMENT);
	}
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent e) {
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), true);
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), false);
	}
}
