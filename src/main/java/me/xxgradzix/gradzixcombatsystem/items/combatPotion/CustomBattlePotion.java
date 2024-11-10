package me.xxgradzix.gradzixcombatsystem.items.combatPotion;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CustomBattlePotion implements CustomItem {

    public static final String CUSTOM_ID = "gradzixcombat_fallen_hero_potion";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public ItemStack getDefaultItemStack(Object... optionalArgs) {


        ItemStack itemStack = new ItemStack(getMaterial(1));

        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(getName(1));

        PotionMeta potionMeta = (PotionMeta) meta;

        potionMeta.setColor(Color.OLIVE);
        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.LUCK, 0, 0, true, false, false), false);

        meta.setCustomModelData(getModelData(0));

        defaultSetItemCustomId(meta);

        setLoreAndName(meta, 1);
        hideAll(meta);

        itemStack.setItemMeta(meta);

        return itemStack;

    }

    @Override
    public void setLoreAndName(ItemMeta meta, int tier) {
        List<String> lore = new ArrayList<>();
        lore.add(" ");

        HashMap<Integer, CustomBattlePotionOrb> orbs = getOrbs(meta);

        for (int i = 1; i <= 2; i++) {
            CustomBattlePotionOrb orb = orbs.get(i);

            if(orb == null) {
                lore.add(ChatColor.GRAY + "ꜱʟᴏᴛ " + i + ": " + ChatColor.RED + "ᴘᴜꜱᴛᴇ");
                continue;
            }

            lore.add(ChatColor.GRAY + "ꜱʟᴏᴛ " + i + ": " + orb.getName(1));
        }

        meta.setLore(lore);
    }


    @Override
    public String getName(int tier) {
        return (ChatColor.DARK_GREEN + ChatColor.BOLD.toString() + "ᴍɪᴋꜱᴛᴜʀᴀ ᴜᴘᴀᴅłᴇɢᴏ ʙᴏʜᴀᴛᴇʀᴀ");
    }

    @Override
    public int getModelData(int tier) {
        return 0;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.POTION;
    }

    @Override
    public void addBukkitEnchantments(int tier, ItemMeta meta) {

    }

    public static NamespacedKey getOrbNamespaceByIndex(int index) {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_fallen_hero_potion_orb_" + index);
    }


    public HashMap<Integer, CustomBattlePotionOrb> getOrbs(ItemMeta meta) {

        HashMap<Integer, CustomBattlePotionOrb> orbsWithIndex = new HashMap<>();

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();

        for (int i = 1; i <= 2; i++) {
            NamespacedKey orbNamespace = getOrbNamespaceByIndex(i);
            String orbID = persistentDataContainer.get(orbNamespace, PersistentDataType.STRING);
            CustomBattlePotionOrb orb = CustomItemManager.getCustomPotionOrbByOrbID(orbID);
            if (orb == null) continue;
            orbsWithIndex.put(i, orb);
        }

        return orbsWithIndex;
    }
    public void setOrbAtIndex(ItemMeta meta, int index, CustomBattlePotionOrb orb) {

        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();

        NamespacedKey orbNamespace = getOrbNamespaceByIndex(index);

        persistentDataContainer.set(orbNamespace, PersistentDataType.STRING, (orb != null ? orb.getCustomId() : ""));

    }


}
