package me.fainted.autosave;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import me.fainted.Tutorial;
import me.fainted.module.Module;
import me.fainted.settings.Setting;
import net.minecraft.client.Minecraft;

public class SaveLoad {
	
	public static final Minecraft mc = Minecraft.getMinecraft();
	
	private File dir;
	private File dataFile;
	
	public SaveLoad(){
		dir = new File(mc.mcDataDir, "faintedclient");
		if (!dir.exists()) {
			dir.mkdir();
		}
		dataFile = new File(dir, "data.txt");
		if (!dataFile.exists()) {
			try {
				dataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.save();
	}
	
	public void save() {
		ArrayList<String> toSave = new ArrayList<String>();
		
		for (Module mod : Tutorial.instance.moduleManager.modules) {
			toSave.add("MOD:" + mod.getName() + ":" + mod.isToggled() + ":" + mod.getKey());
		}
		
		for (Setting set : Tutorial.instance.settingsManager.getSettings()) {
			if (set.isCheck()) {
				toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
			}
			if (set.isCombo()) {
				toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValString());
			}
			if (set.isSlider()) {
				toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
			}
		}
		
		try {
			PrintWriter pw = new PrintWriter(this.dataFile);
			for (String str : toSave) {
				pw.println(str);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.load();
	}
	
	public void load() {
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.dataFile));
			String line = reader.readLine();
			while(line != null) {
				lines.add(line);
				line = reader.readLine();
			}
			reader.close();
		}
		catch (Exception e) {e.printStackTrace();}
		
		for (String s : lines) {
			String[] args = s.split(":");
			if (s.toLowerCase().startsWith("mod:")) {
				Module m = Tutorial.instance.moduleManager.getModule(args[1]);
				if (m != null) {
					m.setToggled(Boolean.parseBoolean(args[2]));
					m.setKey(Integer.parseInt(args[3]));
				}
			} else if (s.toLowerCase().startsWith("set")) {
				Module m = Tutorial.instance.moduleManager.getModule(args[2]);
				if (m != null) {
					Setting set = Tutorial.instance.settingsManager.getSettingByName(m, args[1]);
					if (set != null) {
						if (set.isCheck()) {
							set.setValBoolean(Boolean.parseBoolean(args[3]));
						}
						if (set.isCombo()) {
							set.setValString(args[3]);
						}
						if (set.isSlider()) {
							set.setValDouble(Double.parseDouble(args[3]));
						}
					}
				}
				
			}
		}
	}
}
