package com.bainhero.OlympiansMod.core.init;

import com.bainhero.OlympiansMod.OlympiansMod;
import com.bainhero.OlympiansMod.common.blocks.JudgementBlock;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			OlympiansMod.MOD_ID);
	
	// Blocks
		public static final RegistryObject<Block> BRONZE_ORE = BLOCKS.register("bronze_ore",
				() -> new OreBlock(AbstractBlock.Properties.of(Material.STONE)
						.requiresCorrectToolForDrops()
						.strength(5.0f, 5.0f)
						.harvestTool(ToolType.PICKAXE)
						.harvestLevel(4)));
		
		public static final RegistryObject<Block> BRONZE_BLOCK = BLOCKS.register("bronze_block",
				() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL)
						.requiresCorrectToolForDrops()
						.strength(6.0f, 6.0f)
						.harvestTool(ToolType.PICKAXE)
						.harvestLevel(4)
						.sound(SoundType.METAL)));
		
		public static final RegistryObject<Block> CELESTIAL_BLOCK = BLOCKS.register("celestial_block",
				() -> new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL)
						.requiresCorrectToolForDrops()
						.strength(10.0f, 10.0f)
						.harvestTool(ToolType.PICKAXE)
						.harvestLevel(5)
						.sound(SoundType.METAL)
						.noOcclusion()
						.lightLevel((blockState) -> 14)));
		
		public static final RegistryObject<Block> DEEP_EARTH = BLOCKS.register("deep_earth", 
				() -> new Block(AbstractBlock.Properties.of(Material.STONE)
						.strength(-1.0f, -1.0f)
						.sound(SoundType.NETHERITE_BLOCK)
						.harvestLevel(-1)));
		
		public static final RegistryObject<JudgementBlock> JUDGEMENT = BLOCKS.register("judgement", 
				() -> new JudgementBlock(AbstractBlock.Properties.of(Material.HEAVY_METAL)
						.strength(-1.0f, -1.0f)
						.harvestLevel(-1)
						.sound(SoundType.ANVIL)));
}

