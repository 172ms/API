package ru.fakeduck_king.utils;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import java.util.Arrays;

@SuppressWarnings("deprecation")
public class CustomItem {
	public static ItemStack create(Material material, String name, Enchantment enchantment, int level, String[] lore, int amount, Short data) {
		ItemStack itemStack = new ItemStack(material, amount, data);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, Enchantment enchantment, int level, String[] lore, int amount, Short data) {
		ItemStack itemStack = new ItemStack(material, amount, data);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, Enchantment enchantment, int level, String[] lore, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, Enchantment enchantment, int level, int amount, Short data) {
		ItemStack itemStack = new ItemStack(material, amount, data);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, Enchantment enchantment, int level, String[] lore) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, Enchantment enchantment, int level, String[] lore, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, Enchantment enchantment, int level, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, Enchantment enchantment, int level, int amount, Short data) {
		ItemStack itemStack = new ItemStack(material, amount, data);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, String[] lore, int amount, Short data) {
		ItemStack itemStack = new ItemStack(material, amount, data);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, Enchantment enchantment, int level, String[] lore) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, Enchantment enchantment, int level) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, Enchantment enchantment, int level, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, String[] lore, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String[] lore, int amount, Short data) {
		ItemStack itemStack = new ItemStack(material, amount, data);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, int amount, Short data) {
		ItemStack itemStack = new ItemStack(material, amount, data);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, Enchantment enchantment, int level) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, String[] lore) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String[] lore, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String[] lore) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, String name) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material, int amount) {
		ItemStack itemStack = new ItemStack(material, amount);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(Material material) {
		ItemStack itemStack = new ItemStack(material);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String name, Enchantment enchantment, int level, String[] lore, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String name, Enchantment enchantment, int level, String[] lore) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, Enchantment enchantment, int level, String[] lore, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String name, Enchantment enchantment, int level, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, Enchantment enchantment, int level, String[] lore) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String name, Enchantment enchantment, int level) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, Enchantment enchantment, int level, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String name, String[] lore, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, Enchantment enchantment, int level) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.addEnchant(enchantment, level, true);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String name, String[] lore) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String[] lore, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String name, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String[] lore) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setLore(Arrays.asList(lore));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, String name) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack, int amount) {
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemStack.setAmount(amount);
		itemStack.setItemMeta(itemMeta);
		
		return itemStack;
	}
	
	public static ItemStack create(ItemStack itemStack) {
		return itemStack;
	}
}