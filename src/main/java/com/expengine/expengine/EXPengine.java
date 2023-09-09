package com.expengine.expengine;

import com.expengine.expengine.EXPCommand.EXPengineOPhelp;
import com.expengine.expengine.EXPCommand.EXPenginehelp;
import com.expengine.expengine.EXPCommand.EXPsystem;
import com.expengine.expengine.EXPListener.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class EXPengine extends JavaPlugin {
    private static EXPengine instance;

    File folder = new File(getDataFolder(),"\\playeryml");//创建玩家数据文件夹234
    @Override
    public void onEnable() {
        instance = this;
        //初始化一个Config
        registerConfig();

        //监听注册
        Bukkit.getPluginManager().registerEvents(new JoinEvent(),this);//监听加入事件

        //命令注册

        getCommand("expengine").setExecutor(new EXPenginehelp());
        Objects.requireNonNull(Bukkit.getPluginCommand("expsystem")).setExecutor(new EXPsystem());
        Objects.requireNonNull(Bukkit.getPluginCommand("expsystem")).setTabCompleter((TabCompleter) new EXPsystem());
        getCommand("expoperater").setExecutor(new EXPengineOPhelp());

        //Bukkit.getPluginCommand().register();

        //玩家数据文件夹是否存在，如果没有则创建
        if(!folder.exists())
        {
            folder.mkdirs();
        }

        // Plugin startup logic
        say("EXPengine开启了");
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
    public static String LevelSwitch(int l_num) //level数字转level文字
    {
        String key = "level."+ l_num ;
        if(EXPengine.getInstance().getConfig().contains(key))
        {
            return EXPengine.getInstance().getConfig().getString(key);
        }else{
            return "error,阶段过高或过低导致不在天机之中";
        }
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
