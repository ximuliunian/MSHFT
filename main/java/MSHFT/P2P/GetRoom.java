package MSHFT.P2P;

import MSHFT.IOJson;
import MSHFT.SystemPrint;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
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
    private TextArea textArea;

    // 开始连接
    public void texta() throws IOException {

        JSONObject jsonObject = new JSONObject((Map<String, Object>) list.get(0));
        jsonObject.put("PeerNode", biaoshi.getText());
        jsonObject.put("DstPort", Integer.parseInt(tcpdk.getText()));
        jsonObject.put("SrcPort", Integer.parseInt(bendi.getText()));
        // 更新数据
        new IOJson().inputP2P(map);

        // 把输出到控制台的内容输出到TextArea
        new SystemPrint().outPrint(textArea);
        Process process = Runtime.getRuntime().exec("cmd /c cd /d openp2p && openp2p.exe");
        Charset charset = Charset.forName("gbk");
        new SystemPrint().cmdPrint(process, charset);
    }
}
