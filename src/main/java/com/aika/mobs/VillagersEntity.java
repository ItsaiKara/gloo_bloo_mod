package com.aika.mobs;

import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.client.render.debug.VillageDebugRenderer.PointOfInterest;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.village.VillagerType;
import net.minecraft.world.World;

public class VillagersEntity  {

    // public static VillagerProfession registerVillagerProfession(String name, RegistryKey<PointOfInterest> type){
    //     return Registry.register(Registry.VILLAGER_PROFESSION, name, new Identifier("gloo_bloo", name), type),
    //     ;
    // }


    public VillagersEntity(EntityType<? extends VillagerEntity> entityType, World world, VillagerType type) {

    }

    
    
}
