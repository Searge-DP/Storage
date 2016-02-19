package com.tattyseal.storage.client.gui;

import org.lwjgl.opengl.GL11;

import com.tattyseal.storage.tileentity.TileEntityChest;
import com.tattyseal.storage.util.RenderUtil;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;

public class GuiChest extends GuiContainer
{
	public TileEntityChest chest;
	public BlockPos pos;
	public EntityPlayer player;
	
	public boolean done;
	
	public GuiChest(Container container, EntityPlayer player, TileEntityChest chest, BlockPos pos) 
	{
		super(container);
		this.chest = chest;
		this.pos = pos;
		this.player = player;
		done = !(chest.getStackInSlot(0) == null);
		
        this.xSize = 7 + (Math.max(9, 9) * 18) + 7;
        this.ySize = 15 + (3 * 18) + 13 + 54 + 4 + 18 + 7;
	}
	
	@Override
	public void updateScreen() 
	{
		super.updateScreen();
		
		if((chest.getStackInSlot(0) == null) == done)
		{
			player.addChatComponentMessage(new ChatComponentText("Chest has new SSD. Please reopen!"));
			onGuiClosed();
			this.mc.displayGuiScreen(null);
		}
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		RenderUtil.renderChestBackground(this, guiLeft, guiTop, 9, 3);
		
		int invX = 9;
		int invY = 3;
		
		if(done)
		{
			RenderUtil.renderTabs(this, 3, guiLeft - 27, guiTop + 15 + ((2 * 18) - 3));
			
			RenderUtil.renderSlots(guiLeft + 7 + ((Math.max(9, invX) * 18) / 2) - (invX * 18) / 2, guiTop + 17, invX, invY);
		}

        RenderUtil.renderSlots(guiLeft + 7 + (((Math.max(9, invX) * 18) / 2) - ((9 * 18) / 2)), guiTop + 17 + (invY * 18) + 13, 9, 3);
        RenderUtil.renderSlots(guiLeft + 7 + (((Math.max(9, invX) * 18) / 2) - ((9 * 18) / 2)), guiTop + 17 + (invY * 18) + 13 + 54 + 4, 9, 1);

        int ssdx = guiLeft + (done ? -27 + 9 : ((xSize / 2) - 9));
        int ssdy = guiTop + (done ? 18 + (18 * 2) : (((15 + (3 * 18) + 13) / 2) - 9));
        
        RenderUtil.renderSlots(ssdx, ssdy, 1, 1);
	}
	
	@Override
	public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);

		int invX = 9;
		int invY = 3;
		
        mc.fontRendererObj.drawString("Chest", 8, 6, 4210752);
        mc.fontRendererObj.drawString("Inventory", 8, 15 + (invY * 18) + 5, 4210752);
	}
	
	public int getXSize()
	{
		return xSize;
	}
	
	public int getYSize()
	{
		return ySize;
	}
}
