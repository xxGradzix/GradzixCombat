package me.xxgradzix.gradzixcombatsystem.managers.modifiersManager;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.weapons.ModifiableWeapon;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ModifiersManager {

    private static final NamespacedKey MODIFIER_NAME = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_modifier_enum_name");

    private static final NamespacedKey RANGE_MULTIPLIER_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_range_multiplier");
    private static final NamespacedKey DAMAGE_MULTIPLIER_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_damage_multiplier");
    private static final NamespacedKey SPEED_MULTIPLIER_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_speed_multiplier");


    private static final NamespacedKey KNOCK_BACK_MULTIPLIER_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_knockback_multiplier");
    private static final NamespacedKey CRIT_CHANCE_MULTIPLIER_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_crit_chance_multiplier");
    private static final NamespacedKey ARROW_SPEED_MULTIPLIER_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_arrow_speed_multiplier");

    private static final String ATTACK_DAMAGE_MULTIPLIER_ATTRIBUTE_NAME = "gradzixcombat_attack_damage_multiplier";
    private static final String ATTACK_SPEED_MULTIPLIER_ATTRIBUTE_NAME = "gradzixcombat_attack_speed_multiplier";

    public enum Multiplier {
        RANGE_MULTIPLIER, DAMAGE_MULTIPLIER, ATTACK_SPEED_MULTIPLIER, KNOCK_BACK_MULTIPLIER, CRIT_CHANCE_MULTIPLIER,
        ARROW_SPEED
    }

    interface Modifier {
        ModifierRecord getModifier();
    }

    public enum UniversalModifier implements Modifier {

        KEEN(new ModifierRecord(ColorFixer.addColors("#7dffb5ᴄʜęᴛɴʏ"), new HashMap<>() {{
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.03);
        }})),
        SUPERIOR(new ModifierRecord(ColorFixer.addColors("#c5d66dᴢɴᴀᴋᴏᴍɪᴛʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.03);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.1);
        }})),
        FORCEFUL(new ModifierRecord(ColorFixer.addColors("#e366dfᴇɴᴇʀɢɪᴄᴢɴʏ"), new HashMap<>() {{
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }})),
        BROKEN(new ModifierRecord(ColorFixer.addColors("#695c52ᴢᴇᴘꜱᴜᴛʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 0.7);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.8);
        }})),
        DAMAGED(new ModifierRecord(ColorFixer.addColors("#8a8078ᴜꜱᴢᴋᴏᴅᴢᴏɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 0.85);
        }})),
        SHODDY(new ModifierRecord(ColorFixer.addColors("#9c673dᴛᴀɴᴅᴇᴛɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 0.9);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.85);
        }})),
        HURTFUL(new ModifierRecord(ColorFixer.addColors("#9c433dʙᴏʟᴇꜱɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
        }})),
        STRONG(new ModifierRecord(ColorFixer.addColors("#c96416ꜱɪʟɴʏ"), new HashMap<>() {{
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }})),
        UNPLEASANT(new ModifierRecord(ColorFixer.addColors("#94ba34ɴɪᴇᴘʀᴢʏᴊᴇᴍɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.05);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }})),
        WEAK(new ModifierRecord(ColorFixer.addColors("#615339ꜱᴌᴀʙʏ"), new HashMap<>() {{
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.8);
        }})),
        RUTHLESS(new ModifierRecord(ColorFixer.addColors("#bdb5a6ʙᴇᴢᴡᴢɢʟęᴅɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.18);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.9);
        }})),
        GODLY(new ModifierRecord(ColorFixer.addColors("#a0dadeʙᴏꜱᴋɪ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.15);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.05);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }})),
        DEMONIC(new ModifierRecord(ColorFixer.addColors("#542a2aᴅᴇᴍᴏɴɪᴄᴢɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.15);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.05);
        }})),
        ZEALOUS(new ModifierRecord(ColorFixer.addColors("#677566ɢᴏʀʟɪᴡʏ"), new HashMap<>() {{
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.05);
        }}));

        private final ModifierRecord modifierRecord;

        UniversalModifier(ModifierRecord modifierRecord) {
            this.modifierRecord = modifierRecord;
        }

        @Override
        public ModifierRecord getModifier() {
            return modifierRecord;
        }
    }


    public enum CommonModifier implements Modifier {
        QUICK(new ModifierRecord(ColorFixer.addColors("#5de383ꜱᴢʏʙᴋɪ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 1.1);
        }})),
        DEADLY(new ModifierRecord(ColorFixer.addColors("#e3424fᴢᴀʙóᴊᴄᴢʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 1.1);
        }})),
        AGILE(new ModifierRecord(ColorFixer.addColors("#21c442ᴢʀęᴄᴢɴʏ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 1.1);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.03);
        }})),
        NIMBLE(new ModifierRecord(ColorFixer.addColors("#4a9e5bᴢᴡɪɴɴʏ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 1.05);
        }})),
        MURDEROUS(new ModifierRecord(ColorFixer.addColors("#7d2c28ᴍᴏʀᴅᴇʀᴄᴢʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.07);
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 1.06);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.03);
        }})),
        SLOW(new ModifierRecord(ColorFixer.addColors("#6b6732ᴡᴏʟɴʏ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 0.85);
        }})),
        SLUGGISH(new ModifierRecord(ColorFixer.addColors("#575433ᴘᴏᴡᴏʟɴʏ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 0.8);
        }})),
        LAZY(new ModifierRecord(ColorFixer.addColors("#6e6b4bʟᴇɴɪᴡʏ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 0.92);
        }})),
        ANNOYING(new ModifierRecord(ColorFixer.addColors("#776378ɪʀʏᴛᴜᴊąᴄʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 0.8);
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 0.85);
        }})),
        NASTY(new ModifierRecord(ColorFixer.addColors("#752e23ᴘᴀꜱᴋᴜᴅɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.05);
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 1.1);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.02);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.9);
        }}));

        private final ModifierRecord modifierRecord;

        CommonModifier(ModifierRecord modifierRecord) {
            this.modifierRecord = modifierRecord;
        }
        @Override
        public ModifierRecord getModifier() {
            return modifierRecord;
        }
    }
    public enum RangeModifier implements Modifier {

        SIGHTED(new ModifierRecord(ColorFixer.addColors("#237550ᴡɪᴅᴢąᴄʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.03);
        }})),
        RAPID(new ModifierRecord(ColorFixer.addColors("#5ce640ꜱᴢʏʙᴋɪ"), new HashMap<>() {{
            put(Multiplier.ARROW_SPEED, 1.1);
        }})),
        HASTY(new ModifierRecord(ColorFixer.addColors("#e0d12bᴘᴏʀʏᴡᴄᴢʏ"), new HashMap<>() {{
            put(Multiplier.ARROW_SPEED, 1.15);
        }})),
        INTIMIDATING(new ModifierRecord(ColorFixer.addColors("#362396ꜱᴛʀᴀꜱᴢɴʏ"), new HashMap<>() {{
            put(Multiplier.ARROW_SPEED, 1.05);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }})),
        DEADLY(new ModifierRecord(ColorFixer.addColors("#631169śᴍɪᴇʀᴛᴇʟɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.02);
            put(Multiplier.ARROW_SPEED, 1.05);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.05);
        }})),
        STAUNCH(new ModifierRecord(ColorFixer.addColors("#c7bc65ᴡɪᴇʀɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }})),
        AWFUL(new ModifierRecord(ColorFixer.addColors("#5f6e6cᴏᴋʀᴏᴘɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 0.85);
            put(Multiplier.ARROW_SPEED, 0.9);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.9);
        }})),
        LETHARGIC(new ModifierRecord(ColorFixer.addColors("#5f6e6cᴏꜱᴘᴀᴌʏ"), new HashMap<>() {{
            put(Multiplier.ARROW_SPEED, 0.9);
        }})),
        AWKWARD(new ModifierRecord(ColorFixer.addColors("#966c75ɴɪᴇᴢʀęᴄᴢɴʏ"), new HashMap<>() {{
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.8);
        }})),
        POWERFUL(new ModifierRecord(ColorFixer.addColors("#968f86ᴘᴏᴛężɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.15);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.01);
        }})),
        FRENZYING(new ModifierRecord(ColorFixer.addColors("#b5974aꜱᴢᴀʟᴏɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 0.85);
        }})),
        UNREAL(new ModifierRecord(ColorFixer.addColors("#29ccbeɴɪᴇʀᴇᴀʟɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.15);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.05);
            put(Multiplier.ARROW_SPEED, 1.1);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }}));

        private final ModifierRecord modifierRecord;

        RangeModifier(ModifierRecord modifierRecord) {
            this.modifierRecord = modifierRecord;
        }
        @Override
        public ModifierRecord getModifier() {
            return modifierRecord;
        }
    }
    public enum MelleModifier implements Modifier {

        LEGENDARY(new ModifierRecord(ColorFixer.addColors("#cca508ʟᴇɢᴇɴᴅᴀʀɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.15);
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 1.1);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.05);
            put(Multiplier.RANGE_MULTIPLIER, 1.1);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }})),

        LARGE(new ModifierRecord(ColorFixer.addColors("#b5754aᴡɪᴇʟᴋɪ"), new HashMap<>() {{
            put(Multiplier.RANGE_MULTIPLIER, 1.12);
        }})),

        MASSIVE(new ModifierRecord(ColorFixer.addColors("#99613cᴍᴀꜱʏᴡɴʏ"), new HashMap<>() {{
            put(Multiplier.RANGE_MULTIPLIER, 1.18);
        }})),

        DANGEROUS(new ModifierRecord(ColorFixer.addColors("#7d390cɴɪᴇʙᴇᴢᴘɪᴇᴄᴢɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.05);
            put(Multiplier.CRIT_CHANCE_MULTIPLIER, 1.02);
            put(Multiplier.RANGE_MULTIPLIER, 1.05);
        }})),

        SAVAGE(new ModifierRecord(ColorFixer.addColors("#a6846dʙʀᴜᴛᴀʟɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
            put(Multiplier.RANGE_MULTIPLIER, 1.1);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.1);
        }})),

        SHARP(new ModifierRecord(ColorFixer.addColors("#5d949eᴏꜱᴛʀʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.15);
        }})),
        POINTY(new ModifierRecord(ColorFixer.addColors("#885d9eᴢᴀᴏꜱᴛʀᴢᴏɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
        }})),
        TINY(new ModifierRecord(ColorFixer.addColors("#9cdee6ᴍᴀʟᴜᴛᴋɪ"), new HashMap<>() {{
            put(Multiplier.RANGE_MULTIPLIER, 0.82);
        }})),
        SMALL(new ModifierRecord(ColorFixer.addColors("#7bbfc7ᴍᴀᴌʏ"), new HashMap<>() {{
            put(Multiplier.RANGE_MULTIPLIER, 0.9);
        }})),
        DULL(new ModifierRecord(ColorFixer.addColors("#909976ᴛęᴘʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 0.85);
        }})),
        UNHAPPY(new ModifierRecord(ColorFixer.addColors("#768058ɴɪᴇꜱᴢᴄᴢęśʟɪᴡʏ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 0.9);
            put(Multiplier.RANGE_MULTIPLIER, 0.9);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.9);
        }})),
        BULKY(new ModifierRecord(ColorFixer.addColors("#535e30ɢʀᴜʙʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.05);
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 0.85);
            put(Multiplier.RANGE_MULTIPLIER, 1.1);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.1);
        }})),
        SHAMEFUL(new ModifierRecord(ColorFixer.addColors("#707560ʜᴀɴɪᴇʙɴʏ"), new HashMap<>() {{
            put(Multiplier.DAMAGE_MULTIPLIER, 1.1);
            put(Multiplier.RANGE_MULTIPLIER, 1.1);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.1);
        }})),
        HEAVY(new ModifierRecord(ColorFixer.addColors("#4c4d4aᴄɪężᴋɪ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 0.9);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 1.15);
        }})),
        LIGHT(new ModifierRecord(ColorFixer.addColors("#62c7ccʟᴇᴋᴋɪ"), new HashMap<>() {{
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 1.15);
            put(Multiplier.KNOCK_BACK_MULTIPLIER, 0.9);
        }})),

        TERRIBLE(new ModifierRecord("#857d71ᴏᴋʀᴏᴘɴʏ", new HashMap<>() {{
            put(Multiplier.RANGE_MULTIPLIER, 0.87);
            put(Multiplier.DAMAGE_MULTIPLIER, 0.9);
            put(Multiplier.ATTACK_SPEED_MULTIPLIER, 0.9);
        }}));

        private final ModifierRecord modifierRecord;

        MelleModifier(ModifierRecord modifierRecord) {
            this.modifierRecord = modifierRecord;
        }

        @Override
        public ModifierRecord getModifier() {
            return modifierRecord;
        }
    }

    private record ModifierRecord(String name, HashMap<Multiplier, Double> multipliers) {
    }

    public static double getMultiplierValue(ItemMeta itemMeta, Multiplier multiplier) {

        if (itemMeta == null) {
            return 1;
        }

        switch (multiplier) {
            case RANGE_MULTIPLIER -> {
                return itemMeta.getPersistentDataContainer().getOrDefault(RANGE_MULTIPLIER_KEY, PersistentDataType.DOUBLE, 1.0);
            }
            case DAMAGE_MULTIPLIER -> {

                if(!itemMeta.hasAttributeModifiers()) return 1;
                Collection<AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE);
                if(attributeModifiers == null) return 1;

                for (AttributeModifier attributeModifier : attributeModifiers) {
                    if(AttributeModifier.Operation.MULTIPLY_SCALAR_1.equals(attributeModifier.getOperation())) {
                        return attributeModifier.getAmount() + 1;
                    }
                }
                return 1;
            }
            case KNOCK_BACK_MULTIPLIER -> {
                return itemMeta.getPersistentDataContainer().getOrDefault(KNOCK_BACK_MULTIPLIER_KEY, PersistentDataType.DOUBLE, 1.0);
            }
            case ARROW_SPEED -> {
                return itemMeta.getPersistentDataContainer().getOrDefault(ARROW_SPEED_MULTIPLIER_KEY, PersistentDataType.DOUBLE, 1.0);
            }
            case CRIT_CHANCE_MULTIPLIER -> {
                return itemMeta.getPersistentDataContainer().getOrDefault(CRIT_CHANCE_MULTIPLIER_KEY, PersistentDataType.DOUBLE, 1.0);
            }
            case ATTACK_SPEED_MULTIPLIER -> {

                if(!itemMeta.hasAttributeModifiers()) return 1;
                Collection<AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED);
                if(attributeModifiers == null) return 1;
                for (AttributeModifier attributeModifier : attributeModifiers) {
                    if(AttributeModifier.Operation.MULTIPLY_SCALAR_1.equals(attributeModifier.getOperation())) {
                        return attributeModifier.getAmount() + 1;
                    }
                }
                return 1;
            }
        }
        return 1;
    }

    public static double getMultiplierValue(ItemStack itemStack, Multiplier multiplier) {

        if (itemStack == null) {
            return 1;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        return getMultiplierValue(itemMeta, multiplier);
    }

    public static void setMultiplier(ItemMeta itemMeta, Multiplier multiplier, double value) {
        removeMultiplier(itemMeta, multiplier);

        if (value == 1) {
            return;
        }
        switch (multiplier) {
            case RANGE_MULTIPLIER -> {
//                itemMeta.getPersistentDataContainer().set(RANGE_MULTIPLIER_KEY, PersistentDataType.DOUBLE, value);
                itemMeta.addAttributeModifier(Attribute.PLAYER_ENTITY_INTERACTION_RANGE, new AttributeModifier(RANGE_MULTIPLIER_KEY, value - 1, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.MAINHAND));
            }
            case DAMAGE_MULTIPLIER -> {
                itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(DAMAGE_MULTIPLIER_KEY, value - 1, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.MAINHAND));
            }
            case KNOCK_BACK_MULTIPLIER -> {
                itemMeta.getPersistentDataContainer().set(KNOCK_BACK_MULTIPLIER_KEY, PersistentDataType.DOUBLE, value);
            }
            case ATTACK_SPEED_MULTIPLIER -> {
                itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(SPEED_MULTIPLIER_KEY, (value - 1), AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlotGroup.MAINHAND));
            }
            case ARROW_SPEED -> {
                itemMeta.getPersistentDataContainer().set(ARROW_SPEED_MULTIPLIER_KEY, PersistentDataType.DOUBLE, value);
            }
            case CRIT_CHANCE_MULTIPLIER -> {
                itemMeta.getPersistentDataContainer().set(CRIT_CHANCE_MULTIPLIER_KEY, PersistentDataType.DOUBLE, value);
            }
        }
    }

    private static void removeMultiplier(ItemMeta itemMeta, Multiplier multiplier) {
        switch (multiplier) {
            case RANGE_MULTIPLIER -> {
//                itemMeta.getPersistentDataContainer().remove(RANGE_MULTIPLIER_KEY);
                Collection<AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers(Attribute.PLAYER_ENTITY_INTERACTION_RANGE);

                if(attributeModifiers != null) {

                    for (AttributeModifier attributeModifier : attributeModifiers) {
                        if(AttributeModifier.Operation.MULTIPLY_SCALAR_1.equals(attributeModifier.getOperation())) {
                            itemMeta.removeAttributeModifier(Attribute.PLAYER_ENTITY_INTERACTION_RANGE, attributeModifier);
                        }
                    }
                }
            }
            case DAMAGE_MULTIPLIER -> {
                Collection<AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE);

                if(attributeModifiers != null) {

                    for (AttributeModifier attributeModifier : attributeModifiers) {
                        if(AttributeModifier.Operation.MULTIPLY_SCALAR_1.equals(attributeModifier.getOperation())) {
                            itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attributeModifier);
                        }
                    }
                }
            }
            case KNOCK_BACK_MULTIPLIER -> {
                itemMeta.getPersistentDataContainer().remove(KNOCK_BACK_MULTIPLIER_KEY);
            }
            case ARROW_SPEED -> {
                itemMeta.getPersistentDataContainer().remove(ARROW_SPEED_MULTIPLIER_KEY);
            }
            case CRIT_CHANCE_MULTIPLIER -> {
                itemMeta.getPersistentDataContainer().remove(CRIT_CHANCE_MULTIPLIER_KEY);
            }
            case ATTACK_SPEED_MULTIPLIER -> {

                Collection<AttributeModifier> attributeModifiers = itemMeta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED);

                if(attributeModifiers != null) {
                    for (AttributeModifier attributeModifier : attributeModifiers) {
                        if(AttributeModifier.Operation.MULTIPLY_SCALAR_1.equals(attributeModifier.getOperation())) {
                            itemMeta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attributeModifier);
                        }
                    }
                }
            }
        }
    }
    private static void removeMultiplier(ItemStack itemStack, Multiplier multiplier) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        removeMultiplier(itemMeta, multiplier);
        itemStack.setItemMeta(itemMeta);
    }

    public static String getModifierName(ItemMeta itemMeta) {

        if (itemMeta == null) {
            return "";
        }
        return itemMeta.getPersistentDataContainer().getOrDefault(MODIFIER_NAME, PersistentDataType.STRING, "");
    }
    public static ArrayList<String> getModifiersLore(ItemMeta itemMeta) {

        ArrayList<String> lore = new ArrayList<>();

        for (Multiplier multiplier : Multiplier.values()) {
            String suffix = "";
            switch (multiplier) {
                case KNOCK_BACK_MULTIPLIER -> suffix = ColorFixer.addColors("ᴏᴅʀᴢᴜᴛᴜ");
                case DAMAGE_MULTIPLIER -> suffix = ColorFixer.addColors("ᴏʙʀᴀżᴇń");
                case ARROW_SPEED -> suffix = ColorFixer.addColors("ᴘʀęᴅᴋᴏśᴄɪ ꜱᴛʀᴢᴀᴌ");
                case RANGE_MULTIPLIER -> suffix = ColorFixer.addColors("ᴢᴀꜱɪęɢᴜ");
                case CRIT_CHANCE_MULTIPLIER -> suffix = ColorFixer.addColors("ꜱᴢᴀɴꜱʏ ɴᴀ ᴏʙʀᴀżᴇɴɪᴀ ᴋʀʏᴛʏᴄᴢɴᴇ");
                case ATTACK_SPEED_MULTIPLIER -> suffix = ColorFixer.addColors("ᴘʀęᴅᴋᴏśᴄɪ ᴀᴛᴀᴋᴜ");
            }
            double value = getMultiplierValue(itemMeta, multiplier);
            if ((value - 1) != 0) {
                if(lore.isEmpty()) lore.add(" ");
                int percentage = (int) ((value - 1) * 100);
                lore.add(ColorFixer.addColors((percentage >= 0 ? "&a+" : "&c") + percentage + "%") + " " + suffix);
            }
        }

        return lore;
    }

    public static void setModifier(ItemMeta itemMeta, Modifier modifier) {

        itemMeta.getPersistentDataContainer().set(MODIFIER_NAME, PersistentDataType.STRING, modifier.getModifier().name());

        for (Multiplier multiplier : Multiplier.values()) {
            removeMultiplier(itemMeta, multiplier);
        }

        for (Multiplier multiplier : modifier.getModifier().multipliers.keySet()) {
            setMultiplier(itemMeta, multiplier, modifier.getModifier().multipliers.get(multiplier));
        }
    }

    public static void setModifier(ItemStack itemStack, Modifier melleModifier) {

        ItemMeta itemMeta = itemStack.getItemMeta();
        setModifier(itemMeta, melleModifier);
        itemStack.setItemMeta(itemMeta);
    }

    public static void applyRandomModifier(ItemStack itemStack) {

        CustomWeapon weaponType = ItemManager.getWeaponType(itemStack);

        if(!(weaponType instanceof ModifiableWeapon modifiableWeapon)) {
            return;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        ArrayList<Modifier> modifiers = new ArrayList<>();

        Set<Class> acceptableModifiers = modifiableWeapon.getApplicableModifications();

        for (Class acceptableModifier : acceptableModifiers) {
            if (acceptableModifier.equals(UniversalModifier.class)) {
                modifiers.addAll(Arrays.asList(UniversalModifier.values()));
            } else if (acceptableModifier.equals(CommonModifier.class)) {
                modifiers.addAll(Arrays.asList(CommonModifier.values()));
            } else if (acceptableModifier.equals(RangeModifier.class)) {
                modifiers.addAll(Arrays.asList(RangeModifier.values()));
            } else if (acceptableModifier.equals(MelleModifier.class)) {
                modifiers.addAll(Arrays.asList(MelleModifier.values()));
            }
        }

        Random random = new Random();
        Modifier modifier = modifiers.get(random.nextInt(modifiers.size()));


        setModifier(itemMeta, modifier);
        weaponType.setLoreAndName(itemMeta, CustomWeapon.getTier(itemStack));

        itemStack.setItemMeta(itemMeta);
    }

    public static void setModifierEnumName(ItemMeta itemMeta, String name) {
        itemMeta.getPersistentDataContainer().set(MODIFIER_NAME, PersistentDataType.STRING, name);
    }

    public static void copyModifiers(ItemMeta fromMeta, ItemMeta toMeta) {
        setModifierEnumName(toMeta, getModifierName(fromMeta));

        for (Multiplier multiplier : Multiplier.values()) {
            setMultiplier(toMeta, multiplier, getMultiplierValue(fromMeta, multiplier));
        }
    }
    public static void copyModifiers(ItemStack from, ItemStack to) {
        ItemMeta fromMeta = from.getItemMeta();
        ItemMeta toMeta = to.getItemMeta();
        copyModifiers(fromMeta, toMeta);
        to.setItemMeta(toMeta);
    }


}
