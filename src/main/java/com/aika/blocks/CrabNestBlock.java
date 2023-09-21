package com.aika.blocks;

import com.aika.mobs.CrabEntity;
import com.aika.EntryPoint;
import com.aika.block_entities.CrabBlockEntity;

// import net.fabricmc.fabric.api.client.networking.v1.C2SPlayChannelEvents.Register;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;

import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CrabNestBlock extends BlockWithEntity  {

    public static final BooleanProperty HAS_CRAB = BooleanProperty.of("has_crab");
    // public static final BooleanProperty HAS_EGG = BooleanProperty.of("has_egg");
    
    private CrabEntity crab = null;
    private CrabBlockEntity crabBlockEntity = null;

    public CrabNestBlock(Settings settings) {
        super(settings.sounds(BlockSoundGroup.SAND));
        setDefaultState(getDefaultState().with(HAS_CRAB, false));
    }


    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance){
        if(!world.isClient && world.random.nextInt(1) == 0){
            this.destroy(world, state, pos, fallDistance);
        }
    }

    public void destroy(World world, BlockState state, BlockPos pos, float fallDistance){
        if(!world.isClient && world.random.nextInt(1) == 0){
            EntryPoint.LOGGER.info("Nest destroyed ...");
            //spawn crab
            if (world.getBlockState(pos).get(HAS_CRAB)) {
                //spawn a crab
                // EntityType<?> entityType = Registries.ENTITY_TYPE.get(new Identifier("aika", "crab"));
                // Entity entity = entityType.create(world);
                CrabEntity entity = new CrabEntity((EntityType<? extends PathAwareEntity>) Registries.ENTITY_TYPE.get(new Identifier("gloo_bloo", "crab")), world);
                entity.refreshPositionAndAngles(pos.getX()+0.5, pos.getY(), pos.getZ()+.5, 0.0F, 0.0F);
                world.spawnEntity(entity);
                EntryPoint.LOGGER.info("Crab exited");
                world.setBlockState(pos, state.with(HAS_CRAB, false));
            } else if (crab != null) {
                EntryPoint.LOGGER.info("Crab lost nest");
                crab.destroyNest();
            } else {
                EntryPoint.LOGGER.info("Crab not found");
            }
            //delete block
            world.removeBlock(pos, false);
            world.playSound(null, pos, SoundEvents.BLOCK_SAND_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }   
    }

    public CrabEntity getCrab(){
        return crab;
    }
    public void setCrab(CrabEntity crab){
        this.crab = crab;
    }

    public void enterNest(CrabEntity crab){
        this.crab = crab;
    }

    public void addCrab(CrabBlockEntity crabBlockEntity){
        this.crabBlockEntity = crabBlockEntity;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(net.minecraft.state.StateManager.Builder<Block, net.minecraft.block.BlockState> builder) {
        builder.add(HAS_CRAB);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState hasCrab) {
        return new CrabBlockEntity(pos, hasCrab);
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    }

    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }

    public BlockState getStateWithProperties(String property) {
        if (property == "HAS_CRAB") {
            return this.getDefaultState().with(HAS_CRAB, true);
        }
        return this.getDefaultState().with(HAS_CRAB, false);
    }

    public BlockPos getPos() {
        return this.getPos();
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, EntryPoint.CRAB_BLOCK_ENTITY, (world1, pos, state1, be) -> CrabBlockEntity.tick(world1, pos, state1, be));
    }
    
} 
