package HTTPClient;

import java.io.*;
import java.net.Socket;

/**
 * Created by Gheorghe on 5/2/2017.
 */
public class HTTPClient {

    public static void sendAndRecieve(){
        Socket socket;
        StringBuilder httpRequest = new StringBuilder();
        httpRequest.append("GET / HTTP/1.1\r\n");
        httpRequest.append("Host: www.resursadefun.ro.\r\n");
        httpRequest.append("User-Agent: CLIENT RIW\r\n");
        httpRequest.append("Connection: close\r\n\r\n");
        try {
            socket = new Socket("104.24.121.23", 80);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.print(httpRequest.toString());

            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String t = br.readLine();

            String [] status = t.split(" ");
            StringBuilder responseHeaders = new StringBuilder();
            StringBuilder httpResponse = new StringBuilder();

            responseHeaders.append(t);
            while(!t.contains("!DOCTYPE")) {
                t = br.readLine();
                responseHeaders.append(t);
            }

            while(t != null) {
                httpResponse.append(t);
                t = br.readLine();
            }
            br.close();
            //write error
            if(status != null && (status[1].equals("500") || status[1].equals("400"))){
                Writer writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream("Error.txt"), "utf-8"));
                    writer.write("HTTP Request:\n" + httpRequest.toString());
                    writer.write("HTTP Response:\n" + responseHeaders.toString() + httpResponse.toString());
                } catch (IOException ex) {
                    // report
                } finally {
                    try {writer.close();} catch (Exception ex) {/*ignore*/}
                }
            } else {
                //write page content
                Writer writer = null;
                try {
                    writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream("page.html"), "utf-8"));
                    writer.write(httpResponse.toString());
                } catch (IOException ex) {
                    // report
                } finally {
                    try {writer.close();} catch (Exception ex) {/*ignore*/}
                }
            }
        } catch (Exception e){
        }
    }
}
