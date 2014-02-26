/**
 * Project : D-Lobby
 * Package : net.siriuser.dlobby.utils
 * Created : 2014/02/25 - 11:17
 */
package jp.dej.dlobby.utils;

import jp.dej.dlobby.Lobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeCordUtil {
    public static void ServerTeleport(final Player player, final String servername, final Lobby plugin) throws IOException {
        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        out.writeUTF("Connect");
        out.writeUTF(servername);

        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }


}
