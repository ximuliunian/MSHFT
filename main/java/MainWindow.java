// 新建服务器

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sun.javafx.scene.control.ReadOnlyUnbackedObservableList;
import com.sun.org.apache.xerces.internal.xs.datatypes.ObjectList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class MainWindow {
    @FXML   // 表格
    private TableView<WorldData> tableView;
    @FXML   // 服务器名
    private TableColumn<WorldData, String> worldName;
    @FXML   // 简介
    private TableColumn<WorldData, String> briefIntroduction;
    @FXML   // 创建日期
    private TableColumn<WorldData, String> startDate;
    @FXML   // 最后运行时间
    private TableColumn<WorldData, String> tailEnd;
    @FXML   //版本
    private TableColumn<WorldData, String> version;
    @FXML   // 编辑
    private TableColumn<WorldData, Button> redact;
    @FXML   // 配置
    private TableColumn<WorldData, Button> config;


    // 把json内容输送到表里面
    public void updata() {
        ObservableList<WorldData> list = FXCollections.observableArrayList();
        Map<String, List<WorldData>> map = JSON.parseObject(new IOJson().readJson(), new TypeReference<Map<String, List<WorldData>>>() {
        });
        for (Map.Entry<String, List<WorldData>> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                list.addAll(entry.getValue().get(i));
            }
        }
        // 进行列绑定
        worldName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WorldData, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<WorldData, String> param) {
                return new SimpleStringProperty(param.getValue().getWorldName());
            }
        });
        briefIntroduction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WorldData, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<WorldData, String> param) {
                return new SimpleStringProperty(param.getValue().getBriefIntroduction());
            }
        });
        startDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WorldData, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<WorldData, String> param) {
                return new SimpleStringProperty(param.getValue().getStartDate());
            }
        });
        tailEnd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WorldData, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<WorldData, String> param) {
                return new SimpleStringProperty(param.getValue().getTailEnd());
            }
        });
        version.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<WorldData, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<WorldData, String> param) {
                return new SimpleStringProperty(param.getValue().getVersion());
            }
        });
        tableView.setItems(list);
    }


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
        Pane root = FXMLLoader.load(getClass().getResource("/FXML/FileIntegrity.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("文件完整性");
        stage.getIcons().add(new Image("favicon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}
