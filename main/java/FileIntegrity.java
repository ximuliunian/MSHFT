import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.*;
import java.util.*;

// 初始化并检查核心与配置文件的完整性
public class FileIntegrity {
    // 检查核心和配置文件是否存在不存在抛出报错提示并尝试进行修复

    // 判断配置文件
    void ErrorConfig() {
        ErrorExists errorEx = new ErrorExists();
        if (errorEx.Cf("config.json")) {
            System.out.println("配置文件：正常");
        } else {
            //抛出错误并创建新的配置文件（此配置文件为新的只有最基本的数据）
            System.out.println("配置文件：缺失");
            System.out.println("正在尝试构建");
            Mkdirs();
        }
    }

    // 判断版本配置文件
    void ErrorVM() {
        ErrorExists errorEx = new ErrorExists();
        if (errorEx.Cf("versionManagement.json")) {
            System.out.println("版本配置文件：正常");
        } else {
            //抛出错误并创建新的配置文件（此配置文件为新的只有最基本的数据）
            System.out.println("版本配置文件：缺失");
            System.out.println("正在尝试构建");
            MkdirsCf();
        }
    }

    // 调用ErrorHx类来判断并输出核心是否存在
    void ErrorHx() {
        ErrorExists errorEx = new ErrorExists();

        if (errorEx.Hx("1.12.2")) {
            System.out.println("核心1.12.2：正常");
        } else {
            System.out.println("核心1.12.2：缺失");
            //尝试进行恢复核心
            recoverHx("1.12.2");
        }

        if (errorEx.Hx("1.16.5")) {
            System.out.println("核心1.16.5：正常");
        } else {
            System.out.println("核心1.16.5：缺失");
            //尝试进行恢复核心
            recoverHx("1.16.5");
        }

        if (errorEx.Hx("1.18.2")) {
            System.out.println("核心1.18.2：正常");
        } else {
            System.out.println("核心1.18.2：缺失");
            //尝试进行恢复核心
            recoverHx("1.18.2");
        }
    }

    // 尝试恢复核心
    void recoverHx(String bb) {
        // 拿到json数据
        String json = new IOJson().readJson("config.json");
        // 反序列化map
        Map<String, InitMkdirs> map = JSON.parseObject(json, new TypeReference<Map<String, InitMkdirs>>() {
        });
        for (Map.Entry<String, InitMkdirs> entry : map.entrySet()) {
            if (bb.equals(entry.getKey()))
                if (entry.getValue() != null) {
                    // 尝试恢复
                } else {
                    System.out.println("无法恢复，请前往https://catmc.org下载" + bb + "版本");
                }
        }
    }

    // 判断文件
    void ErrorMkdir() {
        // 判断文件夹 - 1.12.2
        File v1_12 = new File("1.12.2");
        if (!v1_12.exists()) Mkdir("1.12.2");

        // 判断文件夹 - 1.16.5
        File v1_16 = new File("1.16.5");
        if (!v1_16.exists()) Mkdir("1.16.5");

        // 判断文件夹 - 1.18.2
        File v1_18 = new File("1.18.2");
        if (!v1_18.exists()) Mkdir("1.18.2");
    }

    // 生成指定文件夹
    private void Mkdir(String version) {
        File file = new File(version);
        System.out.println("创建" + version + "：" + file.mkdir());
    }

    // 生成新的配置文件
    private void Mkdirs() {
        // 实例化一个对象（判断核心是否存在）
        ErrorExists errorEx = new ErrorExists();
        InitMkdirs initmkdirs = new InitMkdirs();
        initmkdirs.setV1_12(errorEx.Hx("1.12.2"));
        initmkdirs.setV1_16(errorEx.Hx("1.16.5"));
        initmkdirs.setV1_18(errorEx.Hx("1.18.2"));
        initmkdirs.setVM(errorEx.Cf("versionManagement.json"));

        IOJson json = new IOJson();
        json.initJson(initmkdirs);
    }

    // 生成新的版本配置文件
    private void MkdirsCf() {
        Map<String, List<WorldData>> map = new HashMap<>();
        map.put("1.12.2", null);
        map.put("1.16.5", null);
        map.put("1.18.2", null);

        IOJson json = new IOJson();
        json.initJsonCf(map);

    }
}

// 判断核心
class ErrorExists {
    // 判断核心
    boolean Hx(String hx) {
        File versionHx = new File("server/CatServer-" + hx + ".jar");
        return versionHx.exists();
    }

    // 判断文件
    boolean Cf(String file) {
        File cf = new File(file);
        return cf.exists();
    }
}