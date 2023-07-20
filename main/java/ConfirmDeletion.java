import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;


// 二次确认操作
public class ConfirmDeletion {
    @FXML
    private Button cNo;

    private static String cVer, cFileServer;
    private static Stage del;

    public void hint(String V, String F, Stage d) {
        cVer = V;
        cFileServer = F;
        del = d;
    }

    // 点击正确是
    public void Yes() throws IOException {
        // 删除JSON里面的内容
        Map<String, WorldData> map = JSON.parseObject(new IOJson().readJson(), new TypeReference<Map<String, WorldData>>() {
        });
        map.remove(cFileServer);
        new IOJson().inputJsonCF(map);

        // 删除之前要解除所有占用

        // 删除文件夹及以下内容
        deleteFile(new File(cVer + "/" + cFileServer));

        // 回到主页
        ReturnMain();
    }

    // 删除文件夹以及文件里面的内容
    private void deleteFile(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFile(f);
                } else {
                    f.delete();
                }
            }
        }
        file.delete();
    }

    public void ReturnMain() throws IOException {
        // 关闭弹窗的同时关闭服务器操作界面
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/FXML/MainWindow.fxml"))));
        stage.setTitle("MSHFT");
        stage.getIcons().add(new Image("favicon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        Stage col = (Stage) cNo.getScene().getWindow();
        col.close();
        Stage col2 = del;
        col2.close();
    }


    // 点击取消显示
    public void No() {
        Stage stage = (Stage) cNo.getScene().getWindow();
        stage.close();
    }
}
