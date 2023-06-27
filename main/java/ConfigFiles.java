import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

// 配置文件的属性初始化
class InitMkdirs {
    // 核心和版本文件是否存在
    @JSONField(name = "1.12.2")
    private boolean v1_12;
    @JSONField(name = "1.16.5")
    private boolean v1_16;
    @JSONField(name = "1.18.2")
    private boolean v1_18;
    @JSONField(name = "versionManagement.json")
    private boolean VM;

    public boolean isVM() {
        return VM;
    }

    public void setVM(boolean VM) {
        this.VM = VM;
    }

    public boolean isV1_12() {
        return v1_12;
    }

    public boolean isV1_16() {
        return v1_16;
    }

    public boolean isV1_18() {
        return v1_18;
    }

    public void setV1_12(boolean v1_12) {
        this.v1_12 = v1_12;
    }

    public void setV1_16(boolean v1_16) {
        this.v1_16 = v1_16;
    }

    public void setV1_18(boolean v1_18) {
        this.v1_18 = v1_18;
    }
}

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