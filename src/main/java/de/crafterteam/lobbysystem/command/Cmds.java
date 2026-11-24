package de.crafterteam.lobbysystem.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

final class Cmds {

    private Cmds() {
    }

    static List<String> filter(List<String> options, String input) {
        String lower = input.toLowerCase();
        return options.stream().filter(o -> o.toLowerCase().startsWith(lower)).collect(Collectors.toList());
    }

    static List<String> players(String input) {
        return filter(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()), input);
    }
}
