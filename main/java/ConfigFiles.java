import com.alibaba.fastjson.annotation.JSONField;

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

// 版本控制
class VersionManagement {

    @JSONField(name = "1.12.2")
    private String b1_12;
    @JSONField(name = "1.16.5")
    private String b1_16;
    @JSONField(name = "1.18.2")
    private String b1_18;

    public String getB1_12() {
        return b1_12;
    }

    public void setB1_12(String b1_12) {
        this.b1_12 = b1_12;
    }

    public String getB1_16() {
        return b1_16;
    }

    public void setB1_16(String b1_16) {
        this.b1_16 = b1_16;
    }

    public String getB1_18() {
        return b1_18;
    }

    public void setB1_18(String b1_18) {
        this.b1_18 = b1_18;
    }
}