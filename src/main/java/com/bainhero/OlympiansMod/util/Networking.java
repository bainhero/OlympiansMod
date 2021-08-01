package com.bainhero.OlympiansMod.util;

import com.bainhero.OlympiansMod.util.packets.OlympiansModPacketHandler;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class Networking {
	public static SimpleChannel INSTANCE;
	
	private static int ID = 0;
	
	private static int nextID() {
        return ID++;
    }
	
	public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation("olympiansmod" + "demigod"),
                () -> "1.0", s -> true, s -> true);    
        INSTANCE.messageBuilder(OlympiansModPacketHandler.class, nextID())
        .encoder(OlympiansModPacketHandler::toBytes)
        .decoder(OlympiansModPacketHandler::new)
        .consumer(OlympiansModPacketHandler::handle)
        .add();
	}
	
	public static void sendToServer(Object packet, SimpleChannel instance) {
        instance.sendToServer(packet);
    }

    public static void sendToClient(Object packet, ServerPlayerEntity player) {
        INSTANCE.sendTo(packet, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
}
