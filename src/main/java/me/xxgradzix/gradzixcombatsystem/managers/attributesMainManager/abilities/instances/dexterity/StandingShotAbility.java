package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.DexterityOrigin;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum StandingShotAbility implements CombatAbility, DexterityOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_dexterity_standing_shot");
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
        return 1;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 1;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of();
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 5;
    }

    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onEntityDamageByEntity(EntityShootBowEvent event) {

                if(!(event.getEntity() instanceof Player shooter)) return;

                ItemStack bow = event.getBow();

                if(Material.BOW.equals(bow.getType())) return;

                //todo check ability
                // TODO do usuniecia

                Vector velocity = shooter.getVelocity();

                boolean isStanding = velocity.getX() == 0 && velocity.getZ() == 0;

                if(!isStanding) return;

                event.getProjectile().getVelocity().multiply(1.8);
                Bukkit.broadcastMessage("Standing shot ability triggered");

            }
        };
    }

    @Override
    public String getAbilityName() {
        return "&#430C53&lᴄ&#4C115E&lᴇ&#551669&lʟ&#5E1B73&lɴ&#67207E&lʏ &#7A2B94&lꜱ&#83309F&lᴛ&#8C35AA&lʀ&#953AB4&lᴢ&#9E3FBF&lᴀ&#A744CA&lł";
    }



    @Override
    public List<String> getAbilityDescription(int playerLevel) {
        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {

            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));

                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴇɴᴇʀɢɪᴇ ɪ ᴘʀęᴅᴋᴏść ꜱᴛʀᴢᴀł ɢᴅʏ "));
                lore.add(ColorFixer.addColors("&7ɢʀᴀᴄᴢ ꜱᴛᴏɪ ᴡ ᴍɪᴇᴊꜱᴄᴜ ᴘᴏᴅᴄᴢᴀꜱ ꜱᴛʀᴢᴀłᴜ ᴢ łᴜᴋᴜ"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴡɪęᴋꜱᴢᴀ ᴇɴᴇʀɢɪᴇ ɪ ᴘʀęᴅᴋᴏść ꜱᴛʀᴢᴀł ɢᴅʏ "));
                lore.add(ColorFixer.addColors("&7ɢʀᴀᴄᴢ ꜱᴛᴏɪ ᴡ ᴍɪᴇᴊꜱᴄᴜ ᴘᴏᴅᴄᴢᴀꜱ ꜱᴛʀᴢᴀłᴜ ᴢ łᴜᴋᴜ"));
            }

        }
        return lore;
    }
}
