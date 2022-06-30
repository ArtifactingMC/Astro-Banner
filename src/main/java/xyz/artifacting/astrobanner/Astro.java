package xyz.artifacting.astrobanner;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.artifacting.astrobanner.commands.AcbanCommand;
import xyz.artifacting.astrobanner.commands.ManageExemptCommand;
import xyz.artifacting.astrobanner.commands.TogglebansCommand;

public final class Astro extends JavaPlugin {

    public static Astro plugin;

    @Override
    public void onEnable() {
        plugin = this;
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Bukkit.getLogger().info("    _        _             ____");
        Bukkit.getLogger().info("   / \\   ___| |_ _ __ ___ | __ )  __ _ _ __  _ __   ___ _ __ ");
        Bukkit.getLogger().info("  / _ \\ / __| __| '__/ _ \\|  _ \\ / _` | '_ \\| '_ \\ / _ \\ '__|");
        Bukkit.getLogger().info(" / ___ \\\\__ \\ |_| | | (_) | |_) | (_| | | | | | | |  __/ |   ");
        Bukkit.getLogger().info("/_/   \\_\\___/\\__|_|  \\___/|____/ \\__,_|_| |_|_| |_|\\___|_|");
        Bukkit.getLogger().info(" ");
        getCommand("acban").setExecutor(new AcbanCommand());
        getCommand("togglebans").setExecutor(new TogglebansCommand());
        getCommand("acexempt").setExecutor(new ManageExemptCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("    _        _             ____");
        Bukkit.getLogger().info("   / \\   ___| |_ _ __ ___ | __ )  __ _ _ __  _ __   ___ _ __ ");
        Bukkit.getLogger().info("  / _ \\ / __| __| '__/ _ \\|  _ \\ / _` | '_ \\| '_ \\ / _ \\ '__|");
        Bukkit.getLogger().info(" / ___ \\\\__ \\ |_| | | (_) | |_) | (_| | | | | | | |  __/ |   ");
        Bukkit.getLogger().info("/_/   \\_\\___/\\__|_|  \\___/|____/ \\__,_|_| |_|_| |_|\\___|_|");
        Bukkit.getLogger().info(" ");
    }
}
