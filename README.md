# EXPengine

MC插件,API版本是1.20

测试服务端是Paper

修仙引擎,用于修仙玩法服务器

2023/9/5停更一天


# 目前功能:

创建玩家数据文件夹.yml

### 普通命令<br>
`/expe exp [玩家名]`<br>

获取当前修为(EXP),以及别人的修为<br>
并且对方知晓你探查了他（是不是很硬核）<br>

## config
`level`:阶段名称<br>
`leveltolevel`:升级所需修为,1:100指从1升级到2要100<br>
`leveltribualation`:渡劫等级，比如金丹期要渡劫并且雷劈4次就是14:4<br>
`levelad`:破境界公告:`数字`境界等级;`self`若为0则可见，非0则仅自己可见;`words`破境界标语<br>
`levelquailty`:`数字`境界等级;`healthpoint`生命值加成;`magicpoint`法力值加成<br>
`level[数字]`:表示第n种修炼阶段，比如体法双修

## 更新

#### 更新规划的调整

## [最新]增加了管理员命令
`/expo giveexp [玩家名] [数量]` 给经验<br>
`/expo removexp [玩家名] [数量]` 减经验(最少到0<br>
`/expo setlevel [玩家名] [数量]` 设置阶段<br>
`/expo reload` 重新加载插件配置文件config.yml
## [ 正在开发 ]
##### 更合理的指令使用:
咋就是说，我发现输错指令没反应，所以得加这个
##### 修为系统其一:
包括不限于:<br>
升级播报（一般是大境界提升<br>
双修系统-不是那个涩涩的，是体修法修的<br>
诸如`/exps levelup` 升级阶段/expsystem指令类<br>
##### 修为系统简介
能够花费EXP升级境界，并且升级境界可以大幅增加基本属性<br>
有的境界,(一般是大境界)突破时候要渡劫,被雷劈，劈死了渡劫失败<br>

`PS:特殊属性要通过功法书籍之类的提升`
# 后续将上线
更高的自定义性

修为系统
通过花费修为升级【注意是花费修为】

属性数值系统
包括但不限于灵根，五行，法则
并且对玩家的各项能力有效果，比如速度，当然
如果速度太快无法控制，所以会设定一个法咒系统

# 扩展插件
由于考虑到EXPengine的主题，后续系统将通过其他插件来实现<br>
包括渡劫系统<br>
以及
法咒系统<br>
通过指令来实现自身效果的大小
比如我使用神行千里法咒/xxxxx可以获得速度提升，
但是明显不能常规用，所以会消耗蓝条
再比如使用类似于kill的插件，对某某人施加诅咒，但是会消耗红条和蓝条之类的

后续会增加，更多。。。。。。
