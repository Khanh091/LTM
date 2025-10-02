package igcvulba_tcp_data_stream;

import java.io.*;
import java.net.Socket;

public class IgcvuLba_TCP_Data_Stream {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("203.162.10.109", 2207);

            DataInputStream ip = new DataInputStream(socket.getInputStream());
            DataOutputStream op = new DataOutputStream(socket.getOutputStream());

            String message = "B22DCCN451;IgcvuLba";
            op.writeUTF(message);
            op.flush();

            int x = ip.readInt();
            System.out.println("Nhận từ server: " + x);

            String he8 = Integer.toOctalString(x);
            String he16 = Integer.toHexString(x).toUpperCase();

            op.writeUTF(he8);
            op.writeUTF(he16);
            op.flush();

            op.close();
            ip.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
