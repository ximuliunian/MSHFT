import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.util.List;
import java.util.Map;

// 读取配置信息
class IOJson {
    //输出信息
    String readJson(String Cf) {
        String json = null;
        try {
            FileReader f = new FileReader(Cf);
            Reader reader = new InputStreamReader(new FileInputStream(Cf));
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

    // 初始化配置文件
    void initJson(InitMkdirs im) {
        String json = JSON.toJSONString(im, SerializerFeature.PrettyFormat);
        File newcf = new File("config.json");
        iptJson(json, newcf);
    }

    // 初始化版本配置文件
    void initJsonCf(Map<String, List<WorldData>> map) {
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat);
        File newvn = new File("versionManagement.json");
        iptJson(json, newvn);
    }

    // 正常输入
    void inputJsonCF(Map<String, List<WorldData>> map) {
        File newvn = new File("versionManagement.json");
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat);
        iptJson(json, newvn);
    }

    // 把内容输入文件，输入时先创建文件，如果有文件不执行无文件的话创建该文件
    private void iptJson(String s, File upData) {
        try {
            upData.createNewFile();
            Writer write = new OutputStreamWriter(new FileOutputStream(upData), "UTF-8");
            write.write(s);
            write.flush();
            write.close();
        } catch (IOException e) {
            System.out.println("失败");
        }
    }
}