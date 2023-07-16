package com.aika;

import net.fabricmc.api.ClientModInitializer;



public class EntityLoaderClient implements ClientModInitializer {
    // public static final EntityModelLayer MODEL_CRAB_LAYER = new EntityModelLayer(new Identifier("gloo_bloo", "crab"), "main");

    @Override
    public void onInitializeClient() {
        // EntityRendererRegistry.register(EntityLoader.CRAB, (context) -> {
        //     return new CrabEntityRenderer(context);
        // });
        // EntityModelLayerRegistry.registerModelLayer(MODEL_CRAB_LAYER, CrabEntityModel::getTexturedModelData);
    }

}
