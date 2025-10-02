import javax.xml.crypto.Data;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.Arrays;
import java.util.Comparator;

public class Client {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress sa = InetAddress.getByName("203.162.10.109");
            int sp = 2207;
            String message = ";B22DCCN451;ua0q1gsC";
            DatagramPacket sent = new DatagramPacket(message.getBytes(),message.length(),sa,sp);
            socket.send(sent);
            //b
            byte buffer[] = new byte[1024];
            DatagramPacket received = new DatagramPacket(buffer,buffer.length);
            socket.receive(received);
            String str = new String(received.getData(),0,received.getLength());
            System.out.println(str);
            //c
            String words[] = str.split(";");
            String request = words[0];
            String words2[] = words[1].split(",");
            Arrays.sort(words2, Comparator.comparingInt(Integer::parseInt));
            String message2 = request +";"+ words2[words2.length-1] +","+ words2[0];
            DatagramPacket sent2 = new DatagramPacket(message2.getBytes(),message2.length(),sa,sp);
            socket.send(sent2);
            System.out.println(message2);
            socket.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
