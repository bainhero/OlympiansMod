package com.bainhero.OlympiansMod.common.items;

import java.util.function.Supplier;

import com.bainhero.OlympiansMod.core.init.RegistryInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

public enum ModItemTier implements IItemTier{
	CELESTIAL_BRONZE(15, -1, 0F, 0F, 0, () -> {
		return Ingredient.of(RegistryInit.CELESTIAL_INGOT.get());
	}),
	BRONZE(5, 10000, 10F, 10F, 30, () -> {
		return Ingredient.of(RegistryInit.BRONZE_INGOT.get());
	}),
	;
	
	private final int harvestLevel;
	private final int maxUses;
	private final float efficiency;
	private final float attackDamage;
	private final int enchantability;
	private final Supplier<Ingredient> repairMaterial;
	
	ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage,
			int enchantability, Supplier<Ingredient> repairMaterial) {
		this.harvestLevel = harvestLevel;
		this.maxUses = maxUses;
		this.efficiency = efficiency;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairMaterial = repairMaterial;
	}

	@Override
	public float getAttackDamageBonus() {
		// TODO Auto-generated method stub
		return attackDamage;
	}

	@Override
	public int getEnchantmentValue() {
		// TODO Auto-generated method stub
		return enchantability;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return harvestLevel;
	}

	@Override
	public Ingredient getRepairIngredient() {
		// TODO Auto-generated method stub
		return repairMaterial.get();
	}

	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return efficiency;
	}

	@Override
	public int getUses() {
		// TODO Auto-generated method stub
		return maxUses;
	}

}
