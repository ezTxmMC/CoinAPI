package dev.eztxm.coinapi;

import dev.eztxm.coinapi.api.Coins;
import dev.eztxm.coinapi.database.MySQL;
import dev.eztxm.coinapi.utils.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoinAPI extends JavaPlugin {
    private CoinAPI coinAPI;
    private static MySQL mySQL;
    private final Coins coins = new Coins();
    private final String url = FileManager.cfg.getString("Host");
    private final int port = FileManager.cfg.getInt("Port");
    private final String database = FileManager.cfg.getString("Database");
    private final String user = FileManager.cfg.getString("User");
    private final String password = FileManager.cfg.getString("Password");

    @Override
    public void onEnable() {
        coinAPI = this;
        FileManager.setup();
        mySQL = new MySQL(url, port, database, user, password);
        mySQL.update("CREATE TABLE IF NOT EXISTS coinapi_playercoins(uuid VARCHAR(100), coins BIGINT(100))");
    }

    @Override
    public void onDisable() {
        coinAPI = null;
        mySQL = null;
    }

    public Coins getCoins() {
        return coins;
    }

    public static MySQL getMySQL() {
        return mySQL;
    }

    public CoinAPI get() {
        return coinAPI;
    }
}
