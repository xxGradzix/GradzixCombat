package me.xxgradzix.gradzixcombatsystem.managers.EnchantManager;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Attributable;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributePointsManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.items.weapons.EnchantableWeapon;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;

public class EnchantManager {

    public enum Enchant {

        FREEZE(3, 3, "&#5D7ED0ᴍ&#5C8ECFʀ&#5A9ECFᴏ&#59AECEᴢ&#57BECDᴜ"),
        SOUL_STEAL(3, 3, "&#BEE9FFᴋ&#BEE9FFʀ&#85D6FEᴀ&#4DC3FEᴅ&#14B0FDᴢ&#1BA1E3ɪ&#2192C9ᴇ&#2882AEż&#2E7394ʏ &#3B5560ᴅ&#424545ᴜ&#48362Bꜱ&#48362Bᴢ"),
        MULTI_SHOT(2, 5, "&#BFFFBEᴡ&#BFFFBEɪ&#B1EAA9ᴇ&#A2D494ʟ&#94BF80ᴏ&#85A96Bꜱ&#779456ᴛ&#687E41ʀ&#5A692Dᴢ&#4B5318ᴀ&#3D3E03ł&#3D3E03ᴜ"),
        FIRE_AFFLICTION(3, 3, "&#FFFFFFᴘ&#F1DDB7ł&#E4BA6Fᴏ&#D69827ᴍ&#C67A25ɪ&#B65C24ᴇ&#A63E22ɴ&#962020ɪ"),
        LIFE_STEAL(3, 3, "&#770000ᴋ&#7C0404ʀ&#800808ᴀ&#850C0Cᴅ&#891111ᴢ&#8E1515ɪ&#921919ᴇ&#971D1Dż&#9B2121ʏ &#A22231ᴢ&#A62339ᴅ&#AA2442ʀ&#AD244Aᴏ&#B12552ᴡ&#B4255Aɪ&#B82662ᴀ"),
        ARROW_RAIN(3, 4, "&#A97910ᴅ&#A97910ᴇ&#B78F38ꜱ&#C6A660ᴢ&#D4BC88ᴄ&#E2D2AFᴢ&#F1E9D7ᴜ &#FAEED9ꜱ&#F4DDB4ᴛ&#EFCC8Eʀ&#E9BB68ᴢ&#E9BB68ᴀ&#E9BB68ᴌ"),
        LIGHTNING(3, 3, "&#FFE658ʙ&#FFE86Aᴌ&#FEEA7Bʏ&#FEEB8Dꜱ&#FEED9Fᴋ&#FDEFB0ᴀ&#FDF1C2ᴡ&#FCF2D3ɪ&#FCF4E5ᴄ"),
        ATTACK_COMBO(3, 3, "&#D351E7ᴋ&#B84CD9ᴏ&#9D47CBᴍ&#8242BDʙ&#663EAFᴏ&#4B39A1ꜱ&#303493ó&#152F85ᴡ"),
        WIND_CHARGE(3, 3, "&#FFFFFFᴡ&#FFFFFFɪ&#DBDBDBᴀ&#B8B8B8ᴛ&#949494ʀ&#707070ᴜ"),
        POISON(3, 3, "&#007750ᴢ&#116C3Cᴀ&#226128ᴛ&#325514ʀ&#434A00ᴜ&#434A00ᴄ&#434A00ɪ&#434A00ᴀ"),
        GREED(3, 3, "&#FAC410ᴄ&#E2B720ʜ&#CBAA31ᴄ&#B39D41ɪ&#AE9739ᴡ&#A89131ᴏ&#A38B29ś&#9D8521ᴄ&#987F19ɪ");

        public final int maxLevel;
        public final String enchantSuffix;
        final int intelligenceRequirementPerLevel;

        public int getIntLevelByTier(int tier) {
            return Math.min(tier, maxLevel) * intelligenceRequirementPerLevel;
        }

        Enchant(int maxLevel, int intelligenceRequirementPerLevel, String enchantSuffix) {
            this.enchantSuffix = ColorFixer.addColors(enchantSuffix);
            this.maxLevel = maxLevel;
            this.intelligenceRequirementPerLevel = intelligenceRequirementPerLevel;
        }

    }

    private static final int MAX_ENCHANTS_GLOBAL_LIMITER = 2;

    private static final NamespacedKey maxEnchantsSlotsKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_max_enchant_slots");
    private static final NamespacedKey enchantsKey = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_enchants");

    public static void addEnchant(ItemStack itemStack, Enchant enchant) {

        if(containsEnchant(itemStack, enchant)) return;

        int tier = Tierable.getTier(itemStack);

        CustomItem weaponType = CustomItemManager.getCustomItem(itemStack);

        if(!(weaponType instanceof EnchantableWeapon enchantableWeapon) || !enchantableWeapon.getApplicableEnchants(tier).contains(enchant)) {
            return;
        }

        int freeEnchantSlots = getFreeEnchantSlots(itemStack);

        if(freeEnchantSlots <= 0) return;
        setEnchant(itemStack, enchant, 1);

    }
    public static HashMap<EnchantManager.Enchant, Integer> getEnchants(ItemStack itemStack) {
        if (itemStack == null) return new HashMap<>();
//        ItemMeta itemMeta = itemStack.getItemMeta();
//        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
//        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
//        return orDefault.getEnchantMap();
        return getEnchants(itemStack.getItemMeta());
    }
    public static HashMap<EnchantManager.Enchant, Integer> getEnchants(ItemMeta meta) {
        PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        return orDefault.getEnchantMap();
    }

    public static void upgradeEnchant(ItemStack itemStack, Enchant enchant) {
        if(!containsEnchant(itemStack, enchant)) return;
        if(!canUpgradeEnchant(itemStack, enchant)) return;
        setEnchant(itemStack, enchant, getEnchantLevel(itemStack, enchant) + 1);
    }

    public static boolean setEnchant(ItemStack itemStack, Enchant enchant, int level) {

        boolean canSetEnchant = canSetEnchant(itemStack, enchant, level);

        if(!canSetEnchant) return false;

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        EnchantDto enchantDTO = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        HashMap<EnchantManager.Enchant, Integer> enchantMap = enchantDTO.getEnchantMap();

        enchantMap.put(enchant, level);
        enchantDTO.setEnchantMap(enchantMap);

        persistentDataContainer.set(enchantsKey, new EnchantPersistentDataType(), enchantDTO);

        CustomItem weaponType = CustomItemManager.getCustomItem(itemStack);

        if(weaponType instanceof Attributable) {
            AttributePointsManager.setAttributeRequirement(itemMeta, CombatAttribute.INTELLIGENCE, level * 3);
        }

        if(weaponType instanceof Tierable) {
            weaponType.setLoreAndName(itemMeta, Tierable.getTier(itemStack));
        } else {
            weaponType.setLoreAndName(itemMeta, 1);
        }

        if(Enchant.MULTI_SHOT.equals(enchant)) {
            itemMeta.addEnchant(Enchantment.MULTISHOT, 1, false);
//            SoulStealListener.setSoulsStolen(itemMeta, 0);
        }
//        if(Enchant.LIGHTNING.equals(enchant)) {
//            itemMeta.addEnchant(Enchantment.CHANNELING, 1, false);
//        }

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
        if(itemStack == null) return 0;
        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta == null) return 0;

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());
        HashMap<EnchantManager.Enchant, Integer> enchantMap = orDefault.getEnchantMap();
        return enchantMap.getOrDefault(enchant, 0);
    }


    public static boolean canUpgradeEnchant(ItemStack itemStack, EnchantManager.Enchant enchant) {
        if(enchant == null) return false;
        ItemMeta itemMeta = itemStack.getItemMeta();

        CustomItem customWeapon = CustomItemManager.getCustomItem(itemStack);

        if(!(customWeapon instanceof EnchantableWeapon enchantableWeapon)) return false;

        int tier = Tierable.getTier(itemStack);
        if(!(enchantableWeapon.getApplicableEnchants(tier).contains(enchant))) return false;

        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
        EnchantDto orDefault = persistentDataContainer.getOrDefault(enchantsKey, new EnchantPersistentDataType(), new EnchantDto());

        HashMap<EnchantManager.Enchant, Integer> enchantMap = orDefault.getEnchantMap();

        int level = enchantMap.getOrDefault(enchant, 0);
        if(level >= enchant.maxLevel) return false;
        return enchantMap.containsKey(enchant);
    }

    public static boolean canSetEnchant(ItemStack itemStack, EnchantManager.Enchant enchant, int enchantTier) {

        if(enchant == null) return false;

        CustomItem customItem = CustomItemManager.getCustomItem(itemStack);

        if(!(customItem instanceof EnchantableWeapon enchantableWeapon)) return false;

        int weaponTier = Tierable.getTier(itemStack);
        if(!(enchantableWeapon.getApplicableEnchants(weaponTier).contains(enchant))) return false;

        int freeEnchantSlots = getFreeEnchantSlots(itemStack);

        if(freeEnchantSlots <= 0) return false;

        if(enchantTier > enchant.maxLevel) return false;

        return true;
    }

    public static int getMaxEnchantSlots(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return getMaxEnchantSlots(itemMeta);
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
            lore.add(ColorFixer.addColors("&7ᴢᴀᴋʟęᴄɪᴇ " + enchant.enchantSuffix + " " + MessageManager.getRomanNumerals(enchantMap.get(enchant))));
        }

        for (int i = 0; i < maxEnchantSlots - enchantMap.size(); i++) {
            lore.add(ColorFixer.addColors("#874E92✦ &7ᴘᴜꜱᴛᴇ ᴘᴏʟᴇ ɴᴀ ᴢᴀᴋʟęᴄɪᴇ"));
        }

        if(enchantMap.containsKey(Enchant.SOUL_STEAL)) {
            lore.add(ColorFixer.addColors("&7ᴘᴏᴄʜłᴏɴɪęᴛᴇ ᴅᴜꜱᴢᴇ: " + MagicEffectManager.getSoulsStolen(itemMeta)));
        }
        return lore;
    }
}
