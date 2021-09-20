package com.bainhero.OlympiansMod.common.commands;

import com.bainhero.OlympiansMod.common.world.dimension.ElysiumDimension;
import com.bainhero.OlympiansMod.util.PlayerEventHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.IWorldInfo;

public class RebirthCommand {
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> rebirthCommand 
			= Commands.literal("rebirth")
			.requires((commandSource) -> commandSource.hasPermission(1))
			.executes(RebirthCommand::reincarnation);
		
		dispatcher.register(rebirthCommand);
	}
	
	static int reincarnation(CommandContext<CommandSource> commandContext) {
		
		Entity entity = commandContext.getSource().getEntity();
		MinecraftServer mcserver = commandContext.getSource().getServer();
		RegistryKey<World> elysiumKey = ElysiumDimension.ELYSIUM_KEY;
		ServerWorld elysium = mcserver.getLevel(elysiumKey);
		ServerWorld overworld = mcserver.getLevel(World.OVERWORLD);
		IWorldInfo worldInfo = overworld.getLevelData();
		ITextComponent failure = new StringTextComponent("You are unable to reincarnate at this time.");
		
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			
			ServerWorld world = (ServerWorld) player.level;
			if (world == elysium) {
				if (!world.isClientSide() && player.canChangeDimensions() && player instanceof ServerPlayerEntity) {
					ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
					
					double randNum = Math.random();
					if (randNum <= 0.9d) {
						PlayerEventHandler.setDemigod(player, false);
						PlayerEventHandler.setParent(player, "none");
					} else if (randNum > 0.9) {
						PlayerEventHandler.setDemigod(player, true);
						PlayerEventHandler.setParent(player, "zeus");
					}
					
					PlayerEventHandler.setKarma(player, 0);
					
					serverPlayer.teleportTo(overworld, overworld.getLevelData().getXSpawn(), 
							worldInfo.getYSpawn(), 
							worldInfo.getZSpawn(), 
							worldInfo.getSpawnAngle(), 
							worldInfo.getSpawnAngle());
				}
			} else {
				commandContext.getSource().getServer().getPlayerList().broadcastMessage(failure, ChatType.CHAT, player.getUUID());
			}
		} else {
			commandContext.getSource().getServer().getPlayerList().broadcastMessage(failure, ChatType.SYSTEM, Util.NIL_UUID);
		}
		
		return 1;
	}
}
