package me.xxgradzix.gradzixcombatsystem.items.armors.instances;

import me.xxgradzix.gradzixcombatsystem.items.armors.ArmorType;
import me.xxgradzix.gradzixcombatsystem.items.armors.CustomArmor;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class HeavyArmor implements CustomArmor {

    public static final String CUSTOM_ID = "gradzixcombat_heavy_armor";

    @Override
    public void setArmorCustomId(ItemMeta meta) {
        meta.getPersistentDataContainer().set(armorCustomId, PersistentDataType.STRING, CUSTOM_ID);
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {

        switch (attribute) {
            case STRENGTH -> {
                return (6 + tier);
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
                displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ʜᴇᴌᴍ");
            }
            case CHESTPLATE -> {
                displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
            }
            case LEGGINGS -> {
                displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ");
            }
            case BOOTS -> {
                displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ʙᴜᴛʏ");
            }
        }
        return displayName + ' ' + MessageManager.getRomanNumerals(tier);
    }

    @Override
    public Material getMaterial(int tier, ArmorType armorType) {
        switch (armorType) {
            case HELMET -> {
                return Material.IRON_HELMET;
            }
            case CHESTPLATE -> {
                return Material.IRON_CHESTPLATE;
            }
            case LEGGINGS -> {
                if(tier >= 5) {
                    return Material.NETHERITE_LEGGINGS;
                } else if (tier >= 3) {
                    return Material.IRON_LEGGINGS;
                } else {
                    return Material.CHAINMAIL_LEGGINGS;
                }
            }
            case BOOTS -> {
                return Material.IRON_BOOTS;
            }
        }
        return Material.STICK;
    }

    @Override
    public Optional<Color> getOptionalColor(int tier, ArmorType armorType) {
        return Optional.empty();
    }

    @Override
    public List<String> getShortDescription(int tier) {
        return Arrays.asList(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
    }


    public static final int HEAVY_ARMOR_HELMET = 2;
    public static final int HEAVY_ARMOR_CHEST_PLATE = 5;
    public static final int HEAVY_ARMOR_LEGGINGS = 3;
    public static final int HEAVY_ARMOR_BOOTS = 2;

    @Override
    public void setModifiers(ItemMeta meta, ArmorType armorType, int tier) {

        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
        meta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        meta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);

        int bonus = tier == 3 ? 1 : tier == 5 ? 2 : 0;

        if(tier > 1) {
            meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(genericArmorToughnessKey, 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
        }
        if(tier > 3) {
            meta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(genericKnockBackResistanceKey, 0.15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
        }

        switch (armorType) {
            case HELMET -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(genericArmorKey, HEAVY_ARMOR_HELMET + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
            }
            case CHESTPLATE -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(genericArmorKey, HEAVY_ARMOR_CHEST_PLATE + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
            }
            case LEGGINGS -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(genericArmorKey, HEAVY_ARMOR_LEGGINGS + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
            }
            case BOOTS -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(genericArmorKey, HEAVY_ARMOR_BOOTS + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
            }
        }
    }
}
