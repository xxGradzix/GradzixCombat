package me.xxgradzix.gradzixcombatsystem.items.battleBottles;

import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.Upgradable;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.CombatAttribute;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public interface CustomBottle extends CustomItem, Tierable, Upgradable {

    @Override
    String getCustomId();

    int getModelData(int tier);

    @Override
    default ItemStack getDefaultItemStack(Object... optionalArgs) {

        int tier = 1;
        if(optionalArgs.length == 1) {
            if(optionalArgs[0] instanceof Integer) {
                tier = (int) optionalArgs[0];
            }
        }

        ItemStack itemStack = new ItemStack(Material.LINGERING_POTION);

        setTier(itemStack, tier);

        ItemMeta meta = itemStack.getItemMeta();
        meta.displayName(Component.text(getName(tier)));

        PotionMeta potionMeta = (PotionMeta) meta;
        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.UNLUCK, 0, 0, true, false, false), false);

        potionMeta.setColor(null);
        meta.setMaxStackSize(tier);
        meta.setCustomModelData(getModelData(tier));

        defaultSetItemCustomId(meta);

        setLoreAndName(meta, tier);
        hideAll(meta);

        itemStack.setItemMeta(meta);

        return itemStack;
    }

    void setLoreAndName(ItemMeta meta, int tier);

    int getRequiredAttribute(int tier, CombatAttribute attribute);

    String getName(int tier);

    void affectedEffect(Player caster, LivingEntity livingEntity, int tier);

    CombatPotionType getCombatPotionType();
}
