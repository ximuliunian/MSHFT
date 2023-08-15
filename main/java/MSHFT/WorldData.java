package MSHFT;

import com.alibaba.fastjson.annotation.JSONType;

/**
 * @author: 曦暮流年
 * @Description: 版本资源
 * @date: 2023/8/10 下午 11:38
 */
@JSONType(orders = {"worldName", "briefIntroduction", "startDate", "tailEnd", "version"})
public class WorldData {
    private String worldName; // 世界名
    private String briefIntroduction; // 简介
    private String startDate; // 创建时间
    private String tailEnd; // 结束时间
    private String version; // 版本

    public WorldData(String worldName, String briefIntroduction, String startDate, String tailEnd, String version) {
        this.worldName = worldName;
        this.briefIntroduction = briefIntroduction;
        this.startDate = startDate;
        this.tailEnd = tailEnd;
        this.version = version;
    }

    public WorldData() {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}