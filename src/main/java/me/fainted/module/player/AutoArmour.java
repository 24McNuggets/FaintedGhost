package me.fainted.module.player;

import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.utils.listeners.UpdateListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AutoArmour extends Module implements UpdateListener
{
	public AutoArmour() {
		super("AutoArmour", "Automatically equips armour!", Category.PLAYER);
		// TODO Auto-generated constructor stub
	}

	private int[] bestArmor;
	
	@SubscribeEvent
	public void onWorldLeave(PlayerLoggedOutEvent event) {
		onDisable();
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.RenderTickEvent e)
	{
		try {
		if(mc.currentScreen instanceof GuiContainer && !(mc.currentScreen instanceof GuiInventory)) {
			return;
		} } catch (NullPointerException q) {
			return;
		}
		
		
			bestArmor = new int[4];
			for(int i = 0; i < bestArmor.length; i++) {
				bestArmor[i] = -1;
			}
			
			for(int i = 0; i < 36; i++)
			{
				try {
				ItemStack itemstack = mc.thePlayer.inventory.getStackInSlot(i);
				if(itemstack != null
					&& itemstack.getItem() instanceof ItemArmor)
				{
					ItemArmor armor = (ItemArmor)itemstack.getItem();
					if(armor.damageReduceAmount > bestArmor[3
						- armor.armorType])
						bestArmor[3 - armor.armorType] = i;
				} } catch (Exception qq) {
					return;
				}
			}
			for(int i = 0; i < 4; i++)
			{
				ItemStack itemstack = mc.thePlayer.inventory.armorItemInSlot(i);
				ItemArmor currentArmor;
				if(itemstack != null
					&& itemstack.getItem() instanceof ItemArmor)
					currentArmor = (ItemArmor)itemstack.getItem();
				else
					currentArmor = null;
				ItemArmor bestArmor;
				try
				{
					bestArmor = (ItemArmor)mc.thePlayer.inventory
						.getStackInSlot(this.bestArmor[i]).getItem();
				}catch(Exception q)
				{
					bestArmor = null;
				}
				if(bestArmor != null && (currentArmor == null
					|| bestArmor.damageReduceAmount > currentArmor.damageReduceAmount))
					if(mc.thePlayer.inventory.getFirstEmptyStack() != -1
						|| currentArmor == null)
					{
						mc.playerController.windowClick(0, 8 - i, 0, 1,
							mc.thePlayer);
						mc.playerController.windowClick(0,
							this.bestArmor[i] < 9 ? 36 + this.bestArmor[i]
								: this.bestArmor[i],
							0, 1, Minecraft.getMinecraft().thePlayer);
					}
			}
		}
	
	
	@Override
	public void onDisable() {
		super.onDisable();
	}

	@Override
	public void onEnable() {
		super.onEnable();
		this.updateVals();
	}
	
	public void updateVals() {
		
	}
	
	@Override
	public void onUpdate() {
		
	}
}