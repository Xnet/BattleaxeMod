// Coded by Flann

package mods.battleaxe.src;

import static net.minecraftforge.common.Property.Type.BOOLEAN;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(clientSideRequired=true,serverSideRequired=false)
@Mod(modid=CoreBattleaxe.modid,name="Battleaxe Mod",version="#2")
public class CoreBattleaxe {
	
	public static final String modid = "Battleaxe";
	public static final String texLoc = "battleaxe:";
	
	public static int ItemID = 14550;
	
	public static boolean enableCoreSteel			= true;
	public static boolean enableCoreStickSteel		= false;
	public static boolean enableCoreIngotRedstone	= true;
	
	public static Item battleaxeW, battleaxeS, battleaxeI, battleaxeD, battleaxeG,
		battleaxeE, battleaxeN, battleaxeO, battleaxeR, battleaxeSt;
	
	public static int battleaxeWID, battleaxeSID, battleaxeIID, battleaxeDID, battleaxeGID,
		battleaxeEID, battleaxeNID, battleaxeOID, battleaxeRID, battleaxeStID;
	
	public boolean enableVanilla, enableEmerald, enableNetherrack, enableObsidian, enableRedstone, enableSteel;
	
	public static EnumToolMaterial WOOD = EnumHelper.addToolMaterial("WOOD", 0, 59, 2.0F, 1, 15);
	public static EnumToolMaterial STONE = EnumHelper.addToolMaterial("STONE", 1, 131, 4F, 2, 5);
	public static EnumToolMaterial IRON = EnumHelper.addToolMaterial("IRON", 2, 250, 6F, 3, 14);
	public static EnumToolMaterial DIAMOND = EnumHelper.addToolMaterial("DIAMOND", 3, 1561, 8F, 4, 10);
	public static EnumToolMaterial GOLD = EnumHelper.addToolMaterial("GOLD", 0, 32, 12F, 1, 22);
	
	public static EnumToolMaterial EMERALD = EnumHelper.addToolMaterial("EMERALD", 3, 1700, 10.0F, 8, 25);
	public static EnumToolMaterial NETHERRACK = EnumHelper.addToolMaterial("NETHERRACK", 1, 163, 4F, 3, 5);
	public static EnumToolMaterial OBSIDIAN = EnumHelper.addToolMaterial("OBSIDIAN", 3, 1049, 10F, 7, 8);
	public static EnumToolMaterial REDSTONE = EnumHelper.addToolMaterial("REDSTONE", 2, 218, 7F, 4, 14);
	public static EnumToolMaterial STEEL = EnumHelper.addToolMaterial("STEEL", 2, 500, 6F, 5, 14);
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
			battleaxeWID = config.get("item", "battleaxeWood", ItemID+0).getInt()-256;
			battleaxeSID = config.get("item", "battleaxeStone", ItemID+1).getInt()-256;
			battleaxeIID = config.get("item", "battleaxeIron", ItemID+2).getInt()-256;
			battleaxeDID = config.get("item", "battleaxeDiamond", ItemID+3).getInt()-256;
			battleaxeGID = config.get("item", "battleaxeGold", ItemID+4).getInt()-256;
			
			battleaxeEID = config.get("item", "battleaxeEmerald", ItemID+5).getInt()-256;
			battleaxeNID = config.get("item", "battleaxeNetherrack", ItemID+6).getInt()-256;
			battleaxeOID = config.get("item", "battleaxeObsidian", ItemID+7).getInt()-256;
			battleaxeRID = config.get("item", "battleaxeRedstone", ItemID+8).getInt()-256;
			battleaxeStID = config.get("item", "battleaxeSteel", ItemID+9).getInt()-256;
			
			enableVanilla = config.get("enable", "enableVanilla", true).getBoolean(true);
			enableEmerald = config.get("enable", "enableEmerald", true).getBoolean(true);
			enableNetherrack = config.get("enable", "enableNetherrack", true).getBoolean(true);
			enableObsidian = config.get("enable", "enableObsidian", true).getBoolean(true);
			enableRedstone = config.get("enable", "enableRedstone", true).getBoolean(true);
			enableSteel = config.get("enable", "enableSteel", true).getBoolean(true);
		config.save();
		
		Configuration coreConfig = new Configuration(new File(event.getSuggestedConfigurationFile().getPath().replace(modid, "FlannCore")));
		coreConfig.load();
			if(enableCoreSteel && enableSteel)
				set(coreConfig, "general", "enableSteel", true);
			if(enableCoreStickSteel)
				set(coreConfig, "general", "enableStickSteel", true);
			if(enableCoreIngotRedstone && enableRedstone)
				set(coreConfig, "general", "enableIngotRedstone", true);
		coreConfig.save();
		
		init_pre();
	}
	
	@Init
	public void load(FMLInitializationEvent event){
		init();
	}
	
	public void init_pre(){
		if(enableVanilla){
			battleaxeW = new Flann_ItemBattleaxe(battleaxeWID, "Wooden", texLoc+"battleaxeW", Block.planks, WOOD).setUnlocalizedName("battleaxeWood");
			battleaxeS = new Flann_ItemBattleaxe(battleaxeSID, "Stone", texLoc+"battleaxeS", Block.cobblestone, STONE).setUnlocalizedName("battleaxeStone");
			battleaxeI = new Flann_ItemBattleaxe(battleaxeIID, "Iron", texLoc+"battleaxeI", Item.ingotIron, IRON).setUnlocalizedName("battleaxeIron");
			battleaxeD = new Flann_ItemBattleaxe(battleaxeDID, "Diamond", texLoc+"battleaxeD", Item.diamond, DIAMOND).setUnlocalizedName("battleaxeDiamond");
			battleaxeG = new Flann_ItemBattleaxe(battleaxeGID, "Golden", texLoc+"battleaxeG", Item.ingotGold, GOLD).setUnlocalizedName("battleaxeGold");
		}
		if(enableEmerald){
			String ingot = "emerald";
			String ingot2 = "emerald";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Item.emerald);
			boolean ccmDam = false;
			
			battleaxeE = new Flann_ItemBattleaxe(battleaxeEID, "Emerald", texLoc+"battleaxeE", ccm, ccmDam, EMERALD).setUnlocalizedName("battleaxeEmerald");
		}
		if(enableNetherrack){
			String ingot = "netherrack";
			String ingot2 = "netherrack";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Block.netherrack);
			boolean ccmDam = false;
			
			battleaxeN = new Flann_ItemBattleaxe(battleaxeNID, "Netherrack", texLoc+"battleaxeN", ccm, ccmDam, NETHERRACK).setUnlocalizedName("battleaxeNetherrack");
		}
		if(enableObsidian){
			String ingot = "obsidian";
			String ingot2 = "obsidian";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Block.obsidian);
			boolean ccmDam = false;
			
			battleaxeO = new Flann_ItemBattleaxe(battleaxeOID, "Obsidian", texLoc+"battleaxeO", ccm, ccmDam, OBSIDIAN).setUnlocalizedName("battleaxeObsidian");
		}
		if(enableRedstone){
			String ingot = "ingotRedstone";
			String ingot2 = "ingotRedstone";
			boolean useOD = true;
			ItemStack ccm = new ItemStack(Item.ingotIron);
			boolean ccmDam = false;
			
			battleaxeR = new Flann_ItemBattleaxe(battleaxeRID, "Redstone", texLoc+"battleaxeR", ccm, ccmDam, REDSTONE).setUnlocalizedName("battleaxeRedstone");
		}
		if(enableSteel){
			String ingot = "ingotSteel";
			String ingot2 = "ingotSteelUnhardened";
			boolean useOD = true;
			ItemStack ccm = new ItemStack(Item.ingotIron);
			boolean ccmDam = false;
			
			battleaxeSt = new Flann_ItemBattleaxe(battleaxeStID, "Steel", texLoc+"battleaxeSt", ccm, ccmDam, STEEL).setUnlocalizedName("battleaxeSteel");
		}
	}
	public void init(){
		if(enableVanilla){
			GameRegistry.registerFuelHandler(new Flann_FuelHandler());
			
			MinecraftForge.setToolClass(battleaxeW, "axe", 0);
			MinecraftForge.setToolClass(battleaxeS, "axe", 1);
			MinecraftForge.setToolClass(battleaxeI, "axe", 2);
			MinecraftForge.setToolClass(battleaxeD, "axe", 3);
			MinecraftForge.setToolClass(battleaxeG, "axe", 0);
			
			OreDictionary.registerOre("battleaxeWood", battleaxeW);
			OreDictionary.registerOre("battleaxeStone", battleaxeS);
			OreDictionary.registerOre("battleaxeIron", battleaxeI);
			OreDictionary.registerOre("battleaxeDiamond", battleaxeD);
			OreDictionary.registerOre("battleaxeGold", battleaxeG);
			
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeW,1),"BBB","BIB"," I ",'B',"plankWood",'I',Item.stick));
			GameRegistry.addRecipe(new ItemStack(battleaxeS,1),"BBB","BIB"," I ",'B',Block.cobblestone,'I',Item.stick);
			GameRegistry.addRecipe(new ItemStack(battleaxeI,1),"BBB","BIB"," I ",'B',Item.ingotIron,'I',Item.stick);
			GameRegistry.addRecipe(new ItemStack(battleaxeD,1),"BBB","BIB"," I ",'B',Item.diamond,'I',Item.stick);
			GameRegistry.addRecipe(new ItemStack(battleaxeG,1),"BBB","BIB"," I ",'B',Item.ingotGold,'I',Item.stick);
		}
		if(enableEmerald){
			String ingot = "emerald";
			String ingot2 = "emerald";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Item.emerald);
			boolean ccmDam = false;
			
			MinecraftForge.setToolClass(battleaxeE, "axe", EMERALD.getHarvestLevel());
			OreDictionary.registerOre("battleaxeEmerald", battleaxeE);
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeE,1),"BBB","BIB"," I ",'B', ingot,'I',Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeE,1),"BBB","BIB"," I ",'B', ingot2,'I',Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeE,1),"BBB","BIB"," I ",'B', ccm,'I',Item.stick));
			}
		}
		if(enableNetherrack){
			String ingot = "netherrack";
			String ingot2 = "netherrack";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Block.netherrack);
			boolean ccmDam = false;
			
			MinecraftForge.setToolClass(battleaxeN, "axe", NETHERRACK.getHarvestLevel());
			OreDictionary.registerOre("battleaxeNetherrack", battleaxeN);
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeN,1),"BBB","BIB"," I ",'B', ingot,'I',Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeN,1),"BBB","BIB"," I ",'B', ingot2,'I',Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeN,1),"BBB","BIB"," I ",'B', ccm,'I',Item.stick));
			}
		}
		if(enableObsidian){
			String ingot = "obsidian";
			String ingot2 = "obsidian";
			boolean useOD = false;
			ItemStack ccm = new ItemStack(Block.obsidian);
			boolean ccmDam = false;
			
			MinecraftForge.setToolClass(battleaxeO, "axe", OBSIDIAN.getHarvestLevel());
			OreDictionary.registerOre("battleaxeObsidian", battleaxeO);
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeO,1),"BBB","BIB"," I ",'B', ingot,'I',Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeO,1),"BBB","BIB"," I ",'B', ingot2,'I',Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeO,1),"BBB","BIB"," I ",'B', ccm,'I',Item.stick));
			}
		}
		if(enableRedstone){
			String ingot = "ingotRedstone";
			String ingot2 = "ingotRedstone";
			boolean useOD = true;
			ItemStack ccm = new ItemStack(Item.ingotIron);
			boolean ccmDam = false;
			
			MinecraftForge.setToolClass(battleaxeR, "axe", REDSTONE.getHarvestLevel());
			OreDictionary.registerOre("battleaxeRedstone", battleaxeR);
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeR,1),"BBB","BIB"," I ",'B', ingot,'I',Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeR,1),"BBB","BIB"," I ",'B', ingot2,'I',Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeR,1),"BBB","BIB"," I ",'B', ccm,'I',Item.stick));
			}
		}
		if(enableSteel){
			String ingot = "ingotSteel";
			String ingot2 = "ingotSteelUnhardened";
			boolean useOD = true;
			ItemStack ccm = new ItemStack(Item.ingotIron);
			boolean ccmDam = false;
			
			MinecraftForge.setToolClass(battleaxeSt, "axe", STEEL.getHarvestLevel());
			OreDictionary.registerOre("battleaxeSteel", battleaxeSt);
			if(useOD){
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeSt,1),"BBB","BIB"," I ",'B', ingot,'I',Item.stick));
				if(!ingot2.equals("") && !ingot2.equals(ingot)){
					GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeSt,1),"BBB","BIB"," I ",'B', ingot2,'I',Item.stick));
				}
			}else{
				GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(battleaxeSt,1),"BBB","BIB"," I ",'B', ccm,'I',Item.stick));
			}
		}
	}
	
	public Property set(Configuration config, String category, String key, boolean defaultValue)
    {
		return set(config, category, key, defaultValue, null);
    }
	public Property set(Configuration config, String category, String key, boolean defaultValue, String comment)
    {
        Property prop = config.get(category, key, Boolean.toString(defaultValue), comment, BOOLEAN);
        prop.set(defaultValue);
        return prop;
    }
}
