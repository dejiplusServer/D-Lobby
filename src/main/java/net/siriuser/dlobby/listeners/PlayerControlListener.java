/**
 * Project : D-Lobby
 * Package : net.siriuser.dlobby.listeners
 * Created : 2014/02/22 - 05:15
 */
package net.siriuser.dlobby.listeners;

import net.siriuser.dlobby.Lobby;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerControlListener implements Listener {
    private Lobby plugin;
    public PlayerControlListener(final Lobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFeedLevelChange (final FoodLevelChangeEvent event) {
        final Entity entity = event.getEntity();
        event.setCancelled(entity instanceof Player);
    }

    @EventHandler
    public void onPlayerDamage(final EntityDamageEvent event) {
        final Entity entity = event.getEntity();
        event.setCancelled(entity instanceof Player);
    }

    @EventHandler
    public void onPlayerVoidFall(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final Location toLoc = event.getTo();
        if (toLoc.getY() <= 0.0D) {
            player.teleport(toLoc.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.teleport(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }
}
