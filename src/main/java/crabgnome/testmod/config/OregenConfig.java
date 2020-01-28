package crabgnome.testmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class OregenConfig {
	public static ForgeConfigSpec.IntValue horse_chance;
	public static ForgeConfigSpec.BooleanValue generate_in_overworld;
	
	public static void init(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
		server.comment("Oregen Config");
		
		horse_chance = server
				.comment("Max number of ore veins per chunk")
				.defineInRange("oregen.horse_chance", 2, 1, 100000);
		
		generate_in_overworld = server
				.comment("If you want horse ore to spawn naturally in the overworld")
				.define("oregen.generate_overworld", true);
	}
}
