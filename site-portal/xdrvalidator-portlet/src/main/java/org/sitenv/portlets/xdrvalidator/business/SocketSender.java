package org.sitenv.portlets.xdrvalidator.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SocketSender {

	public static String sendMessage(String endpoint, String payload) throws MalformedURLException, UnknownHostException, IOException {

        URL url = new URL(endpoint);

        String hostname = url.getHost();
        int port = url.getPort();
        if (port == -1) {
            port = 80;
        }
        String path = url.getPath();

        InetAddress addr = InetAddress.getByName(hostname);
        
       // SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
       // SSLSocket socket = (SSLSocket) factory.createSocket(addr,port);
        
        Socket socket = new Socket(addr, port);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

        //   String fullPayload = SimpleSOAPSender.addHttpHeaders(path, payload);
        bufferedWriter.write(payload);
        bufferedWriter.flush();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        return SocketSender.getResponse(bufferedReader);

    }

    private static String getResponse(BufferedReader bufferedReader) throws IOException {

        StringBuilder response = new StringBuilder();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line + "\n");
        }
        return response.toString();

    }
    
    
}
