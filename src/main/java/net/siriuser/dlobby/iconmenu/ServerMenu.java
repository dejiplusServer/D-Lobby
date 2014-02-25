/**
 * Project : D-Lobby
 * Package : net.siriuser.dlobby.iconmenu
 * Created : 2014/02/25 - 11:01
 */
package net.siriuser.dlobby.iconmenu;

import net.siriuser.dlobby.Lobby;
import net.siriuser.dlobby.utils.BungeeCordUtil;
import net.siriuser.dlobby.utils.IconMenu;
import net.syamn.utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;

public class ServerMenu {
    private Lobby plugin;

    private String title;
    private ItemStack item;
    private IconMenu menu;

    public ServerMenu(final Lobby plugin) {
        this.plugin = plugin;
        this.title = Util.coloring("&6サーバー選択 / ServerSelect");


        ItemStack is = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(title);
        is.setItemMeta(im);
        this.item = is;

        menu = new IconMenu(title, 27, new IconMenu.OptionClickEventHandler() {
            @Override
            public void onOptionClick(IconMenu.OptionClickEvent event) {
                final Player player = event.getPlayer();
                if (event.getName().equals(Util.coloring("&bSkyBlock"))) {
                    try {
                        BungeeCordUtil.ServerTeleport(player, "skyblock", plugin);
                    } catch (IOException e) {
                        player.sendMessage("サーバーに接続出来ませんでした。");
                        e.printStackTrace();
                    }
                }

                if (event.getName().equals(Util.coloring("&6閉じる / Close"))) {
                    event.setWillClose(true);
                }
            }
        }, plugin)
                .setOption(0, new ItemStack(Material.DIAMOND , 1), Util.coloring("&bSkyBlock"), "SkyBlockサーバーに接続します。", "Connect SkyBlockServer.")
                .setOption(26, new ItemStack(Material.WOOD_DOOR, 1), Util.coloring("&6閉じる / Close"), "メニューを閉じます。", "Close this menu.")
        ;
    }

    public String getTitle() {
        return title;
    }

    public ItemStack getItem() {
        return item;
    }

    public IconMenu getMenu() {
        return menu;
    }
}
