/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package eyjrxysn_tcp_character_stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author ADMIN
 */
public class EYJrxYSN_TCP_Character_Stream {
    public static boolean check(String s){
        StringBuilder build = new StringBuilder(s);
        String ss = build.substring(build.length()-3,build.length()).toString();
        if(ss.equals("edu")){
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        try{
            Socket socket = new Socket("203.162.10.109",2208);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String message = "B22DCCN451;eYJrxYSN";
            bw.write(message);
            bw.newLine();
            bw.flush();
            //b
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String received = br.readLine();
            System.out.println(received);
            String words[] = received.split(", ");
            String send = "";
            for(String item : words){
                if(check(item)){
                    send += item;
                    if(!item.equals(words[words.length-1])) send += ", ";
                }
                
            }
            System.out.println(send);
            bw.write(send);
            bw.newLine();
            bw.close();
            br.close();
            socket.close();
        } catch(IOException e){
            
        }
    }
    
}
