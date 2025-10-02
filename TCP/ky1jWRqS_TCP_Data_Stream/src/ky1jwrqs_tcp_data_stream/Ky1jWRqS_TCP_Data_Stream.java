/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ky1jwrqs_tcp_data_stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author ADMIN
 */
public class Ky1jWRqS_TCP_Data_Stream {
    public static void main(String[] args) throws IOException {
        try{
            Socket socket = new Socket("203.162.10.109",2207);
            DataInputStream ip = new DataInputStream(socket.getInputStream());
            DataOutputStream op = new DataOutputStream(socket.getOutputStream());
            String str = "B22DCCN451;ky1jWRqS";
            op.writeUTF(str);
            op.flush();
            int a=0;
            int b=0;
            a = ip.readInt();
            b = ip.readInt();
            int tich = a *b;
            int sum = a+b;
            op.writeInt(sum);
            op.writeInt(tich);
            op.flush();
            ip.close();
            op.close();
            socket.close();
        } catch(IOException e){
            
        }
    }
    
}
