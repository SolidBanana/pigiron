package crabgnome.testmod.config;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import crabgnome.testmod.Main;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

	public static final ForgeConfigSpec server_config;
	public static final ForgeConfigSpec client_config;
	
	static { 
		OregenConfig.init(SERVER_BUILDER, CLIENT_BUILDER);
		
		server_config = SERVER_BUILDER.build(); 
		client_config = CLIENT_BUILDER.build();
	}
	
	public static void loadConfig(ForgeConfigSpec config, String path) {
		Main.LOGGER.info("Loading config... " + path);
		
		final CommentedFileConfig file = CommentedFileConfig.builder(
				new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
		Main.LOGGER.info("Built config at " + path);
		
		file.load();
		Main.LOGGER.info("Loaded config at " + path);
		
		config.setConfig(file);
	}
}
