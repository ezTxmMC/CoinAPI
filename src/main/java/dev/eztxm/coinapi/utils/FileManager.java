package dev.eztxm.coinapi.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static File folder = new File("plugins/CoinAPI");
    private static File config = new File("plugin/CoinAPI/config.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(config);

    public static void save() {
        try {
            cfg.save(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setup() {
        if (!folder.exists()) folder.mkdir();
        if (!config.exists()) {
            try {
                config.createNewFile();
            } catch (IOException ignored) {}
        }
        cfg.addDefault("Host", "< HOST >");
        cfg.addDefault("Port", 3306);
        cfg.addDefault("Database", "< DATABASE NAME >");
        cfg.addDefault("User", "< USER >");
        cfg.addDefault("Password", "< PASSWORD >");
        cfg.options().copyDefaults(true);
    }
}
