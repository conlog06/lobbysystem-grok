package de.crafterteam.lobbysystem.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/** JSON-freundliche Location. */
public final class SLoc {

    public String world;
    public double x, y, z;
    public float yaw, pitch;

    public SLoc() {
    }

    public static SLoc of(Location l) {
        SLoc s = new SLoc();
        s.world = l.getWorld().getName();
        s.x = round(l.getX());
        s.y = round(l.getY());
        s.z = round(l.getZ());
        s.yaw = (float) round(l.getYaw());
        s.pitch = (float) round(l.getPitch());
        return s;
    }

    private static double round(double d) {
        return Math.round(d * 100.0) / 100.0;
    }

    public Location toLocation() {
        World w = world == null ? null : Bukkit.getWorld(world);
        if (w == null) return null;
        return new Location(w, x, y, z, yaw, pitch);
    }
}
