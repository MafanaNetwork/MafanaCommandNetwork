package me.tahacheji.mafana.commandExecutor.paramter.impl;

import me.tahacheji.mafana.MafanaCommandNetwork;
import me.tahacheji.mafana.MafanaNetworkCommunicator;
import me.tahacheji.mafana.commandExecutor.paramter.Processor;
import me.tahacheji.mafana.data.Server;

import org.bukkit.ChatColor;

import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ServerProcessor extends Processor<Server> {

    public Server process(CommandSender sender, String supplied) {
        Server server = MafanaNetworkCommunicator.getInstance().getNetworkCommunicatorDatabase().getServerFromID(supplied);
        if (server == null) {
            sender.sendMessage(ChatColor.RED + "A Server by the name of '" + supplied + "' cannot be located.");
            return null;
        }

        return server;
    }

    public List<String> tabComplete(CommandSender sender, String supplied) {
        return MafanaNetworkCommunicator.getInstance().getNetworkCommunicatorDatabase().getAllServerSync()
                .stream()
                .map(Server::getServerID)
                .filter(Objects::nonNull)
                .filter(name -> name.toLowerCase().startsWith(supplied.toLowerCase()))
                .limit(100)
                .collect(Collectors.toList());
    }
}
