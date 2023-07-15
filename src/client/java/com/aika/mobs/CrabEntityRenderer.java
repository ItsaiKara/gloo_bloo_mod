package com.aika.mobs;

import com.aika.ClientEntryPoint;
import com.aika.EntityLoaderClient;


import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class CrabEntityRenderer extends MobEntityRenderer<CrabEntity, CrabEntityModel> {
    
    public CrabEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CrabEntityModel(context.getPart(ClientEntryPoint.MODEL_CRAB_LAYER)),1);
    }

    @Override
    public Identifier getTexture(CrabEntity entity) {
        return new Identifier("gloo_bloo", "textures/entity/crab/crab.png");
    }
}
