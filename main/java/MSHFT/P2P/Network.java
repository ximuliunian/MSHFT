package MSHFT.P2P;

public class Network {
    private String User;
    private int UDPPort1;
    private String Node;
    private int ShareBandwidth;
    private int Token;
    private int UDPPort2;
    private String ServerHost;
    private int ServerPort;
    private int TCPPort;

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public int getUDPPort1() {
        return UDPPort1;
    }

    public void setUDPPort1(int UDPPort1) {
        this.UDPPort1 = UDPPort1;
    }

    public String getNode() {
        return Node;
    }

    public void setNode(String node) {
        Node = node;
    }

    public int getShareBandwidth() {
        return ShareBandwidth;
    }

    public void setShareBandwidth(int shareBandwidth) {
        ShareBandwidth = shareBandwidth;
    }

    public int getToken() {
        return Token;
    }

    public void setToken(int token) {
        Token = token;
    }

    public int getUDPPort2() {
        return UDPPort2;
    }

    public void setUDPPort2(int UDPPort2) {
        this.UDPPort2 = UDPPort2;
    }

    public String getServerHost() {
        return ServerHost;
    }

    public void setServerHost(String serverHost) {
        ServerHost = serverHost;
    }

    public int getServerPort() {
        return ServerPort;
    }

    public void setServerPort(int serverPort) {
        ServerPort = serverPort;
    }

    public int getTCPPort() {
        return TCPPort;
    }

    public void setTCPPort(int TCPPort) {
        this.TCPPort = TCPPort;
    }

    @Override
    public String toString() {
        return "\"User\"=" + User +
                ", \"UDPPort1\"=" + UDPPort1 +
                ", \"Node\"=" + Node +
                ", \"ShareBandwidth\"=" + ShareBandwidth +
                ", \"Token\"=" + Token +
                ", \"UDPPort2\"=" + UDPPort2 +
                ", \"ServerHost\"=" + ServerHost +
                ", \"ServerPort\"=" + ServerPort +
                ", \"TCPPort\"=" + TCPPort;
    }
}
