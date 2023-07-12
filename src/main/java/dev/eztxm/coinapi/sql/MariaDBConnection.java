package dev.eztxm.coinapi.sql;

import java.sql.*;

public class MariaDBConnection {
    private final Connection connection;

    public MariaDBConnection(String url, int port, String database, String username, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + url + ":" + port + "/" + database + "?user=" + username + "&password=" + password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet query(String sql, Object... objects) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setArguments(objects, preparedStatement);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String sql, Object... objects) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            setArguments(objects, preparedStatement);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setArguments(Object[] objects, PreparedStatement preparedStatement) throws SQLException {
        for (int i = 0; i < objects.length; i++) {
            Object object = objects[i];
            if (object instanceof String) {
                preparedStatement.setString(i + 1, (String) object);
            } else if (object instanceof Integer) {
                preparedStatement.setInt(i + 1, (Integer) object);
            } else if (object instanceof Date) {
                preparedStatement.setDate(i + 1, (Date) object);
            } else if (object instanceof Timestamp) {
                preparedStatement.setTimestamp(i + 1, (Timestamp) object);
            } else if (object instanceof Boolean) {
                preparedStatement.setBoolean(i + 1, (Boolean) object);
            } else if (object instanceof Float) {
                preparedStatement.setFloat(i + 1, (Float) object);
            } else if (object instanceof Double) {
                preparedStatement.setDouble(i + 1, (Double) object);
            } else if (object instanceof Long) {
                preparedStatement.setLong(i + 1, (Long) object);
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}