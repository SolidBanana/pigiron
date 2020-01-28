package crabgnome.testmod.world;

import crabgnome.testmod.config.OregenConfig;
import crabgnome.testmod.lists.BlockList;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

	public static void setupOreGeneration() {
		if(OregenConfig.generate_in_overworld.get()) {
			for(Biome biome : ForgeRegistries.BIOMES) {
				
				biome.addFeature(
						Decoration.UNDERGROUND_ORES, 
						Biome.createDecoratedFeature(
								Feature.ORE, 
								new OreFeatureConfig(
										FillerBlockType.NATURAL_STONE, 
										BlockList.horse_ore.getDefaultState(),
										OregenConfig.horse_chance.get()),
								Placement.COUNT_RANGE,
								new CountRangeConfig(5, 20, 20, 100)
						));
				
				biome.addFeature(
						Decoration.UNDERGROUND_ORES, 
						Biome.createDecoratedFeature(
								Feature.ORE, 
								new OreFeatureConfig(
										FillerBlockType.NETHERRACK, 
										BlockList.horse_ore_nether.getDefaultState(),
										OregenConfig.horse_chance.get()),
								Placement.COUNT_RANGE,
								new CountRangeConfig(5, 0, 0, 256)
						));
						
			}
		}
	}
}
