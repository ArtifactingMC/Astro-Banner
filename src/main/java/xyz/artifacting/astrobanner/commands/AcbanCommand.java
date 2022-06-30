package xyz.artifacting.astrobanner.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.artifacting.astrobanner.Astro;
import xyz.artifacting.astrobanner.utils.DefaultFontInfo;
import xyz.artifacting.astrobanner.utils.msg;


public class AcbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // This command is only gonna be ran by console so i dont give a fuck about errors if u dont provide a player
        // cry about it.

        if (!(sender.hasPermission(Astro.plugin.getConfig().getString("acban-permission")))) return false;

        Player player = Bukkit.getPlayer(args[0]);
        if (player != null) {
            if (!(Astro.plugin.getConfig().getStringList("exempted-players").contains(player.getName()))) {
                int counter = Astro.plugin.getConfig().getInt("autobans");
                int v = counter + 1;
                Astro.plugin.getConfig().set("autobans", v);
                Astro.plugin.saveConfig();
                if (Astro.plugin.getConfig().getBoolean("bans-enabled")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Astro.plugin.getConfig().getString("ban-cmd").replaceAll("%player%", player.getPlayer().getName()));
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        sendCenteredMessage(all, " ");
                        sendCenteredMessage(all, "&7⚔ &e&lAstro Anticheat &7⚔");
                        sendCenteredMessage(all, "   &e" + player.getName() + "&7 has been &eautobanned!");
                        sendCenteredMessage(all, "  &e&o" + Astro.plugin.getConfig().getInt("autobans") + " &7total autobans");
                        sendCenteredMessage(all, " ");
                        if (Astro.plugin.getConfig().getBoolean("global-ban-sound")) continue;
                        all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                    }
                } else {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission(Astro.plugin.getConfig().getString("staff-permission"))) {
                            sendCenteredMessage(all, "  ");
                            sendCenteredMessage(all, "&7⚔ &e&lAstro Anticheat &7⚔");
                            sendCenteredMessage(all, "   &e" + player.getName() + "&7 would've been &eautobanned");
                            sendCenteredMessage(all, "  &7but bans are currently disabled!");
                            sendCenteredMessage(all, " ");
                            if (Astro.plugin.getConfig().getBoolean("staff-ban-sound")) continue;
                            all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                        }
                    }
                }
            } else {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission(Astro.plugin.getConfig().getString("staff-permission"))) {
                        sendCenteredMessage(all, "  ");
                        sendCenteredMessage(all, "&7⚔ &e&lAstro Anticheat &7⚔");
                        sendCenteredMessage(all, "   &e" + player.getName() + "&7 would've been &eautobanned");
                        sendCenteredMessage(all, "  &7but they are currently exempt!");
                        sendCenteredMessage(all, " ");
                        if (Astro.plugin.getConfig().getBoolean("staff-ban-sound")) continue;
                        all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0f, 1.0f);
                    }
                }
            }
        }




        return false;
    }

    public void sendCenteredMessage(Player player, String message) {
        if (message == null || message.equals(""))
            player.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;


        for (char c : message.toCharArray()) {
            if (c == '�') {
                previousCode = true;
                continue;
            } else if (previousCode == true) {
                previousCode = false;
                if (c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                } else
                    isBold = false;
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        final int CENTER_PX = 154;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        player.sendMessage(sb + message);
    }
}
