import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
        stage.getIcons().add(new Image("favicon.png"));
        stage.setResizable(false);
        stage.show();
    }
}
