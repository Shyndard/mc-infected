package com.shyndard.minecraft.infected;

import com.shyndard.minecraft.infected.event.WorldEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {

	private static MainPlugin instance;

	public static MainPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;

		// Init config file
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

		// Init services
		// Nothing yet

		// Register event
		getServer().getPluginManager().registerEvents(new WorldEvent(), this);

		// Register channel
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord"); 
		
		// Register command
		// Nothing yet
	}

	@Override
	public void onDisable() {
	}
}