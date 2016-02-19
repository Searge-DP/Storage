package com.tattyseal.storage.client.handler;

import com.tattyseal.storage.client.gui.GuiChest;
import com.tattyseal.storage.client.gui.GuiFabricator;
import com.tattyseal.storage.inventory.ContainerChest;
import com.tattyseal.storage.inventory.ContainerFabricator;
import com.tattyseal.storage.tileentity.TileEntityChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		BlockPos pos = new BlockPos(x, y, z);
		
		switch(ID)
		{
			case 0:
			{
				return new ContainerChest(player, (TileEntityChest) world.getTileEntity(pos), pos);
			}
			case 1:
			{
				return new ContainerFabricator(world, player, pos);
			}
			default:
			{
				return null;
			}
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		BlockPos pos = new BlockPos(x, y, z);
		
		switch(ID)
		{
			case 0:
			{
				return new GuiChest((Container) getServerGuiElement(ID, player, world, x, y, z), player, (TileEntityChest) world.getTileEntity(pos), pos);
			}
			case 1:
			{
				return new GuiFabricator((Container) getServerGuiElement(ID, player, world, x, y, z), world, player, pos);
			}
			default:
			{
				return null;
			}
		}
	}
}
