package com.aika.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class DepthSand extends FallingBlock {

    public DepthSand(Settings settings) {
        super(settings.sounds(BlockSoundGroup.SAND));
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    }



    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    
}
