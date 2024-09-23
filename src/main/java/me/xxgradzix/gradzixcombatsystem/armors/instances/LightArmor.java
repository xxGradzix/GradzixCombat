package me.xxgradzix.gradzixcombatsystem.armors.instances;

import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.armors.CustomArmor;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class LightArmor implements CustomArmor, UpgradableArmor {

    public static final String CUSTOM_ID = "gradzixcombat_light_armor";

    @Override
    public void setArmorCustomId(ItemMeta meta) {
        meta.getPersistentDataContainer().set(armorCustomId, PersistentDataType.STRING, CUSTOM_ID);
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {

        switch (attribute) {
            case STRENGTH -> {
                return (tier / 2);
            }
            case DEXTERITY -> {
                return tier;
            }
            default -> {
                return 0;
            }
        }

    }

    @Override
    public String getName(int tier, ArmorTierManager.ArmorType armorType) {
        String displayName = "";
        switch (armorType) {
            case HELMET -> {
                displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪ ʜᴇᴌᴍ");
            }
            case CHESTPLATE -> {
                displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
            }
            case LEGGINGS -> {
                displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ");
            }
            case BOOTS -> {
                displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪᴇ ʙᴜᴛʏ");
            }
        }
        return displayName + ' ' + MessageManager.getRomanNumerals(tier);
    }

    @Override
    public Material getMaterial(int tier, ArmorTierManager.ArmorType armorType) {
        switch (armorType) {
            case HELMET -> {
                if(tier >= 5) {
                    return Material.LEATHER_HELMET;
                } else if (tier >= 3) {
                    return Material.LEATHER_HELMET;
                } else {
                    return Material.LEATHER_HELMET;
                }
            }
            case CHESTPLATE -> {
                if(tier >= 5) {
                    return Material.LEATHER_CHESTPLATE;
                } else if (tier >= 3) {
                    return Material.LEATHER_CHESTPLATE;
                } else {
                    return Material.LEATHER_CHESTPLATE;
                }
            }
            case LEGGINGS -> {
                if (tier >= 5) {
                    return Material.CHAINMAIL_LEGGINGS;
                } else if (tier >= 3) {
                    return Material.CHAINMAIL_LEGGINGS;
                } else {
                    return Material.LEATHER_LEGGINGS;
                }
            }
            case BOOTS -> {
                if(tier >= 5) {
                    return Material.IRON_BOOTS;
                } else if (tier >= 3) {
                    return Material.LEATHER_BOOTS;
                } else {
                    return Material.LEATHER_BOOTS;
                }
            }
        }
        return Material.STICK;
    }

    @Override
    public Optional<Color> getOptionalColor(int tier, ArmorTierManager.ArmorType armorType) {
        switch (armorType) {
            case HELMET, CHESTPLATE -> {
                if(tier >= 5) {
                    return Optional.of(Color.GREEN);
                } else if (tier >= 3) {
                    return Optional.of(Color.WHITE);
                } else {
                    return Optional.of(Color.MAROON);
                }
            }
            case LEGGINGS -> {
                if(tier < 3) {
                    return Optional.of(Color.MAROON);
                }
            }
            case BOOTS -> {
                if (tier < 5) {
                    return Optional.of(Color.MAROON);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<String> getShortDescription(int tier) {
        return Arrays.asList(ColorFixer.addColors("&7ᴛᴀ ʟᴇᴋᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴘᴏᴅꜱᴛᴀᴡᴏᴡą ᴏʙʀᴏɴę"));
    }


    public static final int LIGHT_ARMOR_HELMET = 1;
    public static final int LIGHT_ARMOR_CHEST_PLATE = 3;
    public static final int LIGHT_ARMOR_LEGGINGS = 2;
    public static final int LIGHT_ARMOR_BOOTS = 2;

    @Override
    public void setModifiers(ItemMeta meta, ArmorTierManager.ArmorType armorType, int tier) {

        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
        meta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        meta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);

        int bonus = tier == 3 ? 1 : tier == 5 ? 2 : 0;

        if(tier > 3) {
            meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(genericMovementSpeedKey, 0.005, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
        } else if(tier > 1) {
            meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(genericMovementSpeedKey, 0.003, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
        }
        switch (armorType) {
            case HELMET -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(genericArmorKey, LIGHT_ARMOR_HELMET + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
            }
            case CHESTPLATE -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(genericArmorKey, LIGHT_ARMOR_CHEST_PLATE + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
            }
            case LEGGINGS -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(genericArmorKey, LIGHT_ARMOR_LEGGINGS + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
            }
            case BOOTS -> {
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(genericArmorKey, LIGHT_ARMOR_BOOTS + bonus, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ARMOR));
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
}
