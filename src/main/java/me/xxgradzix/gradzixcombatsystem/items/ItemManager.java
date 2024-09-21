package me.xxgradzix.gradzixcombatsystem.items;

import com.google.common.collect.Multimap;
import me.xxgradzix.gradzixcombatsystem.ArmorTierManager;
import me.xxgradzix.gradzixcombatsystem.armorEvent.ArmorType;
import me.xxgradzix.gradzixcombatsystem.armors.instances.HeavyArmor;
import me.xxgradzix.gradzixcombatsystem.armors.instances.LightArmor;
import me.xxgradzix.gradzixcombatsystem.armors.instances.MediumArmor;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AttributeManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.instances.*;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
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

    private static final LightArmor lightArmor = new LightArmor();
    private static final MediumArmor mediumArmor = new MediumArmor();
    private static final HeavyArmor heavyArmor = new HeavyArmor();

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


    public static CustomWeapon getWeaponType(ItemStack itemStack) {

        String weaponCustomId = CustomWeapon.getWeaponCustomId(itemStack);

        if(weaponCustomId == null) return null;

        switch (weaponCustomId){
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

        ItemStack newItem = customWeapon.getItemStack(newTier);

        ItemMeta newMeta = newItem.getItemMeta();

        EnchantManager.copyEnchantsFromMetaToMeta(itemStack.getItemMeta(), newMeta);
        ModifiersManager.copyModifiers(itemStack.getItemMeta(), newMeta);

        customWeapon.setLoreAndName(newMeta, newTier);

        itemStack.setItemMeta(newMeta);

        return true;
    }

    private static final Map<WeaponType, CustomWeapon> weapons = Map.ofEntries(
            Map.entry(WeaponType.AXE, battleAxe),
            Map.entry(WeaponType.BOW, battleBow),
            Map.entry(WeaponType.CROSSBOW, battleCrossBow),
            Map.entry(WeaponType.JAVELIN, battleSpear),
            Map.entry(WeaponType.SHIELD, battleShield),
            Map.entry(WeaponType.SWORD, battleSword)
    );
    /*************/
    private static final int baseHeavyArmorStrRequirement = 6;
    private static final int baseMediumArmorStrRequirement = 3;
    private static final int baseLightArmorStrRequirement = 0;

    private static final int baseMediumArmorEnduranceRequirement = 3;
    private static final int baseLightArmorDexRequirement = 0;


    private static final HashMap<ArmorTierManager.ArmorType, HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>>> armorsPerTierAndWeight = new HashMap<>();

    public static HashMap<ArmorTierManager.ArmorType, HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>>> getArmorsPerTierAndWeight() {
        return new HashMap<>(armorsPerTierAndWeight);
    }

    public static @NotNull ItemStack getWeapon(WeaponType weaponType, int i) {
        return weaponsPerTier.getOrDefault(weaponType, new HashMap<>()).get(i);
    }

    public static ArrayList<ItemStack> getArmorPiecesOfWeightAndTier(ArmorTierManager.ArmorWeight armorWeight, int tier) {
        ArrayList<ItemStack> armorPieces = new ArrayList<>();

        for (ArmorTierManager.ArmorType armorType : ArmorTierManager.ArmorType.values()) {
            ItemStack armorPiece = getArmorPiece(armorType, tier, armorWeight);
            armorPieces.add(armorPiece);
        }

        return armorPieces;
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

    public enum WeaponType {
        AXE, SWORD, BOW, JAVELIN, SHIELD, CROSSBOW
    }

    private static final HashMap<WeaponType, HashMap<Integer, ItemStack>> weaponsPerTier = new HashMap<>();

    public static ItemStack getArmorPiece(ArmorTierManager.ArmorType armorType, int tier, ArmorTierManager.ArmorWeight armorWeight) {
        return armorsPerTierAndWeight.getOrDefault(armorType, new HashMap<>()).getOrDefault(tier, new HashMap<>()).get(armorWeight);
    }


    public static void init() {

        createRestartPotion();
        createAggressionStone();

        for (ArmorTierManager.ArmorType armorType : ArmorTierManager.ArmorType.values()) {

            for (int tier = 1; tier <= 5; tier++) {
                for (ArmorTierManager.ArmorWeight armorWeight : ArmorTierManager.ArmorWeight.values()) {
                    ItemStack armorPieceWithParameters = createArmorPieceWithParameters(armorType, tier, armorWeight);
                    HashMap<Integer, HashMap<ArmorTierManager.ArmorWeight, ItemStack>> tierMap = armorsPerTierAndWeight.getOrDefault(armorType, new HashMap<>());
                    HashMap<ArmorTierManager.ArmorWeight, ItemStack> weightMap = tierMap.getOrDefault(tier, new HashMap<>());
                    weightMap.put(armorWeight, armorPieceWithParameters);
                    tierMap.put(tier, weightMap);
                    armorsPerTierAndWeight.put(armorType, tierMap);
                }
            }
        }
        for (WeaponType weaponType : weapons.keySet()) {
            for (int i = 1; i <= 5; i++) {
                ItemStack weaponWithParameters = weapons.get(weaponType).getItemStack(i);
                HashMap<Integer, ItemStack> tierMap = weaponsPerTier.getOrDefault(weaponType, new HashMap<>());
                tierMap.put(i, weaponWithParameters);
                weaponsPerTier.put(weaponType, tierMap);
            }
        }

    }
    private static ItemStack createArmorPieceWithParameters(ArmorTierManager.ArmorType armorType, int tier, ArmorTierManager.ArmorWeight armorWeight) {
        Material material;
        String displayName;
        Optional<Color> optionalColor = Optional.empty();
        switch (armorType) {
            case HELMET-> {
                switch (armorWeight) {
                    case LIGHT -> {
                        if(tier >= 5) {
                            material = Material.LEATHER_HELMET;
                            optionalColor = Optional.of(Color.GREEN);
                        } else if (tier >= 3) {
                            material = Material.LEATHER_HELMET;
                            optionalColor = Optional.of(Color.WHITE);
                        } else {
                            material = Material.LEATHER_HELMET;
                        }
                        material = Material.LEATHER_HELMET;
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪ ʜᴇᴌᴍ");
                    }
                    case MEDIUM -> {
                        material = Material.CHAINMAIL_HELMET;
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪ ʜᴇᴌᴍ");
                    }
                    case HEAVY -> {
                        material = Material.IRON_HELMET;
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ʜᴇᴌᴍ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);

                }
            }
            case CHESTPLATE -> {
                switch (armorWeight) {
                    case LIGHT -> {
                        if(tier >= 5) {
                            material = Material.LEATHER_CHESTPLATE;
                            optionalColor = Optional.of(Color.GREEN);
                        } else if (tier >= 3) {
                            material = Material.LEATHER_CHESTPLATE;
                            optionalColor = Optional.of(Color.WHITE);
                        } else {
                            material = Material.LEATHER_CHESTPLATE;
                        }
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
                    }
                    case MEDIUM -> {
                        material = Material.CHAINMAIL_CHESTPLATE;
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
                    }
                    case HEAVY -> {
                        material = Material.IRON_CHESTPLATE;
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪ ɴᴀᴘɪᴇʀśɴɪᴋ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);
                }
            }
            case LEGGINGS -> {
                switch (armorWeight) {
                    case LIGHT -> {
                        if (tier >= 5) {
                            material = Material.CHAINMAIL_LEGGINGS;
                        } else if (tier >= 3) {
                            material = Material.CHAINMAIL_LEGGINGS;
                        } else {
                            material = Material.LEATHER_LEGGINGS;
                            optionalColor = Optional.of(Color.MAROON);
                        }
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    case MEDIUM -> {
                        if(tier >= 5) {
                            material = Material.LEATHER_LEGGINGS;
                            optionalColor = Optional.of(Color.GRAY);
                        } else if (tier >= 3) {
                            material = Material.LEATHER_LEGGINGS;
                            optionalColor = Optional.of(Color.GRAY);
                        } else {
                            material = Material.CHAINMAIL_LEGGINGS;
                        }
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    case HEAVY -> {
                        if(tier >= 5) {
                            material = Material.NETHERITE_LEGGINGS;
                        } else if (tier >= 3) {
                            material = Material.IRON_LEGGINGS;
                        } else {
                            material = Material.CHAINMAIL_LEGGINGS;
                        }
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ꜱᴘᴏᴅɴɪᴇ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);

                }
            }
            case BOOTS -> {
                switch (armorWeight) {
                    case LIGHT -> {
                        if(tier >= 5) {
                            material = Material.IRON_BOOTS;
                        } else if (tier >= 3) {
                            material = Material.LEATHER_BOOTS;
                            optionalColor = Optional.of(Color.WHITE);
                        } else {
                            material = Material.LEATHER_BOOTS;
                            optionalColor = Optional.of(Color.MAROON);
                        }
                        displayName = ColorFixer.addColors("#3e4040ʟᴇᴋᴋɪᴇ ʙᴜᴛʏ");
                    }
                    case MEDIUM -> {
                        if(tier >= 5) {
                            material = Material.IRON_BOOTS;
                        } else if (tier >= 3) {
                            material = Material.CHAINMAIL_BOOTS;
                        } else {
                            material = Material.CHAINMAIL_BOOTS;
                        }
                        displayName = ColorFixer.addColors("#3e4040śʀᴇᴅɴɪᴇ ʙᴜᴛʏ");
                    }
                    case HEAVY -> {
                        material = Material.IRON_BOOTS;
                        displayName = ColorFixer.addColors("#3e4040ᴄɪężᴋɪᴇ ʙᴜᴛʏ");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + armorWeight);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + armorType);
        }

        String romeNum = getRomanNumerals(tier);

        ItemStack item = new ItemStack(material);

        int strAttributeRequirement = 0;
        int dexAttributeRequirement = 0;
        int enduranceAttributeRequirement = 0;


        switch (armorWeight) {
            case MEDIUM -> {
                strAttributeRequirement = baseMediumArmorStrRequirement + tier;
            }
            case HEAVY -> {
                strAttributeRequirement = baseHeavyArmorStrRequirement + tier;
            }
            default -> {
                strAttributeRequirement = baseLightArmorStrRequirement + (tier / 2);
            }
        }


        if(armorWeight == ArmorTierManager.ArmorWeight.LIGHT) {
            dexAttributeRequirement = baseLightArmorDexRequirement + tier;
        }
        if(armorWeight == ArmorTierManager.ArmorWeight.MEDIUM) {
            enduranceAttributeRequirement = baseMediumArmorEnduranceRequirement + tier;
        }

        if(dexAttributeRequirement > 0) AttributeManager.setAttributeRequirement(item, CombatAttribute.DEXTERITY, dexAttributeRequirement);
        if(enduranceAttributeRequirement > 0) AttributeManager.setAttributeRequirement(item, CombatAttribute.ENDURANCE, enduranceAttributeRequirement);
        if(strAttributeRequirement > 0) AttributeManager.setAttributeRequirement(item, CombatAttribute.STRENGTH, strAttributeRequirement);

        ArmorTierManager.setAttributesPerTierAndWeight(item, armorType, armorWeight, tier);

        ItemMeta itemMeta = item.getItemMeta();

        if(itemMeta instanceof LeatherArmorMeta leatherArmorMeta) {
            optionalColor.ifPresent(leatherArmorMeta::setColor);
        }

        itemMeta.setDisplayName(ColorFixer.addColors(displayName + " " + romeNum));

        item.setItemMeta(itemMeta);

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors(" "));

        Multimap<Attribute, AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers();

        switch (armorWeight) {
            case HEAVY -> {
                lore.add(ColorFixer.addColors("&7ᴛᴀ ᴄɪężᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴡʏꜱᴏᴋą ᴏʙʀᴏɴę"));
            }
            case LIGHT -> {
                lore.add(ColorFixer.addColors("&7ᴛᴀ ʟᴇᴋᴋᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ ᴘᴏᴅꜱᴛᴀᴡᴏᴡą ᴏʙʀᴏɴę"));
            }
            default -> {
                lore.add(ColorFixer.addColors("&7ᴛᴀ śʀᴇᴅɴɪᴀ ᴢʙʀᴏᴊᴀ ᴢᴀᴘᴇᴡɴɪᴀ śʀᴇᴅɴɪą ᴏʙʀᴏɴę"));
            }
        }

        if(attributeModifiers != null) {
            Collection<AttributeModifier> attributeModifiers1 = attributeModifiers.get(Attribute.GENERIC_ATTACK_DAMAGE);
            attributeModifiers1.forEach(attributeModifier -> {
                lore.add(ColorFixer.addColors(" &7&o+" + (int) attributeModifier.getAmount() + " &8pancerza"));
            });
        }

        lore.addAll(AttributeManager.getRequirementLore(itemMeta));
//        lore.add(ColorFixer.addColors(" ")); // ⚡☄⚔🗡
//        lore.add(ColorFixer.addColors("&7ᴀʙʏ ᴋᴏʀᴢʏꜱᴛᴀć ᴢ ᴛᴇɢᴏ ᴘʀᴢᴇᴅᴍɪᴏᴛᴜ ᴘᴏᴛʀᴢᴇʙᴜᴊᴇꜱᴢ:"));
//        if(strAttributeRequirement > 0) lore.add(ColorFixer.addColors("&cꜱɪᴌᴀ &4⚔:  #a18b3d" + strAttributeRequirement));
//        if(enduranceAttributeRequirement > 0) lore.add(ColorFixer.addColors("&7ᴡʏᴛʀᴢʏᴍᴀᴌᴏść &8☄:  #a18b3d" + enduranceAttributeRequirement));
//        if(dexAttributeRequirement > 0) lore.add(ColorFixer.addColors("&a &8:  #a18b3d" + dexAttributeRequirement));

        itemMeta.setLore(lore);

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
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


}
