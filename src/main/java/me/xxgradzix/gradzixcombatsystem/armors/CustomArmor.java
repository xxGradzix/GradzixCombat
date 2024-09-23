package me.xxgradzix.gradzixcombatsystem.armors;

import com.google.common.collect.Multimap;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.MelleWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.ModifiableWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.RangeChangeWeapon;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CustomArmor {

    NamespacedKey armorCustomId = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_armor_custom_id");
    NamespacedKey armorTierKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_armor_tier");

    NamespacedKey genericArmorKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_generic_armor_value");
    NamespacedKey genericArmorToughnessKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_generic_armor_toughness");
    NamespacedKey genericKnockBackResistanceKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_generic_knockback_resistance");
    NamespacedKey genericMovementSpeedKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_generic_movement_speed");

    static String geArmorCustomId(ItemStack itemStack) {
        if(itemStack == null) return null;
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null) return null;
        return meta.getPersistentDataContainer().get(armorCustomId, PersistentDataType.STRING);
    }

//    static int getArmorTier(ItemStack itemStack) {
//        return itemStack.getItemMeta().getPersistentDataContainer().getOrDefault(armorTierKey, PersistentDataType.INTEGER, 0);
//    }
    default void setArmorTier(ItemStack itemStack, int tier) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(armorTierKey, PersistentDataType.INTEGER, tier);
        itemStack.setItemMeta(meta);
    }

    default int getArmorTier(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().getOrDefault(armorTierKey, PersistentDataType.INTEGER, 0);
    }

    void setArmorCustomId(ItemMeta meta);

    int getRequiredAttribute(int tier, CombatAttribute attribute);

    String getName(int tier, ArmorTierManager.ArmorType armorType);

    Material getMaterial(int tier, ArmorTierManager.ArmorType armorType);

    Optional<Color> getOptionalColor(int tier, ArmorTierManager.ArmorType armorType);

    default ItemStack getItemStack(int tier, ArmorTierManager.ArmorType armorType) {

        ItemStack itemStack = new ItemStack(getMaterial(tier, armorType));

        setAttributes(itemStack, tier);

        setArmorTier(itemStack, tier);

        ItemMeta meta = itemStack.getItemMeta();

        setArmorCustomId(meta);

        setModifiers(meta, armorType, tier);

        if(this instanceof LeatherArmorMeta leatherArmorMeta) {
            getOptionalColor(tier, armorType).ifPresent(leatherArmorMeta::setColor);
        }

        setLoreAndName(meta, tier, armorType);

        hideAll(meta);

        itemStack.setItemMeta(meta);

        return itemStack;
    }


    default void setAttributes(ItemStack itemStack, int tier) {
        for (CombatAttribute combatAttribute : CombatAttribute.values()) {
            int requiredAttribute = getRequiredAttribute(tier, combatAttribute);
            if(requiredAttribute != 0) {
                AttributeManager.setAttributeRequirement(itemStack, combatAttribute, requiredAttribute);
            }
        }
    }
    default void hideAll(ItemMeta meta) {
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
    }

    List<String> getShortDescription(int tier);

    void setModifiers(ItemMeta meta, ArmorTierManager.ArmorType armorType, int tier);

    default void setLoreAndName(ItemMeta meta, int tier, ArmorTierManager.ArmorType armorType) {

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");

        lore.addAll(getShortDescription(tier));

        lore.addAll(AttributeManager.getRequirementLore(meta));

        Multimap<Attribute, AttributeModifier> attributeModifiers = meta.getAttributeModifiers();

        if(attributeModifiers != null) {
            Collection<AttributeModifier> attributeModifiers1 = attributeModifiers.get(Attribute.GENERIC_ARMOR);
            attributeModifiers1.forEach(attributeModifier -> {
                lore.add(ColorFixer.addColors(" &7&o+" + (int) attributeModifier.getAmount() + " &8pancerza"));
            });
        }
        meta.setLore(lore);

        meta.setDisplayName(getName(tier, armorType));
    }

}
