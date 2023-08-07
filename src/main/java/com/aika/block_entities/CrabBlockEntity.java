package com.aika.block_entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.aika.EntityLoader;
import com.aika.EntityLoaderClient;
import com.aika.EntryPoint;
import com.aika.mobs.CrabEntity;
import com.google.common.collect.Lists;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeehiveBlockEntity;
import net.minecraft.block.entity.BeehiveBlockEntity.BeeState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.debug.BeeDebugRenderer.Bee;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CrabBlockEntity extends BlockEntity {
    
    public final static BooleanProperty IS_EGG = BooleanProperty.of("false");
    
    public final List<Crab> crabs = Lists.newArrayList();

    public CrabBlockEntity(BlockPos pos, BlockState state) {
        super(EntryPoint.CRAB_BLOCK_ENTITY, pos, state);
    }
    
    public void tryEnterNest(Entity entity, int ticksInHive) {
        if (this.crabs.size() > 0) {
            return ;
        }
        entity.stopRiding();
        entity.removeAllPassengers();
        this.crabs.add(new Crab(entity, ticksInHive));
        if (this.world != null) {
            CrabEntity CrabEntity;
            BlockPos blockPos = this.getPos();
            this.world.playSound(null, (double)blockPos.getX(), (double)blockPos.getY(), blockPos.getZ(), SoundEvents.BLOCK_BEEHIVE_ENTER, SoundCategory.BLOCKS, 1.0f, 1.0f);
            // this.world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(entity, this.getCachedState()));
            entity.discard();
            this.crabs.add(new Crab(entity, ticksInHive));
            super.markDirty();
        }
    }

    @Nullable
    public Crab tryReleaseCrab(BlockState state, CrabEntity crab) {
        if (!crabs.isEmpty()) {
            super.markDirty();
            releaseCrab(this.world, this.pos, crabs.remove(0), null);
        }
        return null;
    }

    private static boolean releaseCrab(World world, BlockPos pos, Crab crab, @Nullable List<Entity> entities) {
        boolean bl = false;
        world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_EXIT, SoundCategory.BLOCKS, 1.0f, 1.0f);
        // world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(entity2, world.getBlockState(pos)));
        bl = world.spawnEntity(Registries.ENTITY_TYPE.get(new Identifier(EntryPoint.MOD_ID, "crab")).downcast(crab.crab));
        return bl;
    }
    
    
    
    private class Crab{
        private final Entity crab;
        private final int ticksInHive;
        
        public Crab(Entity crab, int ticksInHive) {
            this.ticksInHive = ticksInHive;
            this.crab = crab;
        }
    }    
}
