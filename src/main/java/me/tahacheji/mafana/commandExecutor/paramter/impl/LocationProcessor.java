package me.tahacheji.mafana.commandExecutor.paramter.impl;

import me.tahacheji.mafana.commandExecutor.paramter.Processor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocationProcessor extends Processor<Location> {

    public Location process(CommandSender sender, String supplied) {
        // Split the supplied string by commas and trim each part
        String[] locationParts = supplied.split(",");
        List<String> parts = Arrays.stream(locationParts).map(String::trim).collect(Collectors.toList());

        try {
            double x = 0, y = 0, z = 0;
            float yaw = 0, pitch = 0;

            if (parts.size() >= 1) {
                x = Double.parseDouble(parts.get(0));
            }

            if (parts.size() >= 2) {
                String yPart = parts.get(1);
                if (yPart.equalsIgnoreCase("playerlocation")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        Location playerLocation = player.getLocation();
                        x = playerLocation.getX();
                        y = playerLocation.getY();
                        z = playerLocation.getZ();
                        yaw = playerLocation.getYaw();
                        pitch = playerLocation.getPitch();
                    } else {
                        sender.sendMessage(ChatColor.RED + "Cannot use player location without a valid player.");
                        return null;
                    }
                } else {
                    y = Double.parseDouble(yPart);
                }
            }

            if (parts.size() >= 3) {
                String zPart = parts.get(2);
                if (!zPart.equalsIgnoreCase("playerlocation")) {
                    z = Double.parseDouble(zPart);
                }
            }

            if (parts.size() >= 4) {
                String yawPart = parts.get(3);
                if (!yawPart.equalsIgnoreCase("playerlocation")) {
                    yaw = Float.parseFloat(yawPart);
                }
            }

            if (parts.size() >= 5) {
                String pitchPart = parts.get(4);
                if (!pitchPart.equalsIgnoreCase("playerlocation")) {
                    pitch = Float.parseFloat(pitchPart);
                }
            }

            return new Location(null, x, y, z, yaw, pitch);

        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Invalid number format in location input.");
            return null;
        }
    }

    public List<String> tabComplete(CommandSender sender, String supplied) {
        List<String> suggestions = new ArrayList<>();
        String[] parts = supplied.split(",");

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location playerLocation = player.getLocation();

            if (parts.length == 1 && parts[0].trim().equalsIgnoreCase("playerlocation")) {
                suggestions.add(String.format("%.2f,%.2f,%.2f,%.2f,%.2f",
                        playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(),
                        playerLocation.getPitch(), playerLocation.getYaw()));
            } else if (parts.length > 1) {
                String lastPart = parts[parts.length - 1].trim().toLowerCase();

                switch (parts.length - 1) {
                    case 1:
                        suggestions.add(String.format("%.2f,%.2f,%.2f,%.2f,%.2f",
                                playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(),
                                playerLocation.getPitch(), playerLocation.getYaw()));
                        break;
                    case 2:
                        suggestions.add(String.format("%.2f,%.2f,%.2f",
                                playerLocation.getY(), playerLocation.getZ(),
                                playerLocation.getPitch(), playerLocation.getYaw()));
                        break;
                    case 3:
                        suggestions.add(String.format("%.2f,%.2f",
                                playerLocation.getZ(),
                                playerLocation.getPitch(), playerLocation.getYaw()));
                        break;
                    case 4:
                        suggestions.add(String.format("%.2f",
                                playerLocation.getPitch(), playerLocation.getYaw()));
                        break;
                    case 5:
                        break;
                }

                suggestions = suggestions.stream()
                        .map(suggestion -> String.join(",", Arrays.copyOfRange(parts, 0, parts.length - 1)) + "," + suggestion)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList());
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Cannot use player location without a valid player.");
        }

        return suggestions;
    }
}
