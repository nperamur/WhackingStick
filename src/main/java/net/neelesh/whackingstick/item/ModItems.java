package net.neelesh.whackingstick.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.neelesh.whackingstick.WhackingStick;
import net.neelesh.whackingstick.custom.WhackingStickItem;

public class ModItems {
    private static final Identifier ID = Identifier.of(WhackingStick.MOD_ID, "whacking_stick");
    private static final RegistryKey<Item> KEY = RegistryKey.of(RegistryKeys.ITEM, ID);
    private static final Item.Settings SETTINGS = new Item.Settings().registryKey(KEY).useItemPrefixedTranslationKey().enchantable(ToolMaterial.IRON.enchantmentValue()).maxCount(1).maxDamage(200).attributeModifiers(WhackingStickItem.createAttributeModifiers(2.5f, 0.5f)).component(DataComponentTypes.TOOL, WhackingStickItem.createToolComponent());
    public static final Item WHACKING_STICK = new WhackingStickItem(SETTINGS);

    public static void registerModItems() {
        Registry.register(Registries.ITEM, KEY, WHACKING_STICK);
        WhackingStick.LOGGER.info("Registering Whacking Stick item for " + WhackingStick.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(WHACKING_STICK);
        });

    }
}
