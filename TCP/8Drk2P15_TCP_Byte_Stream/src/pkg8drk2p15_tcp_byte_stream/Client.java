/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg8drk2p15_tcp_byte_stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Math.sqrt;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Client {
    public static boolean isPrime(int x) {
        if (x <= 1) {
            return false;
        }
        for (int i = 2; i <= sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
    }
    return true;
}
    public static void main(String[] args) throws IOException {
        try{
            Socket socket = new Socket("203.162.10.109",2206);
            System.out.println(socket);
            OutputStream op = socket.getOutputStream();
            String message = "B22DCCN451;8Drk2P15";
            op.write(message.getBytes());
            op.flush();
            //b
            InputStream ip = socket.getInputStream();
            byte buffer[] = new byte[1024];
            int x = ip.read(buffer);
            String received = new String(buffer,0,x);
            //c
            List<Integer> lst = new ArrayList<>();
            String words[] = received.split(",");
            for(String item:words){
                lst.add(Integer.valueOf(item));
            }
            int sum = 0;
            for(int i=0;i<lst.size();i++){
                if(isPrime(lst.get(i))){
                    sum+=lst.get(i);
                }
            }
            System.out.println(sum);
            String send = String.valueOf(sum);
            op.write(send.getBytes());
            op.flush();
            op.close();
            ip.close();
            socket.close();
            
        } catch(IOException e){
            
        }
    }
}
