package MSHFT.P2P;

// 进入房间

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GoGame {
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
}
