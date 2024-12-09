package me.xxgradzix.gradzixcombatsystem.events.magicUse;

import me.xxgradzix.gradzixcombatsystem.managers.magicEffects.MagicEffectManager;
import org.bukkit.Location;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class MagicEffectApply extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;

    private MagicEffectManager.MagicEffectType effectType = null;
    private MagicEffectManager.MagicUseVariant useVariant = null;
    private boolean superCharge = false;
    private int level = 1;
    private LivingEntity target = null;

    public MagicEffectApply(@NotNull Player player, MagicEffectManager.MagicEffectType effectType, MagicEffectManager.MagicUseVariant useVariant, boolean superCharge, int level) {
        super(player);
        this.effectType = effectType;
        this.useVariant = useVariant;
        this.superCharge = superCharge;
        this.level = level;
        this.target = null;

    }


    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {

    }
    public static HandlerList getHandlerList(){
        return handlers;
    }

    /**
     * Gets a list of handlers handling this event.
     *
     * @return A list of handlers handling this event.
     */
    @Override
    public final HandlerList getHandlers(){
        return handlers;
    }
    public boolean isCanceled() {
        return cancel;
    }

    public void setCanceled(boolean cancel) {
        this.cancel = cancel;
    }

    public MagicEffectManager.MagicUseVariant getUseVariant() {
        return useVariant;
    }

    public void setUseVariant(MagicEffectManager.MagicUseVariant useVariant) {
        this.useVariant = useVariant;
    }

    public MagicEffectManager.MagicEffectType getEffectType() {
        return effectType;
    }

    public void setEffectType(MagicEffectManager.MagicEffectType effectType) {
        this.effectType = effectType;
    }

    public boolean isSuperCharge() {
        return superCharge;
    }

    public void setSuperCharge(boolean superCharge) {
        this.superCharge = superCharge;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }
}
