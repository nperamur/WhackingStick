package net.neelesh.whackingstick;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.config.option.ConfigOptionStorage;
import com.terraformersmc.modmenu.gui.ModMenuOptionsScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.client.screen.ButtonList;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.neelesh.whackingstick.config.WhackingStickConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Environment(EnvType.CLIENT)

public class ModMenuImplApi implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return ModMenuOptionsScreen::new;
    }

    public static class ModMenuOptionsScreen extends GameOptionsScreen {
        int hitSoundNum;
        Element hitSoundButton;
        Element enchantGlintButton;
        int enchantGlint;
        public ModMenuOptionsScreen(Screen parent, GameOptions gameOptions, Text title) {
            super(parent, gameOptions, title);
        }

        public ModMenuOptionsScreen(Screen parent) {
            super(parent, MinecraftClient.getInstance().options, Text.literal("Whacking Stick Config"));
        }

        @Override
        protected void addOptions() {
            hitSoundButton = this.addDrawableChild(ButtonWidget.builder(Text.literal("Hit Sound: " + this.loadHitSound()), (button) -> {
                updateHitConfig();
            }).size(240, 20).position(width/2 - 120, 45).build());
            enchantGlintButton = this.addDrawableChild(ButtonWidget.builder(Text.literal("Enchant Glint: " + this.loadEnchantGlint()), (button) -> {
                updateGlintConfig();
            }).size(240, 20).position(width/2 - 120, 90).build());
        }


        private void updateHitConfig() {
            hitSoundNum = WhackingStickConfig.loadHitSound();
            if (hitSoundNum < 4) {
                hitSoundNum += 1;
            } else {
                hitSoundNum = 0;
            }
            WhackingStickConfig.save(hitSoundNum, enchantGlint);
            remove(hitSoundButton);
            remove(enchantGlintButton);
            addOptions();
        }

        private void updateGlintConfig() {
            enchantGlint = WhackingStickConfig.loadEnchantmentGlint();
            if (enchantGlint <= 1) {
                enchantGlint += 1;
            } else {
                enchantGlint = 0;
            }
            WhackingStickConfig.save(hitSoundNum, enchantGlint);
            remove(enchantGlintButton);
            remove(hitSoundButton);
            addOptions();
        }

        private String loadHitSound() {
            hitSoundNum = WhackingStickConfig.loadHitSound();
            return switch (hitSoundNum) {
                case 0 -> "Rocket Launch";
                case 1 -> "Sweeping Sound";
                case 2 -> "XP Orb Sound";
                case 3 -> "Anvil Hit Sound";
                case 4 -> "Regular Hit Sound";
                default -> throw new IllegalStateException("Unexpected value: " + hitSoundNum);
            };
        }

        private String loadEnchantGlint() {
            enchantGlint = WhackingStickConfig.loadEnchantmentGlint();
            return switch (enchantGlint) {
                case 0 -> "Default";
                case 1 -> "Always";
                case 2 -> "Never";
                default -> throw new IllegalStateException("Unexpected value:" + enchantGlint);
            };
        }
    }
}
