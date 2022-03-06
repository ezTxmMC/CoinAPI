package net.lalura.coinapi.api;

import net.lalura.coinapi.CoinPlugin;
import net.lalura.coinapi.database.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinAPIImpl implements ICoinAPI {
    private MySQL mySQL;

    @Override
    public Integer getCoins(UUID uuid) {
        String qry = "SELECT coins FROM coinapi WHERE uuid=?";
        try (ResultSet resultSet = mySQL.query(qry, uuid.toString())) {
            if (resultSet.next()) {
                return resultSet.getInt("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addCoins(UUID uuid, int amount) {
        int currentCoins = getCoins(uuid);
        String qry = "UPDATE coinapi SET coins=? WHERE uuid=?";
        mySQL.update(qry, currentCoins + amount, uuid.toString());
    }

    @Override
    public void removeCoins(UUID uuid, int amount) {
        int currentCoins = getCoins(uuid);
        String qry = "UPDATE coinapi SET coins=? WHERE uuid=?";
        if (currentCoins >= amount) {
            mySQL.update(qry, currentCoins - amount, uuid.toString());
        }
    }

    @Override
    public void setCoins(UUID uuid, int amount) {
        String qry = "UPDATE coinapi SET coins=? WHERE uuid=?";
        mySQL.update(qry, amount, uuid.toString());
    }

    public void initPlayer(UUID uuid) {
        mySQL.update("INSERT INTO coinapi (uuid, coins) VALUES (?,?)", uuid.toString(), 0);
    }

    public boolean isUserExists(UUID uuid) {
        String qry = "SELECT count(*) AS count FROM coinapi WHERE uuid=?";
        try (ResultSet resultSet = mySQL.query(qry, uuid.toString())) {
            if (resultSet.next()) {
                return resultSet.getInt("count") != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createTables() {
        this.mySQL = CoinPlugin.getInstance().getMySQL();
        mySQL.update("CREATE TABLE IF NOT EXISTS coinapi (uuid VARCHAR(40), coins INT(40))");
    }
}
