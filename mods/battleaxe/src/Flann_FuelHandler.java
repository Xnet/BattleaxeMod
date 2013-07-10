// Coded by Flann

package mods.battleaxe.src;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class Flann_FuelHandler implements IFuelHandler{
	
	@Override
	public int getBurnTime(ItemStack fuel) { //This method sets burn time
		if(fuel.itemID == CoreBattleaxe.battleaxeW.itemID){
			return 300;
		}
		return 0;
	}
}