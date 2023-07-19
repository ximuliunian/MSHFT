/*
 * 服务器详细操作
 * */


import javafx.fxml.FXML;
import javafx.scene.control.TextField;

// 启动、重启、关闭……服务器
public class SettingServer {
    @FXML   // MOD、光影、IP地址、端口
    private TextField MOD, shadow, IP, port;

    private static String FileServer;

    // 获取服务器目录
    public void setFileServerWD(String s) {
        // 获取JSON数据
        FileServer = s;
        System.out.println(FileServer);
    }

}