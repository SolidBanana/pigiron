package crabgnome.testmod.lists;

import crabgnome.testmod.util.Ref;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public enum ArmorTierList implements IArmorMaterial {
	HORSE("horse", 400, new int[]{8, 10, 9, 7}, 25, ItemList.horseingot, "entity.horse.ambient", 0.0f);
	
	private static final int[] MAX_DMG = new int[]{13, 15, 16, 11};
	private String name, equipSound;
	private int durability, enchantability;
	private Item repairItem;
	private int[] damageReductionAmounts;
	private float toughness;
	
	private ArmorTierList(String name, int durability, int[] damageReductionAmounts, int enchantability, Item repairItem, String equipSound, float toughness) {
		this.name = name;
		this.equipSound = equipSound;
		this.durability = durability;
		this.enchantability = enchantability;
		this.repairItem = repairItem;
		this.damageReductionAmounts = damageReductionAmounts;
		this.toughness = toughness;
	}

	@Override
	public int getDurability(EquipmentSlotType slotIn) {
		return MAX_DMG[slotIn.getIndex()] * durability;
	}

	@Override
	public int getDamageReductionAmount(EquipmentSlotType slotIn) {
		return this.damageReductionAmounts[slotIn.getIndex()];
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@Override
	public SoundEvent getSoundEvent() {
		return new SoundEvent(new ResourceLocation(equipSound));
	}

	@Override
	public Ingredient getRepairMaterial() {
		return Ingredient.fromItems(repairItem);
	}

	@Override
	public String getName() {
		return Ref.MOD_ID + ":" + name;
	}

	@Override
	public float getToughness() {
		return toughness;
	}

}
