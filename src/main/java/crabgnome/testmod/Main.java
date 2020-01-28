package crabgnome.testmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import crabgnome.testmod.client.renders.HorseRenderRegistry;
import crabgnome.testmod.config.Config;
import crabgnome.testmod.lists.ArmorTierList;
import crabgnome.testmod.lists.BlockList;
import crabgnome.testmod.lists.EntitiesList;
import crabgnome.testmod.lists.ItemList;
import crabgnome.testmod.lists.ItemTierList;
import crabgnome.testmod.util.Ref;
import crabgnome.testmod.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Foods;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Ref.MOD_ID)
public class Main {
	
	public static final Logger LOGGER = LogManager.getLogger(Ref.MOD_ID);
	public static Main instance;
	
	public static final ItemGroup HORSE = new HorseItemGroup();
	
	public Main() {
		instance = this;
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config, "testmodsimple-client.toml");
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.server_config, "testmodsimple-server.toml");
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("testmodsimple-client.toml").toString());
		Config.loadConfig(Config.server_config, FMLPaths.CONFIGDIR.get().resolve("testmodsimple-server.toml").toString());
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		OreGeneration.setupOreGeneration();
		LOGGER.info("Boy howdy (common setup)");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		HorseRenderRegistry.registryEntityRenders();
		LOGGER.info("Boy howdy (client registries)");
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			
			/* put items here */
			event.getRegistry().registerAll(
					ItemList.horsebucket = new Item(new Item.Properties().group(HORSE)
							.food(Foods.BEETROOT_SOUP))//slorp
							.setRegistryName(location("horsebucket")),
					ItemList.horseingot = new Item(new Item.Properties().group(HORSE))
							.setRegistryName(location("horseingot")),
					/*
					ItemList.horse_nugget = new Item(new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_nugget")),
					*/
							
					ItemList.horseblock = new BlockItem(BlockList.horseblock, new Item.Properties().group(HORSE))
							.setRegistryName(BlockList.horseblock.getRegistryName()),
					ItemList.horse_ore = new BlockItem(BlockList.horse_ore, new Item.Properties().group(HORSE))
							.setRegistryName(BlockList.horse_ore.getRegistryName()),
					ItemList.horse_ore_nether = new BlockItem(BlockList.horse_ore_nether, new Item.Properties().group(HORSE))
							.setRegistryName(BlockList.horse_ore_nether.getRegistryName()),
					
					/* tools */
					ItemList.horse_axe = new AxeItem(ItemTierList.HORSE, -1.0f, 6.0f, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_axe")),
					ItemList.horse_hoe = new HoeItem(ItemTierList.HORSE, 6.0f, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_hoe")),
					ItemList.horse_pickaxe = new PickaxeItem(ItemTierList.HORSE, -2, 6.0f, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_pickaxe")),
					ItemList.horse_shovel = new ShovelItem(ItemTierList.HORSE, -3.0f, 6.0f, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_shovel")),
					ItemList.horse_sword = new SwordItem(ItemTierList.HORSE, 0, 6.0f, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_sword")),
						
					/* armor */
					ItemList.horse_helmet = new ArmorItem(ArmorTierList.HORSE, EquipmentSlotType.HEAD, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_helmet")),		
					ItemList.horse_chestplate = new ArmorItem(ArmorTierList.HORSE, EquipmentSlotType.CHEST, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_chestplate")),		
					ItemList.horse_leggings = new ArmorItem(ArmorTierList.HORSE, EquipmentSlotType.LEGS, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_leggings")),		
					ItemList.horse_boots = new ArmorItem(ArmorTierList.HORSE, EquipmentSlotType.FEET, new Item.Properties().group(HORSE))
							.setRegistryName(location("horse_boots"))
			);
			
			EntitiesList.registerEntitySpawnEggs(event);
			
			LOGGER.info("items registered");
		}
		
		@SubscribeEvent
		public static void registerBlocks(final RegistryEvent.Register<Block> event) {
			
			/* put blocks here */
			event.getRegistry().registerAll(
					BlockList.horseblock = new Block(Block.Properties
							.create(Material.IRON)
							.hardnessAndResistance(2.0f, 3.0f)
							.lightValue(2)
							.sound(SoundType.SLIME))
							.setRegistryName(location("horseblock")),
					BlockList.horse_ore = new Block(Block.Properties
							.create(Material.ROCK)
							.hardnessAndResistance(2.0f, 3.0f)
							.lightValue(2)
							.sound(SoundType.SLIME))
							.setRegistryName(location("horse_ore")),
					BlockList.horse_ore_nether = new Block(Block.Properties
							.create(Material.ROCK)
							.hardnessAndResistance(2.0f, 3.0f)
							.lightValue(4)
							.sound(SoundType.SLIME))
							.setRegistryName(location("horse_ore_nether"))
			);
			
			LOGGER.info("blocks registered");
		}
		
		public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
			
			/* put blocks here */
			event.getRegistry().registerAll(
					EntitiesList.GOLDEN_HORSE_ENTITY
			);
			
			EntitiesList.registerEntityWorldSpawns();
			LOGGER.info("entities registered");
		}
		
		/**
		 * helper function - generates a ResourceLocation for a given item name
		 * @param name
		 * @return ResourceLocation
		 */
		public static ResourceLocation location(String name) {
			return new ResourceLocation(Ref.MOD_ID, name);
		}
	}
}
