package com.bainhero.OlympiansMod.common.commands;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegisterCommandEvent {
	
	@SubscribeEvent
	public static void onReigsterCommandsEvent(RegisterCommandsEvent event) {
		CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
		
		RebirthCommand.register(dispatcher);
		CheckCommand.register(dispatcher);
		SetCommand.register(dispatcher);
	}
	
}
