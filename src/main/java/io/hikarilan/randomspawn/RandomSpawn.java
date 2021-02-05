package io.hikarilan.randomspawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class RandomSpawn extends JavaPlugin implements Listener {

    int randomTeleportRadius;
    String successMessage;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        randomTeleportRadius = getConfig().getInt("RandomTeleportRadius");
        successMessage = getConfig().getString("Message.Success");
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (!(e.getPlayer().getFirstPlayed() == 0L)) return;
        randomTeleport(e.getPlayer());
        e.getPlayer().sendMessage(successMessage);
    }


    private void randomTeleport(Player player) {
        player.teleport(getRandomTeleportLocation(player.getWorld().getSpawnLocation(), randomTeleportRadius));
    }

    private Location getRandomTeleportLocation(Location loc, int radius) {
        Random random = new Random();
        return loc.clone().add(random.nextInt(radius), random.nextInt(radius), random.nextInt(radius));
    }
}
