/**
 * Project : D-Lobby
 * Package : net.siriuser.dlobby.listeners
 * Created : 2014/02/22 - 05:15
 */
package jp.dej.dlobby.listeners;

import jp.dej.dlobby.Lobby;
import jp.dej.dlobby.Perms;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
            player.setFallDistance(0);
            player.teleport(toLoc.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        }
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.teleport(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
        resetInventory(player);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        final Player player = (Player) event.getWhoClicked();

        if (Perms.BYPASS_INVENTORY.has(player)) {
            return;
        }
        event.setResult(Event.Result.DENY);
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();
        if (Perms.BYPASS_INVENTORY.has(player)) {
            return;
        }

        resetInventory(player);
    }

    @EventHandler
    public void onItemPickup(final PlayerPickupItemEvent event) {
        final Player player = event.getPlayer();
        event.setCancelled(!Perms.BYPASS_PICKUP.has(player));
    }

    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        if (Perms.BYPASS_DROP.has(player)) {
            return;
        }
        resetInventory(player);
    }

    @EventHandler
    public void onItemSpawn(final ItemSpawnEvent event) {
        event.getEntity().remove();
    }

    public void resetInventory(final Player player) {
        Inventory inv = Bukkit.createInventory(player, InventoryType.PLAYER);
        inv.setItem(8, Lobby.getServerMenu().getItem());

        player.getInventory().setContents(inv.getContents());
    }
}
