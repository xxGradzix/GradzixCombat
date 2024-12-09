package me.xxgradzix.gradzixcombatsystem;

import me.xxgradzix.gradzixcombatsystem.events.armorEvent.ArmorListener;
import me.xxgradzix.gradzixcombatsystem.commands.*;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.GigantysmOrbListener;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.TeleportOrbListener;
import me.xxgradzix.gradzixcombatsystem.listeners.*;
import me.xxgradzix.gradzixcombatsystem.listeners.battleBottles.BottleListener;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.LeftClickHeroPotion;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.PotionDrinkEvent;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.DashOrbListener;
import me.xxgradzix.gradzixcombatsystem.listeners.combatHeroPotion.effectsEvents.ExplosionOrbListener;
import me.xxgradzix.gradzixcombatsystem.listeners.enchants.*;
import me.xxgradzix.gradzixcombatsystem.managers.EconomyManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.AbilitiesPointsManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class GradzixCombatSystem extends JavaPlugin {

    public static final int MAX_TIER = 5;
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
        getCommand("openUpgradeGui").setExecutor(new OpenUpgradeGuiCommand());

        getCommand("openEnchantGui").setExecutor(new EnchantGuiCommand());
        getCommand("openWitchGui").setExecutor(new OpenWitchGuiCommand());

        getServer().getPluginManager().registerEvents(new ArmorBlock(), this);
        getServer().getPluginManager().registerEvents(new AttackCancelListener(), this);
        getServer().getPluginManager().registerEvents(new ShieldBlock(), this);
        getServer().getPluginManager().registerEvents(new RestartPotionDrink(), this);
        getServer().getPluginManager().registerEvents(new StoneOFAggressionUse(), this);
        getServer().getPluginManager().registerEvents(new BowEvents(), this);
        getServer().getPluginManager().registerEvents(new KnockBackListener(), this);

        getServer().getPluginManager().registerEvents(new ArrowRainEnchant(), this);
        getServer().getPluginManager().registerEvents(new AttackComboListener(), this);
        getServer().getPluginManager().registerEvents(new FireAfflictionEnchant(), this);
        getServer().getPluginManager().registerEvents(new FreezeAttackListener(), this);
        getServer().getPluginManager().registerEvents(new GreedEnchant(), this);
        getServer().getPluginManager().registerEvents(new LifeStealAttackListener(), this);
        getServer().getPluginManager().registerEvents(new LightningEnchant(), this);
        getServer().getPluginManager().registerEvents(new MultiShot(), this);
        getServer().getPluginManager().registerEvents(new SoulStealListener(), this);
        getServer().getPluginManager().registerEvents(new WindEnchantChargeListener(), this);
        getServer().getPluginManager().registerEvents(new PoisonAttackListener(), this);

        getServer().getPluginManager().registerEvents(new BottleListener(), this);

        getServer().getPluginManager().registerEvents(new LeftClickHeroPotion(), this);
        getServer().getPluginManager().registerEvents(new DashOrbListener(), this);
        getServer().getPluginManager().registerEvents(new ExplosionOrbListener(), this);
        getServer().getPluginManager().registerEvents(new PotionDrinkEvent(), this);
        getServer().getPluginManager().registerEvents(new TeleportOrbListener(), this);
        getServer().getPluginManager().registerEvents(new GigantysmOrbListener(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new RecipeOpenEvent(), this);
        getServer().getPluginManager().registerEvents(new CheckCriticalHitListener(), this);
        getServer().getPluginManager().registerEvents(new WeaponCriticalBonusModifierEvent(), this);

        AbilitiesPointsManager.registerListeners();

        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
