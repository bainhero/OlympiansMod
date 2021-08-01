package com.bainhero.OlympiansMod;

import com.bainhero.OlympiansMod.core.init.BlockInit;
import com.bainhero.OlympiansMod.core.init.RegistryInit;
import com.bainhero.OlympiansMod.util.Networking;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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

        bus.addListener(this::setup);
        bus.addListener(this::doClientStuff);
        
        RegistryInit.ITEMS.register(bus);
        RegistryInit.EFFECTS.register(bus);
        BlockInit.BLOCKS.register(bus);


        MinecraftForge.EVENT_BUS.register(this);
    }

	private void setup(final FMLCommonSetupEvent event)
    {
    	Networking.registerMessages();
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
