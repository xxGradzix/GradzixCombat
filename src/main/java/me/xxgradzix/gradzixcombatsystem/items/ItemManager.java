package me.xxgradzix.gradzixcombatsystem.items;

import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.armors.CustomArmor;
import me.xxgradzix.gradzixcombatsystem.armors.instances.HeavyArmor;
import me.xxgradzix.gradzixcombatsystem.armors.instances.LightArmor;
import me.xxgradzix.gradzixcombatsystem.armors.instances.MediumArmor;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.WeaponType;
import me.xxgradzix.gradzixcombatsystem.weapons.instances.*;
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

import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;


public class ItemManager {

    /**  ITEMS. **/

    public static ItemStack restartPotion;
    public static ItemStack stoneOfAggression;

    /** WEAPONS **/

    private static final BattleAxe battleAxe = new BattleAxe();
    private static final BattleBow battleBow = new BattleBow();
    private static final BattleCrossBow battleCrossBow = new BattleCrossBow();
    private static final BattleSpear battleSpear = new BattleSpear();
    private static final BattleShield battleShield = new BattleShield();
    private static final BattleSword battleSword = new BattleSword();

    /** ARMORS **/

    private static final LightArmor lightArmor = new LightArmor();
    private static final MediumArmor mediumArmor = new MediumArmor();
    private static final HeavyArmor heavyArmor = new HeavyArmor();

    /** ARMOR METHODS **/

    public static CustomArmor getArmorTypeByWeight(ArmorTierManager.ArmorWeight armorWeight) {
        switch (armorWeight) {
            case LIGHT -> {
                return lightArmor;
            }
            case MEDIUM -> {
                return mediumArmor;
            }
            case HEAVY -> {
                return heavyArmor;
            }
            default -> {
                return null;
            }
        }
    }

    public static void getLightArmor(Player player, int tier) {
        for (ArmorTierManager.ArmorType armorType : ArmorTierManager.ArmorType.values()) {
            ItemStack armorPiece = lightArmor.getItemStack(tier, armorType);
            player.getInventory().addItem(armorPiece);
        }

    }
    public static void getMediumArmor(Player player, int tier) {
        for (ArmorTierManager.ArmorType armorType : ArmorTierManager.ArmorType.values()) {
            ItemStack armorPiece = mediumArmor.getItemStack(tier, armorType);
            player.getInventory().addItem(armorPiece);
        }
    }

    public static void getHeavyArmor(Player player, int tier) {
        for (ArmorTierManager.ArmorType armorType : ArmorTierManager.ArmorType.values()) {
            ItemStack armorPiece = heavyArmor.getItemStack(tier, armorType);
            player.getInventory().addItem(armorPiece);
        }
    }

    public static CustomArmor getCustomArmor(ItemStack itemStack) {
        String customId = CustomArmor.geArmorCustomId(itemStack);
        if(customId == null) return null;
        switch (customId){
            case LightArmor.CUSTOM_ID -> {
                return lightArmor;
            }
            case MediumArmor.CUSTOM_ID -> {
                return mediumArmor;
            }
            case HeavyArmor.CUSTOM_ID -> {
                return heavyArmor;
            }
            default -> {
                return null;
            }
        }
    }

    /** WEAPON METHODS **/

    public static CustomWeapon getWeaponType(ItemStack itemStack) {

        Optional<String> weaponCustomId = CustomItem.getCustomId(itemStack.getItemMeta());

        if(weaponCustomId.isEmpty()) return null;

        switch (weaponCustomId.get()){
            case BattleSword.CUSTOM_ID -> {
                return battleSword;
            }
            case BattleBow.CUSTOM_ID -> {
                return battleBow;
            }
            case BattleCrossBow.CUSTOM_ID -> {
                return battleCrossBow;
            }
            case BattleSpear.CUSTOM_ID -> {
                return battleSpear;
            }
            case BattleShield.CUSTOM_ID -> {
                return battleShield;
            }
            case BattleAxe.CUSTOM_ID -> {
                return battleAxe;
            }
            default -> {
                return null;
            }
        }
    }

    public static boolean upgradeWeapon(ItemStack itemStack) {

        int newTier = CustomWeapon.getTier(itemStack) + 1;

        if(newTier > 5) return false;

        CustomWeapon customWeapon = getWeaponType(itemStack);
        if(customWeapon == null) return false;

        ItemStack newItem = customWeapon.getDefaultItemStack(newTier);

        ItemMeta newMeta = newItem.getItemMeta();

        EnchantManager.copyEnchantsFromMetaToMeta(itemStack.getItemMeta(), newMeta);
        ModifiersManager.copyModifiers(itemStack.getItemMeta(), newMeta);

        customWeapon.setLoreAndName(newMeta, newTier);

        itemStack.setItemMeta(newMeta);

        return true;
    }

    /** OTHER ITEMS METHODS **/

    public static void init() {
        createRestartPotion();
        createAggressionStone();
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

        itemMeta.setDisplayName(displayName);
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
        itemMeta.setDisplayName(ColorFixer.addColors("&7ᴛɪᴇʀ " + getRomanNumerals(tier)));
        itemMeta.setCustomModelData(10000 + tier);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getLeftArrowItem(boolean isActive) {
        ItemStack item = new ItemStack(Material.GHAST_TEAR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ColorFixer.addColors("&7lewo "));
        itemMeta.setCustomModelData(7777 + (isActive ? 1 : 0));
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack getRightArrowItem(boolean isActive) {
        ItemStack item = new ItemStack(Material.GHAST_TEAR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ColorFixer.addColors("&7prawo "));
        itemMeta.setCustomModelData(8888 + (isActive ? 1 : 0));
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack upgradeWeaponButton() {
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

            itemMeta.setDisplayName(ColorFixer.addColors("&7ꜱᴛᴡóʀᴢ"));

            ArrayList<String> lore = new ArrayList<>();

            lore.add(ColorFixer.addColors("&7ᴋʟɪᴋɴɪᴊ ᴀʙʏ ꜱᴛᴡᴏʀᴢʏć ᴛᴇɴ ᴘʀᴢᴇᴅᴍɪᴏᴛ"));

            itemMeta.setLore(lore);

            item.setItemMeta(itemMeta);
            return item;

    }


    private static void createRestartPotion() {


        ItemStack item = new ItemStack(Material.POTION);

        PotionMeta itemMeta = (PotionMeta) item.getItemMeta();

        itemMeta.setBasePotionType(PotionType.AWKWARD);
        itemMeta.setColor(Color.WHITE);
        itemMeta.setDisplayName(ColorFixer.addColors("&bᴍɪᴋꜱᴛᴜʀᴀ ᴏᴄᴢʏꜱᴢᴄᴢᴇɴɪᴀ"));

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

        itemMeta.setDisplayName(ColorFixer.addColors("&4ᴋᴀᴍɪᴇɴɪᴇ ᴀɢʀᴇꜱᴊɪ"));

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

        itemMeta.setDisplayName(ColorFixer.addColors("&7ᴘʀᴢᴇᴋᴜᴊ"));

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


    public static ItemStack getWeapon(WeaponType weaponType, int tier) {
        switch (weaponType) {
            case SWORD -> {
                return battleSword.getDefaultItemStack(tier);
            }
            case BOW -> {
                return battleBow.getDefaultItemStack(tier);
            }
            case CROSSBOW -> {
                return battleCrossBow.getDefaultItemStack(tier);
            }
            case JAVELIN -> {
                return battleSpear.getDefaultItemStack(tier);
            }
            case SHIELD -> {
                return battleShield.getDefaultItemStack(tier);
            }
            case AXE -> {
                return battleAxe.getDefaultItemStack(tier);
            }
            default -> {
                return null;
            }
        }
    }
}
