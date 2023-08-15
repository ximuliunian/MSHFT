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

/**
 * @author: 曦暮流年
 * @Description: 修改Token和唯一标识
 * @date: 2023/8/11 上午 10:09
 */
public class ConfigP2p {
    @FXML
    private TextField token;
    @FXML
    private TextField biaoshi;

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 从JSON读取把数据显示到屏幕
     * @date: 2023/8/11 上午 10:09
     */
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = JSON.parseObject(new IOJson().readJson("./openp2p/config.json"), new TypeReference<Map<String, Object>>() {
                });
                JSONObject jsonObject = new JSONObject(map);
                token.setText(String.valueOf(jsonObject.getJSONObject("network").get("Token")));
                biaoshi.setText(String.valueOf(jsonObject.getJSONObject("network").get("Node")));
            }
        });
    }


    @FXML
    private Button yes;

    /**
     * @return: void
     * @author: 曦暮流年
     * @description: 把更新数据输入到config.json里面
     * @date: 2023/8/11 上午 10:10
     */

    public void Yes() {
        Map<String, Object> map = JSON.parseObject(new IOJson().readJson("./openp2p/config.json"), new TypeReference<Map<String, Object>>() {
        });
        // 输入新数据
        JSONObject jsonObject = new JSONObject(map);
        // 因为token太长所以使用BigInteger
        jsonObject.getJSONObject("network").put("Token", new BigInteger(token.getText()));
        jsonObject.getJSONObject("network").put("Node", biaoshi.getText());
        // 更新数据
        if (new IOJson().inputP2P(map) == 1) {
            Stage stage = (Stage) yes.getScene().getWindow();
            stage.close();
        }
    }

}
