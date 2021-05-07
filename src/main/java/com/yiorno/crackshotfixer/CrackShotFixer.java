package com.yiorno.crackshotfixer;

import com.shampaggon.crackshot.events.WeaponReloadCompleteEvent;
import com.shampaggon.crackshot.events.WeaponReloadEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import com.shampaggon.crackshot.CSUtility;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

public final class CrackShotFixer extends JavaPlugin implements Listener {

    public static List<Player> reloadingPlayer = new ArrayList<>();
    public static List<Player> coolingPlayer = new ArrayList<>();
    public static List<Material> shuklerBoxes = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Config config = new Config(this);
        config.load();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onReloadStart(WeaponReloadEvent e) {
        Player player = e.getPlayer();
        reloadingPlayer.add(player);

        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                reloadingPlayer.remove(player);
            }
        }, 100L);

    }

    //@EventHandler
    //public void onReloadComplete(WeaponReloadCompleteEvent e) {
    //    Player player = e.getPlayer();
    //    reloadingPlayer.remove(player);
    //    player.sendMessage(ChatColor.YELLOW + "reloaded");
    //}

    @EventHandler
    public void onHeldItem(PlayerItemHeldEvent e){

        Player player = e.getPlayer();
        int heldSlot = e.getNewSlot();
        ItemStack heldItem = player.getInventory().getItem(heldSlot);
        Material heldMaterial = heldItem.getType();

        shuklerBoxes.add(Material.SHULKER_BOX);
        shuklerBoxes.add(Material.SHULKER_BOX);
        shuklerBoxes.add(Material.WHITE_SHULKER_BOX);
        shuklerBoxes.add(Material.ORANGE_SHULKER_BOX);
        shuklerBoxes.add(Material.MAGENTA_SHULKER_BOX);
        shuklerBoxes.add(Material.LIGHT_BLUE_SHULKER_BOX);
        shuklerBoxes.add(Material.YELLOW_SHULKER_BOX);
        shuklerBoxes.add(Material.LIME_SHULKER_BOX);
        shuklerBoxes.add(Material.PINK_SHULKER_BOX);
        shuklerBoxes.add(Material.GRAY_SHULKER_BOX);
        shuklerBoxes.add(Material.LIGHT_GRAY_SHULKER_BOX);
        shuklerBoxes.add(Material.CYAN_SHULKER_BOX);
        shuklerBoxes.add(Material.PURPLE_SHULKER_BOX);
        shuklerBoxes.add(Material.BLUE_SHULKER_BOX);
        shuklerBoxes.add(Material.BROWN_SHULKER_BOX);
        shuklerBoxes.add(Material.GREEN_SHULKER_BOX);
        shuklerBoxes.add(Material.RED_SHULKER_BOX);
        shuklerBoxes.add(Material.BLACK_SHULKER_BOX);


        if(reloadingPlayer.contains(player)){

            if(shuklerBoxes.contains(heldMaterial)) {

                e.setCancelled(true);

                if (!coolingPlayer.contains(player)) {

                    player.sendMessage(ChatColor.YELLOW + "リロード中はシュルカーボックスを持てません；；");
                    coolingPlayer.add(player);

                    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this, new Runnable() {
                        @Override
                        public void run() {
                            coolingPlayer.remove(player);
                        }
                    }, 20L);

                    return;

                } else {
                    return;
                }

            }else{
                return;
            }

        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        Player player = e.getPlayer();

        if(reloadingPlayer.contains(player)){
            reloadingPlayer.remove(player);
            coolingPlayer.remove(player);
        }

    }
}
