package com.expengine.expengine;

import com.expengine.expengine.EXPCommand.EXPengineOPhelp;
import com.expengine.expengine.EXPCommand.EXPenginehelp;
import com.expengine.expengine.EXPListener.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class EXPengine extends JavaPlugin {
    private static EXPengine instance;

    File folder = new File(getDataFolder(),"\\playeryml");//创建玩家数据文件夹234
    @Override
    public void onEnable() {
        instance = this;
        registerConfig();//初始化一个Config
        say("EXPengine开启了");
        //监听注册
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this);//监听加入事件
        //命令注册
        getCommand("expengine").setExecutor(new EXPenginehelp());
        getCommand("expoperater").setExecutor(new EXPengineOPhelp());
        //Bukkit.getPluginCommand().register();
        if(!folder.exists())//玩家数据文件夹是否存在，如果没有则创建
        {
            folder.mkdirs();
        }
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        say("EXPengine关闭了");
    }
    public void say(String s)
    {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(s);
    }
    private void registerConfig()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    public static EXPengine getInstance(){
        return instance;
    }
}
