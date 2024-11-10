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

public class FireAfflictionEnchantBook implements CustomEnchantBook, Tierable {


    private static final String CUSTOM_ID = "gradzixcombat_fire_affliction_enchant_book";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public int getModelData(int tier) {
        return 9191003;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.BOOK;
    }


    @Override
    public EnchantManager.Enchant getAssignedEnchant() {
        return EnchantManager.Enchant.FIRE_AFFLICTION;
    }

    @Override
    public List<String> getEnchantDescription(int tier) {

        List<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴍᴀɢɪᴄᴢɴᴀ ᴋꜱɪęɢᴀ ᴘᴏᴢᴡᴏʟɪ ɴᴀłᴏżʏć ᴢᴀᴋʟęᴄɪᴇ " + getAssignedEnchant().enchantSuffix + " ɴᴀ ʙʀᴏń"));

        lore.add(" ");
        lore.add(MessageManager.getRomanNumeralsForEnchant(1) + (tier >= 1 ? ChatColor.GREEN : ChatColor.RED) + "   ᴋᴀżᴅᴇ ᴜᴅᴇʀᴢᴇɴɪᴇ ʙʀᴏɴɪ ᴘᴏᴅᴘᴀʟᴀ ᴘʀᴢᴇᴄɪᴡɴɪᴋᴀ");
        lore.add(MessageManager.getRomanNumeralsForEnchant(2) + (tier >= 2 ? ChatColor.GREEN : ChatColor.RED) + "  ᴋʟɪᴋɴɪęᴄɪᴇ ᴘᴘᴍ ꜱᴘᴏᴡᴏᴅᴜᴊᴇ ᴡʏꜱᴛʀᴢᴇʟᴇɴɪᴇ ꜱᴛʀᴜᴍɪᴇɴɪᴀ");
        lore.add("   " + (tier >= 2 ? ChatColor.GREEN : ChatColor.RED) + " ᴏɢɴɪᴀ ᴘᴏᴅᴘᴀʟᴀᴊąᴄᴇɢᴏ ᴘʀᴢᴇᴄɪᴡɴɪᴋóᴡ");
        lore.add(MessageManager.getRomanNumeralsForEnchant(3) + (tier >= 3 ? ChatColor.GREEN : ChatColor.RED) + " ᴢᴡɪęᴋꜱᴢᴀ ᴢᴀꜱɪęɢ ᴘᴏᴅᴍᴜᴄʜᴜ ᴏɢɴɪᴀ");

        return lore;
    }

}
