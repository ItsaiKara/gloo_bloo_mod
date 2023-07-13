package com.aika;

import com.aika.mobs.CrabEntity;
import com.aika.mobs.CrabEntityModel;

import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityLoader implements ModInitializer {

    public static final EntityType<CrabEntity> CRAB = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("gloo_bloo", "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrabEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(CRAB, CrabEntity.createMobAttributes());
    }
    
}
