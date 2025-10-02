import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try{
            DatagramSocket socket = new DatagramSocket();
            InetAddress sa = InetAddress.getByName("203.162.10.109");
            int sp = 2208;
            String message = ";B22DCCN451;osDQ7bBK";
            DatagramPacket sent = new DatagramPacket(message.getBytes(),message.length(),sa,sp);
            socket.send(sent);
            //b
            byte buffer[] = new byte[1024];
            DatagramPacket received = new DatagramPacket(buffer,buffer.length);
            socket.receive(received);
            String str = new String(received.getData(),0,received.getLength());
            String words[] = str.split(";");
            String words2[] = words[1].split(" ");
            for(int i=0;i<words2.length;i++){
                words2[i] = words2[i].substring(0,1).toUpperCase() + words2[i].substring(1).toLowerCase();
            }
            String message2 = words[0] + ";" + String.join(" ",words2);
            DatagramPacket sent2 = new DatagramPacket(message2.getBytes(),message2.length(),sa,sp);
            socket.send(sent2);

            socket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
