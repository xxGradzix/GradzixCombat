package me.xxgradzix.gradzixcombatsystem;

import me.xxgradzix.gradzixcombatsystem.commands.AttributeTestCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class GradzixCombatSystem extends JavaPlugin {

    public static GradzixCombatSystem plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

        getCommand("atrybut").setExecutor(new AttributeTestCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
