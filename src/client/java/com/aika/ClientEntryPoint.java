package com.aika;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aika.mobs.CrabEntity;
import com.aika.mobs.CrabEntityRenderer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ClientEntryPoint implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("gloo-bloo");

	public static final EntityModelLayer MODEL_CRAB_LAYER = new EntityModelLayer(new Identifier("gloo_bloo", "crab"), "client");

	@Override
	public void onInitializeClient() {
		LOGGER.info("Initializing client");
		EntityRendererRegistry.register(CrabEntity.CRAB, (context) -> {
            return new CrabEntityRenderer(context);
        });
		LOGGER.info("Registered crab renderer");
        // EntityModelLayerRegistry.registerModelLayer(MODEL_CRAB_LAYER, CrabEntityModel::getTexturedModelData);
		FabricDefaultAttributeRegistry.register(CrabEntity.CRAB, CrabEntity.setAttibutes());
		LOGGER.info("Registered crab model layer");
	}
	
}