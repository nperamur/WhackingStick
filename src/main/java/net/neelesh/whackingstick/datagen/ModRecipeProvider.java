package net.neelesh.whackingstick.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.neelesh.whackingstick.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                ShapedRecipeJsonBuilder.create(registryLookup.getOrThrow(ModItems.WHACKING_STICK.getRegistryEntry().registryKey().getRegistryRef()), RecipeCategory.COMBAT, ModItems.WHACKING_STICK, 1).pattern("#").pattern("#").input('#', Items.IRON_INGOT).criterion(hasItem(Items.IRON_INGOT), getRecipeGenerator(registryLookup, exporter).conditionsFromItem(Items.IRON_INGOT)).offerTo(exporter, String.valueOf(Identifier.of(getRecipeName(ModItems.WHACKING_STICK))));
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
