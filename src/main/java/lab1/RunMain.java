package lab1;

import DNSClient.DNSClient;
import HTTPClient.HTTPClient;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import lab2.Indexer;
import lab4.Operations;
import lab4.QuerryInterpreter;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunMain {

	public static void main(String [] args) throws IOException {

            DNSClient dnsClient = new DNSClient();
            dnsClient.sendAndRecieve("www.google.com", "192.168.43.1", 1, 1, 1);

            //last week

        //HTTPClient.sendAndRecieve();

    }
}
