package dev.eztxm.coinapi;

import dev.eztxm.coinapi.api.Coins;
import dev.eztxm.coinapi.database.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoinAPI extends JavaPlugin {
    private CoinAPI coinAPI;
    private static MySQL mySQL;
    private final Coins coins = new Coins();
    private String url, user, password, database;
    private int port;

    @Override
    public void onEnable() {
        coinAPI = this;
        mySQL = new MySQL(url, port, user, password, database);
        assert url != null;
        assert user != null;
        assert password != null;
        assert database != null;
        mySQL.update("CREATE TABLE IF NOT EXISTS coinapi_playercoins(uuid VARCHAR(100), coins BIGINT(100))");
    }

    @Override
    public void onDisable() {
        coinAPI = null;
        mySQL = null;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDatabase(String database) {
        this.database = database;
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
