package me.xxgradzix.gradzixcombatsystem.managers;


import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageManager extends OkaeriConfig {

    @Comment("Messages")
    @Comment("Missed message")
    public static String YOU_MISSED = "&7ɴɪᴇ ᴛʀᴀꜰɪᴌᴇś";

    @Comment("Missed message")
    public static String NOT_SUFFICIENT_ATTRIBUTES = "&7ɴɪᴇ ᴍᴀꜱᴢ ᴡʏꜱᴛᴀʀᴄᴢᴀᴊąᴄʏᴄʜ ᴀᴛʀʏʙᴜᴛóᴡ";

    @Comment("Not Strong Enough")
    public static String NOT_STRONG_ENOUGH = "&7ɴɪᴇ ᴊᴇꜱᴛᴇś ᴡʏꜱᴛᴀʀᴄᴢᴀᴊąᴄᴏ ꜱɪʟɴʏ ʙʏ ᴛᴏ ᴢᴀᴌᴏżʏć";

    public static void sendMessageFormated(Player player, String message, MessageType type) {
        message = ColorFixer.addColors(message);
        switch (type) {
            case TITLE:
                player.sendTitle(message, null, 15, 70, 15);
                break;
            case SUBTITLE:
                player.sendTitle(null, message, 15, 70, 15);
                break;
            case ACTIONBAR:
                player.sendActionBar(TextComponent.fromLegacyText(message));
                break;
            case CHAT:
                player.sendMessage(message);
                break;
        }
    }


    public static void broadcastMessageFormated(String message, MessageType messageType) {
        message = ColorFixer.addColors(message);
        switch (messageType) {
            case TITLE:
                for(Player p : Bukkit.getOnlinePlayers())
                    p.sendTitle(message, null, 15, 70, 15);
                break;
            case SUBTITLE:
                for(Player p : Bukkit.getOnlinePlayers())
                    p.sendTitle(null, message, 15, 70, 15);
                break;
            case ACTIONBAR:
                for(Player p : Bukkit.getOnlinePlayers())
                    p.sendActionBar(TextComponent.fromLegacyText(message));
                break;
            case CHAT:
                Bukkit.broadcastMessage(message);
                break;
        }
    }

    public static String secondsToTimeFormat(int seconds) {
        int minutes = seconds / 60;
        int sec = seconds % 60;
        int hours = minutes / 60;
        minutes %= 60;
        StringBuilder sb = new StringBuilder();
        sb.append("§3");
        if(hours > 0) {
            sb.append(hours).append(" §3ɢᴏᴅᴢɪɴ §b");
        }
        if(minutes > 0) {
            sb.append(minutes).append(" §3ᴍɪɴᴜᴛ §b");
        }
        if(sec > 0) {
            sb.append(sec).append(" §3ꜱᴇᴋᴜɴᴅ §b");
        }

        return sb.toString();
    }
    public static String secondsToTimeFormatSkipSeconds(int seconds) {
        int minutes = seconds / 60;
        int hours = minutes / 60;
        minutes %= 60;
        StringBuilder sb = new StringBuilder();
        sb.append("§3");
        if(hours > 0) {
            sb.append(hours).append(" §3ɢᴏᴅᴢɪɴ §b");
        }
        if(minutes > 0) {
            sb.append(minutes).append(" §3ᴍɪɴᴜᴛ §b");
        }

        return sb.toString();
    }
}