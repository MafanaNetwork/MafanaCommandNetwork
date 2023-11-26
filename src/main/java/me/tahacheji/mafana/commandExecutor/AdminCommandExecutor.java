package me.tahacheji.mafana.commandExecutor;

import me.tahacheji.mafana.commandExecutor.paramter.Param;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommandExecutor {

    @Command(names = {"mcn", "mafanacommandnetwork"}, permission = "mafana.admin", playerOnly = true)
    public void makePlayerPreformCommand(CommandSender sender, @Param(name = "player") Player target, @Param(name = "command", concated = true) String command) {
        target.performCommand(command);
    }
}
