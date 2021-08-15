package com.bainhero.OlympiansMod.common.commands;

import com.bainhero.OlympiansMod.util.PlayerEventHandler;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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

public class SetCommand {
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> checkCommand 
			= Commands.literal("set")
			.requires((commandSource) -> commandSource.hasPermission(2))
			.then(Commands.literal("karma")
				.then(Commands.argument("value", IntegerArgumentType.integer())
					.then(Commands.argument("player", EntityArgument.player())
							.executes(commandContext -> {
								int value = IntegerArgumentType.getInteger(commandContext, "value");
								PlayerEntity player = (PlayerEntity) EntityArgument.getPlayer(commandContext, "player");
								karma(commandContext, player, value);
								return 1;
							})
					)
					.executes(commandContext -> karma(commandContext, null, IntegerArgumentType.getInteger(commandContext, "value")))
				))
			.then(Commands.literal("demigod")
				.then(Commands.argument("boolean", BoolArgumentType.bool())
					.then(Commands.argument("player", EntityArgument.player())
							.executes(commandContext -> {
								boolean isDemigod = BoolArgumentType.getBool(commandContext, "boolean");
								PlayerEntity player = (PlayerEntity) EntityArgument.getPlayer(commandContext, "player");
								demigod(commandContext, player, isDemigod);
								return 1;
							})
					)
					.executes(commandContext -> demigod(commandContext, null, BoolArgumentType.getBool(commandContext, "boolean")))
				))
			.executes(null);
		
		dispatcher.register(checkCommand);
	}
	
static int karma(CommandContext<CommandSource> commandContext, PlayerEntity player, int value) throws CommandSyntaxException {
		
		Entity entity = commandContext.getSource().getEntity();
		
		if (player != null) {
			PlayerEventHandler.setKarma(player, value);
			
			if (entity instanceof PlayerEntity) {
				
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Karma is now set to %d.", value)), ChatType.CHAT, entity.getUUID());
			} else {
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Karma is now set to %d.", value)), ChatType.SYSTEM, Util.NIL_UUID);
			}
		} else {
			if (entity instanceof PlayerEntity) {
				PlayerEntity pEntity = (PlayerEntity) entity;
				
				PlayerEventHandler.setKarma(pEntity, value);
				
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Karma is now set to %d.", value)), ChatType.CHAT, entity.getUUID());
			} else {
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent("Please specify player to set."), ChatType.SYSTEM, Util.NIL_UUID);
			}
		}
		
		return 1;
	}
	
	static int demigod(CommandContext<CommandSource> commandContext, PlayerEntity player, boolean demigod) throws CommandSyntaxException {
		
		Entity entity = commandContext.getSource().getEntity();
		
		if (player != null) {
			PlayerEventHandler.setDemigod(player, demigod);
			
			if (entity instanceof PlayerEntity) {
				
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Demigod status has been updated to %s.", demigod)), ChatType.CHAT, entity.getUUID());
			} else {
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Demigod status has been updated to %s.", demigod)), ChatType.SYSTEM, Util.NIL_UUID);
			}
		} else {
			if (entity instanceof PlayerEntity) {
				PlayerEntity pEntity = (PlayerEntity) entity;
				
				PlayerEventHandler.setDemigod(pEntity, demigod);
				
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent(String.format("Demigod status has been updated to %s.", demigod)), ChatType.CHAT, entity.getUUID());
			} else {
				commandContext.getSource().getServer().getPlayerList()
				.broadcastMessage(new StringTextComponent("Please specify player to set."), ChatType.SYSTEM, Util.NIL_UUID);
			}
		}
		
		return 1;
	}
}
