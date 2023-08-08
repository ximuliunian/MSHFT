package MSHFT;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    // GUI
    @Override
    public void start(Stage stage) throws Exception {
        // 主窗口
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/FXML/MainWindow.fxml"))));
        stage.setTitle("MSHFT");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        stage.getIcons().add(new Image("img/favicon.png"));
        stage.setResizable(false);
        stage.show();
    }
}
