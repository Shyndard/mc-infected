package com.shyndard.minecraft.infected.entity;

import org.bukkit.Location;

public class Map {

    private String id;
    private String title;
    private Location zombieSpawn;
    private Location survivorSpawn;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Location getZombieSpawn() {
        return this.zombieSpawn;
    }

    public void setZombieSpawn(Location zombieSpawn) {
        this.zombieSpawn = zombieSpawn;
    }

    public Location getSurvivorSpawn() {
        return this.survivorSpawn;
    }

    public void setSurvivorSpawn(Location survivorSpawn) {
        this.survivorSpawn = survivorSpawn;
    }
}