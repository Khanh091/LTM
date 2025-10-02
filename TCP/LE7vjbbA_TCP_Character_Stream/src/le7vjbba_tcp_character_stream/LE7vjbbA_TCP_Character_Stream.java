/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package le7vjbba_tcp_character_stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author ADMIN
 */
public class LE7vjbbA_TCP_Character_Stream {
    public static void main(String[] args) throws IOException {
        try{
            Socket socket = new Socket("203.162.10.109",2208);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String message = "B22DCCN451;LE7vjbbA";
            bw.write(message);
            bw.newLine();
            bw.flush();
            //b
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String received = br.readLine();
            String words[] = received.split("\\s+");
            String send ="";
            for(String item: words){
                String check="";
                int k=1;
                for(int i=0;i<item.length()-1;i++){
                    if(item.charAt(i)==item.charAt(i+1)){
                        k++;
                        if(i+1==item.length()-1){
                            if(k>1){
                                check+= String.valueOf(k);
                                check+= String.valueOf(item.charAt(i));
                            } else {
                                check +=String.valueOf(item.charAt(i));
                            }
                        }
                    } else {
                        if(k>1){
                                check+= String.valueOf(k);
                                check+= String.valueOf(item.charAt(i));
                            } else {
                                check +=String.valueOf(item.charAt(i));
                            }
                        if(i+1==item.length()-1){
                            if(k>1){
                                check+= String.valueOf(k);
                                check+= String.valueOf(item.charAt(i+1));
                            } else {
                                check +=String.valueOf(item.charAt(i+1));
                            }
                        } 
                            
                            
                        
                        k=1;
                    }
                }
                StringBuilder build = new StringBuilder(check);
                send += build.reverse();
                if(!item.equals(words[words.length-1])) send+=" ";
                
            }
            System.out.println(received);
            System.out.println(send);
            bw.write(send);
            bw.flush();
            bw.close();
            br.close();
            socket.close();
        } catch(IOException e){
            
        }
    }
    
}
