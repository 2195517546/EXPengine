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
        //初始化一个Config
        registerConfig();

        say("EXPengine开启了");

        //监听注册
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this);//监听加入事件

        //命令注册
        getCommand("expengine").setExecutor(new EXPenginehelp());
        getCommand("expoperater").setExecutor(new EXPengineOPhelp());

        //Bukkit.getPluginCommand().register();

        //玩家数据文件夹是否存在，如果没有则创建
        if(!folder.exists())
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
    public void say(String s)//一种简写的sender.sendMessage
    {
        CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(s);
    }
    private void registerConfig()//一种不需要在关闭逻辑保存的config创建方式
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    public static EXPengine getInstance(){
        return instance;
    }
}
