package com.expengine.expengine.EXPListener;

import com.expengine.expengine.EXPengine;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class JoinEvent implements Listener {

    public void fileset(Configuration filein)
    {
        filein.set("exp",0);
        filein.set("level",0);
        filein.set("hideexp",0);
        filein.set("tribualation",0);
        filein.set("baseattrib.healthpoint",0);
        filein.set("baseattrib.magicpoint",0);
    }
    File folder = new File(EXPengine.getInstance().getDataFolder(),"\\playeryml");
    @EventHandler
    public void JoinEventListener(PlayerJoinEvent event){//监听玩家进入
        System.out.println("EXPListener启动了");//测试用
        Player p = event.getPlayer();//获取玩家
        String name =p.getName();//获取玩家名称
        File playerfile = new File(folder.getAbsolutePath()+"\\"+name+".yml");//获取玩家数据路径
        if(!playerfile.exists())//检测是否存在玩家数据文件,没有则创建
        {
            try
            {
                playerfile.createNewFile();
                FileConfiguration filein = YamlConfiguration.loadConfiguration(playerfile);
                filein.set("Name",name);
                fileset(filein);
                try
                {
                    filein.save(playerfile);

                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
//        FileConfiguration filein = YamlConfiguration.loadConfiguration(playerfile);
//        int maxHealth=filein.getInt("baseattrib.healthpoint");
//        p.setHealthScaled(true);
//        p.setHealthScale(maxHealth*10);//动态压缩血量
//        p.setMaxHealth(maxHealth);

    }

}
