/**
 * Project : D-Lobby
 * Package : net.siriuser.dlobby.listeners
 * Created : 2014/02/25 - 12:13
 */
package jp.dej.dlobby.listeners;

import jp.dej.dlobby.Lobby;
import jp.dej.dlobby.Perms;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {
    private Lobby plugin;
    public PlayerListener(final Lobby plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack item = player.getItemInHand();

        if (item.getType().equals(Material.ENCHANTED_BOOK)) {
            if (item.getItemMeta().getDisplayName().equals(Lobby.getServerMenu().getTitle()) &&
                    Perms.MENU_SERVER.has(player)) {
                Lobby.getServerMenu().getMenu().open(player);
            }
        }
    }
}
