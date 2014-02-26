/**
 * Project : D-Lobby
 * Package : net.siriuser.dlobby
 * Created : 2014/02/21 - 13:01
 */
package net.siriuser.dlobby;

import org.bukkit.permissions.Permissible;

public enum Perms {
    BYPASS_BREAK("bypass.break"),
    BYPASS_PLACE("bypass.place"),
    BYPASS_PICKUP("bypass.pickup"),
    BYPASS_DROP("bypass.drop"),
    BYPASS_INVENTORY("bypass.inventory"),

    MENU_SERVER("menu.server"),
    ;

    final String HEADER = "dlobby.";
    private String node;

    Perms(final String node) {
        this.node = HEADER + node;
    }

    public boolean has(final Permissible perm) {
        if (perm == null)
            return false;
        return perm.hasPermission(node);
    }
}
