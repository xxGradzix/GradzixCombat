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

public class CombatBottleFreeze implements CustomBottle {

    public static final String CUSTOM_ID = "gradzixcombat_combat_potion_freeze";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public int getModelData(int tier) {
        return 50001;
    }
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

        lore.add(ColorFixer.addColors("&7ᴛᴀ ʙᴜᴛᴇʟᴋᴀ ꜱᴘᴏᴡᴏᴅᴜᴊᴇ ᴡʏʙᴜᴄʜ ᴍʀᴏᴢᴜ ɴᴀᴋłᴀᴅᴀᴊąᴄᴇɢᴏ ᴇꜰᴇᴋᴛ ᴡʏᴄʜłᴏᴅᴢᴇɴɪᴀ"));

        meta.setLore(lore);

        meta.setDisplayName(getName(tier));

    }

    @Override
    public int getRequiredAttribute(int tier, CombatAttribute attribute) {
        return 0;
    }

    @Override
    public String getName(int tier) {
        return ColorFixer.addColors("&7ᴍɪᴋꜱᴛᴜʀᴀ ᴍʀᴏᴢᴜ " + MessageManager.getRomanNumerals(tier));
    }
    @Override
    public void affectedEffect(Player caster, LivingEntity livingEntity, int tier) {

        // todo supercharge

//        MagicEffectManager.useFreezeEffect(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE_APPLY, Optional.of(caster), tier, false, Optional.empty(), Optional.of(livingEntity));
        new MagicEffectManager.SpellBuilder().effectType(MagicEffectManager.MagicEffectType.FREEZE).useVariant(MagicEffectManager.MagicUseVariant.BATTLE_BOTTLE_APPLY).caster(caster).level(tier).superCharge(false).target(livingEntity).cast();


//        int ticksToAdd = switch (tier) {
//            case 1 -> 75;
//            case 2 -> 90;
//            case 3 -> 105;
//            default -> 0;
//        };
//
//        int previousTicks = livingEntity.getFreezeTicks();
//
//        int newTicks = previousTicks + ticksToAdd;
//        if(newTicks > 900) {
//            newTicks = 900;
//        }
//
//        livingEntity.setFreezeTicks(newTicks);

//        livingEntity.getLocation().getWorld().spawnParticle(Particle.SNOWFLAKE, livingEntity.getLocation(), 15, 0.6, 0.6, 0.6, 0.05);

    }

    @Override
    public CombatPotionType getCombatPotionType() {
        return CombatPotionType.FREEZE;
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
