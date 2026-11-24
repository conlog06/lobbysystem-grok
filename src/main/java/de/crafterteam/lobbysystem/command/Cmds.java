package de.crafterteam.lobbysystem.command;

import de.crafterteam.lobbysystem.LobbySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/** Basis für Lobby-Commands mit Permission-Check. */
public abstract class Cmds implements CommandExecutor, TabCompleter {

    protected final LobbySystem plugin;

    protected Cmds(LobbySystem plugin) {
        this.plugin = plugin;
    }

    protected boolean hasPerm(CommandSender sender, String suffix) {
        String full = plugin.getConfigManager().get().permissions.getOrDefault("prefix", "lobbysystem")
                + (suffix == null || suffix.isEmpty() ? "" : "." + suffix);
        return sender.hasPermission(full) || sender.isOp();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }
}
