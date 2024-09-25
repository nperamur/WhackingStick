package net.neelesh.whackingstick;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorResolverRegistry;
import net.fabricmc.fabric.api.item.v1.EnchantmentEvents;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.PlayerHurtEntityCriterion;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.ColorResolver;
import net.neelesh.whackingstick.config.WhackingStickConfig;
import net.neelesh.whackingstick.datagen.ModRecipeProvider;
import net.neelesh.whackingstick.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhackingStick implements ModInitializer {
	public static final String MOD_ID = "whackingstick";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static WhackingStickCriterion whackingStickCriterion = register("whacking", new WhackingStickCriterion());

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		

	}


	public static <T extends Criterion<?>> T register(String id, T criterion) {
		return Registry.register(Registries.CRITERION, id, criterion);

	}
}