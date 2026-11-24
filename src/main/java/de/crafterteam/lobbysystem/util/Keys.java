package de.crafterteam.lobbysystem.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public final class Keys {

    public static NamespacedKey ITEM;
    public static NamespacedKey NPC;
    public static NamespacedKey GADGET;

    private Keys() {
    }

    public static void init(Plugin plugin) {
        ITEM = new NamespacedKey(plugin, "item");
        NPC = new NamespacedKey(plugin, "npc");
        GADGET = new NamespacedKey(plugin, "gadget");
    }

    public static String itemTag(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return null;
        return item.getItemMeta().getPersistentDataContainer().get(ITEM, PersistentDataType.STRING);
    }
}
