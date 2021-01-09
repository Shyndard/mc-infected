package com.shyndard.minecraft.infected.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.shyndard.minecraft.infected.MainPlugin;
import com.shyndard.minecraft.infected.entity.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class MapService {

    private static MapService instance;
    private List<Map> maps;

    public static MapService getInstance() {
        if (instance == null) {
            instance = new MapService();
        }
        return instance;
    }

    public List<Map> getMaps() {
        return maps;
    }

    public void loadMaps() {
        FileConfiguration config = MainPlugin.getInstance().getConfig();
        Set<String> path = config.getConfigurationSection("").getKeys(false);
        maps = path.stream().map(id -> load(id)).collect(Collectors.toList());
    }
    
    public Map load(String id) {
        Map map = new Map();
        map.setTitle(getString(id, "title"));
        map.setSurvivorSpawn(loadLocation(id, "survivor"));
        map.setSurvivorSpawn(loadLocation(id, "zombie"));
        return map;
    }
    
    public void save(Map map) {
        if(map.getId() == null) {
            map.setId(UUID.randomUUID().toString());
        }
        saveString(map.getId(), "title", map.getTitle());
        saveLocation(map.getId(), "survivor", map.getSurvivorSpawn());
        saveLocation(map.getId(), "zombie", map.getZombieSpawn());
    }

    private String getString(String id, String name) {
        return MainPlugin.getInstance().getConfig().getString("map." + id + "." + name);
    }

    private void saveString(String id, String name, String value) {
        MainPlugin.getInstance().getConfig().set("map." + id + "." + name, value);
        MainPlugin.getInstance().saveConfig();
    }

    private Location loadLocation(String id, String name) {
        FileConfiguration config = MainPlugin.getInstance().getConfig();
        World world = Bukkit.getWorld(config.getString("map." + id + ".loc." + name + ".world"));
        double x = config.getDouble("map." + id + ".loc." + name + ".x");
        double y = config.getDouble("map." + id + ".loc." + name + ".y");
        double z = config.getDouble("map." + id + ".loc." + name + ".z");
        float yaw = (float) config.getDouble("map." + id + ".loc." + name + ".yaw");
        float pitch = (float) config.getDouble("map." + id + ".loc." + name + ".pitch");
        return new Location(world, x, y, z, yaw, pitch);
    }

    private void saveLocation(String id, String name, Location location) {
        FileConfiguration config = MainPlugin.getInstance().getConfig();
        config.set("map." + id + ".loc." + name + ".world", location.getWorld().getName());
        config.set("map." + id + ".loc." + name + ".x", location.getX());
        config.set("map." + id + ".loc." + name + ".y", location.getY());
        config.set("map." + id + ".loc." + name + ".z", location.getZ());
        config.set("map." + id + ".loc." + name + ".yaw", location.getYaw());
        config.set("map." + id + ".loc." + name + ".pitch", location.getPitch());
        MainPlugin.getInstance().saveConfig();
    }
}
