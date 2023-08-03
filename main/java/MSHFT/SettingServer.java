package MSHFT;
/*
 * 服务器详细操作
 * */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;

public class SettingServer {
    @FXML   // MOD、IP地址、端口
    private TextField MOD, IP, port;
    @FXML   // 提示窗口
    private TextArea textArea;
    @FXML   // 删除按钮
    private Button del;
    @FXML   // 输入指令窗口
    private TextField instruction;

    // 版本
    private static String ver;
    // 文件夹名
    private static String FileServer;
    // 这个开关默认是开着的，当点击开始服务器的时候开关上，一些按钮不可使用
    private static boolean onOff = true;

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
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(ver + "/" + FileServer + "/server.properties"));
            port.setText(properties.getProperty("server-port"));
        } catch (IOException e) {
            port.setText("无文件");
        }
    }

    // 服务器输入信息
    private Process process;
    // 编码格式
    private Charset charset;

    @FXML   // 开启服务器
    public void Open() throws IOException {
        // 当开关为true的时候才能进行开启服务器
        if (onOff) {
            // 关闭开关
            onOff = false;

            // 更新一下最后启动时间
            Map<String, WorldData> map = JSON.parseObject(new IOJson().readJson("versionManagement.json"), new TypeReference<Map<String, WorldData>>() {
            });
            for (Map.Entry<String, WorldData> entry : map.entrySet()) {
                if (entry.getKey().equals(FileServer)) {
                    entry.getValue().setTailEnd(new NewServer().vdate());
                    new IOJson().inputJsonCF(map);
                }
            }
            // 把输出到控制台的内容输出到TextArea
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
            // 打开窗口
            Process process = Runtime.getRuntime().exec("cmd /c cd /d " + ver + "/" + FileServer + " && java -jar CatServer-" + ver + ".jar");
            this.process = process;
            Charset charset = Charset.forName("gbk");
            this.charset = charset;
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

    }

    // 关闭服务器
    public void Close() throws IOException {
        // 当开关关闭的时候才能进行开启关闭服务器
        if (!onOff) {
            OutputStream output = process.getOutputStream();
            output.write("stop\n".getBytes(charset));
            output.flush();
            // 开启开关
            onOff = true;
        }
    }

    // 打开服务器配置页
    public void ServerProperties() throws IOException {
        new SerProperties().PRO(ver + "/" + FileServer + "/server.properties");
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/FXML/SerProperties.fxml"))));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    // 添加MOD
    public void AddMod() throws IOException {
        Runtime.getRuntime().exec("cmd /c cd /d " + ver + "/" + FileServer + " && explorer mods");
    }

    // 打开世界文件夹
    public void AddWorld() throws IOException {
        Runtime.getRuntime().exec("cmd /c cd /d " + ver + "/" + FileServer + " && explorer world");
    }

    // 删除版本
    public void Del() throws IOException {
        // 当服务器没开的时候可以进行操作
        if (onOff) {
            // 二次确认
            new ConfirmDeletion().hint(ver, FileServer, (Stage) del.getScene().getWindow());

            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/FXML/ConfirmDeletion.fxml"))));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        }
    }

    // 回主页
    public void ReturnMain() throws IOException {
        if (onOff) {
            // 回退主界面
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/FXML/MainWindow.fxml"))));
            stage.setTitle("MSHFT");
            stage.setResizable(false);
            stage.getIcons().add(new Image("img/favicon.png"));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            Stage col = (Stage) del.getScene().getWindow();
            col.close();
        }
    }

    @FXML   // 提交指令
    public void submit() throws IOException {
        if (!onOff && instruction.getText() != null) {
            OutputStream output = process.getOutputStream();
            output.write((instruction.getText() + "\n").getBytes(charset));
            output.flush();
            instruction.setText("");
        }
    }

}