package com.aika;

import com.aika.mobs.CrabEntity;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityLoader implements ModInitializer {

    // public static final EntityType<CrabEntity> CRAB = Registry.register(
    //         Registries.ENTITY_TYPE,
    //         new Identifier("gloo_bloo", "crab"),
    //         FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, CrabEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    // );

    // public static final Item CRAB_SPAWN_EGG_ITEM = new SpawnEggItem(CRAB, 0xc4c4c4, 0xadadad, new FabricItemSettings());

    @Override
    public void onInitialize() {
        // FabricDefaultAttributeRegistry.register(CRAB, CrabEntity.setAttibutes());

        // //add spawn egg
        // Registry.register(Registry.ITEM, new Identifier("tutorial", "iron_golem_spawn_egg"), IRON_GOLEM_SPAWN_EGG);

    }
    
}