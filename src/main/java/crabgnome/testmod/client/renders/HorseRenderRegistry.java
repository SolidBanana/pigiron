package crabgnome.testmod.client.renders;

import crabgnome.testmod.entities.GoldenHorseEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class HorseRenderRegistry {
	public static void registryEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(GoldenHorseEntity.class, new GoldenHorseEntityRender.RenderFactory());
	}
}
