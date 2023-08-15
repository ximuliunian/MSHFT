package MSHFT;

import MSHFT.P2P.Network;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author: 曦暮流年
 * @Description: 输入输出JSON配置信息
 * @date: 2023/8/10 下午 11:49
 */
public class IOJson {
    /**
     * @param map: 传过来的JSON文件路径
     * @return: String
     * @author: 曦暮流年
     * @description: 传过来JSON文件路径并读取内容然后返回所读取内容
     * @date: 2023/8/10 下午 11:49
     */
    public String readJson(String map) {
        String json = null;
        try {
            FileReader f = new FileReader(map);
            Reader reader = new InputStreamReader(Files.newInputStream(Paths.get(map)), StandardCharsets.UTF_8);
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

    /**
     * @return: String
     * @author: 曦暮流年
     * @description: 随机输出A~Z、a~z、0~9的16个组合字符串
     * @date: 2023/8/10 下午 11:54
     */
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

    /**
     * @return: int
     * @author: 曦暮流年
     * @description: P2P初始化JSON返回是否成功
     * @date: 2023/8/10 下午 11:54
     */
    public int initP2P() {
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

    /**
     * @param map: 传过来修改后的map集合
     * @return: int
     * @author: 曦暮流年
     * @description: P2P JSON 输入内容
     * @date: 2023/8/10 下午 11:55
     */
    public int inputP2P(Map<String, Object> map) {
        String json = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        File p2p = new File("openp2p/config.json");
        return iptJson(json, p2p);
    }

    /**
     * @return: int
     * @author: 曦暮流年
     * @description: 初始化版本配置文件
     * @date: 2023/8/10 下午 11:57
     */
    public int initJsonCf() {
        Map<String, WorldData> map = new HashMap<>();
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat);
        File newvn = new File("versionManagement.json");
        return iptJson(json, newvn);
    }

    /**
     * @param map: 传过来修改后的map集合
     * @return: int
     * @author: 曦暮流年
     * @description: 服务器信息 JSON 内容输入
     * @date: 2023/8/11 上午 12:03
     */
    public int inputJsonCF(Map<String, WorldData> map) {
        File newvn = new File("versionManagement.json");
        String json = JSON.toJSONString(map, SerializerFeature.PrettyFormat);
        return iptJson(json, newvn);
    }

    /**
     * @param s:      已经序列化的JSON
     * @param upData: JSON文件路径
     * @return: int
     * @author: 曦暮流年
     * @description: 把内容输入文件，输入时先创建文件，如果有文件不执行无文件的话创建该文件
     * @date: 2023/8/11 上午 12:04
     */
    private int iptJson(String s, File upData) {
        try {
            upData.createNewFile();
            Writer write = new OutputStreamWriter(new FileOutputStream(upData), StandardCharsets.UTF_8);
            write.write(s);
            write.flush();
            write.close();
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }
}