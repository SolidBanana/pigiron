package crabgnome.testmod.entities;

import crabgnome.testmod.lists.EntitiesList;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.world.World;

public class GoldenHorseEntity extends CreatureEntity {

	@SuppressWarnings("unchecked")
	public GoldenHorseEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super((EntityType<? extends CreatureEntity>) EntitiesList.GOLDEN_HORSE_ENTITY, worldIn);
	}
	
	@Override
	public void registerGoals() {
		//creature will sink without swimGoal, which i think is funny
		this.goalSelector.addGoal(0, new RandomWalkingGoal(this, 0.5d));
		this.goalSelector.addGoal(1, new LookRandomlyGoal(this));
	}

	@Override
	public void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0d);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0d);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(2.0d);
	}
}
