package com.tattyseal.storage;

import com.tattyseal.storage.block.BlockChest;
import com.tattyseal.storage.block.BlockFabricator;
import com.tattyseal.storage.client.handler.GuiHandler;
import com.tattyseal.storage.tileentity.TileEntityChest;
import com.tattyseal.storage.tileentity.TileEntityFabricator;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "tattystorage", version = "0.0.1", name = "Storage")
public class Storage 
{
	public static Block chest;
	public static Block fabricator;

	@Instance("tattystorage")
	public static Storage instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		chest = new BlockChest();
		fabricator = new BlockFabricator();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		GameRegistry.registerBlock(chest, "chest");
		GameRegistry.registerBlock(fabricator, "fabricator");

		GameRegistry.registerTileEntity(TileEntityChest.class, "chest");
		GameRegistry.registerTileEntity(TileEntityFabricator.class, "fabricator");
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(fabricator), 0, new ModelResourceLocation("tattystorage:fabricator", "inventory"));
		
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}