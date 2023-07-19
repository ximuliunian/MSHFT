import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

// 服务器创建与版本隔离
public class NewServer {
    @FXML
    private TextField worldName;
    @FXML
    private TextField briefIntroduction;
    @FXML
    private ToggleGroup version;
    @FXML
    private TextField textField;
    @FXML
    private Button colse;


    // 选择要创建服务器的版本
    public void nw_bb() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // 选择要创建的版本
                new Thread() {
                    @Override
                    public void run() {
                        RadioButton radioButton = (RadioButton) version.getSelectedToggle();
                        String v = (String) radioButton.getUserData();
                        switch (v) {
                            case "v12":
                                nw_cj("1.12.2");
                                break;

                            case "v16":
                                nw_cj("1.16.5");
                                break;

                            case "v18":
                                nw_cj("1.18.2");
                                break;
                        }
                    }
                }.start();
            }
        });
    }

    // 创建服务器
    private void nw_cj(String vbt) {
        WorldData wd = new WorldData();
        // 输入名称、简介
        if (worldName.getText().equals("")) {
            wd.setWorldName("服务器");
        } else {
            wd.setWorldName(worldName.getText());
        }
        if (briefIntroduction.getText().equals("")) {
            wd.setBriefIntroduction("阿巴阿巴~~~");
        } else {
            wd.setBriefIntroduction(briefIntroduction.getText());
        }
        wd.setVersion(vbt);
        wd.setStartDate(vdate());
        wd.setTailEnd(vdate());

        // 检查一下版本文件夹是否存在
        FileIntegrity fileIntegrity = new FileIntegrity();
        fileIntegrity.ErrorMkdir();

        /*
         * 在对应的版本文件夹里面创建一个隔离版本文件夹
         * 文件夹命名规则为（世界名 + 时间）最大限度保证不重复
         * */
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String path = "Server." + format.format(new Date());
        File f = new File(vbt + "/" + path);
        if (!f.mkdir()) {
            textField.setText("创建隔离文件：失败");
        } else {
            textField.setText("创建隔离文件：成功");

            // 把核心从 server 中复制到新建的隔离文件夹里面
            File h = new File("server/CatServer-" + vbt + ".jar");
            textField.setText("正在移动核心");
            try {
                // 把核心核心文件夹里面复制一份到新创建的隔离文件夹
                File r = new File(f + "/CatServer-" + vbt + ".jar");
                Files.copy(h.toPath(), r.toPath());
                textField.setText("核心移动成功");
                textField.setText(wd.getWorldName() + "创建完成");

                // json信息进行更新
                Map<String, WorldData> map = JSON.parseObject(new IOJson().readJson(), new TypeReference<Map<String, WorldData>>() {
                });
                map.put(path, wd);

                // 版本信息更新到配置文件里面
                IOJson ioJson = new IOJson();
                ioJson.inputJsonCF(map);

                // 关闭当前页面
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(300);
                            Stage stage = (Stage) colse.getScene().getWindow();
                            stage.close();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });


            } catch (IOException e) {
                f.delete();
                textField.setText("核心移动失败");
            }
        }
    }

    // 输出现在时间
    String vdate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        return format.format(new Date());
    }
}