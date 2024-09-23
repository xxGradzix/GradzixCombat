package me.xxgradzix.gradzixcombatsystem.items;

import me.xxgradzix.gradzixcombatsystem.GradzixCombatSystem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;
import java.util.Optional;

public interface CustomItem {

    NamespacedKey ITEM_CUSTOM_ID_KEY = new NamespacedKey("gradzix_namespace", "gradzix_custom_item_id");

    default void defaultSetItemCustomId(ItemMeta meta) {
        meta.getPersistentDataContainer().set(getItemCustomIdKey(), PersistentDataType.STRING, getCustomId());
    }

    static NamespacedKey getItemCustomIdKey() {
        return ITEM_CUSTOM_ID_KEY;
    }

    String getCustomId();

    static Optional<String> getCustomId(ItemMeta meta) {
        return Optional.ofNullable(meta.getPersistentDataContainer().get(getItemCustomIdKey(), PersistentDataType.STRING));
    }

    ItemStack getDefaultItemStack(Object... optionalArgs);

}
