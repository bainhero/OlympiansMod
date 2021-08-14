package com.bainhero.OlympiansMod.util;

import com.bainhero.OlympiansMod.OlympiansMod;
import com.bainhero.OlympiansMod.common.effects.OEffectList;
import com.bainhero.OlympiansMod.common.items.CelestialBronzeBlockItem;
import com.bainhero.OlympiansMod.common.items.CelestialBronzeItem;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=OlympiansMod.MOD_ID)
public class PlayerEventHandler {
	
	private static final String NBT_DEMIGOD_KEY = "olympiansmod.demigod";
	private static final String NBT_KARMA_KEY = "olympiansmod.karma";
	
	@SubscribeEvent
	public static void onPlayerJoinEvent(PlayerEvent.PlayerLoggedInEvent event) {
		double randNum = Math.random();
		PlayerEntity player = event.getPlayer();
		if (player instanceof ServerPlayerEntity) {
			CompoundNBT data = player.getPersistentData();
			CompoundNBT persistent;
			
			if (!data.contains(PlayerEntity.PERSISTED_NBT_TAG)) {
				data.put(PlayerEntity.PERSISTED_NBT_TAG, (persistent = new CompoundNBT()));
			} else {
				persistent = data.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
			}
			
			if (!persistent.contains(NBT_DEMIGOD_KEY) && randNum <= 0.9d) {
				persistent.putBoolean(NBT_DEMIGOD_KEY, false);
				System.out.println("Not a demigod.");
			} else if (!persistent.contains(NBT_DEMIGOD_KEY) && randNum > 0.9d) {
				persistent.putBoolean(NBT_DEMIGOD_KEY, true);
				System.out.println("Demigod.");
			}
			
			if (!persistent.contains(NBT_KARMA_KEY)) {
				persistent.putInt(NBT_KARMA_KEY, 0);
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
	
	@SubscribeEvent
	public static void onLivingDeathEvent(LivingDeathEvent event) {
		DamageSource source = event.getSource();
		LivingEntity entity = event.getEntityLiving();
		
		if (source.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)source.getEntity();
			if (!(entity instanceof MonsterEntity)) {
				if (entity.isBaby()) {
					addKarma(player, -2);
				} else {
					addKarma(player, -1);
				}
			} else {
				if (entity instanceof WitherEntity || entity instanceof EnderDragonEntity) {
					addKarma(player, 8);
				} else if (entity instanceof ElderGuardianEntity){
					addKarma(player, 2);
				} else {
					addKarma(player, 1);
				}
			}
		}
	}
	
	// Utility Functions
	
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
	public static void setKarma(PlayerEntity player, int val) {
		final CompoundNBT data = player.getPersistentData().getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		data.putInt(NBT_KARMA_KEY, val);
	}
	public static int getKarma(PlayerEntity player) {
		final CompoundNBT data = player.getPersistentData().getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		return data.getInt(NBT_KARMA_KEY);
	}
	public static void addKarma(PlayerEntity player, int addVal) {
		final CompoundNBT data = player.getPersistentData().getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		int currentKarma = data.getInt(NBT_KARMA_KEY);
		data.putInt(NBT_KARMA_KEY, (currentKarma+addVal));
	}
}
