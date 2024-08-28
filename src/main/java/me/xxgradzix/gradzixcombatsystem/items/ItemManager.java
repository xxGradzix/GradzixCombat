package me.xxgradzix.gradzixcombatsystem.items;

import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class ItemManager {

    private static final int heavyArmorStrRequirement = 6;
    private static final int heavyArmorDexRequirement = 8;

    public static ItemStack heavyHelmet_1;
    public static ItemStack heavyChestPlate_1;
    public static ItemStack heavyLeggings_1;
    public static ItemStack heavyBoots_1;





    public static void init() {
        createHeavyHelmet_1();
        createHeavyChestPlate_1();
        createHeavyLeggings_1();
        createHeavyBoots_1();
    }

    private static void createHeavyHelmet_1() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta heavyHelmetMeta = item.getItemMeta();

        heavyHelmetMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ʜᴇᴌᴍ"));

        item.setItemMeta(heavyHelmetMeta);

        ArrayList<String> lore = new ArrayList<>();

        lore.add(ColorFixer.addColors("&7&o+10 do pancerza"));
        lore.add(ColorFixer.addColors("&7&oWymagane:"));
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + heavyArmorStrRequirement));
        lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + heavyArmorDexRequirement));
//        lore.add(ColorFixer.addColors("&eᴢʀęᴄᴢɴᴏść &r\uD83D\uDCAA:  #a18b3d"));

        heavyHelmetMeta.setLore(lore);
        item.setItemMeta(heavyHelmetMeta);

        ItemManager.heavyHelmet_1 = item;

        AttributeManager.setAttributeRequirement(ItemManager.heavyHelmet_1, CombatAttribute.STRENGTH, heavyArmorStrRequirement);
        AttributeManager.setAttributeRequirement(ItemManager.heavyHelmet_1, CombatAttribute.DEXTERITY, heavyArmorStrRequirement);

        ArmorTierManager.setAttributesPerTierAndWeight(ItemManager.heavyHelmet_1, ArmorTierManager.ArmorType.HELMET, ArmorTierManager.ArmorWeight.HEAVY, 1);
    }

    private static void createHeavyChestPlate_1() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta heavyHelmetMeta = item.getItemMeta();

        heavyHelmetMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ɴᴀᴘɪᴇʀśɴɪᴋ"));


        item.setItemMeta(heavyHelmetMeta);

        ArrayList<String> lore = new ArrayList<>();

        lore.add(ColorFixer.addColors("&7&o+10 do pancerza"));
        lore.add(ColorFixer.addColors("&7&oWymagane:"));
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + heavyArmorStrRequirement));
        lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + heavyArmorDexRequirement));

        heavyHelmetMeta.setLore(lore);
        item.setItemMeta(heavyHelmetMeta);

        ItemManager.heavyChestPlate_1 = item;
        AttributeManager.setAttributeRequirement(ItemManager.heavyChestPlate_1, CombatAttribute.STRENGTH, heavyArmorStrRequirement);
        AttributeManager.setAttributeRequirement(ItemManager.heavyChestPlate_1, CombatAttribute.DEXTERITY, heavyArmorStrRequirement);

        ArmorTierManager.setAttributesPerTierAndWeight(ItemManager.heavyChestPlate_1, ArmorTierManager.ArmorType.CHESTPLATE, ArmorTierManager.ArmorWeight.HEAVY, 1);

    }

    private static void createHeavyLeggings_1() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta heavyHelmetMeta = item.getItemMeta();

        heavyHelmetMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ"));


        item.setItemMeta(heavyHelmetMeta);

        ArrayList<String> lore = new ArrayList<>();

        lore.add(ColorFixer.addColors("&7&o+10 do pancerza"));
        lore.add(ColorFixer.addColors("&7&oWymagane:"));
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + heavyArmorStrRequirement));
        lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + heavyArmorDexRequirement));

        heavyHelmetMeta.setLore(lore);
        item.setItemMeta(heavyHelmetMeta);

        ItemManager.heavyLeggings_1 = item;
        AttributeManager.setAttributeRequirement(ItemManager.heavyLeggings_1, CombatAttribute.STRENGTH, heavyArmorStrRequirement);
        AttributeManager.setAttributeRequirement(ItemManager.heavyLeggings_1, CombatAttribute.DEXTERITY, heavyArmorStrRequirement);

        ArmorTierManager.setAttributesPerTierAndWeight(ItemManager.heavyLeggings_1, ArmorTierManager.ArmorType.LEGGINGS, ArmorTierManager.ArmorWeight.HEAVY, 1);
    }

    private static void createHeavyBoots_1() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta heavyHelmetMeta = item.getItemMeta();

        heavyHelmetMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ"));

        item.setItemMeta(heavyHelmetMeta);

        ArrayList<String> lore = new ArrayList<>();

        lore.add(ColorFixer.addColors("&7&o+10 do pancerza"));
        lore.add(ColorFixer.addColors("&7&oWymagane:"));
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + heavyArmorStrRequirement));
        lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + heavyArmorDexRequirement));

        heavyHelmetMeta.setLore(lore);
        item.setItemMeta(heavyHelmetMeta);

        ItemManager.heavyBoots_1 = item;

        AttributeManager.setAttributeRequirement(ItemManager.heavyLeggings_1, CombatAttribute.STRENGTH, heavyArmorStrRequirement);
        AttributeManager.setAttributeRequirement(ItemManager.heavyLeggings_1, CombatAttribute.DEXTERITY, heavyArmorStrRequirement);

        ArmorTierManager.setAttributesPerTierAndWeight(ItemManager.heavyLeggings_1, ArmorTierManager.ArmorType.BOOTS, ArmorTierManager.ArmorWeight.HEAVY, 1);
    }




}
