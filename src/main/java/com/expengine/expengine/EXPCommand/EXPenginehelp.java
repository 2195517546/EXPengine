package com.expengine.expengine.EXPCommand;

import com.expengine.expengine.EXPengine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class EXPenginehelp implements CommandExecutor {
    File folder = new File(EXPengine.getInstance().getDataFolder(),"\\playeryml");
    public void helpMenuSender(CommandSender sender)
    {
        sender.sendMessage("EXPengine指令有:");
        sender.sendMessage("简写可以为expe");
        sender.sendMessage("/expengine 获取帮助");
        sender.sendMessage("/expengine help 获取帮助");
        sender.sendMessage("/expengine exp [玩家名称] 查询自己的修为");
        //记得添加查询别人的修为,已完成。
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
                File playerFile = new File(folder.getAbsolutePath()+"\\"+name+".yml");//获取玩家数据路径
                FileConfiguration fileout = YamlConfiguration.loadConfiguration(playerFile);//输出玩家数据
                int expPoint = fileout.getInt("exp");
                sender.sendMessage("经验有"+ expPoint);
            }
        }else if(args.length==2)
        {
            String argis1=args[0];
            String argis2=args[1];
            if(argis1.equalsIgnoreCase("exp"))
            {
                String s_name =sender.getName();
                String t_name =argis2;//获取目标名称

                Player targetPlayer=org.bukkit.Bukkit.getPlayerExact(t_name);
                /*
                * 1.能够taggetplayer.sendMessage(XXXXXX)
                * 2.targetplayer.getname 必须是 t_name
                * */

                File targetFile=new File(folder.getAbsolutePath()+"\\"+t_name+".yml");
                File senderFile=new File(folder.getAbsolutePath()+"\\"+s_name+".yml");

                if(!targetFile.exists()){
                    sender.sendMessage("此为凡人或不存在");
                    return false;
                }

                if (targetPlayer == null || !targetPlayer.isOnline()) {
                    sender.sendMessage("此修士暂时脱离了天机之中");
                    return false;
                }

                FileConfiguration targetFileout =YamlConfiguration.loadConfiguration(targetFile);//获取目标文件流
                FileConfiguration senderFileout =YamlConfiguration.loadConfiguration(senderFile);//获取指令方文件流

                if(targetFileout.getInt("hideexp")==1)
                {
                    sender.sendMessage("该修士用来秘法遮蔽修为，无法探查。");
                    targetPlayer.sendMessage("有修士探查了你的修为");
                }
                if(senderFileout.getInt("exp")>=targetFileout.getInt("exp") && targetFileout.getInt("hideexp")!=1)
                {
                    if (senderFileout.getInt("level") == senderFileout.getInt("level")) {
                        sender.sendMessage("修士" + t_name + "的境界是" + "(一个函数,用于表示境界名称)" + "修为有" + targetFileout.getInt("exp"));
                        targetPlayer.sendMessage("修士" + s_name + "探查了你的修为");
                    } else {
                        sender.sendMessage("修士" + t_name + "的境界是" + " (一个函数,用于表示境界名称) " + "修为有" + targetFileout.getInt("exp"));
                        targetPlayer.sendMessage("你感觉有修士探查了你的修为");
                    }
                }else {
                    sender.sendMessage("此人修为高深，无法查探一二");
                    targetPlayer.sendMessage("你发现修士"+s_name+"尝试探查你的修为");
                }
            }
        }
        return false;
    }
}
