package com.aika;


import com.aika.mobs.CrabEntityModel;
import com.aika.mobs.CrabEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

import net.minecraft.util.Identifier;




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
