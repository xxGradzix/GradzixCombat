package me.xxgradzix.gradzixcombatsystem.items.enchantBooks.instances;

import me.xxgradzix.gradzixcombatsystem.items.enchantBooks.CustomEnchantBook;
import me.xxgradzix.gradzixcombatsystem.items.weapons.Tierable;
import me.xxgradzix.gradzixcombatsystem.managers.EnchantManager.EnchantManager;
import me.xxgradzix.gradzixcombatsystem.managers.messages.MessageManager;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class AttackComboEnchantBook implements CustomEnchantBook, Tierable {


    private static final String CUSTOM_ID = "gradzixcombat_attack_combo_enchant_book";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public int getModelData(int tier) {
        return 9191002;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.BOOK;
    }


    @Override
    public EnchantManager.Enchant getAssignedEnchant() {
        return EnchantManager.Enchant.ATTACK_COMBO;
    }

    @Override
    public List<String> getEnchantDescription(int tier) {

        List<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴍᴀɢɪᴄᴢɴᴀ ᴋꜱɪęɢᴀ ᴘᴏᴢᴡᴏʟɪ ɴᴀłᴏżʏć ᴢᴀᴋʟęᴄɪᴇ " + getAssignedEnchant().enchantSuffix + " ɴᴀ ʙʀᴏń"));
        lore.add(" ");

        lore.add(MessageManager.getRomanNumeralsForEnchant(1) + (tier >= 1 ? ChatColor.GREEN : ChatColor.RED) + ColorFixer.addColors("   ᴋᴀżᴅʏ ᴋᴏʟᴇᴊɴʏ ᴄɪᴏꜱ ᴢᴀᴅᴀɴʏ ᴡ ꜱᴇʀɪɪ, ᴢᴡɪᴇᴋꜱᴢᴀ ᴢᴀᴅᴀᴡᴀɴᴇ"));
        lore.add("   " + (tier >= 1 ? ChatColor.GREEN : ChatColor.RED) + ColorFixer.addColors(" ᴏʙʀᴀżᴇɴɪᴀ ᴅᴏ ᴄᴢᴀꜱᴜ ᴏᴛʀᴢʏᴍᴀɴɪᴀ ᴏʙʀᴀżᴇń"));
        lore.add(MessageManager.getRomanNumeralsForEnchant(2) + (tier >= 2 ? ChatColor.GREEN : ChatColor.RED) + ColorFixer.addColors("  ᴢᴡɪęᴋꜱᴢᴀ ᴘʀᴏᴄᴇɴᴛᴏᴡʏ ᴡᴢʀᴏꜱᴛ ᴏʙʀᴀżᴇń"));
        lore.add(MessageManager.getRomanNumeralsForEnchant(3) + (tier >= 3 ? ChatColor.GREEN : ChatColor.RED) + ColorFixer.addColors(" ᴅʀᴀꜱᴛʏᴄᴢɴɪᴇ ᴢᴡɪęᴋꜱᴢᴀ ᴘʀᴏᴄᴇɴᴛᴏᴡʏ ᴡᴢʀᴏꜱᴛ ᴏʙʀᴀżᴇń"));
        
        return lore;
    }

}
