package com.expengine.expengine.EXPCommand;

import com.expengine.expengine.EXPengine;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class EXPsystem implements CommandExecutor {

    public void helpMenuSender(CommandSender sender)
    {
        sender.sendMessage("EXPengine的system指令有:");
        sender.sendMessage("全写为expsystem");
        sender.sendMessage("简写可以为exps");
        sender.sendMessage("/expsystem 获取帮助");
        sender.sendMessage("/expsystem help 获取帮助");
        sender.sendMessage("/expsystem levelup 境界提升(需要花费修为)");

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
            if(argis.equalsIgnoreCase("levelup"))
            {
                String s_name = sender.getName();
                File senderFile=new File(folder.getAbsolutePath()+"\\"+s_name+".yml");
                FileConfiguration senderUse= YamlConfiguration.loadConfiguration(senderFile);
                int s_exp = senderUse.getInt("exp");//获取当前EXP
                int level = senderUse.getInt("level");//获取当前level
                int hp=senderUse.getInt("baseattrib.healthpoint");
                int mp=senderUse.getInt("baseattrib.magicpoint");
                boolean error_C_exp =EXPengine.getInstance().getConfig().getBoolean("leveltolevel."+level);
                if(!error_C_exp)
                {
                    sender.sendMessage("进阶未知");
                    return false;
                }
                int c_exp = EXPengine.getInstance().getConfig().getInt("leveltolevel."+level);//升级所需level
                int levelTribualation = level+1;
                if(EXPengine.getInstance().getConfig().getBoolean("leveltribualation."+levelTribualation))
                {
                    sender.sendMessage("你需要渡劫,才能提升");
                    return false;
                }
                if(s_exp>=c_exp)
                {
                    //更新提醒，设置一个dujie=1或者0,当升级的时候判断下一等级是否渡劫。
                    int f_exp = s_exp-c_exp;
                    level++;
                    int f_hp=hp+EXPengine.getInstance().getConfig().getInt("levelquailty."+level+"healthpoint");
                    int f_mp=mp+EXPengine.getInstance().getConfig().getInt("levelquailty."+level+"magicpoint");

                    //保存
                    senderUse.set("baseattrib.healthpoint",f_hp);
                    senderUse.set("baseattrib.magicpoint",f_mp);
                    senderUse.set("level",level);
                    senderUse.set("exp",f_exp);
                    try {
                        senderUse.save(senderFile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    sender.sendMessage("你已提升至"+LevelSwitch(level));
                    if(EXPengine.getInstance().getConfig().getBoolean("levellad."+level) && 0 == EXPengine.getInstance().getConfig().getInt("levellad."+level+".self")){
                        String word=EXPengine.getInstance().getConfig().getString("levelad."+level+"words");
                        org.bukkit.Bukkit.broadcastMessage(sender.getName() +" "+ word);
                    }
                }else{
                    sender.sendMessage("修为不足,无法进阶");
                }

            }
        }

        return false;
    }
    public String LevelSwitch(int l_num) //level数字转level文字
    {
        String key = "level."+ l_num ;
        if(EXPengine.getInstance().getConfig().contains(key))
        {
            return EXPengine.getInstance().getConfig().getString(key);
        }else{
            return "error,阶段过高或过低导致不在天机之中";
        }
    }
}
