package me.xxgradzix.gradzixcombatsystem.items;

import me.xxgradzix.gradzixcombatsystem.items.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.MelleWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.ModifiableWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.ShootableWeapon;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public interface CustomItem {

    NamespacedKey ITEM_CUSTOM_ID_KEY = new NamespacedKey("gradzix_namespace", "gradzix_custom_item_id");

    default void defaultSetItemCustomId(ItemMeta meta) {
        meta.getPersistentDataContainer().set(getItemCustomIdKey(), PersistentDataType.STRING, getCustomId());
    }

    static NamespacedKey getItemCustomIdKey() {
        return ITEM_CUSTOM_ID_KEY;
    }

    String getCustomId();

    static Optional<String> getCustomId(ItemMeta meta) {
        return Optional.ofNullable(meta.getPersistentDataContainer().get(getItemCustomIdKey(), PersistentDataType.STRING));
    }


    ItemStack getDefaultItemStack(Object... optionalArgs);


    String getName(int tier);

    int getModelData(int tier);

    Material getMaterial(int tier);

    /** ITEM CREATION **/


    void addBukkitEnchantments(int tier, ItemMeta meta);

    default void hideAll(ItemMeta meta) {
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.setEnchantmentGlintOverride(false);
    }

    default void setLoreAndName(ItemStack itemStack, int tier) {
        ItemMeta meta = itemStack.getItemMeta();
        setLoreAndName(meta, tier);
        itemStack.setItemMeta(meta);
    }
    default void setLoreAndName(ItemMeta meta, int tier) {

        if(tier == 0) {
            meta.setDisplayName(ColorFixer.addColors("#3e4040") + getName(tier));
            ArrayList<String> lore = new ArrayList<>();
            lore.add("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴡʏʙʀᴀć ᴛᴇɴ ᴘʀᴢᴇᴅᴍɪᴏᴛ".replace("&", "§"));
            meta.setLore(lore);
            return;
        }

        ArrayList<String> lore = new ArrayList<>();

        if(this instanceof ShootableWeapon weapon) {
            lore.add(" ");
            lore.add(MessageManager.weaponDamageWithWords(weapon.getWeaponDamage(tier)));
        }
        if(this instanceof MelleWeapon weapon) {
            lore.add(" ");
            lore.add(MessageManager.weaponDamageWithWords(weapon.getAttackDamage(tier)));
            lore.add(MessageManager.weaponSpeedWithWords(weapon.getAttackSpeed(tier)));
        }

        String qualityName = "";

        if(this instanceof ModifiableWeapon modifiableWeapon) {
            qualityName = modifiableWeapon.getQualityName(meta);
            lore.addAll(modifiableWeapon.getQualityLore(meta));
        }

        lore.addAll(AttributeManager.getRequirementLore(meta));

        String enchantSuffix = "";

        if(this instanceof EnchantableWeapon enchantableWeapon) {
            HashMap<EnchantManager.Enchant, Integer> enchants = EnchantManager.getEnchants(meta);

            for (Map.Entry<EnchantManager.Enchant, Integer> entry : enchants.entrySet()) {
                EnchantManager.Enchant enchant = entry.getKey();
                int level = entry.getValue();
                enchantSuffix = " " + enchant.enchantSuffix + " " + MessageManager.getRomanNumeralsForEnchant(level);
            }

            lore.addAll(enchantableWeapon.getEnchantLore(meta));
        }
        meta.setLore(lore);

        if(qualityName == null || qualityName.isEmpty()) {
            qualityName = ColorFixer.addColors("#3e4040");
        } else {
            qualityName = qualityName + " ";
        }
        meta.setDisplayName(qualityName + getName(tier) + enchantSuffix);
    }

}
