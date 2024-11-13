//package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.endurance;
//
//import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
//import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
//import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.EnduranceOrigin;
//import org.bukkit.NamespacedKey;
//import org.bukkit.attribute.Attribute;
//import org.bukkit.attribute.AttributeModifier;
//import org.bukkit.entity.Player;
//
//import java.util.Set;
//
//public enum QuickSneak implements CombatAbility, EnduranceOrigin {
//
//    INSTANCE;
//
//    @Override
//    public NamespacedKey getKey() {
//        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_quick_sneak");
//    }
//
//
//    private static final NamespacedKey SNEAK_SPEED_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_quick_sneak_sneak_speed");
//
//    @Override
//    public void applyAbility(Player player, int level) {
//        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
//        removeAbility(player);
//        setAbilityLevel(player, level);
//        player.getAttribute(Attribute.PLAYER_SNEAKING_SPEED).addModifier(new AttributeModifier(SNEAK_SPEED_KEY, 0.1, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
//    }
//
//    @Override
//    public void removeAbility(Player player) {
//        setAbilityLevel(player, 0);
//        player.getAttribute(Attribute.PLAYER_SNEAKING_SPEED).removeModifier(SNEAK_SPEED_KEY);
//    }
//
//    @Override
//    public int getRequiredAttributeLevel() {
//        return 1;
//    }
//
//    @Override
//    public int getMaxAbilityLevel() {
//        return 1;
//    }
//
//    @Override
//    public Set<CombatAbility> getRequiredAbilities() {
//        return Set.of();
//    }
//
//
//
//}
