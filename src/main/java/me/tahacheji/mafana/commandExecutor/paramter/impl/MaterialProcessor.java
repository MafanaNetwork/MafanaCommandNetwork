package me.tahacheji.mafana.commandExecutor.paramter.impl;

import me.tahacheji.mafana.MafanaNetworkCommunicator;
import me.tahacheji.mafana.commandExecutor.bukkit.Material;
import me.tahacheji.mafana.commandExecutor.paramter.Processor;
import me.tahacheji.mafana.data.Server;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MaterialProcessor extends Processor<Material> {

    public Material process(CommandSender sender, String supplied) {
        // Split the supplied string by commas and trim each part
        String[] materialNames = supplied.split(",");
        List<org.bukkit.Material> materials = new ArrayList<>();

        // Convert each material name to Material type
        for (String materialName : materialNames) {
            org.bukkit.Material material = org.bukkit.Material.matchMaterial(materialName.trim());
            if (material != null) {
                materials.add(material);
            } else {
                sender.sendMessage(ChatColor.RED + "A material by the name of '" + materialName + "' cannot be located.");
                return null;
            }
        }

        return new Material(materials);
    }

    public List<String> tabComplete(CommandSender sender, String supplied) {
        String[] parts = supplied.split(",");

        if (parts.length > 1) {
            String lastPart = parts[parts.length - 1].trim().toLowerCase();
            String prefix = String.join(",", Arrays.copyOfRange(parts, 0, parts.length - 1)) + ",";

            return Arrays.stream(org.bukkit.Material.values())
                    .map(org.bukkit.Material::name)
                    .filter(name -> name.toLowerCase().startsWith(lastPart))
                    .map(name -> prefix + name.toLowerCase())
                    .collect(Collectors.toList());
        } else {
            return Arrays.stream(org.bukkit.Material.values())
                    .map(org.bukkit.Material::name)
                    .filter(name -> name.toLowerCase().startsWith(supplied.toLowerCase()))
                    .map(name -> name.toLowerCase() + ",")
                    .collect(Collectors.toList());
        }
    }

}
