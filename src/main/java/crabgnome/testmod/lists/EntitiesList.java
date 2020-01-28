package crabgnome.testmod.lists;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.event.RegistryEvent;
import crabgnome.testmod.Main;
import crabgnome.testmod.Main.RegistryEvents;
import crabgnome.testmod.entities.GoldenHorseEntity;
import crabgnome.testmod.util.Ref;

public class EntitiesList {
	public static EntityType<?> GOLDEN_HORSE_ENTITY = EntityType.Builder
			.create(GoldenHorseEntity::new,  EntityClassification.CREATURE)
			.build(Ref.MOD_ID + ":golden_horse_entity")
			.setRegistryName(RegistryEvents.location("golden_horse_entity"));
	
	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				ItemList.golden_horse_egg = registerEntitySpawnEgg(GOLDEN_HORSE_ENTITY, 0xFFFFFF, 0xcc9900, "golden_horse_egg")
		);
	}
	
	public static void registerEntityWorldSpawns() {
		registerEntityWorldSpawn(GOLDEN_HORSE_ENTITY, Biomes.DARK_FOREST, Biomes.DARK_FOREST_HILLS, Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE);
	}
	
	public static void registerEntityWorldSpawn(EntityType<?> entity, Biome... biomes) {
		for(Biome biome : biomes) {
			if(biome != null) {
				biome.getSpawns(entity.getClassification()).add(0, new SpawnListEntry(entity, 1, 1, 2));
			}
		}
	}
	
	private static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name) {
		SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(Main.HORSE));
		item.setRegistryName(RegistryEvents.location(name));
		
		return item;
	}
}
