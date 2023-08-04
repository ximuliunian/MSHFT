package MSHFT.P2P;

import MSHFT.IOJson;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetRoom {
    @FXML
    private TextField biaoshi, tcpdk, bendi;


    // 初始化
    public void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = JSON.parseObject(new IOJson().readJson("./openp2p/config.json"), new TypeReference<Map<String, Object>>() {
                });
                List<Object> list = (List<Object>) map.get("apps");
                // 判断list是否为空，如果是空的话，则新创建JSON对象
                if (list == null) {
                    List<Object> list1 = new ArrayList<>();
                    Apps apps = new Apps();
                    apps.setAppName("qwq");
                    apps.setProtocol("tcp");
                    apps.setSrcPort(0);
                    apps.setPeerNode("");
                    apps.setDstPort(0);
                    apps.setDstHost("localhost");
                    apps.setPeerUser("");
                    apps.setEnabled(1);
                    list1.add(apps);
                    map.put("apps", list1);
                    new IOJson().inputP2P(map);
                }
                // 显示到屏幕上
                JSONObject jsonObject = new JSONObject((Map<String, Object>) list.get(0));
                biaoshi.setText(String.valueOf(jsonObject.get("PeerNode")));
                tcpdk.setText(String.valueOf(jsonObject.get("DstPort")));
                bendi.setText(String.valueOf(jsonObject.get("SrcPort")));
            }
        });

    }

    @FXML
    private Button lianjie;

    // 开始连接
    public void texta() throws IOException {
        Map<String, Object> map = JSON.parseObject(new IOJson().readJson("./openp2p/config.json"), new TypeReference<Map<String, Object>>() {
        });
        List<Object> list = (List<Object>) map.get("apps");
        JSONObject jsonObject = new JSONObject((Map<String, Object>) list.get(0));
        jsonObject.put("PeerNode", biaoshi.getText());
        jsonObject.put("DstPort", Integer.parseInt(tcpdk.getText()));
        jsonObject.put("SrcPort", Integer.parseInt(bendi.getText()));
        // 更新数据
        new IOJson().inputP2P(map);
        // 打开窗口
        Runtime.getRuntime().exec("cmd /c cd /d openp2p && openp2p.exe");
        Stage stage = (Stage) lianjie.getScene().getWindow();
        stage.close();
    }
}
