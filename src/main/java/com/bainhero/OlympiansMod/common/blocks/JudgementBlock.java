package com.bainhero.OlympiansMod.common.blocks;

import com.bainhero.OlympiansMod.common.world.dimension.ElysiumDimension;
import com.bainhero.OlympiansMod.common.world.dimension.FOADimension;
import com.bainhero.OlympiansMod.common.world.dimension.FOPDimension;
import com.bainhero.OlympiansMod.util.DimensionChangeHandler;
import com.bainhero.OlympiansMod.util.PlayerEventHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class JudgementBlock extends Block{

	public JudgementBlock(Properties props) {
		super(props);
	}
	
	@Override
	public ActionResultType use(BlockState blockState, World worldIn, BlockPos blockPos,
			PlayerEntity player, Hand handIn, BlockRayTraceResult ray) {
		
		int karma = PlayerEventHandler.getKarma(player);
		RegistryKey<World> foa = FOADimension.FOA_KEY;
		RegistryKey<World> fop = FOPDimension.FOP_KEY;
		RegistryKey<World> elysium = ElysiumDimension.ELYSIUM_KEY;
		MinecraftServer mcserver = player.getServer();
		ServerWorld destination;
		BlockPos location;
		
		
		if (karma < -10) {
			if (!player.level.isClientSide() && player.canChangeDimensions() && player instanceof ServerPlayerEntity) {
				ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
				destination = mcserver.getLevel(fop);
				location = DimensionChangeHandler.checkPos(serverPlayer, destination);
				serverPlayer.teleportTo(destination, location.getX(), location.getY(), location.getZ(), player.getRotationVector().x, player.getRotationVector().y);
			}
		} else if (karma > 100) {
			if (!player.level.isClientSide() && player.canChangeDimensions() && player instanceof ServerPlayerEntity) {
				ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
				destination = mcserver.getLevel(elysium);
				location = DimensionChangeHandler.checkPos(serverPlayer, destination);
				serverPlayer.teleportTo(destination, location.getX(), location.getY(), location.getZ(), player.getRotationVector().x, player.getRotationVector().y);
			}
		} else {
			if (!player.level.isClientSide() && player.canChangeDimensions() && player instanceof ServerPlayerEntity) {
				ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
				destination = mcserver.getLevel(foa);
				location = DimensionChangeHandler.checkPos(serverPlayer, destination);
				serverPlayer.teleportTo(destination, location.getX(), location.getY(), location.getZ(), player.getRotationVector().x, player.getRotationVector().y);
			}
		}
		
		return ActionResultType.SUCCESS;
	}
}
