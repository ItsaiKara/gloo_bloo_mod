package com.aika.world;

import java.util.function.Consumer;

import com.mojang.datafixers.util.Pair;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils.Continentalness;
import terrablender.api.ParameterUtils.Depth;
import terrablender.api.ParameterUtils.Erosion;
import terrablender.api.ParameterUtils.Humidity;
import terrablender.api.ParameterUtils.ParameterPointListBuilder;
import terrablender.api.ParameterUtils.Temperature;
import terrablender.api.ParameterUtils.Weirdness;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;




import static terrablender.api.ParameterUtils.*;

public class GlooBeachRegion extends Region{

    public GlooBeachRegion(Identifier id,RegionType rt, int weight) {
        super(id, rt, weight);
    }

    // @Override
    // public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper){
    //     VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
    //     // Overlap Vanilla's parameters with our own for our COLD_BLUE biome.
    //     // The parameters for this biome are chosen arbitrarily.
    //     new ParameterPointListBuilder()
    //         .temperature(Temperature.span(Temperature.COOL, Temperature.FROZEN))
    //         .humidity(Humidity.span(Humidity.ARID, Humidity.DRY))
    //         .continentalness(Continentalness.INLAND)
    //         .erosion(Erosion.EROSION_0, Erosion.EROSION_1)
    //         .depth(Depth.SURFACE, Depth.FLOOR)
    //         .weirdness(Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING)
    //         .build().forEach(point -> builder.add(point, WorldGen.GLOO_BEACH));

    //     // Add our points to the mapper
    //     builder.build().forEach(mapper::accept);
    // }
    
}
