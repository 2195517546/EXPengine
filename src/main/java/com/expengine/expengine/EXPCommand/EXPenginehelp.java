package com.expengine.expengine.EXPCommand;

import com.expengine.expengine.EXPengine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.checkerframework.checker.index.qual.PolyUpperBound;

import java.io.File;

public class EXPenginehelp implements CommandExecutor {
    File folder = new File(EXPengine.getInstance().getDataFolder(),"\\playeryml");
    public void helpMenuSender(CommandSender sender)
    {
        sender.sendMessage("EXPengine指令有:");
        sender.sendMessage("简写可以谢伟exphelp或expe");
        sender.sendMessage("/expengine 获取帮助");
        sender.sendMessage("/expengine help 获取帮助");
        sender.sendMessage("/expengine exp查询自己的修为");
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s,String[] args) {
        if(args.length==0)
        {
            helpMenuSender(sender);
        }else if(args.length ==1)
        {
            String argis=args[0];
            if(argis.equalsIgnoreCase("help"))//帮助
            {
                helpMenuSender(sender);
            }
            if(argis.equalsIgnoreCase("exp"))//查询修为
            {
                String name =sender.getName();//获取命令发送者名字
                File playerfile = new File(folder.getAbsolutePath()+"\\"+name+".yml");//获取玩家数据路径
                FileConfiguration fileout = YamlConfiguration.loadConfiguration(playerfile);//输出玩家数据
                int exppoint = fileout.getInt("exp");
                sender.sendMessage("经验有"+ exppoint);
            }
        }
        return false;
    }
}
