import UDP.Student;

import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.AnnotatedArrayType;
import java.net.*;

public class Client {
    public static String setName(String s){
        String words[] = s.split("\\s+");
        String name = "";
        for(int i=0;i<words.length;i++){
            name += (words[i].substring(0,1).toUpperCase()+words[i].substring(1).toLowerCase());
            name += " ";
        }
        return name.trim();
    }
    public static String taoMail(String name){
        String mail = "";
        String words[] = name.split("\\s+");
        mail+= words[words.length-1].toLowerCase();
        for(int i=0;i<words.length-1;i++){
            mail += words[i].substring(0,1).toLowerCase();
        }
        mail += "@ptit.edu.vn";
        return mail;
    }
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress sa = InetAddress.getByName("203.162.10.109");
            int sp=2209;
            //a
            String message = ";B22DCCN451;j9dtpROC";
            DatagramPacket ds = new DatagramPacket(message.getBytes(),message.length(),sa,sp);
            socket.send(ds);
            //b
            byte buffer[] = new byte[1024];
            DatagramPacket dc = new DatagramPacket(buffer,buffer.length);
            socket.receive(dc);
            String rq = new String(dc.getData(),0,8);
            ObjectInputStream ois = new ObjectInputStream(new
                    ByteArrayInputStream(dc.getData(),8,dc.getLength()-8));
            Student s = (Student) ois.readObject();
            System.out.println(s.getName());
            s.setName(setName(s.getName()));
            System.out.println(s.getName());
            s.setEmail(taoMail(s.getName()));
            System.out.println(s.getEmail());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(s);
            oos.flush();
            byte[] sendD = new byte[1024];
            System.arraycopy(rq.getBytes(),0,sendD,0,8);
            System.arraycopy(baos.toByteArray(),0,sendD,8,baos.size());
            DatagramPacket ds2 = new DatagramPacket(sendD,sendD.length,sa,sp);
            socket.send(ds2);

            socket.close();



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
