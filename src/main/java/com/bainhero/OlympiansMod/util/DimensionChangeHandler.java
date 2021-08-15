package com.bainhero.OlympiansMod.util;

import com.bainhero.OlympiansMod.OlympiansMod;
import com.bainhero.OlympiansMod.common.world.dimension.UnderworldDimension;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
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
		
		if(!player.level.isClientSide() && !player.isPassenger() &&
				player.canChangeDimensions() &&
				!player.isCrouching() && player instanceof ServerPlayerEntity) {
			
			ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
			BlockPos location = checkPos(serverPlayer, destination);
			serverPlayer.teleportTo(destination, location.getX(), location.getY(), location.getZ(), player.getRotationVector().x, player.getRotationVector().y);
		}
	}
	
	public static BlockPos checkPos(ServerPlayerEntity player, ServerWorld destination) {
		BlockPos currentPos = player.blockPosition();
		BlockPos checkPos  = new BlockPos(currentPos.getX(), 1, currentPos.getZ());
		BlockPos goodPos = null;
		boolean prevBlock = false;
		int prevAir = 0;
		
		while(goodPos == null) {
			checkPos = new BlockPos(checkPos.getX(), 1, checkPos.getZ());
			while (checkPos.getY() < 256) {
				if(destination.getBlockState(checkPos).getMaterial() != Material.AIR && destination.getBlockState(checkPos).getMaterial() != Material.LAVA && destination.getBlockState(checkPos).getMaterial() != Material.FIRE) {
					prevBlock = true;
					prevAir = 0;
					System.out.println(checkPos.getY());
				} else if (destination.getBlockState(checkPos).getMaterial() == Material.AIR && prevBlock == true) {
					prevAir++;
					if (prevAir == 2) {
						goodPos = checkPos;
						break;
					}
				} else {
					prevAir = 0;
					prevBlock = false;
				}
				checkPos = checkPos.above();
			}
			checkPos = checkPos.east(10);
			checkPos = checkPos.west(10);
		}
		return goodPos;
	}
}
