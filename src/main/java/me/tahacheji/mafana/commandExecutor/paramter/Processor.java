package me.tahacheji.mafana.commandExecutor.paramter;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;
import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Getter
public abstract class Processor<T> {

    private final Class<?> type;

    @SneakyThrows
    public Processor() {
        Type type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.type = Class.forName(type.getTypeName());
        ParamProcessor.createProcessor(this);
    }

    public abstract T process(CommandSender sender, String supplied);

    public List<String> tabComplete(CommandSender sender, String supplied) {
        return new ArrayList<>();
    }

}
