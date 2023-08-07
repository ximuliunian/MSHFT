package MSHFT;
// 新建服务器

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MainWindow {
    @FXML   // 表格
    private TableView<WorldData> tableView;
    @FXML   // 服务器名、简介、创建日期、最后运行时间、版本
    private TableColumn<WorldData, String> worldName, briefIntroduction, startDate, tailEnd, version;
    @FXML
    private Button SC;

    //初始化表格内容
    @FXML
    public void initialize() throws InterruptedException, IOException {
        List<WorldData> list = WD();
        tableView.setItems(FXCollections.observableArrayList(list));
        worldName.setCellValueFactory(new PropertyValueFactory<>("worldName"));
        briefIntroduction.setCellValueFactory(new PropertyValueFactory<>("briefIntroduction"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tailEnd.setCellValueFactory(new PropertyValueFactory<>("tailEnd"));
        version.setCellValueFactory(new PropertyValueFactory<>("version"));

        // 在根目录创建P2P联机文件夹
        File file = new File("openp2p");
        if (!file.exists()) file.mkdir();
        // 首先判断联机文件是否存在
        if (new File(file + "/openp2p.exe").exists())
            // 判断config.json这个文件是否够存在，不存在创建
            if (!new File(file + "/config.json").exists()) {
                new File(file + "/config.json").createNewFile();
                new IOJson().initP2P();
            }

        // 禁止拖拽列
        tableView.getColumns().addListener(new ListChangeListener() {
            @Override
            public void onChanged(Change change) {
                change.next();
                if (change.wasReplaced()) {
                    tableView.getColumns().clear();
                    tableView.getColumns().addAll(worldName, briefIntroduction, startDate, tailEnd, version);
                }
            }
        });
    }

    // 从json文件中读取
    private List<WorldData> WD() {
        // 先判断json文件是否存在不存在创建配置文件
        if (!new File("versionManagement.json").exists()) new FileIntegrity().creationCF();

        List<WorldData> list = new ArrayList<>();
        Map<String, WorldData> map = JSON.parseObject(new IOJson().readJson("versionManagement.json"), new TypeReference<Map<String, WorldData>>() {
        });
        // 把所有数据都写入集合
        for (Map.Entry<String, WorldData> entry : map.entrySet()) {
            WorldData data = new WorldData();
            data.setWorldName(entry.getValue().getWorldName());
            data.setBriefIntroduction(entry.getValue().getBriefIntroduction());
            data.setStartDate(entry.getValue().getStartDate());
            data.setTailEnd(entry.getValue().getTailEnd());
            data.setVersion(entry.getValue().getVersion());
            list.add(data);
        }
        return list;
    }

    // 打开服务器操作页面并关闭当前页面
    public void SerCon() throws IOException {
        // 获取点击的行
        WorldData data = tableView.getSelectionModel().getSelectedItem();
        if (data != null) {
            Map<String, WorldData> map = JSON.parseObject(new IOJson().readJson("versionManagement.json"), new TypeReference<Map<String, WorldData>>() {
            });
            for (Map.Entry<String, WorldData> entry : map.entrySet()) {
                // 找到跟值一样的数据
                if (entry.getValue().getStartDate().equals(data.getStartDate())) {
                    new SettingServer().setFileServerWD(entry.getValue().getVersion(), entry.getKey());
                }
            }
            Open("/FXML/SettingServer.fxml", "服务器操作");
            Stage col = (Stage) SC.getScene().getWindow();
            col.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("请先选择服务器");
            alert.show();
        }
    }

    // 打开更改信息页面
    public void Change() throws IOException {
        WorldData data = tableView.getSelectionModel().getSelectedItem();
        if (data != null) {
            new ChangeTo().ChTo(data);
            Open("/FXML/ChangeTo.fxml", "更改信息");
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("请先选择服务器");
            alert.show();
        }
    }

    // 点击打开新建服务器页面
    public void newSer() throws IOException {
        Open("/FXML/NewServer.fxml", "新建服务器");
    }

    // 点击打开文件完整性
    public void cFile() throws IOException {
        Open("/FXML/FileIntegrity.fxml", "文件完整性");
    }

    @FXML
    private Button get;

    // 进入P2P联机页面并关闭当前页面
    public void GoGame(ActionEvent actionEvent) throws IOException {
        if (new File("./openp2p/openp2p.exe").exists()) {
            Open("/P2P/GoGame.fxml", "进入P2P联机页面（进入房间）");
            Stage cl = (Stage) get.getScene().getWindow();
            cl.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("没有联机文件\n如要使用本模块，请先下载openp2p.exe文件\n下载地址：https://github.com/openp2p-cn/openp2p/releases/latest");
            alert.show();
        }
    }

    // 软件详情信息
    public void about(ActionEvent actionEvent) throws IOException {
        Open("/FXML/About.fxml", "关于 MSHFT");
    }

    // 打开一个当在当前窗口打开一个新窗口时，除了新窗口之外，其余窗口不得使用/不能改变窗口大小
    public void Open(String file, String title) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(file))));
        stage.setTitle(title);
        stage.getIcons().add(new Image("img/favicon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}
