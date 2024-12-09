package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.events.critEvent.CriticalHitEvent;
import me.xxgradzix.gradzixcombatsystem.items.CustomItem;
import me.xxgradzix.gradzixcombatsystem.items.CustomItemManager;
import me.xxgradzix.gradzixcombatsystem.items.weapons.instances.BattleSpear;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength.AdrenalineRushAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum EnduranceCritChanceAbility implements CombatAbility, EnduranceOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_endurance_crit_chance");
    }

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);
    }

    @Override
    public void removeAbility(Player player) {
        setAbilityLevel(player, 0);
    }

    @Override
    public int getRequiredAttributeLevel() {
        return 9;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 3;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of(EffectResistance.INSTANCE);
    }

    @Override
    public int getRow() {
        return 4;
    }

    @Override
    public int getColumn() {
        return 3;
    }

    @Override
    public String getAbilityName() {
        return "&#BC001E&lᴜ&#B90623&lᴅ&#B60D28&lᴇ&#B4132D&lʀ&#B11A32&lᴢ&#AE2037&lᴇ&#AB273C&lɴ&#A92D41&lɪ&#A63446&lᴀ &#A04150&lᴋ&#9E4755&lʀ&#9B4E5A&lʏ&#98545F&lᴛ&#98545F&lʏ&#98545F&lᴄ&#98545F&lᴢ&#98545F&lɴ&#98545F&lᴇ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ &a5%"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ 5%"));
                lore.add(" ");
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ &a10%"));
            }
            case 2 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ 10%"));
                lore.add(" ");
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ &a15%"));
            }
            case 3 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ꜱᴢᴀɴꜱᴇ ɴᴀ ᴜᴅᴇʀᴢᴇɴɪᴇ ᴋʀʏᴛʏᴄᴢɴᴇ ᴏ &a15%"));
            }

        }
        lore.add(" ");
        lore.add(ColorFixer.addColors("&7ᴘʀᴇᴍɪᴀ ᴅᴏᴛʏᴄᴢʏ ᴛʏʟᴋᴏ ᴡłóᴄᴢɴɪ"));

        return lore;
    }

    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onCritAttck(CriticalHitEvent event) {

                Player player = event.getPlayer();

                ItemStack itemInMainHand = player.getInventory().getItemInMainHand();

                if(itemInMainHand == null) return;

                CustomItem customItem = CustomItemManager.getCustomItem(itemInMainHand);

                if(!(customItem instanceof BattleSpear)) return;

                event.setCriticalChance(event.getCriticalChance() + getAbilityLevel(player) * 0.05);

            }

        };
    }
}
