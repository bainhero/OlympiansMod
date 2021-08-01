package com.bainhero.OlympiansMod.core.init;

import com.bainhero.OlympiansMod.OlympiansMod;
import com.bainhero.OlympiansMod.common.effects.OEffectList;
import com.bainhero.OlympiansMod.common.items.Anaklusmos;
import com.bainhero.OlympiansMod.common.items.CelestialBronzeBlockItem;
import com.bainhero.OlympiansMod.common.items.CelestialBronzeItem;
import com.bainhero.OlympiansMod.common.items.ModItemTier;
import com.bainhero.OlympiansMod.common.items.NectarItem;
import com.bainhero.OlympiansMod.common.items.TempItem;

import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryInit {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, 
			OlympiansMod.MOD_ID);
	
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS,
			OlympiansMod.MOD_ID);
	
	// Items
	
	public static final RegistryObject<Anaklusmos> ANAKLUSMOS = ITEMS.register("anaklusmos", 
			() -> new Anaklusmos(ModItemTier.CELESTIAL_BRONZE, -1, -1F, new SwordItem.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).stacksTo(1)));
	
	public static final RegistryObject<NectarItem> NECTAR = ITEMS.register("nectar", 
			() -> new NectarItem(new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).food(new Food.Builder().nutrition(20).saturationMod(20f).alwaysEat().build())));
	
	public static final RegistryObject<BlockItem> BRONZE_ORE = ITEMS.register("bronze_ore",
			() -> new BlockItem(BlockInit.BRONZE_ORE.get(), new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP)));
	
	public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot",
			() -> new Item(new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP)));
	
	public static final RegistryObject<BlockItem> BRONZE_BLOCK = ITEMS.register("bronze_block",
			() -> new BlockItem(BlockInit.BRONZE_BLOCK.get(), new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP)));
	
	public static final RegistryObject<Item> CELESTIAL_INGOT = ITEMS.register("celestial_ingot",
			() -> new CelestialBronzeItem(new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).rarity(Rarity.EPIC)));
	
	public static final RegistryObject<BlockItem> CELESTIAL_BLOCK = ITEMS.register("celestial_block",
			() -> new CelestialBronzeBlockItem(BlockInit.CELESTIAL_BLOCK.get(), new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).fireResistant().rarity(Rarity.EPIC)));
	
	public static final RegistryObject<Item> TEMP_ITEM = ITEMS.register("temp_item",
			() -> new TempItem(new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP)));
	
	// Effects
	
	public static final RegistryObject<Effect> OLYMPIAN = EFFECTS.register("olympian",
			() -> OEffectList.OLYMPIAN);
	
	// Tools
	
	public static final RegistryObject<Item> BRONZE_SWORD = ITEMS.register("bronze_sword",
			() -> new SwordItem(ModItemTier.BRONZE, -1, -1f, new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).stacksTo(1)));
	public static final RegistryObject<Item> BRONZE_PICKAXE = ITEMS.register("bronze_pickaxe", 
			() -> new PickaxeItem(ModItemTier.BRONZE, -2, -2f, new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).stacksTo(1)));
	public static final RegistryObject<Item> BRONZE_AXE = ITEMS.register("bronze_axe", 
			() -> new AxeItem(ModItemTier.BRONZE, 0, -3f, new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).stacksTo(1)));
	public static final RegistryObject<Item> BRONZE_SHOVEL = ITEMS.register("bronze_shovel", 
			() -> new ShovelItem(ModItemTier.BRONZE, -3, -2f, new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).stacksTo(1)));
	public static final RegistryObject<Item> BRONZE_HOE = ITEMS.register("bronze_hoe", 
			() -> new HoeItem(ModItemTier.BRONZE, -4, -2f, new Item.Properties().tab(OlympiansMod.OLYMPIANS_GROUP).stacksTo(1)));
}
