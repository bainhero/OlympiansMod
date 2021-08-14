package com.bainhero.OlympiansMod.common.world.dimension;

import com.bainhero.OlympiansMod.OlympiansMod;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;

public class FOADimension {	
    public static final RegistryKey<World> FOA_KEY = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(OlympiansMod.MOD_ID, "fields_of_asphodel"));
	
    public static void setupDimension() {
        
    }
    
    public static void worldTick(TickEvent.WorldTickEvent event){
        if(event.phase == TickEvent.Phase.END && !event.world.isClientSide()){
            OlympiansWorldData.tick((ServerWorld) event.world);
        }
    }
}
