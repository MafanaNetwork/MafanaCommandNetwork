package me.tahacheji.mafana.commandExecutor.paramter.impl;

import me.tahacheji.mafana.MafanaCommandNetwork;
import me.tahacheji.mafana.MafanaRankManager;
import me.tahacheji.mafana.commandExecutor.paramter.Processor;
import me.tahacheji.mafana.rankManager.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RankProcessor extends Processor<Rank> {

    public Rank process(CommandSender sender, String supplied) {
        Rank rank = MafanaRankManager.getInstance().getRankDatabase().getRankSync(supplied);

        if (rank == null) {
            sender.sendMessage(ChatColor.RED + "A Rank by the name of '" + supplied + "' cannot be located.");
            return null;
        }

        return rank;
    }

    public List<String> tabComplete(CommandSender sender, String supplied) {
        return MafanaRankManager.getInstance().getRankDatabase().getAllRankSync()
                .stream()
                .map(Rank::getRankID)
                .filter(Objects::nonNull)
                .filter(name -> name.toLowerCase().startsWith(supplied.toLowerCase()))
                .limit(100)
                .collect(Collectors.toList());
    }
}
