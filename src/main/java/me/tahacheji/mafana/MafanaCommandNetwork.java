package me.tahacheji.mafana;

import me.tahacheji.mafana.commandExecutor.AdminCommandExecutor;
import me.tahacheji.mafana.commandExecutor.CommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class MafanaCommandNetwork extends JavaPlugin {

    private static MafanaCommandNetwork instance;

    @Override
    public void onEnable() {
        instance = this;
        CommandHandler.registerCommands(AdminCommandExecutor.class, this);
    }

    public static MafanaCommandNetwork getInstance() {
        return instance;
    }
}
