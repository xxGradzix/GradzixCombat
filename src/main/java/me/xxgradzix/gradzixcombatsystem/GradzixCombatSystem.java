package me.xxgradzix.gradzixcombatsystem;

import me.xxgradzix.gradzixcombatsystem.armorEvent.ArmorListener;
import me.xxgradzix.gradzixcombatsystem.commands.*;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.listeners.*;
import me.xxgradzix.gradzixcombatsystem.listeners.enchants.AttackComboListener;
import me.xxgradzix.gradzixcombatsystem.listeners.enchants.FreezeAttackListener;
import me.xxgradzix.gradzixcombatsystem.listeners.enchants.LifeStealAttackListener;
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
        getCommand("upgradeItem").setExecutor(new ItemUpgradeGuiCommand());
        getCommand("openReforgeGui").setExecutor(new OpenReforgeGuiCommand());
        getCommand("openUpgradeGui").setExecutor(new OpenUpgradeGuiCommand());

        getServer().getPluginManager().registerEvents(new ArmorBlock(), this);
        getServer().getPluginManager().registerEvents(new AttackCancelListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldBlock(), this);
        getServer().getPluginManager().registerEvents(new RestartPotionDrink(), this);
        getServer().getPluginManager().registerEvents(new StoneOFAggressionUse(), this);
        getServer().getPluginManager().registerEvents(new AttackRangeListener(), this);
        getServer().getPluginManager().registerEvents(new KnockBackListener(), this);

        getServer().getPluginManager().registerEvents(new FreezeAttackListener(), this);
        getServer().getPluginManager().registerEvents(new AttackComboListener(), this);
        getServer().getPluginManager().registerEvents(new LifeStealAttackListener(), this);

        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
