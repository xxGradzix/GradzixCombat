package me.xxgradzix.gradzixcombatsystem.items.armors;

import com.google.common.collect.Multimap;
import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.Upgradable;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Attributable;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CustomArmor extends CustomItem, Attributable, Upgradable, Tierable {


    static NamespacedKey genericArmorKey(ArmorType armorType) {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_generic_armor_value_" + armorType.name().toLowerCase());
    }
    static NamespacedKey genericArmorToughnessKey(ArmorType armorType) {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_generic_armor_toughness_" + armorType.name().toLowerCase());
    }
    static NamespacedKey genericKnockBackResistanceKey(ArmorType armorType) {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_generic_knockback_resistance_" + armorType.name().toLowerCase());
    }
    static NamespacedKey genericMovementSpeedKey(ArmorType armorType) {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_generic_movement_speed_" + armorType.name().toLowerCase());
    }

    Material getMaterial(int tier, ArmorType armorType);

    String getName(int tier, ArmorType armorType);

    Optional<Color> getOptionalColor(int tier, ArmorType armorType);


    @Override
    default ItemStack getDefaultItemStack(Object... optionalArgs) {
        int tier = 1;
        ArmorType armorType = ArmorType.HELMET;
        if(optionalArgs.length == 2) {
            if(optionalArgs[0] instanceof Integer) tier = (int) optionalArgs[0];
            if(optionalArgs[1] instanceof ArmorType) armorType = (ArmorType) optionalArgs[1];
        }
        ItemStack itemStack = new ItemStack(getMaterial(tier, armorType));
        setAttributes(itemStack, tier);
        setTier(itemStack, tier);
        ItemMeta meta = itemStack.getItemMeta();
        defaultSetItemCustomId(meta);
        setModifiers(meta, armorType, tier);
        if(meta instanceof LeatherArmorMeta leatherArmorMeta) getOptionalColor(tier, armorType).ifPresent(leatherArmorMeta::setColor);
        setLoreAndName(meta, tier, armorType);
        hideAll(meta);
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    List<String> getShortDescription(int tier);

    void setModifiers(ItemMeta meta, ArmorType armorType, int tier);

    default void setLoreAndName(ItemMeta meta, int tier, ArmorType armorType) {

        if(tier == 0) {
            meta.setDisplayName(ColorFixer.addColors("#3e4040") + getName(tier));
            ArrayList<String> lore = new ArrayList<>();
            lore.add("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴡʏʙʀᴀć ᴛᴇɴ ᴘʀᴢᴇᴅᴍɪᴏᴛ".replace("&", "§"));
            return;
        }

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
