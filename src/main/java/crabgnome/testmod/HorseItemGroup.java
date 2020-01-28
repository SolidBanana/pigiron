package crabgnome.testmod;

import crabgnome.testmod.lists.ItemList;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class HorseItemGroup extends ItemGroup {
	public HorseItemGroup() {
		super("Horse");
	}
	
	@Override
	public ItemStack createIcon() {
		return new ItemStack(ItemList.horseingot);
	}
}
