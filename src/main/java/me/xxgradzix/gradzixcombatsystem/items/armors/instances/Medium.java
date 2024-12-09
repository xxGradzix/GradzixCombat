package me.xxgradzix.gradzixcombatsystem.items.armors.instances;

import me.xxgradzix.gradzixcombatsystem.items.armors.ArmorType;
import me.xxgradzix.gradzixcombatsystem.items.armors.CustomArmor;
import me.xxgradzix.gradzixcombatsystem.items.Upgradable;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Medium implements CustomArmor, Upgradable {

    public static final String CUSTOM_ID = "gradzixcombat_medium_armor";


    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        switch (attribute) {
            case STRENGTH, DEXTERITY -> {
                return (3 + tier);
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public String getName(int tier, ArmorType armorType) {
        String displayName = "";
        switch (armorType) {
            case HELMET -> {
                displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪ ʜᴇᴌᴍ");
            }
            case CHESTPLATE -> {
                displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
            }
            case LEGGINGS -> {
                displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪᴇ ꜱᴘᴏᴅɴɪᴇ");
            }
            case BOOTS -> {
                displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪᴇ ʙᴜᴛʏ");
            }
        }
        return displayName + ' ' + MessageManager.getRomanNumerals(tier);
    }

    @Override
    public Material getMaterial(int tier, ArmorType armorType) {
        switch (armorType) {
            case HELMET -> {
                return Material.CHAINMAIL_HELMET;
            }
            case CHESTPLATE -> {
                return Material.CHAINMAIL_CHESTPLATE;
            }
            case LEGGINGS -> {
                if(tier >= 5) {
                    return Material.LEATHER_LEGGINGS;
                } else if (tier >= 3) {
                    return Material.LEATHER_LEGGINGS;
                } else {
                    return Material.CHAINMAIL_LEGGINGS;
                }
            }
            case BOOTS -> {
                if(tier >= 5) {
                    return Material.IRON_BOOTS;
                } else if (tier >= 3) {
                    return Material.CHAINMAIL_BOOTS;
                } else {
                    return Material.CHAINMAIL_BOOTS;
                }
            }
        }
        return Material.STICK;
    }

    @Override
    public Optional<Color> getOptionalColor(int tier, ArmorType armorType) {
        return Optional.of(Color.GRAY);
    }

    @Override
    public List<String> getShortDescription(int tier) {
        return Arrays.asList(ColorFixer.addColors("&7ᴛᴀ śʀᴇᴅɴɪᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ śʀᴇᴅɴɪą ᴏʙʀᴏɴę"));
    }

    public static final int MEDIUM_ARMOR_HELMET = 1;
    public static final int MEDIUM_ARMOR_CHEST_PLATE = 4;
    public static final int MEDIUM_ARMOR_LEGGINGS = 3;
    public static final int MEDIUM_ARMOR_BOOTS = 2;

    @Override
    public void setModifiers(ItemMeta meta, ArmorType armorType, int tier) {

        ArmorMeta armorMeta = (ArmorMeta) meta;


        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
        meta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        meta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);


        int bonus = tier == 3 ? 1 : tier == 5 ? 2 : 0;

        if(tier > 1) {
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(CustomArmor.genericArmorToughnessKey(armorType), 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
        }
        if(tier > 3) {
            meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(CustomArmor.genericKnockBackResistanceKey(armorType), 0.05, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
        }

        switch (armorType) {
            case HELMET -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(CustomArmor.genericArmorKey(armorType), MEDIUM_ARMOR_HELMET + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD));
            }
            case CHESTPLATE -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(CustomArmor.genericArmorKey(armorType), MEDIUM_ARMOR_CHEST_PLATE + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST));
            }
            case LEGGINGS -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(CustomArmor.genericArmorKey(armorType), MEDIUM_ARMOR_LEGGINGS + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS));
            }
            case BOOTS -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(CustomArmor.genericArmorKey(armorType), MEDIUM_ARMOR_BOOTS + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET));
            }
        }
    }

    @Override
    public List<ItemStack> getRequiredItems(int tier) {
        if(tier < 4) {
            return List.of(new ItemStack(Material.RABBIT_HIDE, 16), new ItemStack(Material.IRON_INGOT, 4));
        } else {
            return List.of(new ItemStack(Material.LEATHER, 16), new ItemStack(Material.IRON_INGOT, 4));
        }
    }

    @Override
    public int getRequiredMoney(int tier) {
        return 0;
    }

    @Override
    public boolean isLowerTierItemRequired(int tier) {
        return tier != 1;
    }

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public String getName(int tier) {
        return getName(tier, ArmorType.HELMET);
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public Material getMaterial(int tier) {
        return getMaterial(tier, ArmorType.HELMET);
    }

    @Override
    public void addBukkitEnchantments(int tier, ItemMeta meta) {

    }
}