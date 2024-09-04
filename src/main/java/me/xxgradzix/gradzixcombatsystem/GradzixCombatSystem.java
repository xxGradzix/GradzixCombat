package me.xxgradzix.gradzixcombatsystem;

import me.xxgradzix.gradzixcombatsystem.armorEvent.ArmorListener;
import me.xxgradzix.gradzixcombatsystem.commands.AttributeTestCommand;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.listeners.ArmorBlock;
import me.xxgradzix.gradzixcombatsystem.listeners.CriticalDamageListener;
import me.xxgradzix.gradzixcombatsystem.listeners.ShieldBlock;
import me.xxgradzix.gradzixcombatsystem.listeners.SpearRange;
import org.bukkit.plugin.java.JavaPlugin;

public final class GradzixCombatSystem extends JavaPlugin {

    public static GradzixCombatSystem plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

        ItemManager.init();

        getCommand("atrybut").setExecutor(new AttributeTestCommand());

        getServer().getPluginManager().registerEvents(new ArmorBlock(), this);
        getServer().getPluginManager().registerEvents(new CriticalDamageListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldBlock(), this);
        getServer().getPluginManager().registerEvents(new SpearRange(), this);

        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
