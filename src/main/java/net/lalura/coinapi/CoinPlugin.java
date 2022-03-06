package net.lalura.coinapi;

import net.lalura.coinapi.api.CoinAPI;
import net.lalura.coinapi.api.CoinAPIImpl;
import net.lalura.coinapi.database.MySQL;
import net.lalura.coinapi.listeners.JoinListener;
import net.lalura.coinapi.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoinPlugin extends JavaPlugin {
    private static CoinPlugin instance;
    private MySQL mySQL;
    private CoinAPIImpl coinAPI;
    public String host;
    public int port;
    public String database;
    public String user;
    public String password;

    @Override
    public void onEnable() {
        instance = this;
        FileManager.setupFiles();
        mySQL = MySQL.newBuilder()
                .withUrl(host)
                .withPort(port)
                .withDatabase(database)
                .withUser(user)
                .withPassword(password)
                .create();
        coinAPI = new CoinAPIImpl();
        coinAPI.createTables();
        CoinAPI.setAPI(coinAPI);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public CoinAPIImpl getCoinAPI() {
        return coinAPI;
    }

    public static CoinPlugin getInstance() {
        return instance;
    }
}
