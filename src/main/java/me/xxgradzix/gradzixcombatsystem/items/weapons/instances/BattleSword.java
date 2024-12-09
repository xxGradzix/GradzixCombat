package me.xxgradzix.gradzixcombatsystem.items.weapons.instances;

import me.xxgradzix.gradzixcombatsystem.items.Upgradable;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.modifiersManager.ModifiersManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import me.xxgradzix.gradzixcombatsystem.items.weapons.CustomWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.EnchantableWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.MelleWeapon;
import me.xxgradzix.gradzixcombatsystem.items.weapons.ModifiableWeapon;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

import static me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager.getRomanNumerals;

public class BattleSword implements CustomWeapon, MelleWeapon, EnchantableWeapon, ModifiableWeapon, Upgradable {

    public static final String CUSTOM_ID = "gradzixcombat_battle_sword";

    @Override
    public double getAttackDamage(int tier) {
        switch (tier) {
            case 1:
                return 4;
            case 2:
                return 5;
            case 3:
                return 6;
            case 4:
                return 7;
            case 5:
                return 8;
            default:
                return 0;
        }
    }


    @Override
    public List<String> getItemPreviewLore() {
        return List.of(
                ColorFixer.addColors("&7ᴛᴀ ʙʀᴏń ᴏꜰᴇʀᴜᴊᴇ ᴜᴍɪᴀʀᴋᴏᴡᴀɴᴇ ᴏʙʀᴀżᴇɴɪᴀ"),
                ColorFixer.addColors("&7ᴛᴏ ʀᴀᴄᴢᴇᴊ ꜱᴢʏʙᴋᴀ ʙʀᴏń"),
                ColorFixer.addColors("&7 "),
                ColorFixer.addColors("&7ᴛᴀ ʙʀᴏń ᴏᴘɪᴇʀᴀ ꜱɪę ɴᴀ ᴀᴛʀʏʙᴜᴛᴀᴄʜ:"),
                ColorFixer.addColors("&8 - " + CombatAttribute.DEXTERITY.getCustomName())
        );
    }

    @Override
    public double getAttackSpeed(int tier) {
        switch (tier) {
            case 1:
                return 1.5;
            case 2:
                return 1.6;
            case 3:
                return 1.7;
            case 4:
                return 1.7;
            case 5:
                return 1.8;
            default:
                return 0;
        }
    }

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        switch (tier){
            case 1 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 3;
            }
            case 2 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 4;
            }
            case 3 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 6;
            }
            case 4 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 8;
            }
            case 5 -> {
                if(attribute.equals(CombatAttribute.DEXTERITY)) return 10;
            }
        }
        return 0;
    }

    @Override
    public String getName(int tier) {
//        return ColorFixer.addColors("#3e4040ᴅᴌᴜɢɪ ᴍɪᴇᴄᴢ " + getRomanNumerals(tier));
        return ColorFixer.addColors("ᴅᴌᴜɢɪ ᴍɪᴇᴄᴢ " + getRomanNumerals(tier));
    }

    @Override
    public int getModelData(int tier) {
        return 5;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.IRON_SWORD;
    }

    @Override
    public void addBukkitEnchantments(int tier, ItemMeta meta) {

    }

    @Override
    public void setEnchantSlots(ItemStack itemStack, int tier) {
        if(tier >= 3) EnchantManager.setMaxSlots(itemStack, 1);
    }

    @Override
    public Set<EnchantManager.Enchant> getApplicableEnchants(int tier) {
        return Set.of(EnchantManager.Enchant.LIFE_STEAL, EnchantManager.Enchant.FIRE_AFFLICTION, EnchantManager.Enchant.WIND_CHARGE, EnchantManager.Enchant.POISON, EnchantManager.Enchant.ATTACK_COMBO);
    }

    @Override
    public Set<Class> getApplicableModifications() {
        return Set.of(ModifiersManager.MelleModifier.class, ModifiersManager.UniversalModifier.class, ModifiersManager.CommonModifier.class);
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
