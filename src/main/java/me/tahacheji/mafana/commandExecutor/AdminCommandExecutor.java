package me.tahacheji.mafana.commandExecutor;

import me.tahacheji.mafana.MafanaNetworkCommunicator;
import me.tahacheji.mafana.commandExecutor.paramter.Param;
import me.tahacheji.mafana.data.ProxyPlayer;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommandExecutor {

    @Command(names = "mcn preformPlayerCommand", permission = "mafana.admin", playerOnly = false)
    public void preformPlayerCommand(CommandSender sender, @Param(name = "player") OfflinePlayer player, @Param(name = "command", concated = true) String command) {
        try {
            ProxyPlayer proxyPlayer = MafanaNetworkCommunicator.getInstance().getNetworkCommunicatorDatabase().getProxyPlayer(player);

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
    public void preformConsoleCommand(CommandSender sender, @Param(name = "command") String command, @Param(name = "server") String server) {
        try {
            MafanaNetworkCommunicator.getInstance().preformConsoleCommand(server, command);
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "An error occurred while processing the command.");
        }
    }
    @Command(names = "mcn sendPlayerToServer", permission = "mafana.admin")
    public void sendPlayerToServer(CommandSender sender, @Param(name = "target") OfflinePlayer target, @Param(name = "serverName") String serverName) {
        ProxyPlayer proxyPlayer = MafanaNetworkCommunicator.getInstance().getNetworkCommunicatorDatabase().getProxyPlayer(target);
        if(proxyPlayer != null) {
            proxyPlayer.connectPlayerToServer(serverName);
            sender.sendMessage(ChatColor.GREEN + "Sent player to server.");
        } else {
            sender.sendMessage(ChatColor.RED + "Player not found or not connected to the network.");
        }
    }
}
