package me.xxgradzix.gradzixcombatsystem.items.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.items.Upgradable;
import me.xxgradzix.gradzixcombatsystem.items.weapons.*;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;

public class BattleBow implements CustomWeapon, ShootableWeapon, EnchantableWeapon, ModifiableWeapon, Upgradable {

    public static final String CUSTOM_ID = "gradzixcombat_battle_bow";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public List<String> getItemPreviewLore() {
        return List.of(
                ColorFixer.addColors("&7ᴛᴀ ʙʀᴏń ᴅʏꜱᴛᴀɴꜱᴏᴡᴀ ᴏꜰᴇʀᴜᴊᴇ ᴜᴍɪᴀʀᴋᴏᴡᴀɴᴇ ᴏʙʀᴀżᴇɴɪᴀ"),
                ColorFixer.addColors("&7ᴛᴏ ʀᴀᴄᴢᴇᴊ ꜱᴢʏʙᴋᴀ ʙʀᴏń"),
                ColorFixer.addColors("&7 "),
                ColorFixer.addColors("&7ᴛᴀ ʙʀᴏń ᴏᴘɪᴇʀᴀ ꜱɪę ɴᴀ ᴀᴛʀʏʙᴜᴛᴀᴄʜ:"),
                ColorFixer.addColors("&8 - " + CombatAttribute.DEXTERITY.getCustomName())
        );
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        switch (tier){
            case 1 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 3;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 1;
            }
            case 2 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 4;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 2;

            }
            case 3 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 6;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 3;

            }
            case 4 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 8;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 4;
            }
            case 5 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 10;
                if(attribute.equals(CombatAttribute.ENDURANCE)) return 5;
            }
        }
        return 0;
    }

    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("ᴌᴜᴋ " + getRomanNumerals(tier));
    }

    @Override
    public int getModelData(int tier) {
        return 5;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.BOW;
    }

    @Override
    public void addBukkitEnchantments(int tier, ItemMeta meta) {
        switch (tier) {
            case 1 -> {
                meta.addEnchant(Enchantment.POWER, 1, true);
            }
            case 2 -> {
                meta.addEnchant(Enchantment.POWER, 2, true);
            }
            case 3 -> {
                meta.addEnchant(Enchantment.POWER, 3, true);
            }
            case 4 -> {
                meta.addEnchant(Enchantment.POWER, 4, true);
            }
            case 5 -> {
                meta.addEnchant(Enchantment.POWER, 5, true);
            }
        }
    }
//    @Override
//    public ItemStack getDefaultItemStack(Object... optionalArgs) {
//
//        int tier = 1;
//        if(optionalArgs.length == 1) {
//            if(optionalArgs[0] instanceof Integer) {
//                tier = (int) optionalArgs[0];
//            }
//        }
//
//
//        ItemStack itemStack = new ItemStack(getMaterial(tier));
//
//        setAttributes(itemStack, tier);
//
//        setTier(itemStack, tier);
//
//        ItemMeta meta = itemStack.getItemMeta();
//
//        addBukkitEnchantments(tier, meta);
//        setWeaponDamage(meta, getWeaponDamage(tier));
//
//        meta.setCustomModelData(getModelData(tier));
//        defaultSetItemCustomId(meta);
//        setLoreAndName(meta, tier);
//        hideAll(meta);
//
//        itemStack.setItemMeta(meta);
//
//        return itemStack;
//    }
    @Override
    public void setEnchantSlots(ItemStack itemStack, int tier) {
        if(tier >= 3) EnchantManager.setMaxSlots(itemStack, 1);

    }

    @Override
    public Set<EnchantManager.Enchant> getApplicableEnchants(int tier) {
        return Set.of(EnchantManager.Enchant.LIFE_STEAL, EnchantManager.Enchant.ARROW_RAIN);
    }

    @Override
    public Set<Class> getApplicableModifications() {
        return Set.of(ModifiersManager.RangeModifier.class, ModifiersManager.UniversalModifier.class);
    }

    @Override
    public double getWeaponDamage(int tier) {
        switch (tier) {
            case 1 -> {
                return 2;
            }
            case 2 -> {
                return 2.5;
            }
            case 3 -> {
                return 3;
            }
            case 4 -> {
                return 3.5;
            }
            case 5 -> {
                return 4;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public List<ItemStack> getRequiredItems(int tier) {
        return List.of(new ItemStack(Material.IRON_INGOT, 4), new ItemStack(Material.OAK_LOG));
    }

    @Override
    public int getRequiredMoney(int tier) {
        return 100;
    }

    @Override
    public boolean isLowerTierItemRequired(int tier) {
        return tier != 1;
    }
}
