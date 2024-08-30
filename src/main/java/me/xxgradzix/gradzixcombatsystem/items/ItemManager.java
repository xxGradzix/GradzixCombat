package me.xxgradzix.gradzixcombatsystem.items;

import com.google.common.collect.Multimap;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


public class ItemManager {

    private static final int baseHeavyArmorStrRequirement = 6;
    private static final int baseMediumArmorStrRequirement = 3;
    private static final int baseLightArmorStrRequirement = 0;

    private static final int baseMediumArmorDexRequirement = 3;


    private static final HashMap<ArmorTierManager.ArmorType, HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>>> armorsPerTierAndWeight = new HashMap<>();

    public static HashMap<ArmorTierManager.ArmorType, HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>>> getArmorsPerTierAndWeight() {
        return new HashMap<>(armorsPerTierAndWeight);
    }

    public static ItemStack getArmorPiece(ArmorTierManager.ArmorType armorType, int tier, ArmorTierManager.ArmorWeight armorWeight) {
        return armorsPerTierAndWeight.getOrDefault(armorType, new HashMap<>()).getOrDefault(tier, new HashMap<>()).get(armorWeight);
    }

    public static List<ItemStack> getAllItems() {
        List<ItemStack> items = new ArrayList<>();
        for (HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>> tierMap : armorsPerTierAndWeight.values()) {
            for (HashMap<ArmorTierManager.ArmorWeight, ItemStack> weightMap : tierMap.values()) {
                items.addAll(weightMap.values());
            }
        }
        return items;
    }
    public static List<ItemStack> getAllItems(ArmorTierManager.ArmorWeight armorWeight) {
        List<ItemStack> items = new ArrayList<>();
        for (HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>> tierMap : armorsPerTierAndWeight.values()) {
            for (HashMap<ArmorTierManager.ArmorWeight, ItemStack> weightMap : tierMap.values()) {
                items.add(weightMap.get(armorWeight));
            }
        }
        return items;
    }



    public static void init() {
        for (ArmorTierManager.ArmorType armorType : ArmorTierManager.ArmorType.values()) {

            for (int tier = 1; tier <= 5; tier++) {
                for (ArmorTierManager.ArmorWeight armorWeight : ArmorTierManager.ArmorWeight.values()) {
                    ItemStack armorPieceWithParameters = createArmorPieceWithParameters(armorType, tier, armorWeight);
                    HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>> tierMap = armorsPerTierAndWeight.getOrDefault(armorType, new HashMap<>());
                    HashMap<ArmorTierManager.ArmorWeight, ItemStack> weightMap = tierMap.getOrDefault(tier, new HashMap<>());
                    weightMap.put(armorWeight, armorPieceWithParameters);
                    tierMap.put(tier, weightMap);
                    armorsPerTierAndWeight.put(armorType, tierMap);
                }
            }
        }


    }
    private static ItemStack createArmorPieceWithParameters(ArmorTierManager.ArmorType armorType, int tier, ArmorTierManager.ArmorWeight armorWeight) {

        Material material;
        String displayName;

        switch (armorType) {
            case HELMET-> {
                switch (armorWeight) {
                    case LIGHT -> {
                        material = Material.LEATHER_HELMET;
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪ ʜᴇᴌᴍ");
                    }
                    case MEDIUM -> {
                        material = Material.CHAINMAIL_HELMET;
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪ ʜᴇᴌᴍ");
                    }
                    case HEAVY -> {
                        material = Material.IRON_HELMET;
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ʜᴇᴌᴍ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);

                }
            }
            case CHESTPLATE -> {
                switch (armorWeight) {
                    case LIGHT -> {
                        material = Material.LEATHER_CHESTPLATE;
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
                    }
                    case MEDIUM -> {
                        material = Material.CHAINMAIL_CHESTPLATE;
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
                    }
                    case HEAVY -> {
                        material = Material.IRON_CHESTPLATE;
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);

                }
            }
            case LEGGINGS -> {
                switch (armorWeight) {
                    case LIGHT -> {
                        material = Material.LEATHER_LEGGINGS;
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    case MEDIUM -> {
                        material = Material.CHAINMAIL_LEGGINGS;
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    case HEAVY -> {
                        material = Material.IRON_LEGGINGS;
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);

                }
            }
            case BOOTS -> {
                switch (armorWeight) {
                    case LIGHT -> {
                        material = Material.LEATHER_BOOTS;
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪᴇ ʙᴜᴛʏ");
                    }
                    case MEDIUM -> {
                        material = Material.CHAINMAIL_BOOTS;
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪᴇ ʙᴜᴛʏ");
                    }
                    case HEAVY -> {
                        material = Material.IRON_BOOTS;
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ʙᴜᴛʏ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + armorType);
        }

        String romeNum = "";
        switch (tier) {
            case 1 -> romeNum = "ɪ";
            case 2 -> romeNum = "ɪɪ";
            case 3 -> romeNum = "ɪɪɪ";
            case 4 -> romeNum = "ɪᴠ";
            case 5 -> romeNum = "ᴠ";
            case 6 -> romeNum = "ᴠɪ";
            case 7 -> romeNum = "ᴠɪɪ";
            case 8 -> romeNum = "ᴠɪɪɪ";
            case 9 -> romeNum = "ɪx";
            case 10 -> romeNum = "x";
        }

        ItemStack item = new ItemStack(material);

        int strAttributeRequirement = 0;
        int dexAttributeRequirement = 0;
        int another = 0;


        switch (armorWeight) {
            case MEDIUM -> {
                strAttributeRequirement = baseMediumArmorStrRequirement + tier;
            }
            case HEAVY -> {
                strAttributeRequirement = baseHeavyArmorStrRequirement + tier;
            }
            default -> {
                strAttributeRequirement = baseLightArmorStrRequirement + (tier / 2);
            }
        }


        if(armorWeight == ArmorTierManager.ArmorWeight.MEDIUM) {
            dexAttributeRequirement = baseMediumArmorDexRequirement + tier;
        }

        if(dexAttributeRequirement > 0) AttributeManager.setAttributeRequirement(item, CombatAttribute.DEXTERITY, baseMediumArmorDexRequirement + tier);
        if(strAttributeRequirement > 0) AttributeManager.setAttributeRequirement(item, CombatAttribute.STRENGTH, strAttributeRequirement);

        ArmorTierManager.setAttributesPerTierAndWeight(item, armorType, armorWeight, tier);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(ColorFixer.addColors(displayName + " " + romeNum));

        item.setItemMeta(itemMeta);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors(" "));


        Multimap<Attribute, AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers();



        if(attributeModifiers != null) {
            Collection<AttributeModifier> attributeModifiers1 = attributeModifiers.get(Attribute.GENERIC_ARMOR);
            attributeModifiers1.forEach(attributeModifier -> {
                lore.add(ColorFixer.addColors("&7&o+" + attributeModifier.getAmount() + " do pancerza"));
            });
        }


        lore.add(ColorFixer.addColors("&7&oWymagane:"));
        lore.add(ColorFixer.addColors(" "));
        switch (armorWeight) {
            case HEAVY -> {
                lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
            }
            case LIGHT -> {
                lore.add(ColorFixer.addColors("&7ᴛᴀ ʟᴇᴋᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴘᴏᴅꜱᴛᴀᴡᴏᴡą ᴏʙʀᴏɴę"));
            }
            default -> {
                lore.add(ColorFixer.addColors("&7ᴛᴀ śʀᴇᴅɴɪᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ śʀᴇᴅɴɪą ᴏʙʀᴏɴę"));
            }
        }
        lore.add(ColorFixer.addColors(" "));
        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        if(strAttributeRequirement > 0) lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + strAttributeRequirement));
        if(dexAttributeRequirement > 0) lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + dexAttributeRequirement));

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

//
//    private static void createHeavyHelmet_1() {
//        ItemStack item = new ItemStack(Material.IRON_HELMET);
//        ItemMeta heavyHelmetMeta = item.getItemMeta();
//
//        heavyHelmetMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ʜᴇᴌᴍ"));
//
//        item.setItemMeta(heavyHelmetMeta);
//
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(ColorFixer.addColors("&7&o+10 do pancerza"));
//        lore.add(ColorFixer.addColors("&7&oWymagane:"));
//        lore.add(ColorFixer.addColors(" "));
//        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
//        lore.add(ColorFixer.addColors(" "));
//        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
//        lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + heavyArmorStrRequirement));
//        lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + heavyArmorDexRequirement));
//
//        heavyHelmetMeta.setLore(lore);
//        item.setItemMeta(heavyHelmetMeta);
//
//        ItemManager.heavyHelmet_1 = item;
//
//        AttributeManager.setAttributeRequirement(ItemManager.heavyHelmet_1, CombatAttribute.STRENGTH, heavyArmorStrRequirement);
//        AttributeManager.setAttributeRequirement(ItemManager.heavyHelmet_1, CombatAttribute.DEXTERITY, heavyArmorStrRequirement);
//
//        ArmorTierManager.setAttributesPerTierAndWeight(ItemManager.heavyHelmet_1, ArmorTierManager.ArmorType.HELMET, ArmorTierManager.ArmorWeight.HEAVY, 1);
//    }
//
//    private static void createHeavyChestPlate_1() {
//        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
//        ItemMeta heavyHelmetMeta = item.getItemMeta();
//
//        heavyHelmetMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ɴᴀᴘɪᴇʀśɴɪᴋ"));
//
//
//        item.setItemMeta(heavyHelmetMeta);
//
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(ColorFixer.addColors("&7&o+10 do pancerza"));
//        lore.add(ColorFixer.addColors("&7&oWymagane:"));
//        lore.add(ColorFixer.addColors(" "));
//        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
//        lore.add(ColorFixer.addColors(" "));
//        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
//        lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + heavyArmorStrRequirement));
//        lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + heavyArmorDexRequirement));
//
//        heavyHelmetMeta.setLore(lore);
//        item.setItemMeta(heavyHelmetMeta);
//
//        ItemManager.heavyChestPlate_1 = item;
//        AttributeManager.setAttributeRequirement(ItemManager.heavyChestPlate_1, CombatAttribute.STRENGTH, heavyArmorStrRequirement);
//        AttributeManager.setAttributeRequirement(ItemManager.heavyChestPlate_1, CombatAttribute.DEXTERITY, heavyArmorStrRequirement);
//
//        ArmorTierManager.setAttributesPerTierAndWeight(ItemManager.heavyChestPlate_1, ArmorTierManager.ArmorType.CHESTPLATE, ArmorTierManager.ArmorWeight.HEAVY, 1);
//
//    }
//
//    private static void createHeavyLeggings_1() {
//        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
//        ItemMeta heavyHelmetMeta = item.getItemMeta();
//
//        heavyHelmetMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ"));
//
//
//        item.setItemMeta(heavyHelmetMeta);
//
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(ColorFixer.addColors("&7&o+10 do pancerza"));
//        lore.add(ColorFixer.addColors("&7&oWymagane:"));
//        lore.add(ColorFixer.addColors(" "));
//        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
//        lore.add(ColorFixer.addColors(" "));
//        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
//        lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + heavyArmorStrRequirement));
//        lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + heavyArmorDexRequirement));
//
//        heavyHelmetMeta.setLore(lore);
//        item.setItemMeta(heavyHelmetMeta);
//
//        ItemManager.heavyLeggings_1 = item;
//        AttributeManager.setAttributeRequirement(ItemManager.heavyLeggings_1, CombatAttribute.STRENGTH, heavyArmorStrRequirement);
//        AttributeManager.setAttributeRequirement(ItemManager.heavyLeggings_1, CombatAttribute.DEXTERITY, heavyArmorStrRequirement);
//
//        ArmorTierManager.setAttributesPerTierAndWeight(ItemManager.heavyLeggings_1, ArmorTierManager.ArmorType.LEGGINGS, ArmorTierManager.ArmorWeight.HEAVY, 1);
//    }
//
//    private static void createHeavyBoots_1() {
//        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
//        ItemMeta heavyHelmetMeta = item.getItemMeta();
//
//        heavyHelmetMeta.setDisplayName(ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ"));
//
//        item.setItemMeta(heavyHelmetMeta);
//
//        ArrayList<String> lore = new ArrayList<>();
//
//        lore.add(ColorFixer.addColors("&7&o+10 do pancerza"));
//        lore.add(ColorFixer.addColors("&7&oWymagane:"));
//        lore.add(ColorFixer.addColors(" "));
//        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
//        lore.add(ColorFixer.addColors(" "));
//        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
//        lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &r\uD83D\uDCAA:  #a18b3d" + heavyArmorStrRequirement));
//        lore.add(ColorFixer.addColors("&ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &r\uD83D\uDCAA:  #a18b3d" + heavyArmorDexRequirement));
//
//        heavyHelmetMeta.setLore(lore);
//        item.setItemMeta(heavyHelmetMeta);
//
//        ItemManager.heavyBoots_1 = item;
//
//        AttributeManager.setAttributeRequirement(ItemManager.heavyLeggings_1, CombatAttribute.STRENGTH, heavyArmorStrRequirement);
//        AttributeManager.setAttributeRequirement(ItemManager.heavyLeggings_1, CombatAttribute.DEXTERITY, heavyArmorStrRequirement);
//
//        ArmorTierManager.setAttributesPerTierAndWeight(ItemManager.heavyLeggings_1, ArmorTierManager.ArmorType.BOOTS, ArmorTierManager.ArmorWeight.HEAVY, 1);
//    }




}
