// Coded by Flann

package mods.battleaxe.src;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Flann_ItemBattleaxe extends ItemAxe {

	public String t;
	public ItemStack ccm;
	public boolean hasDam;
	public String matName;
	
	public Flann_ItemBattleaxe(int id, String materialName, String tex, ItemStack customCraftingMaterial, boolean hasDamage, EnumToolMaterial enumtoolmaterial) {
		super(id, enumtoolmaterial);
		setCreativeTab(CreativeTabs.tabCombat);
		t = tex;
		ccm = customCraftingMaterial;
		hasDam = hasDamage;
		matName = materialName;
	}
	
	public Flann_ItemBattleaxe(int id, String materialName, String tex, Item ccm, EnumToolMaterial enumtoolmaterial) {
		this(id, materialName, tex, new ItemStack(ccm), false, enumtoolmaterial);
	}
	
	public Flann_ItemBattleaxe(int i, String materialName, String t, Block ccm, EnumToolMaterial enumtoolmaterial) {
		this(i, materialName, t, new ItemStack(ccm), false, enumtoolmaterial);
	}
	
	@Override
	public String getItemDisplayName(ItemStack par1ItemStack)
    {
		return matName + " Battleaxe";
    }
	
	@Override
	@SideOnly(Side.CLIENT) //Makes sure that only the client side can call this method
	public void registerIcons(IconRegister IR){
		this.itemIcon = IR.registerIcon(t);
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	public boolean isFull3D() {
		return true;
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack) {
		return EnumAction.block;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.setItemInUse(par1ItemStack,
				this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	/**
	 * Returns the strength of the stack against a given block. 1.0F base,
	 * (Quality+1)*2 if correct blocktype, 1.5F if sword
	 */
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return par2Block != null
				&& (par2Block.blockMaterial == Material.wood || par2Block.blockID == Block.web.blockID) ? this.efficiencyOnProperMaterial
				: super.getStrVsBlock(par1ItemStack, par2Block);
	}
	
	@Override
	public boolean getIsRepairable(ItemStack i, ItemStack j){
		if(hasDam)
			return (i.itemID == itemID && j.itemID == ccm.itemID && j.getItemDamage() == ccm.getItemDamage());
		return (i.itemID == itemID && j.itemID == ccm.itemID);
	}
}