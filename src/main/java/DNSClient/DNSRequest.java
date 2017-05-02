package DNSClient;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Gheorghe on 5/1/2017.
 */
public class DNSRequest {

    private int idClient;
    private int questionCount;
    private int questionType;
    private int questionClass;
    private int addressLenght;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public int getQuestionClass() {
        return questionClass;
    }

    public void setQuestionClass(int questionClass) {
        this.questionClass = questionClass;
    }

    public int getAddressLenght() {
        return addressLenght;
    }

    public void setAddressLenght(int addressLenght) {
        this.addressLenght = addressLenght;
    }

    public byte[] getSendbyte() {
        return sendbyte;
    }

    public void setSendbyte(byte[] sendbyte) {
        this.sendbyte = sendbyte;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    byte[] sendbyte;
    private String address;
    private InetAddress ip;

    public DNSRequest(){}
    public DNSRequest(String address, String ipString, int questionCount, int questionType, int questionClass) throws UnknownHostException {
        this.address = address;
        this.ip = InetAddress.getByName(ipString);
        this.questionClass = questionClass;
        this.questionCount = questionCount;
        this.questionType  = questionType;
        this.addressLenght = address.length() + 2;
        this.sendbyte = new byte[12 + addressLenght + 4];
        this.idClient = 123;
    }

    public void setData(String address){
        String [] parsed = address.split("\\.");
        int idx = 12;
        for (String s : parsed){
            sendbyte[idx++] = (byte)s.length();
            for(char c : s.toCharArray()){
                sendbyte[idx++] = (byte) (c);
            }
        }
    }

    public void setRequest(){
        sendbyte[0] = (byte) ((idClient >> 8) & 0xFF);
        sendbyte[1] = (byte) (idClient & 0xFF);


        sendbyte[4] = (byte) ((questionCount>> 8) & 0xFF);
        sendbyte[5] = (byte) ((questionCount & 0xFF));

        sendbyte[12 + addressLenght] = (byte) ((questionCount >> 8) & 0xFF);
        sendbyte[12 + addressLenght + 1] = (byte) ((questionCount & 0xFF));
        sendbyte[12 + addressLenght + 2] = (byte) ((questionCount >> 8) & 0xFF);
        sendbyte[12 + addressLenght + 3] = (byte) ((questionCount & 0xFF));

        setData(this.address);
    }

    public DatagramPacket getDatagramPacket(){
        this.setRequest();
        return new DatagramPacket(sendbyte, sendbyte.length, ip, 53);
    }


    public void printDNSRequest(){
        for(byte b : sendbyte){
            System.out.format("0x%x ",b);
        }

    }
}
