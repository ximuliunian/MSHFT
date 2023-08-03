package MSHFT;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Properties;

public class SerProperties {
    @FXML   // 关闭页面
    private Button cl;
    @FXML   // 下界 true/false
    private ChoiceBox<String> allow_nether;
    @FXML   // 强制玩家加入时为默认游戏模式 true/false
    private ChoiceBox<String> force_gamemode;
    @FXML   // 白名单 true/false
    private ChoiceBox<String> white_list;
    @FXML   // 极限模式 true/false
    private ChoiceBox<String> hardcore;
    @FXML   // 攻击队友 true/false
    private ChoiceBox<String> pvp;
    @FXML   // 命令方块 true/false
    private ChoiceBox<String> enable_command_block;
    @FXML   // 正版验证 true/false
    private ChoiceBox<String> online_mode;
    @FXML   //世界类型 超平坦/旧世界/默认
    private ChoiceBox<String> level_type;
    @FXML   // 生成村民 true/false
    private ChoiceBox<String> spawn_npcs;
    @FXML   // 生成动物 true/false
    private ChoiceBox<String> spawn_animals;
    @FXML   // 生成敌对生物 true/false
    private ChoiceBox<String> spawn_monsters;
    @FXML   // 生成村庄 true/false
    private ChoiceBox<String> generate_structures;
    @FXML   // 游戏模式 生存/创造/冒险/旁观
    private ChoiceBox<String> gamemode;
    @FXML   // 游戏难度 和平/简单/普通/困难
    private ChoiceBox<String> difficulty;
    @FXML   // 端口
    private TextField server_port;
    @FXML   // 待机踢出
    private TextField player_idle_timeout;
    @FXML   // 种子
    private TextField level_seed;
    @FXML   // 玩家数量
    private TextField max_players;

    // 开关
    private final String[] yesNo = {"开启", "关闭"};
    //世界类型
    private final String[] worldType = {"默认", "超平坦", "旧世界"};
    // 游戏模式
    private final String[] gameMode = {"生存", "创造", "冒险", "旁观"};
    // 游戏难度
    private final String[] gameDifficulty = {"和平", "简单", "普通", "困难"};

    // 文件路径
    private static String pro;

    private static Properties properties;

    // 获取文件路径
    public void PRO(String p) {
        pro = p;
    }

    // 初始化
    @FXML
    public void initialize() throws IOException {
        properties = new Properties();
        properties.load(new FileReader(pro));
        Platform.runLater(() -> {
            // 端口
            server_port.setText(properties.getProperty("server-port"));

            // 种子
            level_seed.setText(properties.getProperty("level-seed"));

            // 人数
            max_players.setText(properties.getProperty("max-players"));

            // 待机踢出
            player_idle_timeout.setText(properties.getProperty("player-idle-timeout"));

            // 下界
            yn(allow_nether, "allow-nether");

            // 玩家默认游戏模式
            yn(force_gamemode, "force-gamemode");

            // 白名单
            yn(white_list, "white-list");

            // 极限模式
            yn(hardcore, "hardcore");

            // 攻击队友
            yn(pvp, "pvp");

            // 命令方块
            yn(enable_command_block, "enable-command-block");

            // 正版验证
            yn(online_mode, "online-mode");

            // 生成村民
            yn(spawn_npcs, "spawn-npcs");

            // 生成动物
            yn(spawn_animals, "spawn-animals");

            // 生成敌对生物
            yn(spawn_monsters, "spawn-monsters");

            // 生成村庄
            yn(generate_structures, "generate-structures");

            // 世界类型
            level_type.getItems().addAll(worldType);
            if (properties.getProperty("level-type").equals("DEFAULT") || properties.getProperty("level-type").equals("default"))
                level_type.getSelectionModel().select(0);
            else if (properties.getProperty("level-type").equals("FLAT") || properties.getProperty("level-type").equals("flat"))
                level_type.getSelectionModel().select(1);
            else level_type.getSelectionModel().select(2);

            // 游戏模式
            gamemode.getItems().addAll(gameMode);
            if (properties.getProperty("gamemode").equals("0") || properties.getProperty("gamemode").equals("survival"))
                gamemode.getSelectionModel().select(0);
            else if (properties.getProperty("gamemode").equals("1") || properties.getProperty("gamemode").equals("creative"))
                gamemode.getSelectionModel().select(1);
            else if (properties.getProperty("gamemode").equals("2") || properties.getProperty("gamemode").equals("adventure"))
                gamemode.getSelectionModel().select(2);
            else gamemode.getSelectionModel().select(3);

            // 游戏难度
            difficulty.getItems().addAll(gameDifficulty);
            if (properties.getProperty("difficulty").equals("0") || properties.getProperty("difficulty").equals("peaceful"))
                difficulty.getSelectionModel().select(0);
            else if (properties.getProperty("difficulty").equals("1") || properties.getProperty("difficulty").equals("easy"))
                difficulty.getSelectionModel().select(1);
            else if (properties.getProperty("difficulty").equals("2") || properties.getProperty("difficulty").equals("normal"))
                difficulty.getSelectionModel().select(2);
            else difficulty.getSelectionModel().select(3);
        });
    }

    // 正常的输出信息 开启/关闭
    private void yn(ChoiceBox<String> c, String s) {
        c.getItems().addAll(yesNo);
        if (properties.getProperty(s).equals("true")) c.getSelectionModel().select(0);
        else c.getSelectionModel().select(1);
    }

    // 确认
    public void affirm() throws IOException {
        // 端口
        properties.setProperty("server-port", server_port.getText());

        // 种子
        properties.setProperty("level-seed", level_seed.getText());

        // 人数
        properties.setProperty("max-players", max_players.getText());

        // 待机踢出
        properties.setProperty("player-idle-timeout", player_idle_timeout.getText());

        // 下界
        ipt(allow_nether, "allow-nether");

        // 玩家默认游戏模式
        ipt(force_gamemode, "force-gamemode");

        // 白名单
        ipt(white_list, "white-list");

        // 极限模式
        ipt(hardcore, "hardcore");

        // 攻击队友
        ipt(pvp, "pvp");

        // 命令方块
        ipt(enable_command_block, "enable-command-block");

        // 正版验证
        ipt(online_mode, "online-mode");

        // 生成村民
        ipt(spawn_npcs, "spawn-npcs");

        // 生成动物
        ipt(spawn_animals, "spawn-animals");

        // 生成敌对生物
        ipt(spawn_monsters, "spawn-monsters");

        // 生成村庄
        ipt(generate_structures, "generate-structures");

        // 世界类型
        if (level_type.getValue().equals("默认")) {
            if (properties.getProperty("level-type").equals("LEGACY") || properties.getProperty("level-type").equals("DEFAULT") || properties.getProperty("level-type").equals("FLAT"))
                properties.setProperty("level-type", "DEFAULT");
            else properties.setProperty("level-type", "default");
        } else if (level_type.getValue().equals("超平坦")) {
            if (properties.getProperty("level-type").equals("LEGACY") || properties.getProperty("level-type").equals("DEFAULT") || properties.getProperty("level-type").equals("FLAT"))
                properties.setProperty("level-type", "FLAT");
            else properties.setProperty("level-type", "flat");
        } else {
            if (properties.getProperty("level-type").equals("LEGACY") || properties.getProperty("level-type").equals("DEFAULT") || properties.getProperty("level-type").equals("FLAT"))
                properties.setProperty("level-type", "LEGACY");
            else properties.setProperty("level-type", "legacy");
        }

        // 游戏模式
        if (gamemode.getValue().equals("生存")) {
            if (properties.getProperty("gamemode").equals("0") || properties.getProperty("gamemode").equals("1") || properties.getProperty("gamemode").equals("2") || properties.getProperty("gamemode").equals("3"))
                properties.setProperty("gamemode", "0");
            else properties.setProperty("gamemode", "survival");
        } else if (gamemode.getValue().equals("创造")) {
            if (properties.getProperty("gamemode").equals("0") || properties.getProperty("gamemode").equals("1") || properties.getProperty("gamemode").equals("2") || properties.getProperty("gamemode").equals("3"))
                properties.setProperty("gamemode", "1");
            else properties.setProperty("gamemode", "creative");
        } else if (gamemode.getValue().equals("冒险")) {
            if (properties.getProperty("gamemode").equals("0") || properties.getProperty("gamemode").equals("1") || properties.getProperty("gamemode").equals("2") || properties.getProperty("gamemode").equals("3"))
                properties.setProperty("gamemode", "3");
            else properties.setProperty("gamemode", "adventure");
        } else {
            if (properties.getProperty("gamemode").equals("0") || properties.getProperty("gamemode").equals("1") || properties.getProperty("gamemode").equals("2") || properties.getProperty("gamemode").equals("3"))
                properties.setProperty("gamemode", "3");
            else properties.setProperty("gamemode", "spectator");
        }
        
        // 游戏难度
        if (difficulty.getValue().equals("和平")) {
            if (properties.getProperty("difficulty").equals("0") || properties.getProperty("difficulty").equals("1") || properties.getProperty("difficulty").equals("2") || properties.getProperty("difficulty").equals("3"))
                properties.setProperty("difficulty", "0");
            else properties.setProperty("difficulty", "peaceful");
        } else if (difficulty.getValue().equals("简单")) {
            if (properties.getProperty("difficulty").equals("0") || properties.getProperty("difficulty").equals("1") || properties.getProperty("difficulty").equals("2") || properties.getProperty("difficulty").equals("3"))
                properties.setProperty("difficulty", "1");
            else properties.setProperty("difficulty", "easy");
        } else if (difficulty.getValue().equals("普通")) {
            if (properties.getProperty("difficulty").equals("0") || properties.getProperty("difficulty").equals("1") || properties.getProperty("difficulty").equals("2") || properties.getProperty("difficulty").equals("3"))
                properties.setProperty("difficulty", "3");
            else properties.setProperty("difficulty", "normal");
        } else {
            if (properties.getProperty("difficulty").equals("0") || properties.getProperty("difficulty").equals("1") || properties.getProperty("difficulty").equals("2") || properties.getProperty("difficulty").equals("3"))
                properties.setProperty("difficulty", "3");
            else properties.setProperty("difficulty", "hard");
        }

        // 提交到文本文件中
        properties.store(new FileOutputStream(pro), "");
        // 关闭窗口
        clo();
    }

    // 正常的输出信息 开启/关闭
    private void ipt(ChoiceBox<String> c, String s) {
        if (c.getValue().equals("开启")) properties.setProperty(s, "true");
        else properties.setProperty(s, "false");
    }


    // 关闭窗口
    public void clo() {
        Stage stage = (Stage) cl.getScene().getWindow();
        stage.close();
    }
}
