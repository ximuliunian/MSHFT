package MSHFT.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: 曦暮流年
 * @Description: 版权年限
 * @date: 2023/8/11 上午 10:24
 */
public class About {
    @FXML
    private Label banquan;

    public void initialize() {
        banquan.setText("Copyright © 曦暮流年 2023 - " + aDate() + ". All Rights Reserved.");
    }

    private String aDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date());
    }
}
