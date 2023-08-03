package MSHFT;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Map;


public class ChangeTo {
    // 开始时间

    private static WorldData worldData;

    // 定义一个用来传值的方法（千万不要使用类的构造方法，千万不要）
    public void ChTo(WorldData data) {
        ChangeTo.worldData = data;
    }

    @FXML   // 服务器名、简介、提示信息
    private TextField worldName, briefIntroduction;
    @FXML   // 确认按钮
    private Button button;

    // 初始化把更改之前的数据打印在上面
    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                worldName.setText(worldData.getWorldName());
                briefIntroduction.setText(worldData.getBriefIntroduction());
            }
        });

    }

    // 更改数据
    @FXML
    public void change() throws InterruptedException {
        // 先找到要更改的那条json数据
        Map<String, WorldData> map = JSON.parseObject(new IOJson().readJson("versionManagement.json"), new TypeReference<Map<String, WorldData>>() {
        });
        for (Map.Entry<String, WorldData> entry : map.entrySet()) {
            if (entry.getValue().getStartDate().equals(worldData.getStartDate())) {
                // 更改新的数据
                entry.getValue().setWorldName(worldName.getText());
                entry.getValue().setBriefIntroduction(briefIntroduction.getText());

                // 判断是否成功更改界面是的话关闭界面
                if (new IOJson().inputJsonCF(map) == 1) {
                    Stage stage = (Stage) button.getScene().getWindow();
                    Thread.sleep(300);
                    stage.close();
                }
                break;
            }
        }
    }
}

