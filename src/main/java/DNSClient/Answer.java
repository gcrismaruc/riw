package DNSClient;

import java.net.InetAddress;

/**
 * Created by Gheorghe on 5/1/2017.
 */
public class Answer {
    private int type;
    private int classAns;
    private int ttl;
    private InetAddress address;
    private int resourceDataLength;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getClassAns() {
        return classAns;
    }

    public void setClassAns(int classAns) {
        this.classAns = classAns;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getResourceDataLength() {
        return resourceDataLength;
    }

    public void setResourceDataLength(int resourceDataLength) {
        this.resourceDataLength = resourceDataLength;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public Answer(){}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("---------------------------------\n");
        sb.append("\tType: " + type + "\n");
        sb.append("\tClass: " + classAns + "\n");
        sb.append("\tTTL: " + ttl + "\n");
        sb.append("\tResourceDataLength: " + resourceDataLength + "\n");
        sb.append("\tAddress: " + address.getHostAddress() + "\n");

        return sb.toString();
    }
}
