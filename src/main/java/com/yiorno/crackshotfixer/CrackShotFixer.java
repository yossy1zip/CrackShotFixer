package com.yiorno.crackshotfixer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class CrackShotFixer extends JavaPlugin {

    static CrackShotFixer instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Fix fix = new Fix();
        fix.registerMat();
        getServer().getPluginManager().registerEvents(new Fix(), this);
        getServer().getPluginManager().registerEvents(new Indicator(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args){
        if(command.getName().equalsIgnoreCase("gun")){

            if(!(sender instanceof Player)){
                return true;
            }

            Player p = (Player)sender;
            Indicator ind = new Indicator();
            ind.testMode(p);

        }

        return false;
    }

}
