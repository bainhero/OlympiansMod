package com.bainhero.OlympiansMod.common.commands;

import com.bainhero.OlympiansMod.util.PlayerEventHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;

public class CheckCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> checkCommand 
			= Commands.literal("check")
			.requires((commandSource) -> commandSource.hasPermission(2))
			.then(Commands.literal("karma")
				.then(Commands.argument("player", EntityArgument.player())
						.executes(commandContext -> {
							PlayerEntity player = (PlayerEntity) EntityArgument.getPlayer(commandContext, "player");
							karma(commandContext, player);
							return 1;
						})
				)
				.executes(commandContext -> karma(commandContext, null)))
			.then(Commands.literal("demigod")
				.then(Commands.argument("player", EntityArgument.player())
						.executes(commandContext -> {
							PlayerEntity player = (PlayerEntity) EntityArgument.getPlayer(commandContext, "player");
							demigod(commandContext, player);
							return 1;
						})
				)
				.executes(commandContext -> demigod(commandContext, null)))
			.executes(null);
		
		dispatcher.register(checkCommand);
	}
	
	static int karma(CommandContext<CommandSource> commandContext, PlayerEntity player) throws CommandSyntaxException {
		
		int pKarma;
		Entity entity = commandContext.getSource().getEntity();
		
		if (player != null) {
			pKarma = PlayerEventHandler.getKarma(player);
			
			if (entity instanceof PlayerEntity) {
				
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Karma: %d", pKarma)), ChatType.CHAT, entity.getUUID());
			} else {
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Karma: %d", pKarma)), ChatType.SYSTEM, Util.NIL_UUID);
			}
		} else {
			if (entity instanceof PlayerEntity) {
				PlayerEntity pEntity = (PlayerEntity) entity;
				
				pKarma = PlayerEventHandler.getKarma(pEntity);
				
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Karma: %d", pKarma)), ChatType.CHAT, entity.getUUID());
			} else {
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent("Please specify player to check."), ChatType.SYSTEM, Util.NIL_UUID);
			}
		}
		
		return 1;
	}
	
	static int demigod(CommandContext<CommandSource> commandContext, PlayerEntity player) throws CommandSyntaxException {
		
		boolean isDemigod;
		Entity entity = commandContext.getSource().getEntity();
		
		if (player != null) {
			isDemigod = PlayerEventHandler.checkDemigod(player);
			
			if (entity instanceof PlayerEntity) {
				
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Demigod Status: %s", isDemigod)), ChatType.CHAT, entity.getUUID());
			} else {
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Demigod Status: %s", isDemigod)), ChatType.SYSTEM, Util.NIL_UUID);
			}
		} else {
			if (entity instanceof PlayerEntity) {
				PlayerEntity pEntity = (PlayerEntity) entity;
				
				isDemigod = PlayerEventHandler.checkDemigod(pEntity);
				
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Demigod Status: %s", isDemigod)), ChatType.CHAT, entity.getUUID());
			} else {
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent("Please specify player to check."), ChatType.SYSTEM, Util.NIL_UUID);
			}
		}
		
		return 1;
	}
}
