package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.inteligence;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum MagicLinkAbility implements CombatAbility, StrengthOrigin, EventableAbility {
     INSTANCE;

     @Override
     public NamespacedKey getKey() {
         return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_inteligence_magic_link");
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
         return Set.of();
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
         return "ᴍᴀɢɪᴄᴢɴᴀ ᴡɪęź/ geniusz";
     }

     @Override
     public List<String> getAbilityDescription(int playerLevel) {

         ArrayList<String> lore = new ArrayList<>();
         switch (playerLevel) {

             case 0 -> {
                 lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                 lore.add(ColorFixer.addColors("&7ᴋᴀżᴅʏ ᴀᴛᴀᴋ ᴍᴀɢɪᴄᴢɴʏ ᴍᴀ ꜱᴢᴀɴꜱę ɴᴀ "));
                 lore.add(ColorFixer.addColors("&7ᴢᴏꜱᴛᴀɴɪᴇ ᴡʏᴡᴏłᴀɴʏᴍ ɴᴀ ᴘᴏᴢɪᴏᴍɪᴇ ᴏ 1 ᴡʏżꜱᴢʏᴍ"));
                 lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴀ ᴡʏɴᴏꜱɪ &a10%"));

             }
             case 1 -> {
                 lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                 lore.add(ColorFixer.addColors("&7ᴋᴀżᴅʏ ᴀᴛᴀᴋ ᴍᴀɢɪᴄᴢɴʏ ᴍᴀ ꜱᴢᴀɴꜱę ɴᴀ "));
                 lore.add(ColorFixer.addColors("&7ᴢᴏꜱᴛᴀɴɪᴇ ᴡʏᴡᴏłᴀɴʏᴍ ɴᴀ ᴘᴏᴢɪᴏᴍɪᴇ ᴏ 1 ᴡʏżꜱᴢʏᴍ"));
                 lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴀ ᴡʏɴᴏꜱɪ 10%"));
                 lore.add(" ");
                 lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                 lore.add(ColorFixer.addColors("&7ᴋᴀżᴅʏ ᴀᴛᴀᴋ ᴍᴀɢɪᴄᴢɴʏ ᴍᴀ ꜱᴢᴀɴꜱę ɴᴀ "));
                 lore.add(ColorFixer.addColors("&7ᴢᴏꜱᴛᴀɴɪᴇ ᴡʏᴡᴏłᴀɴʏᴍ ɴᴀ ᴘᴏᴢɪᴏᴍɪᴇ ᴏ 1 ᴡʏżꜱᴢʏᴍ"));
                 lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴀ ᴡʏɴᴏꜱɪ &a20%"));
             }
             case 2 -> {

                 lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                 lore.add(ColorFixer.addColors("&7ᴋᴀżᴅʏ ᴀᴛᴀᴋ ᴍᴀɢɪᴄᴢɴʏ ᴍᴀ ꜱᴢᴀɴꜱę ɴᴀ "));
                 lore.add(ColorFixer.addColors("&7ᴢᴏꜱᴛᴀɴɪᴇ ᴡʏᴡᴏłᴀɴʏᴍ ɴᴀ ᴘᴏᴢɪᴏᴍɪᴇ ᴏ 1 ᴡʏżꜱᴢʏᴍ"));
                 lore.add(ColorFixer.addColors("&7ꜱᴢᴀɴꜱᴀ ᴡʏɴᴏꜱɪ &a20%"));
             }
         }
         lore.add(" ");
         lore.add(ColorFixer.addColors("&7ᴊᴇżᴇʟɪ ᴢᴀᴋʟęᴄɪᴇ ʀᴢᴜᴄᴀɴᴇ ᴊᴇꜱᴛ ɴᴀ ɴᴀᴊᴡʏżꜱᴢʏᴍ ᴘᴏᴢɪᴏᴍɪᴇ"));
         lore.add(ColorFixer.addColors("&7ᴢᴏꜱᴛᴀɴɪᴇ ᴏɴᴇ ᴘʀᴢᴇᴄɪążᴏɴᴇ ᴡᴢᴍᴀᴄɴɪᴀᴊąᴄ ᴊᴇɢᴏ ᴇꜰᴇᴋᴛ"));

         return lore;
     }

     @Override
     public Listener getListener() {

         return new Listener() {
             @EventHandler
             public void onEntityDamageByEntity(EntityDeathEvent event) {

                 Player player = event.getEntity().getKiller();
                 if(player == null) return;

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
