package me.xxgradzix.gradzixcombatsystem.events.critEvent;

import me.xxgradzix.gradzixcombatsystem.events.armorEvent.ArmorEquipEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class CriticalHitEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;

    private double criticalChance = 0.01;
    private boolean overrideIsCritical = false;

    public CriticalHitEvent(@NotNull Player player) {
        super(player);
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

    public double getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(double criticalChance) {
        this.criticalChance = criticalChance;
    }

    public boolean isOverrideIsCritical() {
        return overrideIsCritical;
    }

    public void setOverrideIsCritical(boolean overrideIsCritical) {
        this.overrideIsCritical = overrideIsCritical;
    }
}
