package com.faisal.loom.simple;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.*;
import java.io.*;

@WebServlet("/echoServer")
public class EchoHttpServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("Hello World!" + new String (fetch()) );
    }

    private byte[] fetch() {
        try {
            return new java.net.URL("http://localhost:9090").openStream().readNBytes(5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Something is wrong.");



        // try {
        //     URL local = new URL("http://localhost:9090/");
        //     DataInputStream dis = new DataInputStream(local.openStream());
        //     String inputLine;
            
            
        //     while ((inputLine = dis.readLine()) != null) {
        //         System.out.println(inputLine);
        //     }
        //     dis.close();
        // } catch (MalformedURLException me) {
        //     System.out.println("MalformedURLException: " + me);
        // } catch (IOException ioe) {
        //     System.out.println("IOException: " + ioe);
        // }

        // return "data fetched".getBytes();

    }


}
