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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public interface CustomWeapon extends CustomItem, Tierable, Attributable {

    NamespacedKey DEFAULT_ATTACK_DAMAGE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_default_weapon_damage");
    NamespacedKey DEFAULT_ATTACK_SPEED_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_default_weapon_speed");
    NamespacedKey BONUS_ATTACK_RANGE_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_bonus_weapon_range");

    @Override
    String getCustomId();

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

        if(tier == 0) {

            meta.setCustomModelData(getModelData(tier));
            defaultSetItemCustomId(meta);
            setLoreAndName(meta, tier);

            hideAll(meta);
            itemStack.setItemMeta(meta);
            return itemStack;
        }

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
        defaultSetItemCustomId(meta);
        setLoreAndName(meta, tier);
        hideAll(meta);

        itemStack.setItemMeta(meta);

        return itemStack;
    }

}
