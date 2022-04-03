package me.fainted.module;

import java.util.ArrayList;

import me.fainted.module.combat.AntiBot;
import me.fainted.module.combat.AutoClicker;
import me.fainted.module.combat.Velocity;
import me.fainted.module.movement.Sprint;
import me.fainted.module.render.ClickGUI;
import me.fainted.module.render.HUD;

public class ModuleManager {

	public ArrayList<Module> modules;
	
	public ModuleManager() {
		(modules = new ArrayList<Module>()).clear();
		this.modules.add(new ClickGUI());
		this.modules.add(new HUD());
		this.modules.add(new Sprint());
		this.modules.add(new AutoClicker());
		this.modules.add(new Velocity());
		this.modules.add(new AntiBot());
	}
	
	public Module getModule(String name) {
		for (Module m : this.modules) {
			if (m.getName().equalsIgnoreCase(name)) {
				return m;
			}
		}
		return null;
	}
	
	public ArrayList<Module> getModuleList() {
		return this.modules;
	}
	
	public ArrayList<Module> getModulesInCategory(Category c) {
		ArrayList<Module> mods = new ArrayList<Module>();
		for (Module m : this.modules) {
			if (m.getCategory() == c) {
				mods.add(m);
			}
		}
		return mods;
	}
}
