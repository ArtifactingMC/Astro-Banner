package xyz.artifacting.astrobanner.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.artifacting.astrobanner.Astro;
import xyz.artifacting.astrobanner.utils.msg;

public class TogglebansCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission("op"))) return false;

        if (Astro.plugin.getConfig().getBoolean("bans-enabled")) {
            Astro.plugin.getConfig().set("bans-enabled", false);
            msg.send(sender, "&aDisabled auto-bans!");
        } else {
            Astro.plugin.getConfig().set("bans-enabled", true);
            msg.send(sender, "&aEnabled auto-bans!");
        }

        Astro.plugin.saveConfig();

        return false;
    }
}
