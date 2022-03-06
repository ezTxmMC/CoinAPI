package net.lalura.coinapi.utils;

import net.lalura.coinapi.CoinPlugin;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    public static File folder = new File("plugins/CoinAPI");
    public static File mySQL = new File("plugins/CoinAPI/mysql.yml");
    public static YamlConfiguration mySQLYaml = YamlConfiguration.loadConfiguration(mySQL);

    public static void save() {
        try {
            mySQLYaml.save(mySQL);;
        } catch (IOException ignored) {};
        CoinPlugin.getInstance().host = mySQLYaml.getString("Host");
        CoinPlugin.getInstance().port = mySQLYaml.getInt("Port");
        CoinPlugin.getInstance().database = mySQLYaml.getString("Database");
        CoinPlugin.getInstance().user = mySQLYaml.getString("User");
        CoinPlugin.getInstance().password = mySQLYaml.getString("Password");;
    }

    public static void setupFiles() {
        if (!folder.exists())
            folder.mkdir();
        if (!mySQL.exists()) {
            try {
                mySQL.createNewFile();
            } catch (IOException ignored) {}
        };
        mySQLYaml.addDefault("Host", "< HOST >");
        mySQLYaml.addDefault("Port", 3306);
        mySQLYaml.addDefault("Database", "< DATABASE >");
        mySQLYaml.addDefault("User", "< USER >");
        mySQLYaml.addDefault("Password", "< PASSWORD >");
        mySQLYaml.options().copyDefaults(true);;
        save();;
    }
}
