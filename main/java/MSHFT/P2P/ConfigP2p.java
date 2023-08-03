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

import java.math.BigInteger;
import java.util.Map;

public class ConfigP2p {
    @FXML
    private TextField token;
    @FXML
    private TextField biaoshi;
    @FXML
    private TextField tcpdk;

    // 初始化
    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = JSON.parseObject(new IOJson().readJson("./openp2p/config.json"), new TypeReference<Map<String, Object>>() {
                });
                JSONObject jsonObject = new JSONObject(map);
                token.setText(String.valueOf(jsonObject.getJSONObject("network").get("token")));
                biaoshi.setText(String.valueOf(jsonObject.getJSONObject("network").get("node")));
                tcpdk.setText(String.valueOf(jsonObject.getJSONObject("network").get("tCPPort")));
            }
        });
    }


    // 更改完成之后点击确定
    @FXML
    private Button yes;

    public void Yes() {
        Map<String, Object> map = JSON.parseObject(new IOJson().readJson("./openp2p/config.json"), new TypeReference<Map<String, Object>>() {
        });
        // 输入新数据
        JSONObject jsonObject = new JSONObject(map);
        // 因为token太长所以使用BigInteger
        jsonObject.getJSONObject("network").put("token", new BigInteger(token.getText()));
        jsonObject.getJSONObject("network").put("tCPPort", Integer.parseInt(tcpdk.getText()));
        jsonObject.getJSONObject("network").put("node", biaoshi.getText());
        // 更新数据
        if (new IOJson().inputP2P(map) == 1) {
            Stage stage = (Stage) yes.getScene().getWindow();
            stage.close();
        }
    }

}
