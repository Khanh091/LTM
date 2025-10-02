/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package d6bukh9w_tcp_byte_stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class D6BuKh9w_TCP_Byte_Stream {
    public static void main(String[] args) throws IOException {
        try{
            Socket socket = new Socket("203.162.10.109",2206);
            DataOutputStream op = new DataOutputStream(socket.getOutputStream());
            String message = "B22DCCN451;D6BuKh9w";
            op.write(message.getBytes());
            op.flush();
            DataInputStream ip = new DataInputStream(socket.getInputStream());
            byte buffer[] = new byte[1024];
            int x = ip.read(buffer);
            String received = new String(buffer,0,x);
            List<Integer> lst = new ArrayList<>();
            String words[] = received.split("\\|");
            for(String item:words){
                lst.add(Integer.valueOf(item));
            }
            int sum =0;
            for(int i =0;i<lst.size();i++){
                sum += lst.get(i);
            }
            System.out.println(sum);
            String result = String.valueOf(sum);
            op.write(result.getBytes());
            op.close();
            ip.close();
            socket.close();
        } catch (IOException e){
            
        }
    }
    
}
