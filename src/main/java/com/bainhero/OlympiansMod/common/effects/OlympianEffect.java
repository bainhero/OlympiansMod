package com.bainhero.OlympiansMod.common.effects;

import com.bainhero.OlympiansMod.util.PlayerEventHandler;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class OlympianEffect extends Effect{

	public OlympianEffect(EffectType effect, int color) {
		super(effect, color);
	}
	
	@Override
	public void applyEffectTick(LivingEntity entity, int amp) {
		if(!PlayerEventHandler.checkDemigod((PlayerEntity) entity)){
			entity.hurt(DamageSource.MAGIC, 4.0f);
		}
	}
	
	@Override
	public boolean isDurationEffectTick(int duration, int amp) {
		return true;
	}
}
