import com.alibaba.fastjson.annotation.JSONType;

// 版本资源
@JSONType(orders = {"worldName", "briefIntroduction", "startDate", "tailEnd", "mkdir"})
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