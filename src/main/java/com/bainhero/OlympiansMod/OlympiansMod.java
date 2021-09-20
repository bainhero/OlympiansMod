package com.bainhero.OlympiansMod;

import com.bainhero.OlympiansMod.common.OlympianList;
import com.bainhero.OlympiansMod.common.commands.RegisterCommandEvent;
import com.bainhero.OlympiansMod.common.world.dimension.ElysiumDimension;
import com.bainhero.OlympiansMod.common.world.dimension.FOADimension;
import com.bainhero.OlympiansMod.common.world.dimension.FOPDimension;
import com.bainhero.OlympiansMod.common.world.dimension.UnderworldDimension;
import com.bainhero.OlympiansMod.core.init.BlockInit;
import com.bainhero.OlympiansMod.core.init.RegistryInit;
import com.bainhero.OlympiansMod.util.Networking;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(OlympiansMod.MOD_ID)
public class OlympiansMod
{
	
	public static final String MOD_ID = "olympiansmod";
	public static final ItemGroup OLYMPIANS_GROUP = new OlympiansGroup("olympians");

    public OlympiansMod() {
    	
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    	IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
        
        RegistryInit.ITEMS.register(bus);
        RegistryInit.EFFECTS.register(bus);
        BlockInit.BLOCKS.register(bus);
        
        forgeBus.addListener(EventPriority.NORMAL, UnderworldDimension::worldTick);
        forgeBus.addListener(EventPriority.NORMAL, FOADimension::worldTick);
        forgeBus.addListener(EventPriority.NORMAL, FOPDimension::worldTick);
        forgeBus.addListener(EventPriority.NORMAL, ElysiumDimension::worldTick);

        forgeBus.register(this);
        
        OlympianList.generateMap();
    }

	private void setup(final FMLCommonSetupEvent event)
    {
    	Networking.registerMessages();
    	
    	event.enqueueWork(() ->
		{
			UnderworldDimension.setupDimension();
			FOADimension.setupDimension();
			FOPDimension.setupDimension();
			ElysiumDimension.setupDimension();
		});
    	
    	MinecraftForge.EVENT_BUS.register(RegisterCommandEvent.class);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        
    }
    
    public static class OlympiansGroup extends ItemGroup {

		public OlympiansGroup(String label) {
			super(label);
		}

		@Override
		public ItemStack makeIcon() {
			return RegistryInit.NECTAR.get().getDefaultInstance();
		}
    	
    }
}
