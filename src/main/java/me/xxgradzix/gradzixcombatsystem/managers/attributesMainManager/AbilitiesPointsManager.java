package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.AttributeOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.dexterity.*;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength.*;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.stream.Collectors;

public class AbilitiesPointsManager {

    private static final Set<CombatAbility> abilities = Set.of(

            AssassinAbility.INSTANCE,
            AttackSpeedAbility.INSTANCE,
            BowSwordComboAbility.INSTANCE,
            DexterityCritChanceAbility.INSTANCE,
            DodgeAbility.INSTANCE,
            LightArmorSetBonusAbility.INSTANCE,
            OneHandAbility.INSTANCE,
            StandingShotAbility.INSTANCE,

            AmmoSavingAbility.INSTANCE,
            BullsEyeAbility.INSTANCE,
            CombatHealingAbility.INSTANCE,
            StaggerAbility.INSTANCE,
            HeavyArmorSetBonusAbility.INSTANCE,
            BerserkerAbility.INSTANCE,
            FattyAbility.INSTANCE,
            WarMachineAbility.INSTANCE,
            OneManArmyAbility.INSTANCE,
            StrengthCritChanceAbility.INSTANCE,
            BonusDamageAbility.INSTANCE,
            AdrenalineRushAbility.INSTANCE

    );

    public static void resetAbilities(Player player) {
        for (CombatAbility ability : abilities) {
            ability.removeAbility(player);
        }
    }

    public static void registerListeners() {
        for (CombatAbility ability : abilities) {
            if(ability instanceof EventableAbility eventableAbility) {
                GradzixCombatSystem.plugin.getServer().getPluginManager().registerEvents(eventableAbility.getListener(), GradzixCombatSystem.plugin);
            }
        }
    }

    public static void openAbilitiesGui(Player player, CombatAttribute attribute) {
        new AbilitiesGuiManager(player, attribute);
    }

    public static Set<CombatAbility> getAbilities(CombatAttribute attribute) {

        return abilities.stream()
                .filter(ability -> ability instanceof AttributeOrigin origin && origin.getAttributeOrigin().equals(attribute))
                .collect(Collectors.toSet());
    }

}
