package me.tahacheji.mafana;

import me.tahacheji.mafana.commandExecutor.AdminCommandExecutor;
import me.tahacheji.mafana.commandExecutor.CommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class MafanaCommandNetwork extends JavaPlugin {


    @Override
    public void onEnable() {
        CommandHandler.registerCommands(AdminCommandExecutor.class, this);
    }
}
