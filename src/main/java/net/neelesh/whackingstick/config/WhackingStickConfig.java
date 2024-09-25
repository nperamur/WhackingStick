package net.neelesh.whackingstick.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.minecraft.client.ObjectMapper;
import net.fabricmc.loader.api.FabricLoader;
import net.neelesh.whackingstick.WhackingStick;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.terraformersmc.modmenu.config.ModMenuConfigManager.save;

public class WhackingStickConfig {
    public static void save(int newHitSound, int enchantGlint) {
        JsonObject jsonObject =  new JsonObject();
        jsonObject.addProperty("hit_sound", newHitSound);
        jsonObject.addProperty("enchantment_glint", enchantGlint);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try  {
            Files.write(FabricLoader.getInstance().getConfigDir().resolve("whacking_stick_config.json"), gson.toJson(jsonObject).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadHitSound() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("whacking_stick_config.json");
        if (!Files.exists(path)) {
            save(0, 0);
        }
        JsonObject jsonObject;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            jsonObject = new JsonParser().parse(bufferedReader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.get("hit_sound").getAsInt();
    }


    public static int loadEnchantmentGlint() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("whacking_stick_config.json");
        if (!Files.exists(path)) {
            save(0, 0);
        }
        JsonObject jsonObject;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(path);
            jsonObject = new JsonParser().parse(bufferedReader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.get("enchantment_glint").getAsInt();
    }
}
