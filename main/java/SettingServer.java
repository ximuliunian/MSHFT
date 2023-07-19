/*
 * 服务器详细操作
 * */


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.InetAddress;
import java.nio.charset.Charset;

public class SettingServer {
    @FXML   // MOD、IP地址、端口
    private TextField MOD, IP, port;
    @FXML   // 提示窗口
    private TextArea textArea;
    @FXML   // 输入指令窗口
    private TextField instruction;
    @FXML   // 提交按钮
    private Button submit;


    private static String ver;
    private static String FileServer;

    // 获取服务器目录
    public void setFileServerWD(String v, String server) {
        // 获取文件路径
        ver = v;
        FileServer = server;
    }

    // 数据初始化
    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Mod();
                Ip();
                Port();
            }
        });
    }

    // MOD的数量
    private void Mod() {
        File file = new File(ver + "/" + FileServer + "/mods");
        if (file.exists()) {
            int mod = 0;
            for (File file1 : file.listFiles()) {
                if (file1.isFile()) mod++;
            }
            MOD.setText(String.valueOf(mod));
        } else MOD.setText("好像没这个文件");
    }

    // 获取本机IP
    private void Ip() {
        InetAddress ia = null;
        try {
            ia = ia.getLocalHost();
            IP.setText(ia.getHostAddress());
        } catch (Exception e) {
            IP.setText("失败");
        }
    }

    // 获取服务器端口
    private void Port() {
        port.setText("正在努力中");
    }

    @FXML   // 开启服务器
    public void Open() throws IOException {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                String text = String.valueOf((char) b);
                Platform.runLater(() -> {
                    textArea.appendText(text);
                });
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                String s = new String(b, off, len);
                Platform.runLater(() -> textArea.appendText(s));
            }
        }, true));
        System.setErr(System.out);
        // 执行启动命令
        Process process = Runtime.getRuntime().exec("cmd /c cd /d " + ver + "/" + FileServer + " && java -jar CatServer-" + ver + ".jar");
        Charset charset = Charset.forName("gbk");
        new Thread(() -> {
            try (InputStreamReader reader = new InputStreamReader(process.getInputStream(), charset)) {
                int read;
                while ((read = reader.read()) != -1) {
                    System.out.print((char) read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // 关闭服务器
    public void Close() throws IOException {
    }

    @FXML   // 提交指令
    public void submit() {

    }

}