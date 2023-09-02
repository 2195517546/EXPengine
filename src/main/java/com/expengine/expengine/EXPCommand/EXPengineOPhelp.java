package com.expengine.expengine.EXPCommand;

import com.expengine.expengine.EXPengine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

import static java.lang.Integer.parseInt;

public class EXPengineOPhelp implements CommandExecutor {
    public void helpMenuSender(CommandSender sender)
    {
        sender.sendMessage("EXPengine的OP指令有:");
        sender.sendMessage("全写为expoperater");
        sender.sendMessage("简写可以为expo");
        sender.sendMessage("/expoperater 获取帮助");
        sender.sendMessage("/expoperater help 获取帮助");
        sender.sendMessage("/expoperater reload 重新加载插件");
        sender.sendMessage("/expoperater giveexp [玩家名称] [给予数量] 给予修为");
        sender.sendMessage("/expoperater removexp [玩家名称] [减去数量] 移除修为，并且不可以为负数");
        sender.sendMessage("/expoperater setlevel [玩家名称] 设置阶段");
    }
    File folder = new File(EXPengine.getInstance().getDataFolder(),"\\playeryml");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s,String[] args) {
        if(args.length==0)
        {
            helpMenuSender(sender);
        }
        if(args.length==1)
        {
            String argis=args[0];
            if(argis.equalsIgnoreCase("help"))//帮助
            {
                helpMenuSender(sender);
            }
            if(argis.equalsIgnoreCase("reload"))//重新加载插件
            {
                EXPengine.getInstance().reloadConfig();
            }
        }
        if(args.length==3)
        {
            String argis1=args[0];
            String argis2=args[1];
            String argis3=args[2];
            if(argis1.equalsIgnoreCase("giveexp"))
            {
                String t_name =argis2;//获取目标名称
                Player targetPlayer=org.bukkit.Bukkit.getPlayerExact(t_name);//获取目标玩家

                File targetFile=new File(folder.getAbsolutePath()+"\\"+t_name+".yml");//目标
                if(!targetFile.exists()){
                    sender.sendMessage("不存在对象");
                    return false;
                }
                if (targetPlayer == null || !targetPlayer.isOnline()) {
                    sender.sendMessage("此修士暂时脱离了天机之中");
                    return false;
                }

                FileConfiguration targetFileout = YamlConfiguration.loadConfiguration(targetFile);//获取目标文件流
                int s_exp = targetFileout.getInt("exp");//初识数值
                int g_exp = parseInt(argis3);//输入数值
                int f_exp = s_exp+g_exp;//最终数值

                targetFileout.set("exp",f_exp);//设置数值

                sender.sendMessage(sender.getName()+"发送了 "+t_name + " 点 "+ g_exp+" EXP");//管理命令所以不会扣修为。
                targetPlayer.sendMessage("获取了EXP "+g_exp+" 点");

            }
            if(argis1.equalsIgnoreCase("removexp"))
            {
                String t_name =argis2;//获取目标名称
                Player targetPlayer=org.bukkit.Bukkit.getPlayerExact(t_name);//获取目标玩家

                File targetFile=new File(folder.getAbsolutePath()+"\\"+t_name+".yml");//目标
                if(!targetFile.exists()){
                    sender.sendMessage("不存在对象");
                    return false;
                }
                if (targetPlayer == null || !targetPlayer.isOnline()) {
                    sender.sendMessage("此修士暂时脱离了天机之中");
                    return false;
                }

                FileConfiguration targetFileout = YamlConfiguration.loadConfiguration(targetFile);//获取目标文件流
                int s_exp = targetFileout.getInt("exp");//初识数值
                int r_exp = parseInt(argis3);//输入数值
                if(r_exp>s_exp)
                {
                    int f_exp = 0;
                    targetFileout.set("exp",f_exp);
                    sender.sendMessage("输入值过大，默认对方"+t_name+"修为移除至0");
                    targetPlayer.sendMessage("你的修为被管理员置为了0");
                    return false;
                }
                int f_exp = s_exp-r_exp;//最终数值
                targetFileout.set("exp",f_exp);
                targetPlayer.sendMessage("你的修为被管理员减少到"+f_exp+" ，你原来有"+s_exp);
                sender.sendMessage("你将"+t_name+"的修为减少到了"+f_exp+"而原来他有"+s_exp);

            }
            if(argis1.equalsIgnoreCase("setlevel"))
            {
                String t_name =argis2;//获取目标名称
                Player targetPlayer=org.bukkit.Bukkit.getPlayerExact(t_name);//获取目标玩家

                File targetFile=new File(folder.getAbsolutePath()+"\\"+t_name+".yml");//目标
                if(!targetFile.exists()){
                    sender.sendMessage("不存在对象");
                    return false;
                }
                if (targetPlayer == null || !targetPlayer.isOnline()) {
                    sender.sendMessage("此修士暂时脱离了天机之中");
                    return false;
                }

                FileConfiguration targetFileout = YamlConfiguration.loadConfiguration(targetFile);//获取目标文件流
                int s_level = targetFileout.getInt("exp");//初识数值
                int g_level = parseInt(argis3);//输入数值

                targetFileout.set("exp",g_level);//设置数值

                sender.sendMessage("将 "+t_name + "阶段设置为"+ g_level+"也就是"+LevelSwitch(g_level)+" 而，原数值为"+s_level+"也就是"+LevelSwitch(s_level));
                targetPlayer.sendMessage("阶段被设置为 "+LevelSwitch(g_level));


            }
        }
        return false;
    }
    public String LevelSwitch(int l_num) //level数字转level文字
    {
        String key = "level."+l_num;
        if(EXPengine.getInstance().getConfig().contains(key))
        {
            return EXPengine.getInstance().getConfig().getString(key);
        }else{
            return "error,阶段过高或过低导致不在天机之中";
        }
    }
}
