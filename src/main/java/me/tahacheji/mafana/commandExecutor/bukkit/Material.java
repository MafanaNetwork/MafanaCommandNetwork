package me.tahacheji.mafana.commandExecutor.bukkit;

import java.util.ArrayList;
import java.util.List;

public class Material {

    private List<org.bukkit.Material> materials = new ArrayList<>();

    public Material(List<org.bukkit.Material> materials) {
        this.materials = materials;
    }

    public List<org.bukkit.Material> getMaterials() {
        return materials;
    }
}
