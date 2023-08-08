package MSHFT;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;

public class SystemPrint {
   public void outPrint(TextArea textArea){
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

   public void cmdPrint(Process process, Charset charset){
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
}
