package MSHFT.FXML;

import MSHFT.IOJson;
import MSHFT.WorldData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 曦暮流年
 * @Description: 检查文件的完整性
 * @date: 2023/8/11 上午 12:15
 */
public class FileIntegrity {
    @FXML
    private TextArea textArea;
    @FXML
    private TextField v12;
    @FXML
    private TextField v16;
    @FXML
    private TextField v18;
    @FXML
    private TextField vn;
    @FXML
    private TextField P2P;

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 判断版本配置文件是否存在
     * @date: 2023/8/11 上午 09:51
     */
    void ErrorVM() throws InterruptedException {
        if (new File("versionManagement.json").exists()) {
            textArea.appendText("\n版本配置文件正常");
            vn.setText("正常");
        } else {
            //抛出错误并创建新的配置文件（此配置文件为新的只有最基本的数据）
            textArea.appendText("\n版本配置文件缺失");
            Thread.sleep(1000);
            vn.setText("缺失");
            textArea.appendText("\n尝试恢复配置文件");
            Thread.sleep(1000);

            // 生成新的版本配置文件
            Map<String, WorldData> map = new HashMap<>();


            if (new IOJson().initJsonCf() == 1) {
                textArea.appendText("\n恢复成功");
                vn.setText("正常");
            } else {
                textArea.appendText("\n恢复失败");
            }
        }
        Thread.sleep(1000);
    }

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 调用ErrorHx类来判断并输出核心是否存在
     * @date: 2023/8/11 上午 09:52
     */
    void ErrorHx() throws InterruptedException {
        ErrorExists errorEx = new ErrorExists();

        if (errorEx.Hx("1.12.2")) {
            textArea.appendText("\n核心1.12.2正常");
            v12.setText("正常");
        } else {
            textArea.appendText("\n核心1.12.2缺失");
            //尝试进行恢复核心
            recoverHx("1.12.2");
        }
        Thread.sleep(1000);


        if (errorEx.Hx("1.16.5")) {
            textArea.appendText("\n核心1.16.5正常");
            v16.setText("正常");
        } else {
            textArea.appendText("\n核心1.16.5缺失");
            //尝试进行恢复核心
            recoverHx("1.16.5");
        }
        Thread.sleep(1000);

        if (errorEx.Hx("1.18.2")) {
            textArea.appendText("\n核心1.18.2正常");
            v18.setText("正常");
        } else {
            textArea.appendText("\n核心1.18.2缺失");
            //尝试进行恢复核心
            recoverHx("1.18.2");
        }
        Thread.sleep(1000);
    }

    /**
     * @param bb: 恢复的版本号
     * @return: void
     * @author: 曦暮流年
     * @description: 尝试恢复核心
     * @date: 2023/8/11 上午 09:52
     */
    void recoverHx(String bb) throws InterruptedException {
        Thread.sleep(1000);
        textArea.appendText("\n正在尝试恢复");
        Thread.sleep(1000);
        // 拿到json数据
        String json = new IOJson().readJson("versionManagement.json");
        // 反序列化map
        Map<String, WorldData> map = JSON.parseObject(json, new TypeReference<Map<String, WorldData>>() {
        });
        for (Map.Entry<String, WorldData> entry : map.entrySet()) {
            // 先找到要恢复的版本然后判断是否有那个文件夹
            if (bb.equals(entry.getValue().getVersion())) {
                // 尝试恢复核心
                File f1 = new File(bb + "/" + entry.getKey());
                File f2 = new File(f1 + "/CatServer-" + bb + ".jar");
                if (!f1.exists() && !f2.exists()) continue;
                try {
                    Files.copy(f2.toPath(), new File("server/CatServer-" + bb + ".jar").toPath());
                    textArea.appendText("\n恢复成功");
                    if (bb.equals("1.12.2")) {
                        v12.setText("正常");
                    } else if (bb.equals("1.16.5")) {
                        v16.setText("正常");
                    } else {
                        v18.setText("正常");
                    }
                    return;
                } catch (IOException e) {
                    if (bb.equals("1.12.2")) {
                        v12.setText("缺失");
                    } else if (bb.equals("1.16.5")) {
                        v16.setText("缺失");
                    } else {
                        v18.setText("缺失");
                    }
                    textArea.appendText("\n恢复失败请前往https://catmc.org下载" + bb + "版本");
                }
            }
        }
        // 正常循环完成之后代表没有核心，这时候给出错误提示
        if (bb.equals("1.12.2")) {
            v12.setText("缺失");
        } else if (bb.equals("1.16.5")) {
            v16.setText("缺失");
        } else {
            v18.setText("缺失");
        }
        textArea.appendText("\n恢复失败请前往https://catmc.org下载" + bb + "版本");
    }

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 判断隔离版本文件夹
     * @date: 2023/8/11 上午 09:53
     */
    void ErrorMkdir() {
        // 判断文件夹 - server
        File server = new File("server");
        if (!server.exists()) Mkdir("server");

        // 判断文件夹 - 1.12.2
        File v1_12 = new File("1.12.2");
        if (!v1_12.exists()) Mkdir("1.12.2");

        // 判断文件夹 - 1.16.5
        File v1_16 = new File("1.16.5");
        if (!v1_16.exists()) Mkdir("1.16.5");

        // 判断文件夹 - 1.18.2
        File v1_18 = new File("1.18.2");
        if (!v1_18.exists()) Mkdir("1.18.2");

        // 判断文件夹 - openP2P
        File openP2P = new File("openP2P");
        if (!openP2P.exists()) Mkdir("openP2P");
    }

    /**
     * @param version: 文件夹名称
     * @return: void
     * @author: 曦暮流年
     * @description: 生成指定文件夹
     * @date: 2023/8/11 上午 09:53
     */
    private void Mkdir(String version) {
        File file = new File(version);
        if (file.mkdir()) {
            textArea.appendText("\n创建" + version + "文件夹成功");
        }
    }


    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 点击按钮之后进行打印输出到TextArea
     * @date: 2023/8/11 上午 09:54
     */

    public void file() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // 创建一个线程进行打印
                new Thread() {
                    @Override
                    public void run() {
                        // 打印之前先清空打印台
                        textArea.setText("");
                        try {
                            // 检查版本配置文件是否正常
                            textArea.appendText("检查版本配置文件是否正常");
                            Thread.sleep(1000);
                            ErrorVM();

                            // 检查联机文件是否缺失是否缺失
                            textArea.appendText("\n检查联机文件是否正常");
                            if (new File("./openp2p/openp2p.exe").exists()) {
                                textArea.appendText("\n联机文件正常");
                                P2P.setText("正常");
                            } else {
                                textArea.appendText("\n联机文件缺失");
                                P2P.setText("缺失");
                            }
                            Thread.sleep(1000);

                            // 检查版本隔离文件夹是否存在
                            textArea.appendText("\n检查文件夹是否正常");
                            Thread.sleep(1000);
                            ErrorMkdir();
                            textArea.appendText("\n隔离文件夹正常");

                            // 检查版本核心是否缺失
                            textArea.appendText("\n检查配置核心文件是否正常");
                            Thread.sleep(1000);
                            ErrorHx();
                            textArea.appendText("\n检查完成……");
                        } catch (InterruptedException ex) {
                            textArea.appendText("\n出错了");
                        }
                    }
                }.start();
            }
        });
    }
}

/**
 * @author: 曦暮流年
 * @Description: 判断核心是否存在
 * @date: 2023/8/11 上午 09:55
 */
class ErrorExists {
    boolean Hx(String hx) {
        File versionHx = new File("server/CatServer-" + hx + ".jar");
        return versionHx.exists();
    }
}