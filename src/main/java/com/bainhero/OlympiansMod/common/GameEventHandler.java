package com.bainhero.OlympiansMod.common;

import com.bainhero.OlympiansMod.OlympiansMod;
import com.bainhero.OlympiansMod.common.parents.Olympian;
import com.bainhero.OlympiansMod.util.PlayerEventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=OlympiansMod.MOD_ID)
public class GameEventHandler {
	private static Olympian olympian;
	@SubscribeEvent
	public static void onUpdate(TickEvent.PlayerTickEvent tick) {
		PlayerEntity player = tick.player;
		World level = player.level;
		String parent = "none";
		
		if(!level.isClientSide() && PlayerEventHandler.checkDemigod(player)) {
			parent = PlayerEventHandler.getParent(player);
			olympian = OlympianList.getOlympian(parent);
			
			for(EffectInstance e : olympian.getEffects()) {
				player.addEffect(e);
			}
		}
	}
}
