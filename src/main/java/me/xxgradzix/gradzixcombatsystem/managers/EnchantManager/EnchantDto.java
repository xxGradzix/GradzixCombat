package me.xxgradzix.gradzixcombatsystem.managers.EnchantManager;

import java.io.Serializable;
import java.util.HashMap;

public class EnchantDto implements Serializable {

    private HashMap<EnchantManager.Enchant, Integer> enchantMap;

    public EnchantDto() {
        this.enchantMap = new HashMap<>();
    }

    public void setEnchantMap(HashMap<EnchantManager.Enchant, Integer> enchantMap) {
        this.enchantMap = enchantMap;
    }
    public HashMap<EnchantManager.Enchant, Integer> getEnchantMap() {
        return enchantMap;
    }

}
