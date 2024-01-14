package me.tahacheji.mafana.commandExecutor;

import me.tahacheji.mafana.MafanaNetworkCommunicator;
import me.tahacheji.mafana.commandExecutor.paramter.Param;
import me.tahacheji.mafana.data.OfflineProxyPlayer;
import me.tahacheji.mafana.data.ProxyPlayer;
import me.tahacheji.mafana.data.Server;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

public class AdminCommandExecutor {

    @Command(names = "mcn preformPlayerCommand", permission = "mafana.admin", playerOnly = false)
    public void preformPlayerCommand(CommandSender sender, @Param(name = "player") ProxyPlayer proxyPlayer, @Param(name = "command", concated = true) String command) {
        try {
            if (proxyPlayer != null) {
                proxyPlayer.preformCommand(command);
            } else {
                sender.sendMessage(ChatColor.RED + "Player not found or not connected to the network.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "An error occurred while processing the command.");
        }
    }

    @Command(names = "mcn preformConsoleCommand", permission = "mafana.admin", playerOnly = false)
    public void preformConsoleCommand(CommandSender sender, @Param(name = "command") String command, @Param(name = "server") Server server) {
        try {
            MafanaNetworkCommunicator.getInstance().preformConsoleCommand(server.getServerID(), command);
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "An error occurred while processing the command.");
        }
    }
    @Command(names = "mcn sendPlayerToServer", permission = "mafana.admin")
    public void sendPlayerToServer(CommandSender sender, @Param(name = "target") ProxyPlayer proxyPlayer, @Param(name = "serverName") String serverName) {
        if(proxyPlayer != null) {
            proxyPlayer.connectPlayerToServer(serverName);
            sender.sendMessage(ChatColor.GREEN + "Sent player to server.");
        } else {
            sender.sendMessage(ChatColor.RED + "Player not found or not connected to the network.");
        }
    }
}
