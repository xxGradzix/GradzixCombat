package me.xxgradzix.gradzixcombatsystem.items.battleBottles.instances;

import me.xxgradzix.gradzixcombatsystem.items.battleBottles.CombatPotionType;
import me.xxgradzix.gradzixcombatsystem.items.battleBottles.CustomBottle;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CombatBottlePoison implements CustomBottle {

    public static final String CUSTOM_ID = "gradzixcombat_combat_potion_poison";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public int getModelData(int tier) {
        return 50005;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.LINGERING_POTION;
    }

    @Override
    public void addBukkitEnchantments(int tier, ItemMeta meta) {

    }

    @Override
    public void setLoreAndName(ItemMeta meta, int tier) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");

        lore.add(ColorFixer.addColors("&7ᴛᴀ ʙᴜᴛᴇʟᴋᴀ ꜱᴘᴏᴡᴏᴅᴜᴊᴇ ʀᴏᴢᴘʀᴢᴇꜱᴛʀᴢᴇɴɪᴇɴɪᴇ ᴛʀᴜᴄɪᴢɴʏ"));

        meta.setLore(lore);

        meta.setDisplayName(getName(tier));
    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        return 0;
    }


    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("&7ᴍɪᴋꜱᴛᴜʀᴀ ᴢᴀᴛʀᴜᴄɪᴀ " + MessageManager.getRomanNumerals(tier));
    }

    @Override
    public void affectedEffect(Player caster, LivingEntity livingEntity, int tier) {

        MagicEffectManager.usePoisonEffect(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE_APPLY, Optional.of(caster), tier, false, Optional.empty(), Optional.of(livingEntity));
//        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * 5, tier >= 3 ? 2 : 1));
//        if(tier >= 2) {
//            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 20 * 2, 2));
//        }

    }

    @Override
    public CombatPotionType getCombatPotionType() {
        return CombatPotionType.POISON;
    }

    @Override
    public List<ItemStack> getRequiredItems(int tier) {
        return List.of();
    }

    @Override
    public int getRequiredMoney(int tier) {
        return 0;
    }

    @Override
    public boolean isLowerTierItemRequired(int tier) {
        return false;
    }
}
