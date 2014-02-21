/**
 * Project : D-Lobby
 * Package : net.siriuser.dlobby.listeners
 * Created : 2014/02/21 - 13:48
 */
package net.siriuser.dlobby.listeners;

import net.siriuser.dlobby.Lobby;
import net.siriuser.dlobby.Perms;
import net.syamn.utils.LogUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerEditListener implements Listener {
    private Lobby plugin;
    public PlayerEditListener(final Lobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBlockBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        event.setCancelled(!Perms.BYPASS_BREAK.has(player));
    }

    @EventHandler
    public void onPlayerBlockPlace(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        event.setCancelled(!Perms.BYPASS_PLACE.has(player));
    }

    @EventHandler
    public void onHangingBreakByEntity(final HangingBreakByEntityEvent event) {
        final Entity entity = event.getRemover();
        if (!(entity instanceof Player)) return;
        final Player player = (Player)entity;

        switch (event.getCause()) {
            case ENTITY:
                break;
            default:
                return;
        }

        event.setCancelled(!Perms.BYPASS_BREAK.has(player));
    }

    @EventHandler
    public void onHangingPlace(final HangingPlaceEvent event) {
        final Player player = event.getPlayer();
        event.setCancelled(!Perms.BYPASS_PLACE.has(player));
    }

    @EventHandler
    public void onPlayerInteractEntity(final PlayerInteractEntityEvent event) {
        final Player player = event.getPlayer();
        if (event.getRightClicked() instanceof ItemFrame) {
            event.setCancelled(!Perms.BYPASS_PLACE.has(player));
        }
    }

    //TODO: ItemFream内のアイテムを取り除ける問題を対策
}
