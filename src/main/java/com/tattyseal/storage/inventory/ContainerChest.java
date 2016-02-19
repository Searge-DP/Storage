package com.tattyseal.storage.inventory;

import com.tattyseal.storage.tileentity.TileEntityChest;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class ContainerChest extends Container
{
	public TileEntityChest chest;
	public BlockPos pos;
	public EntityPlayer player;
	
	public int xSize;
	public int ySize;
	
	public int lastId;
	
	public ContainerChest(EntityPlayer player, TileEntityChest chest, BlockPos pos) 
	{
		super();
		this.chest = chest;
		this.pos = pos;
		this.player = player;
		
		int invX = 9;
		int invY = 3;
		
        this.xSize = 7 + (invX < 9 ? (9 * 18) : (invX * 18)) + 7;
        this.ySize = 15 + (invY * 18) + 13 + 54 + 4 + 18 + 7;
        
		int slotX = (xSize / 2) - (invX * 18 / 2) + 1; 
        int slotY = 18; //(ySize / 2) - ((invY * 18) / 2);
        
		boolean done = !(chest.getStackInSlot(0) == null);

        int lastId = 1;
        
        if(done)
        {
        	for(int y = 0; y < invY; y++)
            {
            	for(int x = 0; x < invX; x++)
                {
                    Slot slot = new Slot(chest, lastId, slotX + (x * 18), slotY + (y * 18));
                    addSlotToContainer(slot);
                    lastId++;
                }
            }
        }
        
        int ssdx = done ? -27 + 10 : ((xSize / 2) - 8);
        int ssdy = done ? 19 + (18 * 2) : (((15 + (3 * 18) + 13) / 2) - 8);
        
        Slot ssdSlot = new Slot(chest, 0, ssdx, ssdy);
        addSlotToContainer(ssdSlot);
        
        this.lastId = lastId;

        slotX = (xSize / 2) - ((9 * 18) / 2) + 1;
        slotY = slotY + (invY * 18) + 13;

        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 3; y++)
            {
            	Slot slot = new Slot(player.inventory, x + y * 9 + 9, slotX + (x * 18), slotY + (y * 18));
                addSlotToContainer(slot);
            }
        }

        slotY = slotY + (3 * 18) + 4;

        for(int x = 0; x < 9; x++)
        {
        	boolean immovable = false;
        	
        	Slot slot = new Slot(player.inventory, x, slotX + (x * 18), slotY);
            addSlotToContainer(slot);
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return true;
	}
}
