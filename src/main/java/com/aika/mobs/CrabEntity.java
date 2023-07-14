package com.aika.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class CrabEntity extends PathAwareEntity {

        public static final EntityType<? extends MobEntity> TYPE = null;

        public CrabEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
            super(entityType, world);
        }

        public static DefaultAttributeContainer.Builder setAttibutes(){
            return MobEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.85D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0D)
            .add(EntityAttributes.GENERIC_ARMOR, 2.0D)
            .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5D);
        }

        @Override
        public void initGoals(){
            super.initGoals();
            this.goalSelector.add(0, new SwimGoal(this));
            this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
            this.goalSelector.add(2, new FleeEntityGoal(this, PlayerEntity.class, 8.0F, 0.2D, 0.5D));
            this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.85D));
            this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
            this.goalSelector.add(5, new LookAroundGoal(this));
        }

        // @Override
        // public void registerControllers(AnimationData data) {
        //     data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        // }
        // @Override
        // public void playStepSound(){
        //     this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, .5F);
        //     super.playStepSound(source);
        // }
        @Override
        protected void playHurtSound(DamageSource source){
            this.playSound(SoundEvents.ENTITY_TURTLE_EGG_BREAK, 0.15F, .5F);
        }

        // @Override
        // protected SoundEvent playDeathSound(){
        //     return SoundEvents.ENTITY_TURTLE_AMBIENT_LAND;
        // }


    
}
