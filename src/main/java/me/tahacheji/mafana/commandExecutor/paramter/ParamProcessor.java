package me.tahacheji.mafana.commandExecutor.paramter;

import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.Getter;
import me.tahacheji.mafana.commandExecutor.bukkit.Material;
import me.tahacheji.mafana.commandExecutor.duration.Duration;
import me.tahacheji.mafana.commandExecutor.node.ArgumentNode;
import me.tahacheji.mafana.commandExecutor.paramter.impl.*;
import me.tahacheji.mafana.data.OfflineProxyPlayer;
import me.tahacheji.mafana.data.ProxyPlayer;
import me.tahacheji.mafana.data.Server;
import me.tahacheji.mafana.rankManager.Rank;
import me.tahacheji.mafanatextnetwork.data.AllowedRecipient;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class ParamProcessor {
    @Getter private static final HashMap<Class<?>, Processor<?>> processors = new HashMap<>();
    private static boolean loaded = false;

    private final ArgumentNode node;
    private final String supplied;
    private final CommandSender sender;

    public Object get() {
        if(!loaded) loadProcessors();

        Processor<?> processor = processors.get(node.getParameter().getType());
        if(processor == null) return supplied;

        return processor.process(sender, supplied);
    }

    public List<String> getTabComplete() {
        if(!loaded) loadProcessors();

        Processor<?> processor = processors.get(node.getParameter().getType());
        if(processor == null) return new ArrayList<>();

        return processor.tabComplete(sender, supplied);
    }

    public static void createProcessor(Processor<?> processor) {
        processors.put(processor.getType(), processor);
    }

    public static void loadProcessors() {
        loaded = true;

        processors.put(int.class, new IntegerProcessor());
        processors.put(long.class, new LongProcessor());
        processors.put(double.class, new DoubleProcessor());
        processors.put(boolean.class, new BooleanProcessor());

        processors.put(Server.class, new ServerProcessor());
        processors.put(Location.class, new LocationProcessor());
        processors.put(Material.class, new MaterialProcessor());
        processors.put(AllowedRecipient.class, new RecipientProcessor());
        processors.put(Rank.class, new RankProcessor());
        processors.put(ProxyPlayer.class, new ProxyPlayerProcessor());
        processors.put(OfflineProxyPlayer.class, new OfflineProxyPlayerProcessor());
        processors.put(ChatColor.class, new ChatColorProcessor());
        processors.put(Player.class, new PlayerProcessor());
        processors.put(OfflinePlayer.class, new OfflinePlayerProcessor());
        processors.put(World.class, new WorldProcessor());
        processors.put(Duration.class, new DurationProcessor());
        processors.put(GameMode.class, new GamemodeProcessor());
    }
}
