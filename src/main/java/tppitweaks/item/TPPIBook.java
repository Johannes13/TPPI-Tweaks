package tppitweaks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import tppitweaks.TPPITweaks;
import tppitweaks.client.gui.GuiHelper;
import tppitweaks.config.ConfigurationHandler;
import tppitweaks.util.FileLoader;
import tppitweaks.util.TxtParser;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class TPPIBook extends ItemEditableBook
{
	public TPPIBook(int par1)
	{
		super(par1);
		setCreativeTab(TPPITweaks.creativeTab);
	}

	private Icon[] icons = new Icon[3];
	private String[] unlocNames = {"tppiWelcomePacket", "tppiChangelog", "tppiGuide"};

	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons[0] = par1IconRegister.registerIcon("tppitweaks:tppibook");
		icons[1] = Item.writtenBook.getIconFromDamage(0);
		icons[2] = par1IconRegister.registerIcon(ConfigurationHandler.guideSkin == 0 ? "tppitweaks:tppiGuide1" : "tppitweaks:tppiGuide2");
	}
	
	@Override
	public Icon getIcon(ItemStack stack, int pass)
	{
		return icons[stack.getItemDamage()];
	}
	
	@Override
	public Icon getIconFromDamage(int par1)
	{
		return icons[par1];
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (stack.getItemDamage() == 2 && FMLCommonHandler.instance().getSide().isClient())
		{
			GuiHelper.doGuideGUI();
		}
		else if (stack.stackTagCompound == null || !stack.getTagCompound().getString("version").equals(TPPITweaks.VERSION) || stack.getItemDamage() == 1)
		{
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			{
				stack.setTagCompound(new NBTTagCompound());
				addTextToBook(stack, stack.getItemDamage());

				player.inventoryContainer.detectAndSendChanges();

				GuiHelper.doBookGUI(player, stack, false);
				return stack;
			}
		}
		else if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			GuiHelper.doBookGUI(player, stack, false);
		return stack;
	}

	public ItemStack addTextToBook(ItemStack book, int damage)
	{
		NBTTagCompound nbttagcompound;
		NBTTagList bookPages;

		book.setTagInfo("author", new NBTTagString("author", ConfigurationHandler.bookAuthor));

		switch (damage)
		{
		case 0:			
			book.setTagInfo("title", new NBTTagString("title", ConfigurationHandler.bookTitle));

			nbttagcompound = book.getTagCompound();
			bookPages = new NBTTagList("pages");

			for (int i = 0; i < ConfigurationHandler.bookText.size(); i++)
			{
				bookPages.appendTag(new NBTTagString("" + i, ConfigurationHandler.bookText.get(i)));
			}

			nbttagcompound.setTag("pages", bookPages);
			nbttagcompound.setString("version", TPPITweaks.VERSION);

			break;
		case 1:
			book.setTagInfo("title", new NBTTagString("title", ConfigurationHandler.changelogTitle));
			
			nbttagcompound = book.getTagCompound();
			bookPages = new NBTTagList("pages");
			
			if (ConfigurationHandler.changelog == null)
			{
				ConfigurationHandler.loadChangelogText(FileLoader.getChangelogText());
			}
			
			for (int i = 0; i < ConfigurationHandler.changelog.size(); i++)
			{
				bookPages.appendTag(new NBTTagString("" + i, ConfigurationHandler.changelog.get(i)));
			}

			nbttagcompound.setTag("pages", bookPages);
			nbttagcompound.setString("version", TPPITweaks.VERSION);

			break;
		}
		return book;
	}

	public ItemStack getGuide()
	{
		ConfigurationHandler.bookText = TxtParser.parseFileMain(FileLoader.getGuideText());
		return addTextToBook(new ItemStack(ModItems.tppiBook), 0);
	}
	
	public ItemStack getChangelog()
	{
		ConfigurationHandler.changelog = TxtParser.parseFileMain(FileLoader.getChangelogText());
		return addTextToBook(new ItemStack(ModItems.tppiBook, 1, 1), 1);
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return false;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "TPPIBook";
	}
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return unlocNames[par1ItemStack.getItemDamage()];
	}

}