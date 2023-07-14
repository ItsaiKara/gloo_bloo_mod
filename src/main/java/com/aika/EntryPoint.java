package com.aika;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.object.builder.FabricEntityType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aika.block_entities.CrabBlockEntity;
import com.aika.blocks.CobbledGabbroBlock;
import com.aika.blocks.CrabNestBlock;
import com.aika.blocks.DepthSand;
import com.aika.blocks.DepthstoneBlock;
import com.aika.blocks.EstoutOreBlock;
import com.aika.items.EstoutOreItem;
import com.aika.mobs.CrabEntity;
import com.mojang.datafixers.kinds.Const.Instance;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EntryPoint implements ModInitializer {
	// This logger is used to write text to the console and the log file.
    public static final Logger LOGGER = LoggerFactory.getLogger("gloo-bloo");

	public static final EstoutOreItem ESTOUT_ORE_ITEM = new EstoutOreItem(new FabricItemSettings());

	public static final EstoutOreBlock ESTOUT_ORE_BLOCK = new EstoutOreBlock(FabricBlockSettings.create().strength(4.0f).requiresTool());
	public static final DepthstoneBlock DEPTHSTONE_BLOCK = new DepthstoneBlock(FabricBlockSettings.create().strength(4.0f).requiresTool());
	public static final CobbledGabbroBlock COBBLEDGABBRO_BLOCK = new CobbledGabbroBlock(FabricBlockSettings.create().strength(4.2f).requiresTool());
	public static final DepthSand DEPTH_SAND = new DepthSand(FabricBlockSettings.create().strength(0.7f));
	public static final CrabNestBlock CRAB_NEST = new CrabNestBlock(FabricBlockSettings.create().strength(0.5f));

	public static final BlockEntityType<CrabBlockEntity> CRAB_BLOCK_ENTITY = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        new Identifier("tutorial", "demo_block_entity"),
        FabricBlockEntityTypeBuilder.create(CrabBlockEntity::new, CRAB_NEST).build()
    );

	private static final String MOD_ID = "gloo_bloo";
	// private static final RegistryKey<ItemGroup> BG_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "bloo-gloo"));
	private static final RegistryKey<ItemGroup> ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "test_group"));

	

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Okay i'm in");

		// Register our custom item
		Registry.register(Registries.ITEM, new Identifier("gloo_bloo", "estout_ore_item"), ESTOUT_ORE_ITEM);

		Registry.register(Registries.BLOCK, new Identifier("gloo_bloo", "estout_ore_block"), ESTOUT_ORE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("gloo_bloo", "estout_ore_block"), new BlockItem(ESTOUT_ORE_BLOCK, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("gloo_bloo", "depthstone_block"), DEPTHSTONE_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("gloo_bloo", "depthstone_block"), new BlockItem(DEPTHSTONE_BLOCK, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("gloo_bloo", "cobbledgabbro_block"), COBBLEDGABBRO_BLOCK);
		Registry.register(Registries.ITEM, new Identifier("gloo_bloo", "cobbledgabbro_block"), new BlockItem(COBBLEDGABBRO_BLOCK, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("gloo_bloo", "depthsand_block"), DEPTH_SAND);
		Registry.register(Registries.ITEM, new Identifier("gloo_bloo", "depthsand_block"), new BlockItem(DEPTH_SAND, new FabricItemSettings()));

		Registry.register(Registries.BLOCK, new Identifier("gloo_bloo", "crabnest_block"), CRAB_NEST);
		Registry.register(Registries.ITEM, new Identifier("gloo_bloo", "crabnest_block"), new BlockItem(CRAB_NEST, new FabricItemSettings()));

		

		
		
		// Register our custom item group
		Registry.register(Registries.ITEM_GROUP, ITEM_GROUP, FabricItemGroup.builder()
			.displayName(Text.translatable("gloo_bloo.item_group"))
			.icon(() -> new ItemStack(DEPTHSTONE_BLOCK))
			.entries((context, entries) -> {
				entries.add(ESTOUT_ORE_ITEM);
				entries.add(ESTOUT_ORE_BLOCK);
				entries.add(DEPTHSTONE_BLOCK);
				entries.add(COBBLEDGABBRO_BLOCK);
				entries.add(DEPTH_SAND);
				entries.add(CRAB_NEST);
				// entries.add(ESTOUT_ORE_BLOCK.asItem());
			})
			.build()
		);
		
	}
		
}
