import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Date;

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
