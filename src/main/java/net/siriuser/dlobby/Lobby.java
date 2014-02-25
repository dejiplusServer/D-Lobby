/**
 * Project : D-Lobby
 * Package : net.siriuser.dlobby
 * Created : 2014/02/21 - 12:29
 */
package net.siriuser.dlobby;

import net.siriuser.dlobby.iconmenu.ServerMenu;
import net.siriuser.dlobby.listeners.PlayerControlListener;
import net.siriuser.dlobby.listeners.PlayerEditListener;
import net.siriuser.dlobby.listeners.PlayerListener;
import net.syamn.utils.LogUtil;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Lobby extends JavaPlugin {
    private static Lobby instance;
    private static ServerMenu servermenu;

    @Override
    public void onEnable() {
        LogUtil.init(this);

        servermenu = new ServerMenu(this);

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new PlayerControlListener(this), this);
        pm.registerEvents(new PlayerEditListener(this), this);

        PluginDescriptionFile pdfFile = this.getDescription();
        LogUtil.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        PluginDescriptionFile pdfFile = this.getDescription();
        LogUtil.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!");
    }

    public static ServerMenu getServerMenu() {
        return servermenu;
    }

    public static Lobby getInstance() {
        return instance;
    }
}
