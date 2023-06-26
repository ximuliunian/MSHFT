import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

// 服务器创建与版本隔离
public class NewWorld {
    // 选择要创建服务器的版本
    void nw_bb() {
        int a;
        System.out.println("请输入要创建的服务器版本：");
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        switch (a) {
            case 1:
                nw_cj("1.12.2");
                break;

            case 2:
                nw_cj("1.16.5");
                break;

            case 3:
                nw_cj("1.18.2");
                break;
        }
    }

    // 创建服务器
    private void nw_cj(String vbt) {
        Scanner sc = new Scanner(System.in);

        WorldData wd = new WorldData();
        // 输入名称、简介
        System.out.println("请输入名称：");
        wd.setWorldName(sc.next());
        System.out.println("请输入简介：");
        wd.setBriefIntroduction(sc.next());
        wd.setStartDate(vdate());
        wd.setTailEnd(vdate());

        // 检查一下版本文件夹是否存在
        FileIntegrity fileIntegrity = new FileIntegrity();
        fileIntegrity.ErrorMkdir();

        /*
         * 在对应的版本文件夹里面创建一个隔离版本文件夹
         * 文件夹命名规则为（世界名 + 时间）最大限度保证不重复
         * */
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String path = wd.getWorldName() + "." + format.format(new Date());
        File f = new File(vbt + "/" + path);
        if (!f.mkdir()) {
            System.out.println("创建隔离文件：失败");
        } else {
            System.out.println("创建隔离文件：成功");
            wd.setMkdir(path);

            // 把核心从 server 中复制到新建的隔离文件夹里面
            File h = new File("server/CatServer-" + vbt + ".jar");
            System.out.println("正在移动核心");
            try {
                File r = new File(f + "/CatServer-" + vbt + ".jar");
                Files.copy(h.toPath(), r.toPath());
                System.out.println("核心移动成功");

                // 版本信息更新到配置文件里面
                IOJson ioJson = new IOJson();
                ioJson.inputJson(vbt,wd);

            } catch (IOException e) {
                f.delete();
                System.out.println("核心移动失败");
            }
        }
    }

    // 输出现在时间
    String vdate() {
        SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm");
        return format.format(new Date());
    }
}

// 版本资源
@JSONType(orders = {"worldName", "briefIntroduction", "startDate", "tailEnd"})
class WorldData {
    private String worldName; // 世界名
    private String briefIntroduction; // 简介
    private String startDate; // 创建时间
    private String tailEnd; // 结束时间
    private String mkdir; // 文件夹名


    public String getMkdir() {
        return mkdir;
    }

    public void setMkdir(String mkdir) {
        this.mkdir = mkdir;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getTailEnd() {
        return tailEnd;
    }

    public void setTailEnd(String tailEnd) {
        this.tailEnd = tailEnd;
    }
}