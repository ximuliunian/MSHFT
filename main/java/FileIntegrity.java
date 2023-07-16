import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

// 初始化并检查核心与配置文件的完整性
public class FileIntegrity {
    // 检查核心和配置文件是否存在不存在抛出报错提示并尝试进行修复

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

    // 判断版本配置文件
    void ErrorVM() throws InterruptedException {
        ErrorExists errorEx = new ErrorExists();
        if (errorEx.Cf("versionManagement.json")) {
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
            Map<String, List<WorldData>> map = new HashMap<>();

            map.put("1.12.2", new ArrayList<>());
            map.put("1.16.5", new ArrayList<>());
            map.put("1.18.2", new ArrayList<>());

            if (new IOJson().initJsonCf(map) == 1) {
                textArea.appendText("\n恢复成功");
                vn.setText("正常");
            } else {
                textArea.appendText("\n恢复失败");
            }
        }
        Thread.sleep(1000);
    }

    // 调用ErrorHx类来判断并输出核心是否存在
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

    // 尝试恢复核心
    void recoverHx(String bb) throws InterruptedException {
        textArea.appendText("\n正在尝试恢复");
        Thread.sleep(1000);
        // 拿到json数据
        String json = new IOJson().readJson();
        // 反序列化map
        Map<String, List<WorldData>> map = JSON.parseObject(json, new TypeReference<Map<String, List<WorldData>>>() {
        });
        for (Map.Entry<String, List<WorldData>> entry : map.entrySet()) {
            if (bb.equals(entry.getKey()))
                if (!entry.getValue().isEmpty()) {
                    // 尝试恢复核心
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        File f1 = new File(bb + "/" + entry.getValue().get(i).getMkdir());
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
                            break;
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
                } else {
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

    // 判断隔离版本文件夹
    void ErrorMkdir() {
        // 判断文件夹 - 1.12.2
        File v1_12 = new File("1.12.2");
        if (v1_12.exists()) Mkdir("1.12.2");

        // 判断文件夹 - 1.16.5
        File v1_16 = new File("1.16.5");
        if (!v1_16.exists()) Mkdir("1.16.5");

        // 判断文件夹 - 1.18.2
        File v1_18 = new File("1.18.2");
        if (!v1_18.exists()) Mkdir("1.18.2");
    }

    // 生成指定文件夹
    private void Mkdir(String version) {
        File file = new File(version);
        if (file.mkdir()) {
            textArea.appendText("\n创建" + version + "文件夹成功");
        }
    }


    // 点击按钮之后进行打印输出
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

                            // 检查版本核心是否缺失
                            textArea.appendText("\n检查配置核心文件是否正常");
                            Thread.sleep(1000);
                            ErrorHx();

                            // 检查版本隔离文件夹是否存在
                            textArea.appendText("\n检查隔离文件夹是否正常");
                            Thread.sleep(1000);
                            ErrorMkdir();
                            textArea.appendText("\n隔离文件夹正常");

                        } catch (InterruptedException ex) {
                            textArea.appendText("\n出错了");
                        }
                    }
                }.start();
            }
        });
    }
}

// 判断核心
class ErrorExists {
    // 判断核心
    boolean Hx(String hx) {
        File versionHx = new File("server/CatServer-" + hx + ".jar");
        return versionHx.exists();
    }

    // 判断文件
    boolean Cf(String file) {
        File cf = new File(file);
        return cf.exists();
    }
}