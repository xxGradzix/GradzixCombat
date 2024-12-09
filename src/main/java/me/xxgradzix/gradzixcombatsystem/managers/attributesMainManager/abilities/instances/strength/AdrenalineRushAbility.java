package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.DexterityOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity.OneHandAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum AdrenalineRushAbility implements CombatAbility, StrengthOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_adrenaline_rush");
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
        return 12;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 2;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of(HeavyArmorSetBonusAbility.INSTANCE, CombatHealingAbility.INSTANCE);
    }

    @Override
    public int getRow() {
        return 3;
    }

    @Override
    public int getColumn() {
        return 2;
    }

    @Override
    public String getAbilityName() {
        return "&#708D53&lᴢ&#6C994B&lᴀ&#69A542&lꜱ&#65B23A&lᴛ&#62BE32&lʀ&#5ECA29&lᴢ&#5BD621&lʏ&#57E319&lᴋ &#50FB08&lᴀ&#48E807&lᴅ&#40D506&lʀ&#38C205&lᴇ&#30AF04&lɴ&#279B04&lᴀ&#1F8803&lʟ&#177502&lɪ&#0F6201&lɴ&#074F00&lʏ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();
        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴢᴀʙɪᴄɪᴜ ɢʀᴀᴄᴢᴀ ᴀᴋᴛʏᴡᴜᴊᴇ "));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ ɴᴀ &a5 ꜱᴇᴋᴜɴᴅ"));

            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴢᴀʙɪᴄɪᴜ ɢʀᴀᴄᴢᴀ ᴀᴋᴛʏᴡᴜᴊᴇ "));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴀᴄᴊᴇ ᴢᴅʀᴏᴡɪᴀ ɴᴀ 5 ꜱᴇᴋᴜɴᴅ"));

                lore.add(" ");
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴢᴀʙɪᴄɪᴜ ɢʀᴀᴄᴢᴀ ɴᴀᴛʏᴄʜᴍɪᴀꜱᴛ"));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴜᴊᴇ &a30% ᴢᴅʀᴏᴡɪᴀ"));
            }
            case 2 -> {

                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴘᴏ ᴢᴀʙɪᴄɪᴜ ɢʀᴀᴄᴢᴀ ɴᴀᴛʏᴄʜᴍɪᴀꜱᴛ"));
                lore.add(ColorFixer.addColors("&7ʀᴇɢᴇɴᴇʀᴜᴊᴇ &a30% ᴢᴅʀᴏᴡɪᴀ"));
            }

        }
        return lore;
    }

    @Override
    public Listener getListener() {

        return new Listener() {
            @EventHandler
            public void onEntityDamageByEntity(PlayerDeathEvent event) {

                Player player = event.getEntity();

                int level = getAbilityLevel(player);
                if(level <= 0) return;

                if(level == 1) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
                }
                if (level == 2) {
                    player.setHealth(player.getHealth() + 6);
                }
                Bukkit.broadcastMessage("test Adrenaline rush ability triggered");
            }
        };
    }
}
