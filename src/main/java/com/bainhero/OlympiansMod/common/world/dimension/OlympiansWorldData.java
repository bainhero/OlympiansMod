package com.bainhero.OlympiansMod.common.world.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bainhero.OlympiansMod.OlympiansMod;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.server.TicketType;
import net.minecraft.world.storage.WorldSavedData;


/*
 *  This code was adapted from TelepathicGrunt as a learning resource. The following github is the original source code:
 *  
 *  https://github.com/TelepathicGrunt/UltraAmplifiedDimension-Forge/blob/latest-released/src/main/java/com/telepathicgrunt/ultraamplifieddimension/dimension/UADWorldSavedData.java
 */
public class OlympiansWorldData extends WorldSavedData {

	    public static final String DATA_KEY = OlympiansMod.MOD_ID + ":delayed_teleportation";
	    private List<TeleportEntry> teleportingEntities = new ArrayList<>();

	    public OlympiansWorldData() {
	        super(DATA_KEY);
	    }

	    public static OlympiansWorldData get(ServerWorld world) {
	        return world.getDataStorage().computeIfAbsent(OlympiansWorldData::new, DATA_KEY);
	    }

	    public static void tick(ServerWorld world) {
	        MinecraftServer server = world.getServer();
	        OlympiansWorldData data = get(world);

	        List<TeleportEntry> entityList = data.teleportingEntities;
	        data.teleportingEntities = new ArrayList<>();

	        for (TeleportEntry entry : entityList) {
	            ServerPlayerEntity player = server.getPlayerList().getPlayer(entry.playerUUID);
	            ServerWorld targetWorld = server.getLevel(entry.targetWorld);
	            if (player != null && targetWorld != null && player.level == world) {
	                ChunkPos playerChunkPos = new ChunkPos(player.blockPosition());
	                targetWorld.getChunkSource().registerTickingTicket(TicketType.POST_TELEPORT, playerChunkPos, 1, player.getId());

	                player.fallDistance = 0;
	                player.yOld = 0;
	                player.teleportTo(
	                        targetWorld,
	                        entry.targetVec.x(),
	                        entry.targetVec.y() + 0.2D,
	                        entry.targetVec.z(),
	                        entry.targetLook.getFirst(),
	                        entry.targetLook.getSecond());
	            }
	        }
	    }

	    public void addPlayer(PlayerEntity player, RegistryKey<World> destination, Vector3d targetVec, Pair<Float, Float> targetLook) {
	        this.teleportingEntities.add(new TeleportEntry(PlayerEntity.createPlayerUUID(player.getGameProfile()), destination, targetVec, targetLook));
	    }

	    @Override
	    public void load(CompoundNBT nbt) {
	    }

	    @Override
	    public CompoundNBT save(CompoundNBT compound) {
	        return compound;
	    }

	    static class TeleportEntry {
	        final UUID playerUUID;
	        final RegistryKey<World> targetWorld;
	        final Vector3d targetVec;
	        final Pair<Float, Float> targetLook;

	        public TeleportEntry(UUID playerUUID, RegistryKey<World> targetWorld, Vector3d targetVec, Pair<Float, Float> targetLook) {
	            this.playerUUID = playerUUID;
	            this.targetWorld = targetWorld;
	            this.targetVec = targetVec;
	            this.targetLook = targetLook;
	        }
	    }
	}
