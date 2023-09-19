package com.aika;


import net.fabricmc.api.ModInitializer;



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