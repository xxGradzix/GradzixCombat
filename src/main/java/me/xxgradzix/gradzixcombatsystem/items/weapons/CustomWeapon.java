package me.xxgradzix.gradzixcombatsystem.items.weapons;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public interface CustomWeapon extends CustomItem {

//    NamespacedKey weaponCustomIdKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_weapon_custom_id");
    NamespacedKey weaponTierKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_weapon_tier");

    NamespacedKey DEFAULT_ATTACK_DAMAGE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_default_weapon_damage");
    NamespacedKey DEFAULT_ATTACK_SPEED_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_default_weapon_speed");
    NamespacedKey BONUS_ATTACK_RANGE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_bonus_weapon_range");

    @Override
    String getCustomId();

//    @Override
//    default ItemStack getDefaultItemStack() {
//        return getItemStack(1);
//    }

    static int getTier(ItemStack itemStack) {
        return itemStack.getItemMeta().getPersistentDataContainer().getOrDefault(weaponTierKey, PersistentDataType.INTEGER, 0);
    }

    int getRequiredAttribute(int tier, CombatAttribute attribute);

    String getName(int tier);

    int getModelData(int tier);

    Material getMaterial(int tier);

    /** ITEM CREATION **/

    default void setTier(ItemStack itemStack, int tier) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(weaponTierKey, PersistentDataType.INTEGER, tier);
        itemStack.setItemMeta(meta);
    }

    void addBukkitEnchantments(int tier, ItemMeta meta);

    @Override
    default ItemStack getDefaultItemStack(Object... optionalArgs) {

        int tier = 1;
        if(optionalArgs.length == 1) {
            if(optionalArgs[0] instanceof Integer) {
                tier = (int) optionalArgs[0];
            }
        }

        ItemStack itemStack = new ItemStack(getMaterial(tier));

        if(this instanceof EnchantableWeapon enchantableWeapon) {
            enchantableWeapon.setEnchantSlots(itemStack, tier);
        }

        setAttributes(itemStack, tier);
        setTier(itemStack, tier);

        ItemMeta meta = itemStack.getItemMeta();

        addBukkitEnchantments(tier, meta);

        if(this instanceof MelleWeapon melleWeapon) {
            if(meta.getAttributeModifiers() != null) meta.getAttributeModifiers().clear();
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(DEFAULT_ATTACK_DAMAGE_KEY, melleWeapon.getAttackDamage(tier), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(DEFAULT_ATTACK_SPEED_KEY,  -(4 - melleWeapon.getAttackSpeed(tier)), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));
        }
        if(this instanceof RangeChangeWeapon rangeChangeWeapon) {


            double range = rangeChangeWeapon.getRange(tier);
            meta.addAttributeModifier(Attribute.PLAYER_ENTITY_INTERACTION_RANGE, new AttributeModifier(BONUS_ATTACK_RANGE_KEY, range, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND));

        }

        meta.setCustomModelData(getModelData(tier));
//        setWeaponCustomId(meta);
        defaultSetItemCustomId(meta);
        setLoreAndName(meta, tier);
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

    default void setLoreAndName(ItemMeta meta, int tier) {

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");

        if(this instanceof ShootableWeapon weapon) {
            lore.add(MessageManager.weaponDamageWithWords(weapon.getWeaponDamage(tier)));
        }

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

}
