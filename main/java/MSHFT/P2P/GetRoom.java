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
import java.util.List;
import java.util.Map;

public class GetRoom {
    @FXML
    private TextField biaoshi, tcpdk, bendi;
    Map<String, Object> map = JSON.parseObject(new IOJson().readJson("./openp2p/config.json"), new TypeReference<Map<String, Object>>() {
    });
    List<Object> list = (List<Object>) map.get("apps");

    // 初始化
    public void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

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
