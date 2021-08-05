package com.bainhero.OlympiansMod.util;

import com.bainhero.OlympiansMod.OlympiansMod;
import com.bainhero.OlympiansMod.common.effects.OEffectList;
import com.bainhero.OlympiansMod.common.items.CelestialBronzeBlockItem;
import com.bainhero.OlympiansMod.common.items.CelestialBronzeItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=OlympiansMod.MOD_ID)
public class PlayerEventHandler {
	
	private static final String NBT_DEMIGOD_KEY = "olympiansmod.demigod";
	
	@SubscribeEvent
	public static void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
		
		PlayerEntity player = event.getPlayer();
		if (player instanceof ServerPlayerEntity) {
			CompoundNBT data = player.getPersistentData();
			CompoundNBT persistent;
			
			if (!data.contains(PlayerEntity.PERSISTED_NBT_TAG)) {
				data.put(PlayerEntity.PERSISTED_NBT_TAG, (persistent = new CompoundNBT()));
			} else {
				persistent = data.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
			}
			
			if (!persistent.contains(NBT_DEMIGOD_KEY)) {
				persistent.putBoolean(NBT_DEMIGOD_KEY, false);
				
				player.sendMessage(new StringTextComponent("Welcome, " + 
						player.getDisplayName().getString()), player.getUUID());
			}
		}
	}
	
	@SubscribeEvent
	public static void onLivingTickEvent(TickEvent.PlayerTickEvent event) {
		
		PlayerEntity player = event.player;
		if(!player.level.isClientSide()) {
			int amp = 0;
			EffectInstance olympian = new EffectInstance(OEffectList.OLYMPIAN);
			for(EffectInstance e : player.getActiveEffects()) {
				if(e.getEffect().equals(olympian.getEffect())) {
					amp = e.getAmplifier();
				}
			}
			if(amp == 3) {
				event.player.hurt(DamageSource.MAGIC, 1f);
			} else if(amp == 4) {
				event.player.hurt(DamageSource.MAGIC, player.getMaxHealth());
			}
			
			final ItemStack itemStack = inventoryContainsCB(player.inventory);
        	
        	if(itemStack != null && !checkDemigod(player) && !player.isCreative()) {
        		player.drop(itemStack, false, true);
        		player.inventory.removeItem(itemStack);
        	}
		}
	}
	
	public static boolean checkDemigod(PlayerEntity player) {
		return player.getPersistentData().getCompound(PlayerEntity.PERSISTED_NBT_TAG).getBoolean(NBT_DEMIGOD_KEY);
	}
	
	public static void setDemigod(PlayerEntity player, boolean isDemigod) {
		final CompoundNBT persistent = player.getPersistentData().getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		persistent.putBoolean(NBT_DEMIGOD_KEY, isDemigod);
		return;
	}
	
	public static ItemStack inventoryContainsCB(PlayerInventory inventory) {
		ItemStack itemstack = null;
		for (ItemStack s : inventory.items) {
			if (s != null && (s.getItem() instanceof CelestialBronzeItem || s.getItem() instanceof CelestialBronzeBlockItem)) {
				itemstack = s;
				break;
			}
		}
		return itemstack;
	}
}
