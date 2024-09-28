package me.xxgradzix.gradzixcombatsystem;

import me.xxgradzix.gradzixcombatsystem.armorEvent.ArmorListener;
import me.xxgradzix.gradzixcombatsystem.commands.*;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.listeners.*;
import me.xxgradzix.gradzixcombatsystem.listeners.enchants.AttackComboListener;
import me.xxgradzix.gradzixcombatsystem.listeners.enchants.FreezeAttackListener;
import me.xxgradzix.gradzixcombatsystem.listeners.enchants.LifeStealAttackListener;
import me.xxgradzix.gradzixcombatsystem.managers.EconomyManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class GradzixCombatSystem extends JavaPlugin {

    public static GradzixCombatSystem plugin;

//    private static Economy econ;

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic

        EconomyManager.setupEconomy();

        ItemManager.init();

        getCommand("atrybut").setExecutor(new AttributeCommand());
        getCommand("atributeItem").setExecutor(new ItemGiveCommand());
        getCommand("upgradeItem").setExecutor(new ItemUpgradeGuiCommand());
        getCommand("openReforgeGui").setExecutor(new OpenReforgeGuiCommand());
        getCommand("openUpgradeGui").setExecutor(new OpenUpgradeGuiCommand());

        getServer().getPluginManager().registerEvents(new ArmorBlock(), this);
        getServer().getPluginManager().registerEvents(new AttackCancelListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldBlock(), this);
        getServer().getPluginManager().registerEvents(new RestartPotionDrink(), this);
        getServer().getPluginManager().registerEvents(new StoneOFAggressionUse(), this);
        getServer().getPluginManager().registerEvents(new BowEvents(), this);
        getServer().getPluginManager().registerEvents(new KnockBackListener(), this);

        getServer().getPluginManager().registerEvents(new FreezeAttackListener(), this);
        getServer().getPluginManager().registerEvents(new AttackComboListener(), this);
        getServer().getPluginManager().registerEvents(new LifeStealAttackListener(), this);

        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);


    }
//    private boolean setupEconomy() {
//        Bukkit.broadcastMessage("rsfdsfseef fe   f e              fefefefefefefefefefefefefes");
//        for (@NotNull Plugin plugin : getServer().getPluginManager().getPlugins()) {
//            Bukkit.broadcastMessage("Rlo - " + plugin.getName());
//        }
//        if (getServer().getPluginManager().getPlugin("Vault") == null) {
//            return false;
//        }
//        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
//        if (rsp == null) {
//            return false;
//        }
//        econ = rsp.getProvider();
//        return true;
//    }
//
//    public static Economy getEconomy() {
//        return econ;
//    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
