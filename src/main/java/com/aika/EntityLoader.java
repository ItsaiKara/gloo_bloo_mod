package com.aika;

import com.aika.mobs.CrabEntity;
import com.aika.mobs.CrabEntityModel;

import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EntityLoader implements ModInitializer {

    public static final EntityType<CrabEntity> CRAB = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("gloo_bloo", "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, CrabEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );
    // public static final Item CRAB_SPAWN_EGG_ITEM = new SpawnEggItem(CRAB, 0xc4c4c4, 0xadadad, new FabricItemSettings());

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(CRAB, CrabEntity.createMobAttributes());

        // //add spawn egg
        // Registry.register(Registry.ITEM, new Identifier("tutorial", "iron_golem_spawn_egg"), IRON_GOLEM_SPAWN_EGG);

    }
    
}
