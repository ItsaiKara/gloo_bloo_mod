package com.aika.mobs;


import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrabEntityRenderer extends GeoEntityRenderer<CrabEntity> {
    
    public CrabEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CrabEntityModel());
    }

    @Override
    public Identifier getTextureLocation(CrabEntity entity) {
        return new Identifier("gloo_bloo", "textures/entity/crab/crab.png");
    }

    @Override
    public void render(CrabEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight){
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
