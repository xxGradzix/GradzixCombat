package me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import me.xxgradzix.gradzixcombatsystem.items.ItemManager;
import me.xxgradzix.gradzixcombatsystem.managers.attributesMainManager.abilities.instances.CombatAbility;
import me.xxgradzix.gradzixcombatsystem.utils.ColorFixer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AbilitiesGuiManager {


//    private final HashMap<CombatAttribute, Character> ATTRIBUTE_GUIS = new HashMap<>();
//    {
//        ATTRIBUTE_GUIS.put(CombatAttribute.DEXTERITY, '≑');
//    }

    private Gui gui;
    private Player player;

    public AbilitiesGuiManager(Player player, CombatAttribute attribute) {

        this.player = player;
        this.gui = new Gui(6, ColorFixer.addColors("&f七七七七七七七七≑"));

        gui.disableAllInteractions();

        refreshGuiItems(attribute, 0);

        gui.open(player);
    }

    private void resetGui() {
        GuiItem filler = new GuiItem(ItemManager.invisibleFiller);

        for (int i = 0; i < gui.getRows()*9; i++) {
            gui.updateItem(i, filler);
        }
//        gui.getFiller().fillBetweenPoints(1, 1, 6, 9, filler);

//        gui.update();
    }

    private void refreshGuiItems(CombatAttribute attribute, int offset) {

        int maxOffSet = 0;

        resetGui();
        Set<CombatAbility> abilities = AbilitiesPointsManager.getAbilities(attribute);

        for (CombatAbility ability : abilities) {

            if((ability.getRow() - 4 + 1) > maxOffSet) maxOffSet = ability.getRow() - 4 + 1;


            int abilityRow = ability.getRow() - offset;
            int abilityColumn = ability.getColumn();

            ItemStack itemStack = ability.getDisplayItem(player);

            GuiItem guiItem = new GuiItem(itemStack);

            for (CombatAbility requiredAbility : ability.getRequiredAbilities()) {

                for (SlotData rowRelativeTo : getRowRelativeTo(ability, requiredAbility)) {

                    int rowRelativeToRow = rowRelativeTo.row - offset;
                    int rowRelativeToColumn = rowRelativeTo.column;

                    if(rowRelativeToRow < 0 || rowRelativeToRow > 4 || rowRelativeToColumn < 0 || rowRelativeToColumn > 8) continue;

                    ItemStack curveItem = new ItemStack(Material.GLASS_PANE);
                    ItemMeta curveMeta = curveItem.getItemMeta();
                    curveMeta.setCustomModelData(rowRelativeTo.curveType.getActiveCustomModelData());

                    if(ability.hasAbility(player) && requiredAbility.hasAbility(player)) {
                        curveMeta.setCustomModelData(rowRelativeTo.curveType.getActiveCustomModelData());
                    } else {
                        curveMeta.setCustomModelData(rowRelativeTo.curveType.getInactiveCustomModelData());
                    }

                    curveMeta.setHideTooltip(true);
                    curveItem.setItemMeta(curveMeta);
                    gui.updateItem((5 - rowRelativeToRow), rowRelativeToColumn + 1, new GuiItem(curveItem));
                }

//                    SlotData rowRelativeTo = getRowRelativeTo(ability, requiredAbility);
//                    ItemStack curveItem = new ItemStack(Material.GLASS_PANE);
//                    ItemMeta curveMeta = curveItem.getItemMeta();
//                    curveMeta.setCustomModelData(rowRelativeTo.curveType.getActiveCustomModelData());
//
//                    if(ability.hasAbility(player) && requiredAbility.hasAbility(player)) {
//                        curveMeta.setCustomModelData(rowRelativeTo.curveType.getActiveCustomModelData());
//                    } else {
//                        curveMeta.setCustomModelData(rowRelativeTo.curveType.getInactiveCustomModelData());
//                    }
//
//                    curveMeta.setHideTooltip(true);
//                    curveItem.setItemMeta(curveMeta);
//                    gui.updateItem((5 - rowRelativeTo.row), rowRelativeTo.column + 1, new GuiItem(curveItem));

//                }

            }

            if(abilityRow < 0 || abilityRow > 4 || abilityColumn < 0 || abilityColumn > 8) continue;


            guiItem.setAction(event -> {
                int level = ability.getAbilityLevel(player);

                if(event.isLeftClick()) {

                    if(level == ability.getMaxAbilityLevel()) return;

                    if(ability.getRequiredAttributeLevel() > AttributePointsManager.getAttributeLevel(player, attribute)) {
                        player.sendMessage(ColorFixer.addColors("&cɴɪᴇ ᴍᴀꜱᴢ ᴡʏᴍᴀɢᴀɴᴇɢᴏ ᴘᴏᴢɪᴏᴍᴜ ᴀᴛʀʏʙᴜᴛᴜ"));
                        return;
                    }
                    for (CombatAbility requiredAbility : ability.getRequiredAbilities()) {
                        if(!requiredAbility.hasAbility(player)) {
                            player.sendMessage(ColorFixer.addColors("&cɴɪᴇ ᴍᴀꜱᴢ ᴋᴜᴘɪᴏɴʏᴄʜ ᴡꜱᴢʏꜱᴛᴋɪᴄʜ ᴡʏᴍᴀɢᴀɴʏᴄʜ ᴜᴍɪᴇᴊęᴛɴᴏśᴄɪ"));
                            return;
                        }
                    }

                    ability.applyAbility(player, level + 1);

                }
                refreshGuiItems(attribute, offset);
            });

            gui.updateItem((5 - abilityRow), (abilityColumn + 1), guiItem);

        }

        GuiItem arrowDownItem = new GuiItem(ItemManager.getSmallLeftArrowItem(true, 1));
        arrowDownItem.setAction(event -> {
            int newOffset = offset - 1;
            if(newOffset < 0) return;
            refreshGuiItems(attribute, newOffset);
        });



        GuiItem arrowUpItem = new GuiItem(ItemManager.getSmallLeftArrowItem(false, 1));
        int finalMaxOffSet = maxOffSet;
        arrowUpItem.setAction(event -> {
            int newOffset = offset + 1;
            if(newOffset > finalMaxOffSet) return;
            refreshGuiItems(attribute, newOffset);
        });

        gui.updateItem(6, 7, arrowDownItem);
        gui.updateItem(6, 8, arrowUpItem);


    }

    private enum CurveType {
        UP(1020882, 1020992),
        DOWN(1020882, 1020992),
        LEFT(1020881, 1020991),
        RIGHT(1020881, 1020991),
        LEFT_UP(1020885, 1020995),
        RIGHT_UP(1020883, 1020993),
        LEFT_DOWN(1020886, 1020996),
        RIGHT_DOWN(1020884, 1020994);

        private final int activeCustomModelData;
        private final int inactiveCustomModelData;

        CurveType(int activeCustomModelData, int inactiveCustomModelData) {
            this.activeCustomModelData = activeCustomModelData;
            this.inactiveCustomModelData = inactiveCustomModelData;
        }

        public int getActiveCustomModelData() {
            return activeCustomModelData;
        }
        public int getInactiveCustomModelData() {
            return inactiveCustomModelData;
        }
    }

    record SlotData(int row, int column, CurveType curveType) {}


    private Set<SlotData> getRowRelativeTo(CombatAbility abilityFrom, CombatAbility abilityTo) {

        Set<SlotData> slotDataSet = new HashSet<>();

        if(abilityFrom.getRow() > abilityTo.getRow()) {

            if(abilityFrom.getColumn() > abilityTo.getColumn()) {

                if(abilityFrom.getColumn() - abilityTo.getColumn() == 2 && abilityFrom.getRow() - abilityTo.getRow() == 1) {
                    slotDataSet.add(new SlotData(abilityFrom.getRow() - 1, abilityFrom.getColumn() - 1, CurveType.RIGHT_UP));
                }
                slotDataSet.add(new SlotData(abilityFrom.getRow(), abilityFrom.getColumn() - 1, CurveType.LEFT_DOWN));

            } else if(abilityFrom.getColumn() < abilityTo.getColumn()) {

                if(Math.abs(abilityFrom.getColumn() - abilityTo.getColumn()) == 2 && Math.abs(abilityFrom.getRow() - abilityTo.getRow()) == 1) {
                    slotDataSet.add(new SlotData(abilityFrom.getRow() - 1, abilityFrom.getColumn() + 1, CurveType.LEFT_UP));
                }
                slotDataSet.add(new SlotData(abilityFrom.getRow(), abilityFrom.getColumn() + 1, CurveType.RIGHT_DOWN));
            } else {
                slotDataSet.add(new SlotData(abilityFrom.getRow() - 1, abilityFrom.getColumn(), CurveType.DOWN));
            }


        } else if(abilityFrom.getRow() < abilityTo.getRow()) {
            if(abilityFrom.getColumn() > abilityTo.getColumn()) {
                slotDataSet.add(new SlotData(abilityFrom.getRow() + 1, abilityFrom.getColumn(), CurveType.RIGHT_DOWN));
            } else if(abilityFrom.getColumn() < abilityTo.getColumn()) {
                slotDataSet.add( new SlotData(abilityFrom.getRow() + 1, abilityFrom.getColumn(), CurveType.LEFT_DOWN));
            } else {
                slotDataSet.add( new SlotData(abilityFrom.getRow() + 1, abilityFrom.getColumn(), CurveType.UP));
            }
        } else {
            if(abilityFrom.getColumn() > abilityTo.getColumn()) {
                slotDataSet.add( new SlotData(abilityFrom.getRow(), abilityFrom.getColumn() - 1, CurveType.LEFT));
            } else {
                slotDataSet.add( new SlotData(abilityFrom.getRow(), abilityFrom.getColumn() + 1, CurveType.RIGHT));
            }
        }
        return slotDataSet;
    }


//    private Set<SlotData> getRowRelativeTo(CombatAbility abilityFrom, CombatAbility abilityTo) {
//
//        Set<SlotData> slotData = new HashSet<>();
//
//        if(abilityFrom.getRow() > abilityTo.getRow()) {
//
//            if(abilityFrom.getColumn() > abilityTo.getColumn()) {
//                return new SlotData(abilityFrom.getRow(), abilityFrom.getColumn() - 1, CurveType.LEFT_DOWN);
//            } else if(abilityFrom.getColumn() < abilityTo.getColumn()) {
//                return new SlotData(abilityFrom.getRow(), abilityFrom.getColumn() + 1, CurveType.RIGHT_DOWN);
//            } else {
//                return new SlotData(abilityFrom.getRow() - 1, abilityFrom.getColumn(), CurveType.DOWN);
//            }
//
//        } else if(abilityFrom.getRow() < abilityTo.getRow()) {
//            if(abilityFrom.getColumn() > abilityTo.getColumn()) {
//                return new SlotData(abilityFrom.getRow() + 1, abilityFrom.getColumn(), CurveType.RIGHT_DOWN);
//            } else if(abilityFrom.getColumn() < abilityTo.getColumn()) {
//                return new SlotData(abilityFrom.getRow() + 1, abilityFrom.getColumn(), CurveType.LEFT_DOWN);
//            } else {
//                return new SlotData(abilityFrom.getRow() + 1, abilityFrom.getColumn(), CurveType.UP);
//            }
//        } else {
//            if(abilityFrom.getColumn() > abilityTo.getColumn()) {
//                return new SlotData(abilityFrom.getRow(), abilityFrom.getColumn() - 1, CurveType.LEFT);
//            } else {
//                return new SlotData(abilityFrom.getRow(), abilityFrom.getColumn() + 1, CurveType.RIGHT);
//            }
//        }
//
//    }


}
