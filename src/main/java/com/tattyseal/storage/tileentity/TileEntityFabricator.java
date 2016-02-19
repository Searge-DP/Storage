package com.tattyseal.storage.tileentity;

import com.tattyseal.storage.util.StorageInfo;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.Loader;

public class TileEntityFabricator extends TileEntity implements IInventory, ITickable
{
	public StorageInfo info;
	public int dimension;
	
	public boolean init;
	
	public ItemStack[] items;
	
	public int mode;
	public String player;

	public StorageInfo.Type type;

	public TileEntityFabricator()
	{
		init = false;
		info = new StorageInfo(9, 3);
		items = new ItemStack[getSizeInventory()];
		type = StorageInfo.Type.CHEST;
	}
	
	/*@Override
	public void writeToNBT(NBTTagCompound tag) 
	{
		super.writeToNBT(tag);
		
		tag.setInteger("infoX", info.getSizeX());
		tag.setInteger("infoY", info.getSizeY());
		
		tag.setInteger("mode", mode);
		tag.setString("player", player);
		
		NBTTagList nbtTagList = new NBTTagList();
        for(int slot = 0; slot < getSizeInventory(); slot++)
        {
            if(items[slot] != null)
            {
                NBTTagCompound item = new NBTTagCompound();
                item.setInteger("Slot", slot);
                items[slot].writeToNBT(item);
                nbtTagList.appendTag(item);
            }
        }

        tag.setTag("Items", nbtTagList);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) 
	{
		super.readFromNBT(tag);
		
		this.info = new StorageInfo(tag.getInteger("infoX"), tag.getInteger("infoY"));
		
		this.mode = tag.getInteger("mode");
		this.player = tag.getString("player");
		
		NBTTagList nbtTagList = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        items = new ItemStack[getSizeInventory()];

        for(int slot = 0; slot < getSizeInventory(); slot++)
        {
            NBTTagCompound item = nbtTagList.getCompoundTagAt(slot);
            items[slot] = ItemStack.loadItemStackFromNBT(item);
        }
        
        markDirty();
	}*/
	
	@Override
	public void update() 
	{
		if(!init)
		{
			dimension = getWorld().provider.getDimensionId();
			
			init = true;
		}
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		
		return new S35PacketUpdateTileEntity(getPos(), 1, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) 
	{
		super.onDataPacket(net, pkt);
		readFromNBT(pkt.getNbtCompound());
	}

    /* INVENTORY START */
    
	@Override
	public int getSizeInventory() 
	{
		return 4;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return items[slot];
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
            }
            else
            {
                ItemStack stack2 = stack.splitStack(amount);
                markDirty();
                
                return stack2;
            }
        }

        return stack;
    }

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) 
	{
		items[slot] = stack;
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
	public boolean isItemValidForSlot(int slot, ItemStack stack) 
	{
		if(info != null)
		{
			return stack.getItem().equals(info.getMaterialCost(type).get(slot).getItem());
		}
		
		return false;
	}

	@Override
	public String getName() 
	{
		return "fabricator";
	}

	@Override
	public boolean hasCustomName() 
	{
		return false;
	}

	@Override
	public IChatComponent getDisplayName() 
	{
		return new ChatComponentText(getName());
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
	public void openInventory(EntityPlayer player) 
	{
		
	}

	@Override
	public void closeInventory(EntityPlayer player) 
	{
		
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
