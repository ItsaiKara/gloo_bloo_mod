package com.aika.mobs;

import com.aika.EntryPoint;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CrabEntityModel extends GeoModel<CrabEntity>{

    // private final ModelPart base;

    // public CrabEntityModel(ModelPart modelPart) {
    //     this.base = modelPart.getChild(EntityModelPartNames.CUBE);
    // }

    // public static TexturedModelData getTexturedModelData(){
    //     ModelData modelData = new ModelData();
    //     ModelPartData modelPartData = modelData.getRoot();
    //     modelPartData.addChild(EntityModelPartNames.CUBE, 
    //         ModelPartBuilder.create().uv(0, 0).cuboid(-6F, 12F, -6F, 12F, 12F, 12F),
    //         ModelTransform.pivot(0F, 0F, 0F)
    //     );
    //     return TexturedModelData.of(modelData, 64, 64);
    // }

    // @Override
    // public void setAngles(CrabEntity var1, float var2, float var3, float var4, float var5, float var6) {
        
    // }

    // @Override
    // public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
    //     ImmutableList.of(this.base).forEach((modelRenderer) -> {
    //         modelRenderer.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    //     });
    // }

    @Override
    public Identifier getModelResource(CrabEntity animatable) {
        return new Identifier(EntryPoint.MOD_ID, "geo/crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrabEntity animatable) {
        return new Identifier(EntryPoint.MOD_ID, "textures/entity/crab/crab.png");
    }

    @Override
    public Identifier getAnimationResource(CrabEntity animatable) {
        return new Identifier(EntryPoint.MOD_ID, "animations/crab.animation.json");
    }

    
    
}
