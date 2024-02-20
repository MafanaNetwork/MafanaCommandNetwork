package me.tahacheji.mafana.commandExecutor.node;

import lombok.Data;

import java.lang.reflect.Parameter;

@Data
public class ArgumentNode {

    private final String name;
    private final boolean concated;
    private final boolean required;

    private final Parameter parameter;
}
