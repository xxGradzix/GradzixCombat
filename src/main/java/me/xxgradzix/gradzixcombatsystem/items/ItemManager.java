package me.xxgradzix.gradzixcombatsystem.items;

import me.xxgradzix.gradzixcombatsystem.items.enchantBooks.CustomEnchantBook;
import me.xxgradzix.gradzixcombatsystem.managers.EconomyManager;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem.MAX_TIER;
import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getHaveRequiredItemLoreString;
import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;


public class ItemManager {

    /**  ITEMS. **/

    public static ItemStack restartPotion;
    public static ItemStack stoneOfAggression;

    public static ItemStack invisibleFiller;

    public static ItemStack lightArmorButtonItem;
    public static ItemStack mediumArmorButtonItem;
    public static ItemStack heavyArmorButtonItem;

    public static ItemStack weaponButtonItem;
    public static ItemStack armorButtonItem;
    public static ItemStack otherButtonItem;
    public static ItemStack reforgeButtonItem;

    public static ItemStack moreItemsItemButton;

    /** OTHER ITEMS METHODS **/

    public static void init() {
        createRestartPotion();
        createAggressionStone();

        createInvisibleFiller();

        createLightArmorButtonItem();
        createMediumArmorButtonItem();
        createHeavyArmorButtonItem();

        createWeaponButtonItem();
        createArmorButtonItem();
        createOthersButtonItem();
        createReforgeButtonItem();

        createMoreItemsButton();
    }

    public static @NotNull ItemStack getAttributeItem(Player player, CombatAttribute attribute, int attributeLevel) {
        Material material;
        String displayName;
        switch (attribute) {
            case STRENGTH -> {
                material = Material.RED_DYE;
                displayName = ColorFixer.addColors("#3e4040&lꜱɪᴌᴀ");
            }
            case ENDURANCE -> {
                material = Material.CYAN_DYE;
                displayName = ColorFixer.addColors("#3e4040&lᴡʏᴛʀᴢʏᴍᴀᴌᴏść");
            }
            case DEXTERITY -> {
                material = Material.LIME_DYE;
                displayName = ColorFixer.addColors("#3e4040&lᴢʀęᴄᴢɴᴏść");
            }
            case INTELLIGENCE -> {
                material = Material.PURPLE_DYE;
                displayName = ColorFixer.addColors("#3e4040&lɪɴᴛᴇʟɪɢᴇɴᴄᴊᴀ");
            }
            default -> {
                material = Material.BARRIER;
                displayName = "Unknown";
            }
        }
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName((displayName));
        ArrayList<String> lore = new ArrayList<>();

        lore.add(" ");
        lore.add(ColorFixer.addColors("&7ᴀᴋᴛᴜᴀʟɴɪᴇ ᴘᴏꜱɪᴀᴅᴀꜱᴢ&8: &b" + attributeLevel));
        lore.add(ColorFixer.addColors("&7ᴡᴏʟɴʏᴄʜ ᴘᴜɴᴋᴛóᴡ&8: &b" + AttributeManager.getFreeAttributePoints(player)));
        lore.add(" ");
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴜʟᴇᴘꜱᴢʏć"));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    public static ItemStack getTierItem(int tier) {
        ItemStack item = new ItemStack(Material.GHAST_TEAR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴛɪᴇʀ " + getRomanNumerals(tier))));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴡʏᴛᴡᴏʀᴢᴏɴʏ ᴘʀᴢᴇᴅᴍɪᴏᴛ ʙęᴅᴢɪᴇ ᴛᴇɢᴏ ᴛɪᴇʀᴜ"));
        itemMeta.setLore(lore);
        itemMeta.setCustomModelData(10000 + tier);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getSmallLeftArrowItem(boolean isActive, int previousTier) {
        ItemStack item = new ItemStack(Material.GHAST_TEAR);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴘᴏᴘʀᴢᴇᴅɴɪ ᴛɪᴇʀ")));

        ArrayList<String> lore = new ArrayList<>();

        if (previousTier == 0) {
            lore.add(ColorFixer.addColors("&7ᴀᴋᴛᴜᴀʟɴɪᴇ ᴜꜱᴛᴀᴡɪᴏɴʏ ᴊᴇꜱᴛ ɴᴀᴊɴɪżꜱᴢʏ ᴛɪᴇʀ"));
        } else {
            lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴢᴍɪᴇɴɪć ᴀᴋᴛᴜᴀʟɴʏ ᴛɪᴇʀ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ɴᴀ: " + getRomanNumerals(previousTier)));
        }
        itemMeta.setLore(lore);

        itemMeta.setCustomModelData(99000 + (isActive ? 2 : 1));
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getSmallRightArrowItem(boolean isActive, int nextTier) {
        ItemStack item = new ItemStack(Material.GHAST_TEAR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName((ColorFixer.addColors("&7ɴᴀꜱᴛęᴘɴʏ ᴛɪᴇʀ")));
        ArrayList<String> lore = new ArrayList<>();
        if(nextTier == MAX_TIER + 1) {
            lore.add(ColorFixer.addColors("&7ᴀᴋᴛᴜᴀʟɴɪᴇ ᴜꜱᴛᴀᴡɪᴏɴʏ ᴊᴇꜱᴛ ɴᴀᴊᴡʏżꜱᴢʏ ᴛɪᴇʀ"));
        } else {
            lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴢᴍɪᴇɴɪć ᴀᴋᴛᴜᴀʟɴʏ ᴛɪᴇʀ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ɴᴀ: " + getRomanNumerals(nextTier)));
        }

        itemMeta.setLore(lore);

        itemMeta.setCustomModelData(66000 + (isActive ? 2 : 1));
        item.setItemMeta(itemMeta);
        return item;
    }



    public static ItemStack upgradeWeaponButton(Player player, List<ItemStack> itemsNeeded, ItemStack previousItem, int price) {
        ItemStack item = new ItemStack(Material.MAP);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setCustomModelData(1010);

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        itemMeta.setDisplayName((ColorFixer.addColors("&7ꜱᴛᴡóʀᴢ")));

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");

        if(previousItem != null) {
            String haveRequiredItemLoreString = getHaveRequiredItemLoreString(player, previousItem);
            lore.add(ColorFixer.addColors(haveRequiredItemLoreString));
        }

        for (ItemStack itemStack : itemsNeeded) {
            String haveRequiredItemLoreString = getHaveRequiredItemLoreString(player, itemStack);
            lore.add(ColorFixer.addColors(haveRequiredItemLoreString));
        }

        double balance = EconomyManager.getBalance(player);


        boolean hasMoney = balance >= price;

        StringBuilder string = new StringBuilder("&8• " + (hasMoney ? "&a&l✓" : "&c&l✗") + (hasMoney ? "&6 " : "&c ") + (int)Math.min(price, balance) + "&7/" + (hasMoney ? "&6" : "&c") + price);

        while (string.length() < 22) {
            string.append(" ");
        }
        string.append(" ").append("&7 ᴘɪᴇɴɪęᴅᴢʏ");
        lore.add(ColorFixer.addColors(string.toString()).replace(" ", "\u2007"));

        lore.add(" ");
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ꜱᴛᴡᴏʀᴢʏć ᴛᴇɴ ᴘʀᴢᴇᴅᴍɪᴏᴛ"));

        if(itemsNeeded.isEmpty() && previousItem == null && price == 0) {
            lore.clear();
            lore.add(ColorFixer.addColors("&7ᴡʏʙɪᴇʀᴢ ᴘʀᴢᴇᴅᴍɪᴏᴛ ᴅᴏ ꜱᴛᴡᴏʀᴢᴇɴɪᴀ ᴢ ᴘᴀꜱᴋᴜ ᴏʙᴏᴋ ᴀʟʙᴏ"));
            lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴘʀᴢᴇᴅᴍɪᴏᴛ ᴋᴛóʀʏ ᴄʜᴄᴇꜱᴢ ᴜʟᴇᴘꜱᴢʏć ᴢᴇ ꜱᴡᴏᴊᴇɢᴏ ᴇᴋᴡɪᴘᴜɴᴋᴜ"));
        }

        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return item;

    }


    public static ItemStack enchantWeaponButton(Player player, List<ItemStack> itemsNeeded, ItemStack itemToGet, int price) {
        ItemStack item = new ItemStack(Material.MAP);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setCustomModelData(1010);

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴢᴀᴄᴢᴀʀᴜᴊ")));

        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        for (ItemStack itemStack : itemsNeeded) {

            String haveRequiredItemLoreString = getHaveRequiredItemLoreString(player, itemStack);
            lore.add(ColorFixer.addColors(haveRequiredItemLoreString));
        }

        double balance = EconomyManager.getBalance(player);

        boolean hasMoney = balance >= price;

        StringBuilder string = new StringBuilder("&8• " + (hasMoney ? "&a&l✓" : "&c&l✗") + (hasMoney ? "&6 " : "&c ") + (int)Math.min(price, balance) + "&7/" + (hasMoney ? "&6" : "&c") + price);

        while (string.length() < 22) {
            string.append(" ");
        }
        string.append(" ").append("&7 ᴘɪᴇɴɪęᴅᴢʏ");
        lore.add(ColorFixer.addColors(string.toString()).replace(" ", "\u2007"));

        lore.add(" ");
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ꜱᴛᴡᴏʀᴢʏć ᴛᴇɴ ᴘʀᴢᴇᴅᴍɪᴏᴛ"));

        if(itemsNeeded.isEmpty() && itemToGet == null && price == 0) {
            lore.clear();
            lore.add(ColorFixer.addColors("&7ᴡʏʙɪᴇʀᴢ ᴘʀᴢᴇᴅᴍɪᴏᴛ ᴅᴏ ᴢᴀᴄᴢᴀʀᴏᴡᴀɴɪᴀ, ᴡłóż ɢᴏ ᴡ ᴘᴏʟᴇ"));
            lore.add(ColorFixer.addColors("&7ɪ ᴡʏʙɪᴇʀᴢ ᴊᴇᴅᴇɴ ᴢ ᴅᴏꜱᴛęᴘɴʏᴄʜ ᴇɴᴄʜᴀɴᴛóᴡ ᴢ ᴘᴀɴᴇʟᴜ ᴜ ɢóʀʏ"));
        } else if(itemToGet != null) {
            lore.add(" ");
            lore.add(ColorFixer.addColors("&7ᴡʏʙɪᴇʀᴢ ᴇɴᴄʜᴀɴᴛ ᴡᴇᴀᴘᴏɴᴜ"));
            lore.add(ColorFixer.addColors("&7ɪ ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴢᴀᴄᴢᴀʀᴏᴡᴀć"));
            int atrr = AttributeManager.getAttributeRequirement(itemToGet, CombatAttribute.INTELLIGENCE);
            if ( atrr != 0) {
                lore.add(" ");
                lore.add(ColorFixer.addColors("&7ᴛᴀ ʙʀᴏń ʙęᴅᴢɪᴇ ᴡʏᴍᴀɢᴀć: &b" + atrr + " ɪɴᴛᴇʟɪɢᴇɴᴄᴊɪ"));
            }
        }

        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        return item;

    }


    private static void createRestartPotion() {


        ItemStack item = new ItemStack(Material.POTION);

        PotionMeta itemMeta = (PotionMeta) item.getItemMeta();

        itemMeta.setBasePotionType(PotionType.AWKWARD);
        itemMeta.setColor(Color.WHITE);
        itemMeta.setDisplayName((ColorFixer.addColors("&bᴍɪᴋꜱᴛᴜʀᴀ ᴏᴄᴢʏꜱᴢᴄᴢᴇɴɪᴀ")));

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors(" "));

        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴍɪᴋꜱᴛᴜʀᴀ ᴢᴡʀóᴄɪ ᴡꜱᴢʏꜱᴛᴋɪᴇ ᴘᴜɴᴋᴛʏ ᴀᴛʀʏʙᴜᴛóᴡ ɪ ᴘᴏᴢᴡᴏʟɪ ᴊᴇ ᴘᴏɴᴏᴡɴɪᴇ ʀᴏᴢᴅʏꜱᴘᴏɴᴏᴡᴀć"));

        itemMeta.setLore(lore);

        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);

        item.setItemMeta(itemMeta);

        restartPotion = item;
    }

    private static void createAggressionStone() {


        ItemStack item = new ItemStack(Material.FIREWORK_STAR);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName((ColorFixer.addColors("&4ᴋᴀᴍɪᴇɴɪᴇ ᴀɢʀᴇꜱᴊɪ")));

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors(" "));

        itemMeta.setLore(lore);

        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);

        stoneOfAggression = item;
    }

    public static ItemStack createReforgeItem(int price, String quality, ArrayList<String> modifierLore) {
        ItemStack item = new ItemStack(Material.MAP);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setCustomModelData(1010);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴘʀᴢᴇᴋᴜᴊ")));

        ArrayList<String> lore = new ArrayList<>();

        if(price > 0) {

            lore.add(ColorFixer.addColors("&7ᴏʙᴇᴄɴʏ ᴍᴏᴅʏꜰɪᴋᴀᴛᴏʀ: " + quality));
            lore.addAll(modifierLore);

            lore.add(ColorFixer.addColors(" "));
            lore.add(ColorFixer.addColors("&7ᴄᴇɴᴀ: &b" + price + " ᴍᴏɴᴇᴛ"));
        } else {
            lore.add(ColorFixer.addColors("&7ᴘᴏᴌóż ᴘʀᴢᴇᴅᴍɪᴏᴛ ᴡ ᴘᴏʟᴜ ᴀʙʏ ᴘʀᴢᴇᴋᴜć"));
        }

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getDamageItem(double damage) {
        ItemStack item = new ItemStack(Material.NAUTILUS_SHELL);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴛᴀ ʙʀᴏń ᴢᴀᴅᴀ: &e" + damage + "ᴏʙʀᴀżᴇń")));

        itemMeta.setCustomModelData(111000 + (int) damage);

        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getSpeedItem(double speed) {
        ItemStack item = new ItemStack(Material.NAUTILUS_SHELL);

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴛᴀ ʙʀᴏń ᴜᴅᴇʀᴢᴀ &b" + speed + "&7 ʀᴀᴢʏ ɴᴀ &bꜱᴇᴋᴜɴᴅę")));

        itemMeta.setCustomModelData(222000 +  (int)(speed*10));

        item.setItemMeta(itemMeta);
        return item;
    }

    private static void createInvisibleFiller() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((""));
        itemMeta.setHideTooltip(true);
        item.setItemMeta(itemMeta);
        invisibleFiller = item;
    }

    private static void createLightArmorButtonItem() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴘᴀɴᴄᴇʀᴢ " + "&#305F9Cʟ&#3B659Eᴇ&#466CA1ᴋ&#5072A3ᴋ&#5B78A5ɪ")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴏᴛᴡᴏʀᴢʏć ᴍᴇɴᴜ ᴛᴡᴏʀᴢᴇɴɪᴀ " + "&#305F9Cʟ&#36639Dᴇ&#3C669Fᴋ&#426AA0ᴋ&#496DA1ɪ&#4F71A2ᴇ&#5574A4ɢ&#5B78A5ᴏ" + "&7 ᴘᴀɴᴄᴇʀᴢᴀ"));
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        lightArmorButtonItem = item;
    }

    private static void createMediumArmorButtonItem() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴘᴀɴᴄᴇʀᴢ " + "&#CC691Aś&#CD7027ʀ&#CE7734ᴇ&#D07D40ᴅ&#D1844Dɴ&#D28B5Aɪ")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴏᴛᴡᴏʀᴢʏć ᴍᴇɴᴜ ᴛᴡᴏʀᴢᴇɴɪᴀ " + "&#CC691Aś&#CD6D22ʀ&#CE722Aᴇ&#CE7632ᴅ&#CF7A3Aɴ&#D07E42ɪ&#D1834Aᴇ&#D18752ɢ&#D28B5Aᴏ" + "&7 ᴘᴀɴᴄᴇʀᴢᴀ"));
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        mediumArmorButtonItem = item;
    }
    private static void createHeavyArmorButtonItem() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴘᴀɴᴄᴇʀᴢ " + "&#752525ᴄ&#782A2Aɪ&#7A2F2Fę&#7D3333ż&#7F3838ᴋ&#823D3Dɪ")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴏᴛᴡᴏʀᴢʏć ᴍᴇɴᴜ ᴛᴡᴏʀᴢᴇɴɪᴀ " + "&#752525ᴄ&#772828ɪ&#782B2Bę&#7A2E2Eż&#7C3131ᴋ&#7D3434ɪ&#7F3737ᴇ&#803A3Aɢ&#823D3Dᴏ" + "&7 ᴘᴀɴᴄᴇʀᴢᴀ"));
        itemMeta.setLore(lore);

        item.setItemMeta(itemMeta);
        heavyArmorButtonItem = item;
    }



    private static void createWeaponButtonItem() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((ColorFixer.addColors("&#D2AF51ʙ&#C5A245ʀ&#B89639ᴏ&#AC892Dɴ&#9F7D21ɪ&#927015ᴇ")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴏᴛᴡᴏʀᴢʏć ᴍᴇɴᴜ"));
        lore.add(ColorFixer.addColors("&7ᴡʏʙᴏʀᴜ ʙʀᴏɴɪ ᴅᴏ ꜱᴛᴡᴏʀᴢᴇɴɪᴀ"));
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        weaponButtonItem = item;
    }

    private static void createArmorButtonItem() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((ColorFixer.addColors("&#8F3535ᴘ&#933B3Bᴀ&#964040ɴ&#9A4646ᴄ&#9E4C4Cᴇ&#A15151ʀ&#A55757ᴢ")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴏᴛᴡᴏʀᴢʏć ᴍᴇɴᴜ"));
        lore.add(ColorFixer.addColors("&7ᴡʏʙᴏʀᴜ ᴢʙʀᴏɪ ᴅᴏ ꜱᴛᴡᴏʀᴢᴇɴɪᴀ"));
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        armorButtonItem = item;
    }

    private static void createOthersButtonItem() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((ColorFixer.addColors("&#5FE2C5ᴘ&#5EDEC6ʀ&#5CDAC7ᴢ&#5BD6C8ᴇ&#59D2CAᴅ&#58CFCBᴍ&#56CBCCɪ&#55C7CDᴏ&#54C3CEᴛ&#52BFCFʏ &#4FB7D2ᴅ&#4EB3D3ᴏ&#4DAFD4ᴅ&#4BABD5ᴀ&#4AA8D6ᴛ&#48A4D8ᴋ&#47A0D9ᴏ&#459CDAᴡ&#4498DBᴇ")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴏᴛᴡᴏʀᴢʏć ᴍᴇɴᴜ"));
        lore.add(ColorFixer.addColors("&7ᴡʏʙᴏʀᴜ ᴘʀᴢᴇᴅᴍɪᴏᴛóᴡ ᴅᴏᴅᴀᴛᴋᴏᴡʏᴄʜ ᴅᴏ ꜱᴛᴡᴏʀᴢᴇɴɪᴀ"));
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        otherButtonItem = item;
    }

    private static void createReforgeButtonItem() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((ColorFixer.addColors("&#A27C0Dᴘ&#A57F12ʀ&#A88316ᴢ&#AB861Bᴇ&#AE8A1Fᴋ&#B18D24ᴜ&#B49128ᴄ&#B8942Dɪ&#BB9732ᴇ &#C19E3Bʙ&#C4A23Fʀ&#C7A544ᴏ&#CAA948ɴ&#CDAC4Dɪ")));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴏᴛᴡᴏʀᴢʏć ᴍᴇɴᴜ"));
        lore.add(ColorFixer.addColors("&7ᴘʀᴢᴇᴋᴜᴄɪᴀ ᴊᴀᴋᴏśᴄɪ ʙʀᴏɴɪ"));
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        reforgeButtonItem = item;
    }

    private static void createMoreItemsButton() {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1020);
        itemMeta.setDisplayName((ColorFixer.addColors("&8ᴡɪęᴄᴇᴊ")));

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ᴡʏʙʀᴀć ɪɴɴʏ ᴘʀᴢᴇᴅᴍɪᴏᴛ"));
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        moreItemsItemButton = item;
    }

    public static ItemStack getBookVisualizer(EnchantManager.Enchant enchant, int currentTier) {

        CustomItem customItem = CustomItemManager.getEnchantBookCustomItemByEnchant(enchant);

        if(!(customItem instanceof CustomEnchantBook customEnchantBook)) {

            ItemStack itemStack = new ItemStack(Material.BOOK);

            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName((ColorFixer.addColors("&7ᴡʏʙɪᴇʀᴢ ᴢᴀᴋʟęᴄɪᴇ ᴢ ᴘᴀꜱᴋᴜ ᴅᴏꜱᴛęᴘɴʏᴄʜ ᴢᴀᴋʟęć")));

            itemStack.setItemMeta(itemMeta);

            return itemStack;

        }

        ItemStack itemStack = new ItemStack(Material.BOOK);

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴏʙᴇᴄɴᴇ ᴢᴀᴋʟęᴄɪᴇ: ") + ColorFixer.addColors(customEnchantBook.getName(currentTier))));

        ArrayList<String> lore = new ArrayList<>(customEnchantBook.getEnchantDescription(currentTier));

        lore.add(" ");
        lore.add(ColorFixer.addColors("&7ᴡʏʙɪᴇʀᴢ ᴢᴀᴋʟęᴄɪᴇ ᴢ ᴘᴀꜱᴋᴜ ᴅᴏꜱᴛęᴘɴʏᴄʜ ᴢᴀᴋʟęć ᴀʙʏ ᴢᴍɪᴇɴɪć ᴀᴋᴛᴜᴀʟɴᴇ ᴢᴀᴋʟęᴄɪᴇ"));

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack enchantTierButton(int currentTier, int maxTier) {

        ItemStack tierItem = new ItemStack(Material.PAPER);

        ItemMeta itemMeta = tierItem.getItemMeta();

        itemMeta.setDisplayName((ColorFixer.addColors("&7ᴀᴋᴛᴜᴀʟɴʏ ᴛɪᴇʀ: " + MessageManager.getRomanNumeralsForEnchant(currentTier))));

        ArrayList<String> lore = new ArrayList<>();

        if ((currentTier-1) == 0) {
            lore.add(ColorFixer.addColors("&7ᴀᴋᴛᴜᴀʟɴɪᴇ ᴜꜱᴛᴀᴡɪᴏɴʏ ᴊᴇꜱᴛ ɴᴀᴊɴɪżꜱᴢʏ ᴛɪᴇʀ ᴅʟᴀ ᴛᴇɢᴏ ᴢᴀᴋʟęᴄɪᴀ"));
        } else {
            lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ &aʟᴘᴍ &7ᴀʙʏ ᴢᴍɪᴇɴɪć ᴀᴋᴛᴜᴀʟɴʏ ᴛɪᴇʀ ᴢᴀᴋʟęᴄɪᴀ ɴᴀ: " + getRomanNumerals(currentTier -1)));
        }

        if(currentTier == maxTier) {
            lore.add(ColorFixer.addColors("&7ᴀᴋᴛᴜᴀʟɴɪᴇ ᴜꜱᴛᴀᴡɪᴏɴʏ ᴊᴇꜱᴛ ɴᴀᴊᴡʏżꜱᴢʏ ᴛɪᴇʀ ᴅʟᴀ ᴛᴇɢᴏ ᴢᴀᴋʟęᴄɪᴀ"));
        } else {
            lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ &aᴘᴘᴍ &7ᴀʙʏ ᴢᴍɪᴇɴɪć ᴀᴋᴛᴜᴀʟɴʏ ᴛɪᴇʀ ᴢᴀᴋʟęᴄɪᴀ ɴᴀ: " + getRomanNumerals(currentTier +1)));
        }

//        itemMeta.setLore(lore);
        itemMeta.setLore(lore);

        tierItem.setItemMeta(itemMeta);

        return tierItem;
    }
}
