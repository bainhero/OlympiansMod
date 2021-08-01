package com.bainhero.OlympiansMod.common.items;

import com.bainhero.OlympiansMod.util.Networking;
import com.bainhero.OlympiansMod.util.packets.OlympiansModPacketHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TempItem extends Item {

	public TempItem(Properties p_i48487_1_) {
		super(p_i48487_1_);
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		if(!worldIn.isClientSide()) {
			Networking.sendToServer(new OlympiansModPacketHandler(1), Networking.INSTANCE);
		}
		return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getItemInHand(handIn));
	}
}
