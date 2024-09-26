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
                        ModItems.WHACKING_STICK, // The display icon
                        Text.literal("Whack - A - Mob"), // The title
                        Text.literal("Whack a mob with the Whacking Stick!"), // The description
                        Identifier.of("textures/gui/advancements/backgrounds/adventure.png"), // Background image used
                        AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
                        true, // Show toast top right
                        true, // Announce to chat
                        false // Hidden in the advancement tab
                )
                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
                .criterion("whacking", whackingStickCriterion.create(WhackingStickCriterion.Conditions.create().conditions()))
                .build(consumer, MOD_ID + "/root");
    }


}
