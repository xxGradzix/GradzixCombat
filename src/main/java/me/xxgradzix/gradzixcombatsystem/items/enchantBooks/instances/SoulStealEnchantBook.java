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

public class SoulStealEnchantBook implements CustomEnchantBook, Tierable {


    private static final String CUSTOM_ID = "gradzixcombat_soul_steal_enchant_book";

    @Override
    public String getCustomId() {
        return CUSTOM_ID;
    }

    @Override
    public int getModelData(int tier) {
        return 9191010;
    }

    @Override
    public Material getMaterial(int tier) {
        return Material.BOOK;
    }


    @Override
    public EnchantManager.Enchant getAssignedEnchant() {
        return EnchantManager.Enchant.SOUL_STEAL;
    }

    @Override
    public List<String> getEnchantDescription(int tier) {

        List<String> lore = new ArrayList<>();
        lore.add(ColorFixer.addColors("&7ᴛᴀ ᴍᴀɢɪᴄᴢɴᴀ ᴋꜱɪęɢᴀ ᴘᴏᴢᴡᴏʟɪ ɴᴀłᴏżʏć ᴢᴀᴋʟęᴄɪᴇ " + getAssignedEnchant().enchantSuffix + " ɴᴀ ᴛᴀʀᴄᴢę"));

        lore.add(" ");

        lore.add(MessageManager.getRomanNumeralsForEnchant(1) + (tier >= 1 ? ChatColor.GREEN : ChatColor.RED) + "   ᴋʀʏᴛʏᴄᴢɴᴇ ᴏʙʀᴀżᴇɴɪᴀ ᴘᴏᴡᴏᴅᴜᴊą ᴋᴜᴍᴜʟᴀᴄᴊᴇ ᴅᴜꜱᴢ ᴡ ᴛᴀʀᴄᴢʏ      ");
        lore.add("    " + (tier >= 1 ? ChatColor.GREEN : ChatColor.RED) + " ᴋᴛóʀᴇ ᴍᴏɢą ʙʏć ᴜᴡᴏʟɴɪᴏɴᴇ ᴡ ᴄᴇʟᴜ ᴜᴛᴡᴏʀᴢᴇɴɪᴀ ᴅᴜᴄʜᴏᴡᴇᴊ ᴛᴀʀᴄᴢʏ      " + ColorFixer.addColors("&8[&7ᴍᴀx ᴅᴜꜱᴢ: &b10&8]"));
        lore.add(MessageManager.getRomanNumeralsForEnchant(2) + (tier >= 2 ? ChatColor.GREEN : ChatColor.RED) + "  ᴢᴡɪęᴋꜱᴢᴀ ᴘᴏᴊᴇᴍɴᴏść ᴛᴀʀᴄᴢʏ                                      " + ColorFixer.addColors("&8[&7ᴍᴀx ᴅᴜꜱᴢ: &b20&8]"));
        lore.add(MessageManager.getRomanNumeralsForEnchant(3) + (tier >= 3 ? ChatColor.GREEN : ChatColor.RED) + " ᴢᴡɪęᴋꜱᴢᴀ ᴘᴏᴊᴇᴍɴᴏść ᴛᴀʀᴄᴢʏ ɪ ᴢᴀᴅᴀᴊᴇ ᴏʙʀᴀżᴇɴɪᴀ ɢʀᴀᴄᴢᴏᴍ ᴡ ᴢᴀꜱɪęɢᴜ " + ColorFixer.addColors("&8[&7ᴍᴀx ᴅᴜꜱᴢ: &b30&8]"));

        return lore;
    }

}
