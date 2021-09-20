package com.bainhero.OlympiansMod.common.parents;

import java.util.ArrayList;

import net.minecraft.potion.EffectInstance;

public class Olympian {
	
	private static ArrayList<EffectInstance> effects = new ArrayList<EffectInstance>();
	
	public Olympian(EffectInstance[] effectList) {
		for(EffectInstance e : effectList){
			effects.add(e);
		}
	}
	public ArrayList<EffectInstance> getEffects(){
		return effects;
	}
}
