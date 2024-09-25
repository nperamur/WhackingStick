package net.neelesh.whackingstick;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.client.indigo.renderer.helper.ColorHelper;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.PlayerHurtEntityCriterion;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.neelesh.whackingstick.config.WhackingStickConfig;
import net.neelesh.whackingstick.item.ModItems;

public class WhackingStickClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

    }
}
