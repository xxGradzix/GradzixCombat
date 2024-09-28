package me.xxgradzix.gradzixcombatsystem.managers.EnchantManager;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.items.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.EnchantableWeapon;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class EnchantManager {

    public enum Enchant {
        LIFE_STEAL, FREEZE, BLOOD_LOSS, ATTACK_COMBO
    }

    private static final int MAX_ENCHANTS_GLOBAL_LIMITER = 2;

    private static final NamespacedKey maxEnchantsSlotsKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_max_enchant_slots");
    private static final NamespacedKey enchantsKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_enchants");

    public static boolean addEnchant(ItemStack itemStack, EnchantManager.Enchant enchant) {

        if(containsEnchant(itemStack, enchant)) return false;

        int tier = CustomWeapon.getTier(itemStack);

        CustomWeapon weaponType = ItemManager.getWeaponType(itemStack);

        if(!(weaponType instanceof EnchantableWeapon enchantableWeapon) || !enchantableWeapon.getApplicableEnchants(tier).contains(enchant)) {
            return false;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        int freeEnchantSlots = getFreeEnchantSlots(itemStack);

        if(freeEnchantSlots <= 0) return false;

        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());

        HashMap<EnchantManager.Enchant, Integer> enchantMap = orDefault.getEnchantMap();

        enchantMap.put(enchant, 1);
        orDefault.setEnchantMap(enchantMap);
        persistentDataContainer.set(enchantsKey, new EnchantPersistentDataType(), orDefault);

        weaponType.setLoreAndName(itemMeta, CustomWeapon.getTier(itemStack));

        itemStack.setItemMeta(itemMeta);
        return true;
    }
    public static HashMap<EnchantManager.Enchant, Integer> getEnchants(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        return orDefault.getEnchantMap();
    }

    public static boolean upgradeEnchant(ItemStack itemStack, EnchantManager.Enchant enchant) {
        if(!containsEnchant(itemStack, enchant)) return false;
        if(!canUpgradeEnchant(itemStack, enchant)) return false;

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        HashMap<EnchantManager.Enchant, Integer> enchantMap = orDefault.getEnchantMap();
        int level = enchantMap.get(enchant);
        enchantMap.put(enchant, level + 1);
        orDefault.setEnchantMap(enchantMap);
        persistentDataContainer.set(enchantsKey, new EnchantPersistentDataType(), orDefault);
        CustomWeapon weaponType = ItemManager.getWeaponType(itemStack);
        if(weaponType != null) weaponType.setLoreAndName(itemMeta, CustomWeapon.getTier(itemStack));
        itemStack.setItemMeta(itemMeta);

        return true;
    }

    public static boolean containsEnchant(ItemStack itemStack, EnchantManager.Enchant enchant) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        HashMap<EnchantManager.Enchant, Integer> enchantMap = orDefault.getEnchantMap();
        return enchantMap.containsKey(enchant);
    }
    public static int getEnchantLevel(ItemStack itemStack, Enchant enchant) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta == null) return 0;

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        HashMap<EnchantManager.Enchant, Integer> enchantMap = orDefault.getEnchantMap();
        return enchantMap.getOrDefault(enchant, 0);
    }


    public static boolean canUpgradeEnchant(ItemStack itemStack, EnchantManager.Enchant enchant) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        HashMap<EnchantManager.Enchant, Integer> enchantMap = orDefault.getEnchantMap();
        int level = enchantMap.getOrDefault(enchant, 0);
        if(level >= 3) return false;
        return enchantMap.containsKey(enchant);
    }
    public static int getMaxEnchantSlots(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(maxEnchantsSlotsKey, PersistentDataType.INTEGER, 0);
    }
    public static int getMaxEnchantSlots(ItemMeta itemMeta) {
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        return persistentDataContainer.getOrDefault(maxEnchantsSlotsKey, PersistentDataType.INTEGER, 0);
    }

    public static int getFreeEnchantSlots(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        HashMap<Enchant, Integer> enchantMap = orDefault.getEnchantMap();
        return getMaxEnchantSlots(itemStack) - enchantMap.size();
    }


    private static boolean incrementMaxSlots(ItemStack itemStack, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        int maxEnchantSlots = getMaxEnchantSlots(itemStack);

        if (maxEnchantSlots + amount > MAX_ENCHANTS_GLOBAL_LIMITER) return false;
        persistentDataContainer.set(maxEnchantsSlotsKey, PersistentDataType.INTEGER, maxEnchantSlots + amount);
        itemStack.setItemMeta(itemMeta);
        return true;
    }

    public static boolean setMaxSlots(ItemStack itemStack, int amount) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        if (amount > MAX_ENCHANTS_GLOBAL_LIMITER) return false;
        persistentDataContainer.set(maxEnchantsSlotsKey, PersistentDataType.INTEGER, amount);
        itemStack.setItemMeta(itemMeta);
        return true;
    }

    public static void copyEnchants(ItemStack from, ItemStack to) {
        ItemMeta fromMeta = from.getItemMeta();
        ItemMeta toMeta = to.getItemMeta();

        PersistentDataContainer fromPersistentDataContainer = fromMeta.getPersistentDataContainer();
        PersistentDataContainer toPersistentDataContainer = toMeta.getPersistentDataContainer();

        int maxEnchantSlots = getMaxEnchantSlots(from);

        if(maxEnchantSlots <= 0) return;

        EnchantDto orDefault = fromPersistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        toPersistentDataContainer.set(enchantsKey, new EnchantPersistentDataType(), orDefault);
        toPersistentDataContainer.set(maxEnchantsSlotsKey, PersistentDataType.INTEGER, maxEnchantSlots);

        to.setItemMeta(toMeta);
    }

    public static void copyEnchantsFromMetaToMeta(ItemMeta from, ItemMeta to) {

        PersistentDataContainer fromPersistentDataContainer = from.getPersistentDataContainer();
        PersistentDataContainer toPersistentDataContainer = to.getPersistentDataContainer();

        int maxEnchantSlots = getMaxEnchantSlots(from);
        if(maxEnchantSlots <= 0) return;

        EnchantDto orDefault = fromPersistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        toPersistentDataContainer.set(enchantsKey, new EnchantPersistentDataType(), orDefault);
        toPersistentDataContainer.set(maxEnchantsSlotsKey, PersistentDataType.INTEGER, maxEnchantSlots);
    }

    public static ArrayList<String> getEnchantLore(ItemMeta itemMeta) {
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        HashMap<Enchant, Integer> enchantMap = orDefault.getEnchantMap();

        int maxEnchantSlots = getMaxEnchantSlots(itemMeta);

        if(maxEnchantSlots <= 0) return new ArrayList<>();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        lore.add(ColorFixer.convertColorText("&#790092⎈ &#7B2798ᴢ&#7B3A9Aᴀ&#7C4D9Dᴋ&#9562A8ʟ&#AD76B2ę&#C68BBDᴄ&#B368B2ɪ&#A046A8ᴀ &#790092⎈"));

        for (Enchant enchant : enchantMap.keySet()) {
            switch (enchant) {
                case LIFE_STEAL -> {
                    lore.add(ColorFixer.addColors("&7ᴢᴀᴋʟęᴄɪᴇ &cᴋʀᴀᴅᴢɪᴇżʏ ᴢᴅʀᴏᴡɪᴀ " + MessageManager.getRomanNumerals(enchantMap.get(enchant))));
                }
                case FREEZE ->{
                    lore.add(ColorFixer.addColors("&7ᴢᴀᴋʟęᴄɪᴇ &bᴢᴀᴍʀᴏżᴇɴɪᴀ " + MessageManager.getRomanNumerals(enchantMap.get(enchant))));
                }
                case ATTACK_COMBO -> {
                    lore.add(ColorFixer.addColors("&7ᴢᴀᴋʟęᴄɪᴇ &eᴋᴏᴍʙᴏꜱóᴡ " + MessageManager.getRomanNumerals(enchantMap.get(enchant))));
                }
            }
        }
        for (int i = 0; i < maxEnchantSlots - enchantMap.size(); i++) {
            lore.add(ColorFixer.addColors("#874E92✦ &7ᴘᴜꜱᴛᴇ ᴘᴏʟᴇ ɴᴀ ᴢᴀᴋʟęᴄɪᴇ"));
        }
        return lore;
    }
}
