package me.xxgradzix.gradzixcombatsystem.items;

public class ItemIsNotCustomItem extends Exception {
    public ItemIsNotCustomItem() {
        super("This item is not a custom item.");
    }
}
