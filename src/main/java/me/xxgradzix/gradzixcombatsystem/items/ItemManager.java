package me.xxgradzix.gradzixcombatsystem.items;

import com.google.common.collect.Multimap;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.managers.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class ItemManager {

    private static final int baseHeavyArmorStrRequirement = 6;
    private static final int baseMediumArmorStrRequirement = 3;
    private static final int baseLightArmorStrRequirement = 0;

    private static final int baseMediumArmorDexRequirement = 3;


    private static final HashMap<ArmorTierManager.ArmorType, HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>>> armorsPerTierAndWeight = new HashMap<>();

    public static HashMap<ArmorTierManager.ArmorType, HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>>> getArmorsPerTierAndWeight() {
        return new HashMap<>(armorsPerTierAndWeight);
    }

    public static @NotNull ItemStack getWeapon(WeaponType weaponType, int i) {
        return weaponsPerTier.getOrDefault(weaponType, new HashMap<>()).get(i);
    }

    public enum WeaponType {
        AXE, SWORD, BOW, JAVELIN, SHIELD, CROSSBOW
    }

    private static final HashMap<WeaponType, HashMap<Integer, ItemStack>> weaponsPerTier = new HashMap<>();

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
        HashMap<Integer, ItemStack> tierMap = weaponsPerTier.getOrDefault(WeaponType.JAVELIN, new HashMap<>());
        tierMap.put(1, createAxeWithParameters(WeaponType.JAVELIN, 1));
        weaponsPerTier.put(WeaponType.JAVELIN, tierMap);

    }
    private static ItemStack createArmorPieceWithParameters(ArmorTierManager.ArmorType armorType, int tier, ArmorTierManager.ArmorWeight armorWeight) {
        Material material;
        String displayName;
        Optional<Color> optionalColor = Optional.empty();
        switch (armorType) {
            case HELMET-> {
                switch (armorWeight) {
                    case LIGHT -> {
                        if(tier >= 5) {
                            material = Material.LEATHER_HELMET;
                            optionalColor = Optional.of(Color.GREEN);
                        } else if (tier >= 3) {
                            material = Material.LEATHER_HELMET;
                            optionalColor = Optional.of(Color.WHITE);
                        } else {
                            material = Material.LEATHER_HELMET;
                        }
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
                        if(tier >= 5) {
                            material = Material.LEATHER_CHESTPLATE;
                            optionalColor = Optional.of(Color.GREEN);
                        } else if (tier >= 3) {
                            material = Material.LEATHER_CHESTPLATE;
                            optionalColor = Optional.of(Color.WHITE);
                        } else {
                            material = Material.LEATHER_CHESTPLATE;
                        }
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
                        if (tier >= 5) {
                            material = Material.CHAINMAIL_LEGGINGS;
                        } else if (tier >= 3) {
                            material = Material.CHAINMAIL_LEGGINGS;
                        } else {
                            material = Material.LEATHER_LEGGINGS;
                            optionalColor = Optional.of(Color.MAROON);
                        }
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    case MEDIUM -> {
                        if(tier >= 5) {
                            material = Material.LEATHER_LEGGINGS;
                            optionalColor = Optional.of(Color.GRAY);
                        } else if (tier >= 3) {
                            material = Material.LEATHER_LEGGINGS;
                            optionalColor = Optional.of(Color.GRAY);
                        } else {
                            material = Material.CHAINMAIL_LEGGINGS;
                        }
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    case HEAVY -> {
                        if(tier >= 5) {
                            material = Material.NETHERITE_LEGGINGS;
                        } else if (tier >= 3) {
                            material = Material.IRON_LEGGINGS;
                        } else {
                            material = Material.CHAINMAIL_LEGGINGS;
                        }
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);

                }
            }
            case BOOTS -> {
                switch (armorWeight) {
                    case LIGHT -> {
                        if(tier >= 5) {
                            material = Material.IRON_BOOTS;
                        } else if (tier >= 3) {
                            material = Material.LEATHER_BOOTS;
                            optionalColor = Optional.of(Color.WHITE);
                        } else {
                            material = Material.LEATHER_BOOTS;
                            optionalColor = Optional.of(Color.MAROON);
                        }
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪᴇ ʙᴜᴛʏ");
                    }
                    case MEDIUM -> {
                        if(tier >= 5) {
                            material = Material.IRON_BOOTS;
                        } else if (tier >= 3) {
                            material = Material.CHAINMAIL_BOOTS;
                        } else {
                            material = Material.CHAINMAIL_BOOTS;
                        }
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

        String romeNum = getRomanNumerals(tier);

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

        if(itemMeta instanceof LeatherArmorMeta leatherArmorMeta) {
            optionalColor.ifPresent(leatherArmorMeta::setColor);
//            leatherArmorMeta.setColor(Color.MAROON);
        }

        itemMeta.setDisplayName(ColorFixer.addColors(displayName + " " + romeNum));

        item.setItemMeta(itemMeta);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors(" "));

        Multimap<Attribute, AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers();

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

        if(attributeModifiers != null) {
            Collection<AttributeModifier> attributeModifiers1 = attributeModifiers.get(Attribute.GENERIC_ATTACK_DAMAGE);
            attributeModifiers1.forEach(attributeModifier -> {
                lore.add(ColorFixer.addColors(" &7&o+" + (int) attributeModifier.getAmount() + " &8pancerza"));
            });
        }

        lore.add(ColorFixer.addColors(" ")); // ⚡☄⚔🗡
        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        if(strAttributeRequirement > 0) lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &4⚔:  #a18b3d" + strAttributeRequirement));
        if(dexAttributeRequirement > 0) lore.add(ColorFixer.addColors("&7ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &8☄:  #a18b3d" + dexAttributeRequirement));

        itemMeta.setLore(lore);

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(itemMeta);

        return item;
    }

    private static ItemStack createAxeWithParameters(WeaponType weaponType, int tier) {

        Material material;
        String displayName;

        switch (weaponType) {
            case AXE-> {
                material = Material.IRON_AXE;
                displayName = ColorFixer.addColors("#3e4040ᴛᴏᴘóʀ ʙᴏᴊᴏᴡʏ");
            }
            case SWORD -> {
                material = Material.IRON_SWORD;
                displayName = ColorFixer.addColors("#3e4040ᴅᴌᴜɢɪ ᴍɪᴇᴄᴢ");
            }
            case JAVELIN -> {
                material = Material.TRIDENT;
                displayName = ColorFixer.addColors("#3e4040ᴡᴌóᴄᴢɴɪᴀ");
            }
            case BOW -> {
                material = Material.BOW;
                displayName = ColorFixer.addColors("#3e4040ʁʏᴄᴢᴀ");
            }
            case SHIELD -> {
                material = Material.SHIELD;
                displayName = ColorFixer.addColors("#3e4040ᴛᴀɢᴀ");
            }
            case CROSSBOW -> {
                material = Material.CROSSBOW;
                displayName = ColorFixer.addColors("#3e4040ᴡʏꜱᴛʀᴢᴇᴌɴɪᴄᴀ");
            }
            default -> throw new IllegalStateException("Unexpected value: " + weaponType);
        }

        String romeNum = getRomanNumerals(tier);

        ItemStack item = new ItemStack(material);

        int strAttributeRequirement = 0;
        int dexAttributeRequirement = 0;
        int another = 0;



        if(dexAttributeRequirement > 0) AttributeManager.setAttributeRequirement(item, CombatAttribute.DEXTERITY, baseMediumArmorDexRequirement + tier);
        if(strAttributeRequirement > 0) AttributeManager.setAttributeRequirement(item, CombatAttribute.STRENGTH, strAttributeRequirement);

//        ArmorTierManager.setAttributesPerTierAndWeight(item, weaponType, armorWeight, tier);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(ColorFixer.addColors(displayName + " " + romeNum));

        item.setItemMeta(itemMeta);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors(" "));


        Multimap<Attribute, AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers();

        lore.add(ColorFixer.addColors(" "));
//        switch (armorWeight) {
//            case HEAVY -> {
//                lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
//            }
//            case LIGHT -> {
//                lore.add(ColorFixer.addColors("&7ᴛᴀ ʟᴇᴋᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴘᴏᴅꜱᴛᴀᴡᴏᴡą ᴏʙʀᴏɴę"));
//            }
//            default -> {
//                lore.add(ColorFixer.addColors("&7ᴛᴀ śʀᴇᴅɴɪᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ śʀᴇᴅɴɪą ᴏʙʀᴏɴę"));
//            }
//        }

//        if(attributeModifiers != null) {
//            Collection<AttributeModifier> attributeModifiers1 = attributeModifiers.get(Attribute.GENERIC_ARMOR);
//            attributeModifiers1.forEach(attributeModifier -> {
//                lore.add(ColorFixer.addColors(" &7&o+" + (int) attributeModifier.getAmount() + " &8pancerza"));
//            });
//        }

        lore.add(ColorFixer.addColors(" ")); // ⚡☄⚔🗡
        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
        if(strAttributeRequirement > 0) lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &4⚔:  #a18b3d" + strAttributeRequirement));
        if(dexAttributeRequirement > 0) lore.add(ColorFixer.addColors("&7ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &8☄:  #a18b3d" + dexAttributeRequirement));

        itemMeta.setLore(lore);

        itemMeta.addEnchant(Enchantment.LOYALTY, 1, false);

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);

        return item;
    }

    private static String getRomanNumerals(int num) {
        String romeNum = "";
        switch (num) {
            case 1 -> romeNum = "&7ɪ";
            case 2 -> romeNum = "#877239ɪɪ";
            case 3 -> romeNum = "#68c473ɪɪɪ";
            case 4 -> romeNum = "#4c7ca1ɪᴠ";
            case 5 -> romeNum = "#a30005ᴠ";
            case 6 -> romeNum = "&fᴠɪ";
            case 7 -> romeNum = "ᴠɪɪ";
            case 8 -> romeNum = "ᴠɪɪɪ";
            case 9 -> romeNum = "ɪx";
            case 10 -> romeNum = "x";
        }
        return romeNum;
    }

}
