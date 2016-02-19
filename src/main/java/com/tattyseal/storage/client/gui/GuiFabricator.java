package com.tattyseal.storage.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.tattyseal.storage.tileentity.TileEntityFabricator;
import com.tattyseal.storage.util.RenderUtil;
import com.tattyseal.storage.util.StorageInfo;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by Toby on 09/11/2014.
 */
public class GuiFabricator extends GuiContainer
{
    public World world;
    public EntityPlayer player;
    public BlockPos pos;
    
    public GuiButton buttonAddX;
    public GuiButton buttonMinusX;

    public GuiButton buttonAddY;
    public GuiButton buttonMinusY;
    
    public GuiButton buttonSubmit;
    public GuiButton buttonChangeType;

    public GuiTextField colorField;
    
    public TileEntityFabricator builder;

    public static final int[] allowed = new int[]
    {
        Keyboard.KEY_0,
        Keyboard.KEY_1,
        Keyboard.KEY_2,
        Keyboard.KEY_3,
        Keyboard.KEY_4,
        Keyboard.KEY_5,
        Keyboard.KEY_6,
        Keyboard.KEY_7,
        Keyboard.KEY_8,
        Keyboard.KEY_9,
        Keyboard.KEY_A,
        Keyboard.KEY_B,
        Keyboard.KEY_C,
        Keyboard.KEY_D,
        Keyboard.KEY_E,
        Keyboard.KEY_F,
        Keyboard.KEY_BACK,
        Keyboard.KEY_LEFT,
        Keyboard.KEY_RIGHT
    };
    
    public GuiFabricator(Container container, World world, EntityPlayer player, BlockPos pos)
    {
        super(container);

        this.world = world;
        this.player = player;
        this.pos = pos;
        
        this.builder = ((TileEntityFabricator) world.getTileEntity(pos));

        this.xSize = 7 + 162 + 7;
        this.ySize = 7 + 108 + 13 + 54 + 4 + 18 + 7;
    }
    
    @Override
    public void initGui()
    {
        super.initGui();
                
        /*buttonAddX = new GuiButton(0, guiLeft + 7 + getFontRenderer().getStringWidth("XX Rows --"), guiTop + 62, 20, 20, "+");
        buttonList.add(buttonAddX);
        
        buttonMinusX = new GuiButton(1, guiLeft + 7 + getFontRenderer().getStringWidth("XX Rows --") + 21, guiTop + 62, 20, 20, "-");
        buttonList.add(buttonMinusX);
        
        buttonAddY = new GuiButton(2, guiLeft + 7 + getFontRenderer().getStringWidth("XX Columns ") - 3, guiTop + 62 + 21, 20, 20, "+");
        buttonList.add(buttonAddY);
        
        buttonMinusY = new GuiButton(3, guiLeft + 7 + getFontRenderer().getStringWidth("XX Columns ") - 3 + 21, guiTop + 62 + 21, 20, 20, "-");
        buttonList.add(buttonMinusY);
        
        buttonSubmit = new GuiButton(4, guiLeft + 162 - 93, guiTop + 8 + 108 - 10, 100, 20, "Build Chest");
        buttonList.add(buttonSubmit);

        buttonChangeType = new GuiButton(5, guiLeft + xSize - 7 - 60, guiTop + 8 + 108 - 10 - 21, 60, 20, "Type");
        buttonList.add(buttonChangeType);

        colorField = new GuiTextField(0, getFontRenderer(), guiLeft + xSize - 7 - 52, guiTop + 7 + 35, 50, 20);
        colorField.setText("FFFFFF");
        colorField.setMaxStringLength(6);*/
    }

    public List<Integer> getAllowed()
    {
        List<Integer> intList = new ArrayList<Integer>(allowed.length);

        for (int i = 0; i< allowed.length; i++)
        {
            intList.add(allowed[i]);
        }

        return intList;
    }

    @Override
    protected void keyTyped(char key, int keyid)
    {
        /*if(colorField.isFocused() && getAllowed().contains(keyid) && !GuiScreen.isShiftKeyDown())
        {
            colorField.textboxKeyTyped(key, keyid);
        }

        if(!colorField.isFocused())
        {
            try 
            {
				super.keyTyped(key, keyid);
			} 
            catch (IOException e)
            {
				e.printStackTrace();
			}
        }*/
    }

    @Override
    public void mouseClicked(int x, int y, int b)
    {
        try 
        {
			super.mouseClicked(x, y, b);
		} 
        catch (IOException e) 
        {
			e.printStackTrace();
		}
        
        /*colorField.mouseClicked(x, y, b);

        if(x > guiLeft + xSize - 7 - 34 - 9 && x < guiLeft + xSize - 7 - 9)
        {
            if(y >  guiTop + 7 && y <  guiTop + 7 + 34)
            {
                int mouseX = x - (guiLeft + xSize - 7 - 34 - 9);
                int mouseY = y - (guiTop + 7);

                System.out.println(mouseX + ":" + mouseY);

                if(mouseX < 16 && mouseY < 16)
                {
                    //RED
                    colorField.setText("993431");
                }
                else if(mouseX < 16 && mouseY > 16)
                {
                    //GREEN
                    colorField.setText("49BD3D");
                }
                else if(mouseX > 16 && mouseY < 16)
                {
                    //BLUE
                    colorField.setText("323E9A");
                }
                else if(mouseX > 16 && mouseY > 16)
                {
                    //PINK
                    colorField.setText("D898A9");
                }
            }
        }*/
    }

    @Override
    public void drawGuiContainerForegroundLayer(int arg0, int arg1) 
    {
    	
    }
    
    @Override
    public void drawScreen(int i, int j, float k)
    {
    	super.drawScreen(i, j, k);
    	
    	/*int mouseX = i;
    	int mouseY = j;
    	
    	if(builder != null && builder.info != null)
    	{
    		for(int x = 0; x < 4; x++)
            {
                if(x < builder.info.getMaterialCost(builder.type).size() && builder.info.getMaterialCost(builder.type).get(x) != null)
                {
                    ItemStack stack = builder.info.getMaterialCost(builder.type).get(x);

                    int startX = guiLeft + 7 + x * 18 + 1;
                    int startY = guiTop + 18;

                    int endX = startX + 18;
                    int endY = startY + 18;

                    if(mouseX >= startX && mouseX <= endX)
                    {
                        if(mouseY >= startY && mouseY <= endY)
                        {
                            ArrayList<String> toolList = new ArrayList<String>();
                            toolList.add(stack.getDisplayName());
                            toolList.add(EnumChatFormatting.AQUA + "Amount Required: " + stack.stackSize);

                            drawHoveringText(toolList, mouseX, mouseY, getFontRenderer());
                        }
                    }

                    RenderHelper.disableStandardItemLighting();
                }
            }
    	}*/
    }
    
    @Override
    public void updateScreen() 
    {
    	super.updateScreen();
    	
    	/*if(builder != null && builder.info != null)
    	{
    		if(builder.info.getSizeX() >= 24)
        	{
        		buttonAddX.enabled = false;
        		if(builder.info.getSizeX() > 24) builder.info.setSizeX(24);
        	}
        	else
        	{
        		buttonAddX.enabled = true;
        	}
        	
        	if(builder.info.getSizeX() <= 1)
        	{
        		buttonMinusX.enabled = false;
        		if(builder.info.getSizeX() < 1) builder.info.setSizeX(1);
        	}
        	else
        	{
        		buttonMinusX.enabled = true;
        	}
        	
        	if(builder.info.getSizeY() >= 12)
        	{
        		buttonAddY.enabled = false;
        		if(builder.info.getSizeY() > 12) builder.info.setSizeY(12);
        	}
        	else
        	{
        		buttonAddY.enabled = true;
        	}
        	
        	if(builder.info.getSizeY() <= 1)
        	{
        		buttonMinusY.enabled = false;
        		if(builder.info.getSizeY() < 1) builder.info.setSizeY(1);
        	}
        	else
        	{
        		buttonMinusY.enabled = true;
        	}
    	}*/
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float i, int j, int k)
    {    	
        super.drawGuiContainerForegroundLayer(j, k);
        
        RenderHelper.disableStandardItemLighting();
        GL11.glColor3f(1, 1, 1); 
        
    	drawTexturedModalRect(guiLeft, guiTop, 0, 0, 7, 7);

    	RenderUtil.renderBackground(this, guiLeft, guiTop, 162, 14 + 18 + 36);

        int slotX = guiLeft + (xSize / 2) - ((9 * 18) / 2);
        int slotY = guiTop + 7 + 60 + 13;

        RenderUtil.renderSlots(slotX, slotY, 9, 3);

        slotY = slotY + (3 * 18) + 4;

        RenderUtil.renderSlots(slotX, slotY, 9, 1);
        
        slotY = guiTop + 17;
        
        RenderUtil.renderSlots(slotX, slotY, 4, 1);
        
        slotY = guiTop + 7 + (18 * 2);
        
        RenderUtil.renderSlots(slotX, slotY, 4, 1);
        
        fontRendererObj.drawString("Required Materials", guiLeft + 7, guiTop + 7, 0x404040);

        GL11.glColor3f(1, 1, 1);
        
        StorageInfo info = builder.info;
        
        if(info == null)
        {
        	return;
        }

        /*int color = 0xFFFFFF;

        if(!colorField.getText().isEmpty())
        {
            color = Integer.decode("0x" + colorField.getText());
        }*/
    }

    @Override
    public void actionPerformed(GuiButton button) 
    {
    	try 
    	{
			super.actionPerformed(button);
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
    	
        StorageInfo info = new StorageInfo(builder.info.getSizeX(), builder.info.getSizeY());

    	switch(button.id)
    	{
    		case 0:
    		{
    			info.setSizeX(info.getSizeX() + 1);
    			//CompactStorage.instance.wrapper.sendToServer(new C01PacketUpdateBuilder(pos, builder.dimension, info, builder.type));

    			break;
    		}
    		case 1:
    		{
    			info.setSizeX(info.getSizeX() - 1);
    			//CompactStorage.instance.wrapper.sendToServer(new C01PacketUpdateBuilder(pos, builder.dimension, info, builder.type));

    			break;
    		}
    		case 2:
    		{
    			info.setSizeY(info.getSizeY() + 1);
    			//CompactStorage.instance.wrapper.sendToServer(new C01PacketUpdateBuilder(pos, builder.dimension, info, builder.type));

    			break;
    		}
    		case 3:
    		{
    			info.setSizeY(info.getSizeY() - 1);
    			//CompactStorage.instance.wrapper.sendToServer(new C01PacketUpdateBuilder(pos, builder.dimension, info, builder.type));

    			break;
    		}
    		case 4:
    		{
    			//CompactStorage.instance.wrapper.sendToServer(new C02PacketCraftChest(pos, builder.dimension, info, builder.type, "0x" + (colorField.getText().isEmpty() ? "FFFFFF" : colorField.getText())));
    			
    			break;
    		}
            case 5:
            {
                int currentType = builder.type.ordinal();

                System.out.println(currentType);

                int newType = currentType + 1;

                if(newType == StorageInfo.Type.values().length) newType = 0;

                builder.type = StorageInfo.Type.values()[newType];
                buttonChangeType.displayString = builder.type.name();
                buttonSubmit.displayString = "Build " + builder.type.name();

                //CompactStorage.instance.wrapper.sendToServer(new C01PacketUpdateBuilder(pos, builder.dimension, info, builder.type));

                break;
            }
    	}
    }
    
    public static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel)
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer renderer = tessellator.getWorldRenderer();

        renderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        renderer.pos(x + 0, y + height, zLevel).tex(0,1);
        renderer.pos(x + width, y + height, zLevel).tex(1, 1);
        renderer.pos(x + width, y + 0, zLevel).tex(1,0);
        renderer.pos(x + 0, y + 0, zLevel).tex(0, 0);
        tessellator.draw();
    }
    
    public FontRenderer getFontRenderer()
    {
    	return fontRendererObj;
    }
}
