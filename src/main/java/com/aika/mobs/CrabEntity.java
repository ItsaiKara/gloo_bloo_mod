package com.aika.mobs;


import com.aika.mobs.ai.CrabFindNestGoal;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;

import software.bernie.geckolib.core.object.PlayState;

public class CrabEntity extends AnimalEntity implements GeoEntity {
        public final static int MAX_DIGTIME = 30;
        public final static int MAX_DIG_COOLDOWN = 500;
        private int fuseTimer = 80;
        private boolean ignited = false;
        private Block nestBlock = null;
        private int digCooldown = MAX_DIG_COOLDOWN;
        private int digTime = MAX_DIGTIME;
        private boolean disturbed = false;
        private BlockPos nestPos = null;
        //GEOLIBStuff
        private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

        public static final EntityType<CrabEntity> CRAB = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("gloo_bloo", "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrabEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.35f)).build()
        );

        public CrabEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
            super((EntityType<? extends AnimalEntity>) entityType, world);
            
        }

        // public CrabEntity(EntityType<? extends PathAwareEntity> entityType, World world, int x, int y, int z) {
        //     super(entityType,world);
        //     this.updatePosition(x, y, z);
        // }

        public static DefaultAttributeContainer.Builder setAttibutes(){
            return MobEntity.createMobAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0D)
            .add(EntityAttributes.GENERIC_ARMOR, 2.0D)
            .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.5D);
        }

        public void forceAttributes(){
            CrabEntity.setAttibutes();
        }

        @Override
        public void initGoals(){
            super.initGoals();
            this.goalSelector.add(1, new SwimGoal(this));
            this.goalSelector.add(2, new CrabFindNestGoal(this));
            // this.goalSelector.add(3, new MeleeAttackGoal(this, 0.3D, true));
            this.goalSelector.add(3, new CrabDigSandGoal(this));
            this.goalSelector.add(4, new FleeEntityGoal(this, PlayerEntity.class, 8.0F, 0.2D, 0.8D));
            this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.55D));
            this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
            this.goalSelector.add(7, new LookAroundGoal(this));

            // this.targetSelector.add(1, new );
        }

        public Block getNest (){
            return this.nestBlock;
        }

        @Override
        protected void playHurtSound(DamageSource source){
            this.playSound(SoundEvents.ENTITY_TURTLE_EGG_BREAK, 0.15F, 1.5F);
        }

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
            if (this.digCooldown > 0){
                this.digCooldown--;
            }
        }


        public void explode() {
            if (!this.getWorld().isClient) {
                this.dead = true;
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), (float)1, World.ExplosionSourceType.MOB);
                this.discard();
            }
        }    

        @Override
        public AnimatableInstanceCache getAnimatableInstanceCache() {
            return this.cache;
        }

        @Override
        public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
            controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        }

        private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState){
            // tAnimationState.getController().setAnimation(RawAnimation.begin().then());
            return PlayState.CONTINUE;
        }

        public boolean isDisturbed(){
            return this.disturbed;
        }

        public void setDisturbed(boolean disturbed){
            this.disturbed = disturbed;
        }

    public class CrabDigSandGoal extends Goal {
        private CrabEntity crab = null;

        public CrabDigSandGoal(CrabEntity crab) {
            this.crab = crab;
        }

        @Override
        public boolean canStart() {
            //Get block below crab
            int x = (int) this.crab.getX();
            int y = (int) this.crab.getY();
            int z = (int) this.crab.getZ();
            BlockState block = this.crab.getWorld().getBlockState(new BlockPos(x, y - 1, z));
            //if block is not sand return false
            if (block.getBlock() != Blocks.SAND) {
                return false;
            }
            if (this.crab.digCooldown > 0 ) {
                return false;
            }
            return true;
        }

        @Override
        public void start() {
            System.out.println("Crab started digging");
            this.crab.digTime = MAX_DIGTIME ;
        }
        
        @Override
        public boolean shouldContinue() {
            if (this.crab.digTime <= 0) {
                return false;
            }
            return true;
        }

        @Override
        public void tick() {
            System.out.println(this.crab.digTime + " " + this.crab.digCooldown);
            if (this.crab.digTime > 0) {
                if (this.crab.navigation.isIdle()){
                    this.crab.digTime--;
                    if (this.crab.digTime % 5 == 0) {
                        //prevent crab from moving
                        // this.crab.navigation.setSpeed(0);
                        this.crab.playSound(SoundEvents.BLOCK_SAND_HIT, 0.15F, 1.5F);
                    }
                } else {
                    this.crab.setDisturbed(true);
                    this.stop();
                }
            }else  {
                // this.crab.navigation.setSpeed(0.85D);
                this.crab.setDisturbed(false);
                this.stop();
            }
        }

        @Override
        public void stop() {
            this.crab.digTime = 0;
            this.crab.digCooldown = MAX_DIG_COOLDOWN;
            this.crab.setMovementSpeed((float)0.5D);
            //print isDisturbed
            System.out.println(this.crab.isDisturbed());

            if (this.crab.isDisturbed()==false){
                this.crab.playSound(SoundEvents.BLOCK_SAND_BREAK, 0.15F, 0.5F);
                // this.crab.navigation.setSpeed(0.85D);
            }
        }
    }

    @Override
    public PassiveEntity createChild(ServerWorld var1, PassiveEntity var2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createChild'");
    }
}

