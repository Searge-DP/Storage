package com.tattyseal.storage.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class TileEntityChest extends TileEntity implements IInventory
{
	public ItemStack[] items;
	
	public TileEntityChest()
	{
		super();
		items = new ItemStack[getSizeInventory()];
	}
	
	@Override
	public String getName() 
	{
		return "chest";
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

	@Override
	public IChatComponent getDisplayName() 
	{
		return new ChatComponentText("Chest");
	}

	@Override
	public int getSizeInventory() 
	{
		return 9 * 3 + 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) 
	{
		return items[index];
	}

	@Override
    public ItemStack decrStackSize(int slot, int amount)
    {
    	ItemStack stack = getStackInSlot(slot);

        if(stack != null)
        {
            if(stack.stackSize <= amount)
            {
                setInventorySlotContents(slot, null);
                markDirty();

                return stack.copy();
            }
            else
            {
                ItemStack stack2 = stack.splitStack(amount);
                markDirty();

                return stack2.copy();
            }
        }

        return stack;
    }

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		if(index < getSizeInventory())
		{
			items[index] = null;
		}
		
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		if(index < getSizeInventory())
		{
			items[index] = stack;
		}
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) 
	{
		
	}

	@Override
	public void closeInventory(EntityPlayer player) 
	{
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return true;
	}

	@Override
	public int getField(int id) 
	{
		return 0;
	}

	@Override
	public void setField(int id, int value) 
	{
		
	}

	@Override
	public int getFieldCount() 
	{
		return 0;
	}

	@Override
	public void clear() 
	{
		items = new ItemStack[getSizeInventory()];
	}

}
