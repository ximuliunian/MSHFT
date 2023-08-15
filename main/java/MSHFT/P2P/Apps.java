package MSHFT.P2P;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author: 曦暮流年
 * @Description: 用于创建P2P应用使用
 * @date: 2023/8/11 上午 10:08
 */
public class Apps {
    @JSONField(name = "AppName")
    private String AppName;
    @JSONField(name = "Protocol")
    private String Protocol;
    @JSONField(name = "SrcPort")
    private int SrcPort;
    @JSONField(name = "PeerNode")
    private String PeerNode;
    @JSONField(name = "DstPort")
    private int DstPort;
    @JSONField(name = "DstHost")
    private String DstHost;
    @JSONField(name = "PeerUser")
    private String PeerUser;
    @JSONField(name = "Enabled")
    private int Enabled;


    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getProtocol() {
        return Protocol;
    }

    public void setProtocol(String protocol) {
        Protocol = protocol;
    }

    public int getSrcPort() {
        return SrcPort;
    }

    public void setSrcPort(int srcPort) {
        SrcPort = srcPort;
    }

    public String getPeerNode() {
        return PeerNode;
    }

    public void setPeerNode(String peerNode) {
        PeerNode = peerNode;
    }

    public int getDstPort() {
        return DstPort;
    }

    public void setDstPort(int dstPort) {
        DstPort = dstPort;
    }

    public String getDstHost() {
        return DstHost;
    }

    public void setDstHost(String dstHost) {
        DstHost = dstHost;
    }

    public String getPeerUser() {
        return PeerUser;
    }

    public void setPeerUser(String peerUser) {
        PeerUser = peerUser;
    }

    public int getEnabled() {
        return Enabled;
    }

    public void setEnabled(int enabled) {
        Enabled = enabled;
    }
}
