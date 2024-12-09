package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.strength;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.attributeOrigins.StrengthOrigin;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.EventableAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum FattyAbility implements CombatAbility, StrengthOrigin, EventableAbility {

    INSTANCE;

    @Override
    public NamespacedKey getKey() {
        return new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_fatty");
    }

    private static final NamespacedKey KNOCK_BACK_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_fatty_knock_back");
    private static final NamespacedKey EXPLOSION_KNOCK_BACK_KEY = new NamespacedKey(GradzixCombatSystem.plugin, "gradzixcombat_ability_strength_fatty_explosion_knock_back");

    @Override
    public void applyAbility(Player player, int level) {
        if(level > getMaxAbilityLevel()) level = getMaxAbilityLevel();
        removeAbility(player);
        setAbilityLevel(player, level);

        player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).addModifier(new AttributeModifier(KNOCK_BACK_KEY, 0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        player.getAttribute(Attribute.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE).addModifier(new AttributeModifier(EXPLOSION_KNOCK_BACK_KEY, 0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1));

    }

    @Override
    public void removeAbility(Player player) {
        setAbilityLevel(player, 0);

        player.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).removeModifier(KNOCK_BACK_KEY);
        player.getAttribute(Attribute.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE).removeModifier(EXPLOSION_KNOCK_BACK_KEY);
    }

    @Override
    public int getRequiredAttributeLevel() {
        return 3;
    }

    @Override
    public int getMaxAbilityLevel() {
        return 1;
    }

    @Override
    public Set<CombatAbility> getRequiredAbilities() {
        return Set.of();
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getColumn() {
        return 3;
    }

    @Override
    public String getAbilityName() {
        return "&#FFCF00&lᴛ&#EEC000&lł&#DEB000&lᴜ&#CDA100&lś&#BC9100&lᴄ&#AB8200&lɪ&#9B7200&lᴏ&#8A6300&lᴄ&#795300&lʜ";
    }

    @Override
    public List<String> getAbilityDescription(int playerLevel) {

        ArrayList<String> lore = new ArrayList<>();

        switch (playerLevel) {
            case 0 -> {
                lore.add(ColorFixer.addColors("#877239ɴᴀꜱᴛęᴘɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴍɴɪᴇᴊꜱᴢᴀ ᴏʙʀᴀżᴇɴɪᴀ ᴏᴅ ᴜᴘᴀᴅᴋᴜ, "));
                lore.add(ColorFixer.addColors("&7ᴏᴛʀᴢʏᴍʏᴡᴀɴʏ ᴏᴅʀᴢᴜᴛ ɪ ᴏʙʀᴀżᴇɴɪᴀ ᴏᴅ ᴡʏʙᴜᴄʜóᴡ"));
            }
            case 1 -> {
                lore.add(ColorFixer.addColors("#877239ᴏʙᴇᴄɴʏ ᴘᴏᴢɪᴏᴍ"));
                lore.add(ColorFixer.addColors("&7ᴢᴍɴɪᴇᴊꜱᴢᴀ ᴏʙʀᴀżᴇɴɪᴀ ᴏᴅ ᴜᴘᴀᴅᴋᴜ, "));
                lore.add(ColorFixer.addColors("&7ᴏᴛʀᴢʏᴍʏᴡᴀɴʏ ᴏᴅʀᴢᴜᴛ ɪ ᴏʙʀᴀżᴇɴɪᴀ ᴏᴅ ᴡʏʙᴜᴄʜóᴡ"));
            }

        }
        return lore;

    }

    @Override
    public Listener getListener() {
        return new Listener() {

            @EventHandler
            public void onPlayerDamage(EntityDamageEvent event) {

                boolean explosionEntity = EntityDamageEvent.DamageCause.ENTITY_EXPLOSION.equals(event.getCause());
                boolean explosionBlock = EntityDamageEvent.DamageCause.BLOCK_EXPLOSION.equals(event.getCause());

                if(!explosionEntity && !explosionBlock) return;

                if(!(event.getEntity() instanceof Player player)) return;

                if(!hasAbility(player)) return;

                event.setDamage(event.getDamage() * 0.5);

            }

        };
    }
}
