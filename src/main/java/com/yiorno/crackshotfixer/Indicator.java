package com.yiorno.crackshotfixer;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Indicator implements Listener {

    public void testMode(Player p){
        if(Val.testing.contains(p)){

            Val.testing.remove(p);
            p.sendMessage(Val.prefix + "試し撃ちを終えました！");


        } else {

            Val.testing.add(p);
            p.sendMessage(Val.prefix + "試し撃ちモードになりました！");

        }
    }

    @EventHandler
    public void onHit(WeaponDamageEntityEvent e){
        if(!(Val.testing.contains(e.getPlayer()))){
            return;
        }

        if(e.isHeadshot()){

        e.getPlayer().sendMessage(Val.prefix + ChatColor.GRAY + ChatColor.ITALIC + ChatColor.BOLD +
                "Damage : " + ChatColor.RED + ChatColor.ITALIC + ChatColor.BOLD + e.getDamage() + " ✔");

        } else {

            e.getPlayer().sendMessage(Val.prefix + ChatColor.GRAY + ChatColor.ITALIC + ChatColor.BOLD +
                    "Damage : " + ChatColor.RED + ChatColor.ITALIC + ChatColor.BOLD + e.getDamage());

        }


    }

}
