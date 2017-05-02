package DNSClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by Gheorghe on 5/1/2017.
 */
public class DNSClient {

    private DNSRequest request;
    private DNSResponse response;
    private DatagramSocket datagramSocket;

    public DNSClient(){

    }

    public void sendAndRecieve(String address, String ipString, int questionCount, int questionType, int questionClass) throws UnknownHostException {

        try {
            datagramSocket = new DatagramSocket();

            request = new DNSRequest(address, ipString, questionCount, questionType, questionClass);
            request.setRequest();
            request.printDNSRequest();

            datagramSocket.send(request.getDatagramPacket());
            byte[] receivebyte = new byte[1024];

            DatagramPacket receiver = new DatagramPacket(receivebyte, receivebyte.length);
            datagramSocket.receive(receiver);
            byte response[] = receiver.getData();

            DNSResponse dnsResponse = new DNSResponse(response);
            System.out.println(dnsResponse);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }


}
