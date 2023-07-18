import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.util.List;
import java.util.Map;

// 读取配置信息
class IOJson {
    //输出信息
    String readJson() {
        String json = null;
        try {
            FileReader f = new FileReader("versionManagement.json");
            Reader reader = new InputStreamReader(new FileInputStream("versionManagement.json"));
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            f.close();
            reader.close();
            json = sb.toString();
        } catch (IOException e) {
            System.out.println("获取配置信息失败");
        }
        return json;
    }

    // 初始化版本配置文件
    int initJsonCf(Map<String, WorldData> map) {
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat);
        File newvn = new File("versionManagement.json");
        return iptJson(json, newvn);
    }

    // 正常输入
    int inputJsonCF(Map<String, WorldData> map) {
        File newvn = new File("versionManagement.json");
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat);
        return iptJson(json, newvn);
    }

    // 把内容输入文件，输入时先创建文件，如果有文件不执行无文件的话创建该文件
    private int iptJson(String s, File upData) {
        try {
            upData.createNewFile();
            Writer write = new OutputStreamWriter(new FileOutputStream(upData), "UTF-8");
            write.write(s);
            write.flush();
            write.close();
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }
}