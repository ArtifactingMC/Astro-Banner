package xyz.artifacting.astrobanner.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.artifacting.astrobanner.Astro;
import xyz.artifacting.astrobanner.utils.msg;

import java.util.List;

public class ManageExemptCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender.hasPermission(Astro.plugin.getConfig().getString("exempted-manage-permission")))) return false;

        Player player;
        if (args.length == 0) {
            msg.send(sender, "&cSyntax Error: /acexempt <add/remove> <player>");
            return false;
        } else {
            if (args[0].equals("add")) {
                try {
                    player = Bukkit.getPlayer(args[1]);
                } catch(Exception e) {
                    msg.send(sender, "&cYou did not provide a player!");
                    return false;
                }

                if (Astro.plugin.getConfig().getStringList("exempted-players").contains(player.getName())) {
                    msg.send(sender, "&cThis player is already exempt!");
                    return false;
                } else {
                    add(player.getName());
                    msg.send(sender, "&aSuccessfully added the player to the exempt list!");
                }
            } else if (args[0].equals("remove")) {
                try {
                    player = Bukkit.getPlayer(args[1]);
                } catch(Exception e) {
                    msg.send(sender, "&cYou did not provide a player!");
                    return false;
                }

                if (Astro.plugin.getConfig().getStringList("exempted-players").contains(player.getName())) {
                    remove(player.getName());
                    msg.send(sender, "&aSuccessfully removed the player from the exempt list!");
                } else {
                    msg.send(sender, "&cThis player is not exempt!");
                    return false;
                }
            }
        }

        Astro.plugin.saveConfig();


        return false;
    }

    public List<String> filter(){
        return Astro.plugin.getConfig().getStringList("exempted-players");
    }

    public void remove(String s){
        List<String> f = filter();
        f.remove(s);
        Astro.plugin.getConfig().set("exempted-players", f);
        Astro.plugin.saveConfig();
    }

    public void add(String s){
        List<String> f = filter();
        f.add(s);
        Astro.plugin.getConfig().set("exempted-players", f);
        Astro.plugin.saveConfig();
    }
}
