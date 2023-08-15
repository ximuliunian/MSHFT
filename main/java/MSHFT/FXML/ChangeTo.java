package MSHFT.FXML;

import MSHFT.IOJson;
import MSHFT.WorldData;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Map;

/**
 * @author: 曦暮流年
 * @Description: 更改表格数据
 * @date: 2023/8/11 上午 09:56
 */
public class ChangeTo {
    // 开始时间

    private static WorldData worldData;

    /**
     * @param data: 服务器单条数据
     * @return: void
     * @author: 曦暮流年
     * @description: 进行传值
     * @date: 2023/8/11 上午 09:59
     */
    public void ChTo(WorldData data) {
        ChangeTo.worldData = data;
    }

    @FXML   // 服务器名、简介、提示信息
    private TextField worldName, briefIntroduction;
    @FXML   // 确认按钮
    private Button button;

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 初始化把更改之前的数据打印在上面
     * @date: 2023/8/11 上午 10:00
     */
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

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 获取输入数据然后更改数据
     * @date: 2023/8/11 上午 10:00
     */

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

