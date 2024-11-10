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

public class WindChargeEnchantBook implements CustomEnchantBook, Tierable {


    private static final String CUSTOM_ID = "gradzixcombat_wind_charge_enchant_book";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public int getModelData(int tier) {
        return 9191011;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.BOOK;
    }


    @Override
    public EnchantManager.Enchant getAssignedEnchant() {
        return EnchantManager.Enchant.WIND_CHARGE;
    }

    @Override
    public List<String> getEnchantDescription(int tier) {

        List<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴍᴀɢɪᴄᴢɴᴀ ᴋꜱɪęɢᴀ ᴘᴏᴢᴡᴏʟɪ ɴᴀłᴏżʏć ᴢᴀᴋʟęᴄɪᴇ " + getAssignedEnchant().enchantSuffix + " ɴᴀ ʙʀᴏń"));

        lore.add(" ");
        lore.add(MessageManager.getRomanNumeralsForEnchant(1) + (tier >= 1 ? ChatColor.GREEN : ChatColor.RED) + "   ᴋʟɪᴋɴɪęᴄɪᴇ ᴘᴘᴍ ᴘᴏᴡᴏᴅᴜᴊᴇ ᴡʏꜱᴛʀᴢᴇʟᴇɴɪ ᴇꜱᴇɴᴄᴊɪ ");
        lore.add("   " + (tier >= 1 ? ChatColor.GREEN : ChatColor.RED) + " ᴡɪᴀᴛʀᴜ ᴏᴅᴘʏᴄʜᴀᴊąᴄᴇᴊ ᴡꜱᴢʏꜱᴛᴋᴏ ɴᴀ ꜱᴡᴏᴊᴇᴊ ᴅʀᴏᴅᴢᴇ");
        lore.add(MessageManager.getRomanNumeralsForEnchant(2) + (tier >= 2 ? ChatColor.GREEN : ChatColor.RED) + "  ᴢᴍɴɪᴇᴊꜱᴢᴀ ᴄᴏᴏʟᴅᴏᴡɴ ᴜᴍɪᴇᴊęᴛɴᴏśᴄɪ");
        lore.add(MessageManager.getRomanNumeralsForEnchant(3) + (tier >= 3 ? ChatColor.GREEN : ChatColor.RED) + " ᴢᴡɪęᴋꜱᴢᴀ ᴄᴏᴏʟᴅᴏᴡɴ ᴜᴍɪᴇᴊęᴛɴᴏśᴄɪ ɪ");
        lore.add("   " + (tier >= 3 ? ChatColor.GREEN : ChatColor.RED) + " ᴢᴡɪęᴋꜱᴢᴀ ᴘʀęᴅᴋᴏść ʟᴏᴛᴜ ᴇꜱᴇɴᴄᴊɪ");

        return lore;
    }

}
