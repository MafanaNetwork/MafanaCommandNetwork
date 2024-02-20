package me.tahacheji.mafana.commandExecutor.paramter.impl;

import me.tahacheji.mafana.MafanaCommandNetwork;
import me.tahacheji.mafana.MafanaNetworkCommunicator;
import me.tahacheji.mafana.commandExecutor.paramter.Processor;
import me.tahacheji.mafana.data.ProxyPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProxyPlayerProcessor extends Processor<ProxyPlayer> {

    public ProxyPlayer process(CommandSender sender, String supplied) {
        ProxyPlayer player = MafanaNetworkCommunicator.getInstance().getNetworkCommunicatorDatabase().getProxyPlayer(supplied);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "An proxy player by the name of '" + supplied + "' cannot be located.");
            return null;
        }

        return player;
    }

    public List<String> tabComplete(CommandSender sender, String supplied) {
        return MafanaNetworkCommunicator.getInstance().getNetworkCommunicatorDatabase().getAllConnectedPlayerSync()
                .stream()
                .map(ProxyPlayer::getPlayerName)
                .filter(Objects::nonNull)
                .filter(name -> name.toLowerCase().startsWith(supplied.toLowerCase()))
                .limit(100)
                .collect(Collectors.toList());
    }
}
