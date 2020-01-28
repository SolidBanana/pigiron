package crabgnome.testmod.client.renders;

import crabgnome.testmod.Main.RegistryEvents;
import crabgnome.testmod.client.models.GoldenHorseEntityModel;
import crabgnome.testmod.entities.GoldenHorseEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class GoldenHorseEntityRender extends LivingRenderer<GoldenHorseEntity, GoldenHorseEntityModel> {

	public GoldenHorseEntityRender(EntityRendererManager manager) {
		super(manager, new GoldenHorseEntityModel(), 0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(GoldenHorseEntity entity) {
		return RegistryEvents.location("textures/enity/golden_horse_entity.png");
	}
	
	public static class RenderFactory implements IRenderFactory<GoldenHorseEntity> {
		@Override
		public EntityRenderer<? super GoldenHorseEntity> createRenderFor(EntityRendererManager manager) {
			return new GoldenHorseEntityRender(manager);
		}
	}
}
