/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg0ukou9pz_tcp_object_stream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import TCP.Product;

/**
 *
 * @author ADMIN
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try{
            Socket socket = new Socket("203.162.10.109",2209);
            String message = "B22DCCN451;0uKou9pZ";
            ObjectOutputStream op = new ObjectOutputStream(socket.getOutputStream());
            op.writeObject(message);
            op.flush();
            //b
            Product pro = new Product();
            ObjectInputStream ip = new ObjectInputStream(socket.getInputStream());
            pro = (Product) ip.readObject();
            int x = (int) pro.getPrice();
            System.out.println(x);
            int sum = 0;
            while(x>0){
                int split = x%10;
                sum += split;
                x/=10;
            }
            System.out.println(sum);
            pro.setDiscount(sum);
            op.writeObject(pro);
            op.flush();
            op.close();
            ip.close();
            socket.close();
            
        } catch(IOException e){
            
        }
    }
    
}
