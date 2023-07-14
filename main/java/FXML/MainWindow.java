package FXML;
// 新建服务器

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindow {
    // 点击打开新建服务器页面
    public void newSer() throws IOException {
        Stage stage = new Stage();
        Pane root = FXMLLoader.load(getClass().getResource("/FXML/NewServer.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("新建服务器");
        stage.getIcons().add(new Image("favicon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }

    // 点击打开文件完整性
    public void cFile() throws IOException {
        Stage stage = new Stage();
        Pane root = FXMLLoader.load(getClass().getResource("/FXML/ConfigFiles.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("文件完整性");
        stage.getIcons().add(new Image("favicon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}
