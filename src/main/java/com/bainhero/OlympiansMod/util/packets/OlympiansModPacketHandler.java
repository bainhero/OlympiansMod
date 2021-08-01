package com.bainhero.OlympiansMod.util.packets;

import java.util.function.Supplier;

import com.bainhero.OlympiansMod.util.PlayerEventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class OlympiansModPacketHandler {
	private final int id;
    public OlympiansModPacketHandler(PacketBuffer buf) {
        id = buf.readInt();
    }
    public OlympiansModPacketHandler(int id) {
        this.id = id;
    }
    public void toBytes(PacketBuffer buf) {
        buf.writeInt(id);    
    }

    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
        	
        		final PlayerEntity player = (PlayerEntity)ctx.get().getSender();
        		
        		if(!player.level.isClientSide()) {
        			if(!PlayerEventHandler.checkDemigod(player)) {
        				PlayerEventHandler.setDemigod(player, true);
        			} else {
        				PlayerEventHandler.setDemigod(player, false);
        			}

        		}
        });
        return true;
    }
}
