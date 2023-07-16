import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    // GUI
    @Override
    public void start(Stage stage) throws Exception {
        // 窗口参数
        Pane root = FXMLLoader.load(getClass().getResource("/FXML/MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("MSHFT");
        stage.getIcons().add(new Image("favicon.png"));
        stage.setResizable(false);
        stage.show();
    }
}
