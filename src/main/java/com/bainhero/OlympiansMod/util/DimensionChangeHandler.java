package com.bainhero.OlympiansMod.util;

import com.bainhero.OlympiansMod.OlympiansMod;
import com.bainhero.OlympiansMod.common.world.dimension.UnderworldDimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=OlympiansMod.MOD_ID)
public class DimensionChangeHandler {
	
	@SubscribeEvent
	public static void onPlayerDeathEvent(PlayerRespawnEvent event) {
		PlayerEntity player = event.getPlayer();
		RegistryKey<World> underworld = UnderworldDimension.UNDERWORLD_KEY;
		MinecraftServer mcserver = player.getServer();
		ServerWorld destination = mcserver.getLevel(underworld);
		
		if(!event.getPlayer().level.isClientSide() && !player.isPassenger() &&
				player.canChangeDimensions() &&
				!player.isCrouching() && player instanceof ServerPlayerEntity) {
			
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
			
			serverPlayer.teleportTo(destination, 0, 128D, 0, 0, 0);
			
		}
	}
	
}
