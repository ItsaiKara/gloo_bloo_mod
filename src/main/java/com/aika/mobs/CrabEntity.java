package com.aika.mobs;


import com.mojang.datafixers.types.templates.List;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CrabEntity extends PathAwareEntity {
        private int fuseTimer = 80;
        private boolean ignited = false;

        public static final EntityType<CrabEntity> CRAB = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("gloo_bloo", "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrabEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

        public CrabEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
            super(entityType, world);
            
        }

        // public CrabEntity(EntityType<? extends PathAwareEntity> entityType, World world, int x, int y, int z) {
        //     super(entityType,world);
        //     this.updatePosition(x, y, z);
        // }

        public static DefaultAttributeContainer.Builder setAttibutes(){
            return MobEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.85D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0D)
            .add(EntityAttributes.GENERIC_ARMOR, 2.0D)
            .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5D);
        }

        public void forceAttributes(){
            this.setAttibutes();
        }

        @Override
        public void initGoals(){
            super.initGoals();
            this.goalSelector.add(0, new SwimGoal(this));
            this.goalSelector.add(1, new MeleeAttackGoal(this, 0.3D, true));
            this.goalSelector.add(2, new FleeEntityGoal(this, PlayerEntity.class, 8.0F, 0.2D, 0.5D));
            this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.55D));
            this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
            this.goalSelector.add(5, new LookAroundGoal(this));
        }

        // @Override
        // public void registerControllers(AnimationData data) {
        //     data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        // }
        // @Override
        // public void playStepSound(DamageSource source){
        //     this.playStepSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, .5F);
        //     // super.playStepSound(source);
        // }
        @Override
        protected void playHurtSound(DamageSource source){
            this.playSound(SoundEvents.ENTITY_TURTLE_EGG_BREAK, 0.15F, 1.5F);
        }

        // @Override
        // protected SoundEvent playDeathSound(){
        //     return SoundEvents.ENTITY_TURTLE_AMBIENT_LAND;
        // }


        public void ignite(){
            this.ignited = true;
            //apply levitation effect to self
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 1));
        }

        //on tick 
        @Override
        public void tick(){
            super.tick();
            if (this.ignited){
                this.fuseTimer--;
                if (this.fuseTimer <= 0){
                    this.explode();
                }
            }
        }


        public void explode() {
            if (!this.getWorld().isClient) {
                this.dead = true;
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)1, World.ExplosionSourceType.MOB);
                this.discard();
            }
        }


    
}
