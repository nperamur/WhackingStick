package net.neelesh.whackingstick;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.client.ModelProvider;
import net.neelesh.whackingstick.datagen.ModAdvancementsProvider;
import net.neelesh.whackingstick.datagen.ModRecipeProvider;

public class WhackingStickDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModAdvancementsProvider::new);
	}
}
