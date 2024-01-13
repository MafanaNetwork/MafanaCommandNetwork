package me.tahacheji.mafana.commandExecutor.paramter.impl;

import me.tahacheji.mafana.MafanaNetworkCommunicator;
import me.tahacheji.mafana.commandExecutor.paramter.Processor;
import me.tahacheji.mafana.data.OfflineProxyPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OfflineProxyPlayerProcessor extends Processor<OfflineProxyPlayer> {

    public OfflineProxyPlayer process(CommandSender sender, String supplied) {
        OfflineProxyPlayer player = MafanaNetworkCommunicator.getInstance().getPlayerDatabase().getOfflineProxyPlayer(supplied);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "An offline player by the name of '" + supplied + "' cannot be located.");
            return null;
        }

        return player;
    }

    public List<String> tabComplete(CommandSender sender, String supplied) {
        return MafanaNetworkCommunicator.getInstance().getPlayerDatabase().getAllOfflineProxyPlayers()
                .stream()
                .map(OfflineProxyPlayer::getPlayerName)
                .filter(Objects::nonNull)
                .filter(name -> name.toLowerCase().startsWith(supplied.toLowerCase()))
                .limit(100)
                .collect(Collectors.toList());
    }
}
