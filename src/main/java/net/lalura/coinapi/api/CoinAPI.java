package net.lalura.coinapi.api;

public class CoinAPI {
    private static ICoinAPI api;

    public static ICoinAPI getAPI() {
        return api;
    }

    public static void setAPI(ICoinAPI api) {
        CoinAPI.api = api;
    }
}
