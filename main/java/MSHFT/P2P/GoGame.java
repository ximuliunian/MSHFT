package MSHFT.P2P;

// 进入房间

import MSHFT.IOJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
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

public class GoGame {
    // 初始化
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

    // 更新版本
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

    // 进入房间
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
