package com.bainhero.OlympiansMod.common.items;

import com.bainhero.OlympiansMod.client.OlympianSource;
import com.bainhero.OlympiansMod.common.entities.TartarusEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class Anaklusmos extends SwordItem {

	public Anaklusmos(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties props) {
		super(tier, attackDamageIn, attackSpeedIn, props);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {
		if(entity instanceof TartarusEntity) {
			entity.hurt(new OlympianSource("celestial bronze"), 40f);
		}
		return super.onLeftClickEntity(stack, player, entity);
	}
}
