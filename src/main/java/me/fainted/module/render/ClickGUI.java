package me.fainted.module.render;

import org.lwjgl.input.Keyboard;

import me.fainted.Tutorial;
import me.fainted.module.Category;
import me.fainted.module.Module;

public class ClickGUI extends Module {

	public ClickGUI() {
		super("ClickGUI", "Allows you to enable and disable modules", Category.RENDER);
		this.setKey(Keyboard.KEY_RSHIFT);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		mc.displayGuiScreen(Tutorial.instance.clickGui);
		this.setToggled(false);
	}
}
