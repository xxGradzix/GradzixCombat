package me.xxgradzix.gradzixcombatsystem.items.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.Upgradable;
import me.xxgradzix.gradzixcombatsystem.items.weapons.*;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

public class BattleShield implements CustomItem, Attributable, Upgradable, EnchantableWeapon {

    public static final String CUSTOM_ID = "gradzixcombat_battle_shield";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public ItemStack getDefaultItemStack(Object... optionalArgs) {

        int tier = 1;

//        if (optionalArgs.length == 1) {
//            if (optionalArgs[0] instanceof Integer) {
//                tier = (int) optionalArgs[0];
////                return getItemStack(tier);
//            }
//        }

        ItemStack itemStack = new ItemStack(Material.SHIELD);
//        setTier(itemStack, tier);

//        if(this instanceof EnchantableWeapon enchantableWeapon) {
            setEnchantSlots(itemStack, tier);
//        }


        setAttributes(itemStack, tier);

        ItemMeta meta = itemStack.getItemMeta();

        meta.setCustomModelData(getModelData(tier));
        defaultSetItemCustomId(meta);
        setLoreAndName(meta, tier);
        hideAll(meta);

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {

        if(attribute.equals(CombatAttribute.STRENGTH)) return 5;
        if(attribute.equals(CombatAttribute.ENDURANCE)) return 5;
        return 0;
    }

    public String getName(int tier) {
        return ColorFixer.addColors("ᴛᴀʀᴄᴢᴀ ");
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public Material getMaterial(int tier) {
        return null;
    }

    @Override
    public void addBukkitEnchantments(int tier, ItemMeta meta) {

    }

    @Override
    public List<ItemStack> getRequiredItems(int tier) {
        return List.of(new ItemStack(Material.IRON_INGOT, 5), new ItemStack(Material.OAK_LOG, 5));
    }

    @Override
    public int getRequiredMoney(int tier) {
        return 100;
    }

    @Override
    public boolean isLowerTierItemRequired(int tier) {
        return false;
    }

    @Override
    public void setEnchantSlots(ItemStack itemStack, int tier) {
        EnchantManager.setMaxSlots(itemStack, 1);
    }

    @Override
    public Set<EnchantManager.Enchant> getApplicableEnchants(int tier) {
        return Set.of(EnchantManager.Enchant.SOUL_STEAL);
    }
}
