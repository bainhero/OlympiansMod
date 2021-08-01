package com.bainhero.OlympiansMod.common.items;

import com.bainhero.OlympiansMod.common.effects.OEffectList;
import com.bainhero.OlympiansMod.util.PlayerEventHandler;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class NectarItem extends Item{

	public NectarItem(Properties props) {
		super(props);
	}
	
	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entity) {
		
		EffectInstance olympian = new EffectInstance(OEffectList.OLYMPIAN);
		int amp = 0;
		
		if(entity instanceof ServerPlayerEntity && !entity.level.isClientSide())
        {
			boolean isIn = false;
        	PlayerEntity player = (PlayerEntity) entity;
        	for(EffectInstance e : player.getActiveEffects()) {
        		if(e.getEffect().equals(olympian.getEffect())){
        			isIn = true;
        			amp = e.getAmplifier();
        		}
        	}
        	if(!isIn) {
        		entity.addEffect(new EffectInstance(OEffectList.OLYMPIAN, 6000, 0));
        		if(PlayerEventHandler.checkDemigod(player)) {
        			entity.addEffect(new EffectInstance(Effects.REGENERATION, 600, 0));
        		}
        	}else {
        		if(amp < 4) {
        			if(PlayerEventHandler.checkDemigod(player)) {
        				entity.addEffect(new EffectInstance(OEffectList.OLYMPIAN, 6000, amp+1));
            			entity.addEffect(new EffectInstance(Effects.REGENERATION, 600, amp+1));
            		}
        		}
        	}
        	if(!player.isCreative()) {
        		ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(Items.GLASS_BOTTLE));
        	}
        }
		
		return super.finishUsingItem(stack, worldIn, entity);
	}
	
	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.DRINK;
	}
	
	@Override
	public SoundEvent getEatingSound() {
		return null;
	}
}
