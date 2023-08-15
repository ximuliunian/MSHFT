package MSHFT;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

/**
 * @author: 曦暮流年
 * @Description: 把cmd控制台内容输出到TextArea文本框内和打开页面
 * @date: 2023/8/10 下午 11:39
 */
public class SystemPrint {
    /**
     * @param textArea: 文本框
     * @return: void
     * @author: 曦暮流年
     * @description: 把文本框的值传过来
     * @date: 2023/8/10 下午 11:39
     */
    public void outPrint(TextArea textArea) {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                String text = String.valueOf((char) b);
                Platform.runLater(() -> {
                    textArea.appendText(text);
                });
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                String s = new String(b, off, len);
                Platform.runLater(() -> textArea.appendText(s));
            }
        }, true));
        System.setErr(System.out);
    }

    /**
     * @param process: 命令
     * @param charset: 编码格式
     * @return: void
     * @author: 曦暮流年
     * @description: 输入cmd命令
     * @date: 2023/8/10 下午 11:42
     */
    public void cmdPrint(Process process, Charset charset) {
        new Thread(() -> {
            try (InputStreamReader reader = new InputStreamReader(process.getInputStream(), charset)) {
                int read;
                while ((read = reader.read()) != -1) {
                    System.out.print((char) read);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * @param file:  FXML文件路径
     * @param title: 窗口的名称
     * @return: void
     * @author: 曦暮流年
     * @description: 打开一个当在当前窗口打开一个新窗口时，除了新窗口之外，其余窗口不得使用/不能改变窗口大小
     * @date: 2023/8/10 下午 08:48
     */
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
