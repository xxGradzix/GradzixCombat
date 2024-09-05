package me.xxgradzix.gradzixcombatsystem;

import me.xxgradzix.gradzixcombatsystem.armorEvent.ArmorListener;
import me.xxgradzix.gradzixcombatsystem.commands.AttributeCommand;
import me.xxgradzix.gradzixcombatsystem.commands.ItemGiveCommand;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.listeners.ArmorBlock;
import me.xxgradzix.gradzixcombatsystem.listeners.AttackCancelListener;
import me.xxgradzix.gradzixcombatsystem.listeners.RestartPotionDrink;
import me.xxgradzix.gradzixcombatsystem.listeners.ShieldBlock;
import org.bukkit.plugin.java.JavaPlugin;

public final class GradzixCombatSystem extends JavaPlugin {

    public static GradzixCombatSystem plugin;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

        ItemManager.init();

        getCommand("atrybut").setExecutor(new AttributeCommand());
        getCommand("atributeItem").setExecutor(new ItemGiveCommand());

        getServer().getPluginManager().registerEvents(new ArmorBlock(), this);
        getServer().getPluginManager().registerEvents(new AttackCancelListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldBlock(), this);
        getServer().getPluginManager().registerEvents(new RestartPotionDrink(), this);

        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
