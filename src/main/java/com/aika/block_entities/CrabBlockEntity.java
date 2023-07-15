package com.aika.block_entities;

import com.aika.EntryPoint;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;

public class CrabBlockEntity extends BlockEntity {

    public final static BooleanProperty IS_EGG = BooleanProperty.of("false");

    public CrabBlockEntity(BlockPos pos, BlockState state) {
        super(EntryPoint.CRAB_BLOCK_ENTITY, pos, state);
    }



    
}
