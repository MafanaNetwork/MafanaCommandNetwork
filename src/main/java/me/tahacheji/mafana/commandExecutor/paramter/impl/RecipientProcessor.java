package me.tahacheji.mafana.commandExecutor.paramter.impl;

import me.tahacheji.mafana.MafanaCommandNetwork;
import me.tahacheji.mafana.MafanaNetworkCommunicator;
import me.tahacheji.mafana.MafanaRankManager;
import me.tahacheji.mafana.commandExecutor.paramter.Processor;
import me.tahacheji.mafana.rankManager.Rank;
import me.tahacheji.mafanatextnetwork.MafanaTextNetwork;
import me.tahacheji.mafanatextnetwork.data.AllowedRecipient;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecipientProcessor extends Processor<AllowedRecipient> {


    public AllowedRecipient process(CommandSender sender, String supplied) {
        Player player = (Player) sender;
        AllowedRecipient allowedRecipient = null;
        for(AllowedRecipient ar : MafanaTextNetwork.getInstance().getGamePlayerMessageData().getAllowedRecipientsStringSync(player.getUniqueId())) {
            if(ar.getPlayerName().equalsIgnoreCase(supplied)) {
                allowedRecipient = ar;
                break;
            }
        }

        if (allowedRecipient == null) {
            sender.sendMessage(ChatColor.RED + "A player by the name of '" + supplied + "' cannot be located.");
            return null;
        }

        return allowedRecipient;
    }

    public List<String> tabComplete(CommandSender sender, String supplied) {
        Player player = (Player) sender;
        return MafanaTextNetwork.getInstance().getGamePlayerMessageData().getAllowedRecipientsStringSync(player.getUniqueId())
                .stream()
                .map(AllowedRecipient::getPlayerName)
                .filter(Objects::nonNull)
                .filter(name -> name.toLowerCase().startsWith(supplied.toLowerCase()))
                .limit(100)
                .collect(Collectors.toList());
    }
}
