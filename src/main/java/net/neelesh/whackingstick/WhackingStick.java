package net.neelesh.whackingstick;

import net.fabricmc.api.ModInitializer;

import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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