package me.xxgradzix.gradzixcombatsystem.items.projectiles;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.MelleWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.ModifiableWeapon;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public interface CustomArrow extends CustomItem {

    static double getSavedArrowDamage(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        return meta.getPersistentDataContainer().getOrDefault(arrowDamageKey, PersistentDataType.DOUBLE, 0.0);
    }


    default void setArrowDamage(ItemMeta itemMeta, double damage) {
        itemMeta.getPersistentDataContainer().set(arrowDamageKey, PersistentDataType.DOUBLE, damage);
    }

    NamespacedKey weaponTierKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_arrow_tier");
    NamespacedKey arrowDamageKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_arrow_damage");

    double getArrowDamage(int tier);

    @Override
    String getCustomId();

    default void setTier(ItemStack itemStack, int tier) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(weaponTierKey, PersistentDataType.INTEGER, tier);
        itemStack.setItemMeta(meta);
    }

    int getModelData(int tier);

    @Override
    default ItemStack getDefaultItemStack(Object... optionalArgs) {

        int tier = 1;
        if(optionalArgs.length == 1) {
            if(optionalArgs[0] instanceof Integer) {
                tier = (int) optionalArgs[0];
            }
        }

        ItemStack itemStack = new ItemStack(Material.ARROW);

        setTier(itemStack, tier);

        ItemMeta meta = itemStack.getItemMeta();

        setArrowDamage(meta, getArrowDamage(tier));

        meta.setCustomModelData(getModelData(tier));
        defaultSetItemCustomId(meta);
        setAttributes(itemStack, tier);
        setLoreAndName(meta, tier);
        hideAll(meta);

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    default void hideAll(ItemMeta meta) {
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
    }

    default void setLoreAndName(ItemMeta meta, int tier) {

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");

        lore.add(MessageManager.weaponDamageWithWords(getArrowDamage(tier)));

        String qualityName = "";

        if(this instanceof ModifiableWeapon modifiableWeapon) {
            qualityName = modifiableWeapon.getQualityName(meta);
            lore.addAll(modifiableWeapon.getQualityLore(meta));
        }

        lore.addAll(AttributeManager.getRequirementLore(meta));

        if(this instanceof EnchantableWeapon enchantableWeapon) {
            lore.addAll(enchantableWeapon.getEnchantLore(meta));
        }
        meta.setLore(lore);

        if(qualityName == null || qualityName.isEmpty()) {
            qualityName = ColorFixer.addColors("#3e4040");
        } else {
            qualityName = qualityName + " ";
        }
        meta.setDisplayName(qualityName + getName(tier));

    }
    static int getTier(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().getOrDefault(weaponTierKey, PersistentDataType.INTEGER, 0);
    }

    default void setAttributes(ItemStack itemStack, int tier) {
        for (CombatAttribute combatAttribute : CombatAttribute.values()) {
            int requiredAttribute = getRequiredAttribute(tier, combatAttribute);
            if(requiredAttribute != 0) {
                AttributeManager.setAttributeRequirement(itemStack, combatAttribute, requiredAttribute);
            }
        }
    }
    int getRequiredAttribute(int tier, CombatAttribute attribute);

    String getName(int tier);


}
