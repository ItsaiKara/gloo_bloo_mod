package com.aika.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class CrabEntity extends PathAwareEntity {

        public static final EntityType<? extends MobEntity> TYPE = null;

        public CrabEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
            super(entityType, world);
        }
    
}
