import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        File newvn = new File("versionManagement.json");
        iptJson(json, newvn);
    }

    // 正常输入
    void inputJson(String vbt, WorldData wd) {
        File newvn = new File("versionManagement.json");
        Map<String, List<WorldData>> map = JSON.parseObject(JSON.toJSONString(newvn), new TypeReference<Map<String, List<WorldData>>>() {
        });
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue);
        iptJson(json, newvn);
    }

    private void iptJson(String s, File upData) {
        try {
            upData.createNewFile();
//            Writer write = new OutputStreamWriter(new FileOutputStream(upData), "UTF-8");
            FileWriter write = new FileWriter(upData, true);
            write.write(s);
            write.flush();
            write.close();
            System.out.println("构建成功");
        } catch (IOException e) {
            System.out.println("构建失败");
        }
    }
}