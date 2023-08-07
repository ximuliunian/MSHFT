package MSHFT;

import MSHFT.P2P.Network;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// 读取配置信息
public class IOJson {
    //输出信息
    public String readJson(String map) {
        String json = null;
        try {
            FileReader f = new FileReader(map);
            Reader reader = new InputStreamReader(Files.newInputStream(Paths.get(map)),"UTF-8");
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

    // 随机输出A~Z、a~z、0~9的16个组合字符串
    public static String RandomString() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 16; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    // P2P初始化
    int initP2P() {
        // 创建p2p的json数据
        Network network = new Network();
        network.setToken(0);
        network.setNode(RandomString());
        network.setShareBandwidth(10);
        network.setUser("");
        network.setServerHost("api.openp2p.cn");
        network.setServerPort(27183);
        network.setUDPPort1(27182);
        network.setUDPPort2(27183);
        network.setTCPPort(59989);

        Map<String, Object> map = new HashMap<>();
        map.put("network", network);
        map.put("apps", null);
        map.put("LogLevel", 1);

        String json = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        File p2p = new File("openp2p/config.json");
        return iptJson(json, p2p);
    }

    // P2P联机输入
    public int inputP2P(Map<String, Object> map) {
        String json = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        File p2p = new File("openp2p/config.json");
        return iptJson(json, p2p);
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