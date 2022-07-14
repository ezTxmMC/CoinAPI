package dev.eztxm.coinapi.api;

import dev.eztxm.coinapi.CoinAPI;
import dev.eztxm.coinapi.database.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Coins {
    private static final MySQL mySQL = CoinAPI.getMySQL();

    public void setCoins(UUID uuid, long amount) {
        String qry = "UPDATE coinapi SET coins=? WHERE uuid=?";
        mySQL.update(qry, amount, uuid.toString());
    }

    public void addCoins(UUID uuid, long amount) {
        long addedAmount = getCoins(uuid) + amount;
        setCoins(uuid, addedAmount);
    }

    public void removeCoins(UUID uuid, long amount) {
        long removedAmount = getCoins(uuid) - amount;
        setCoins(uuid, removedAmount);
    }

    public Integer getCoins(UUID uuid) {
        String qry = "SELECT coins FROM coinapi_playercoins WHERE uuid=?";
        try (ResultSet resultSet = mySQL.query(qry, uuid.toString())) {
            if (resultSet.next()) {
                return resultSet.getInt("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
