package net.neelesh.whackingstick.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.neelesh.whackingstick.WhackingStickCriterion;
import net.neelesh.whackingstick.item.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.neelesh.whackingstick.WhackingStick.*;

public class ModAdvancementsProvider extends FabricAdvancementProvider {
    public ModAdvancementsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }



    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry rootAdvancement = Advancement.Builder.create()
                .display(
                        ModItems.WHACKING_STICK,
                        Text.literal("Whack - A - Mob"),
                        Text.literal("Whack a mob with the Whacking Stick!"),
                        Identifier.of("textures/gui/advancements/backgrounds/adventure.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("whacking", whackingStickCriterion.create(WhackingStickCriterion.Conditions.create().conditions()))
                .build(consumer, MOD_ID + "/root");
    }


}
