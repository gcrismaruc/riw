package DNSClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gheorghe on 4/25/2017.
 */
public class DNSResponse {

    private int identifier;
    private int flagsAndCodes;
    private byte responseCode;
    private int answerRC;

    private List<Answer>answers;


    byte []response;

    public static int idx = 0;

    public DNSResponse(){
        answers = new ArrayList<>();
    }

    public DNSResponse(byte [] response) throws UnknownHostException {
        this.response = response;
        answers = new ArrayList<>();
        parseResponse();
    }
    static int getTwoBytes(byte[] array) {
        int ret = ((array[idx] << 8) & 0xFFFF) | (array[idx + 1] & 0xFF);
        idx += 2;
        return ret;
    }

    static int getFourBytes(byte[] array) {
        int ret = ((array[idx] << 24)) | ((array[idx + 1] << 16) & 0xFFFFFF) | ((array[idx + 2] << 8) & 0xFFFF) | (array[idx + 3] & 0xFF);
        idx += 4;
        return ret;
    }

    public void parseResponse() throws UnknownHostException {

        identifier = getTwoBytes(response);
        flagsAndCodes = getTwoBytes(response);
        responseCode = (byte) (response[3] & 0xF);

        if (responseCode != 0) {
            System.out.println("Error response code");
        } else {
            //step over question count always 1
            idx += 2;
            answerRC = getTwoBytes(response);
            if (answerRC < 1) {
                System.out.println("Error answer record count");
            } else {
                //step over name server and additional record count
                idx += 4;

                //get question name
                StringBuilder questionName = new StringBuilder();
                while (response[idx] != 0x0) {
                    questionName.append(Character.toChars(response[idx]));
                    idx++;
                }
                idx++;
                //step over qt and qc
                idx += 4;

                //get all responses
                for (int i = 0; i < answerRC; i++) {
                    int pointer = getTwoBytes(response);
                    pointer &= 0x3FFF;

                    Answer answer = new Answer();
                    answer.setType(getTwoBytes(response));
                    answer.setClassAns(getTwoBytes(response));
                    answer.setTtl(getFourBytes(response));
                    answer.setResourceDataLength(getTwoBytes(response));

                    byte[] ip = new byte[4];
                    if (answer.getType() == 1 && answer.getResourceDataLength() == 4) {
                        for (int a = 0; a < 4; a++) {
                            ip[a] = response[idx + a];
                        }
                        idx += 4;
                    }
                    answer.setAddress(InetAddress.getByAddress(ip));
                    answers.add(answer);
                   // idx+=answer.getResourceDataLength() - 14;
                }
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("\nResponse: \n");
        sb.append("\tIdentifier: " + identifier + "\n");
        sb.append("\tFlagsAndCodes: " + flagsAndCodes + "\n");
        sb.append("\tResponseCode: " + responseCode + "\n");
        sb.append("\tAnswerRecordCount: " + answerRC + "\n");

       for(Answer answer : answers){
           sb.append(answer.toString());
       }

       return sb.toString();
    }
}
