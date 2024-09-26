package net.neelesh.whackingstick.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.neelesh.whackingstick.WhackingStick;
import net.neelesh.whackingstick.custom.WhackingStickItem;

public class ModItems {

    public static final Item WHACKING_STICK = registerItem("whacking_stick", new WhackingStickItem(new Item.Settings().maxCount(1).maxDamage(200).attributeModifiers(WhackingStickItem.createAttributeModifiers(2.5f, 0.5f)).component(DataComponentTypes.TOOL, WhackingStickItem.createToolComponent())));

    public static void registerModItems() {
        WhackingStick.LOGGER.info("Registering Whacking Stick item for " + WhackingStick.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(WHACKING_STICK);
        });

    }


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(WhackingStick.MOD_ID, name), item);
    }



}
