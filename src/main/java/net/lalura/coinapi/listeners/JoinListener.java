package net.lalura.coinapi.listeners;

import net.lalura.coinapi.CoinPlugin;
import net.lalura.coinapi.api.CoinAPIImpl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        CoinAPIImpl coinsAPI = CoinPlugin.getInstance().getCoinAPI();
        if (!coinsAPI.isUserExists(uuid)) {
            coinsAPI.initPlayer(uuid);
        }
    }
}
