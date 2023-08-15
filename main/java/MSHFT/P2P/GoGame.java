package MSHFT.P2P;

// 进入房间

import MSHFT.IOJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 曦暮流年
 * @Description: 告示台并更新信息和进入房间
 * @date: 2023/8/11 上午 12:09
 */
public class GoGame {
    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 初始化内容，判断apps是否为空，空的话创建对象进行输入
     * @date: 2023/8/11 上午 12:11
     */
    public void initialize() {
        Map<String, Object> map = JSON.parseObject(new IOJson().readJson("./openp2p/config.json"), new TypeReference<Map<String, Object>>() {
        });
        List<Object> list = (List<Object>) map.get("apps");
        // 判断list是否为空，如果是空的话，则新创建JSON对象
        if (list == null) {
            List<Object> list1 = new ArrayList<>();
            Apps apps = new Apps();
            apps.setAppName("qwq");
            apps.setProtocol("tcp");
            apps.setSrcPort(25565);
            apps.setPeerNode("");
            apps.setDstPort(0);
            apps.setDstHost("localhost");
            apps.setPeerUser("");
            apps.setEnabled(1);
            list1.add(apps);
            map.put("apps", list1);
            new IOJson().inputP2P(map);
        }
    }

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 更新信息
     * @date: 2023/8/11 上午 12:13
     */
    public void date() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/P2P/ConfigP2p.fxml"))));
        stage.getIcons().add(new Image("img/favicon.png"));
        stage.setTitle("更改P2P信息");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private Button get;

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 进入联机房间
     * @date: 2023/8/11 上午 12:14
     */
    public void getRoom() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/P2P/GetRoom.fxml"))));
        stage.getIcons().add(new Image("img/favicon.png"));
        stage.setTitle("进入房间");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        Stage cl = (Stage) get.getScene().getWindow();
        cl.close();
    }
}
