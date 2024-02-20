package me.tahacheji.mafana.commandExecutor.duration;

import lombok.Data;

@Data
public class Duration {
    private final String parsed;
    private final long time;

    public Duration(String parsed, long time) {
        this.parsed = parsed;
        this.time = time;
    }
}
