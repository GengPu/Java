package SocketExample.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientExample {

	
	public static void connServer(String host, int post,String data) {
		try {
			Socket socket = new Socket(host, post);
			//get printWriter
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			//get reader
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//send data
			pw.println(data);
			pw.flush();
			String line = is.readLine();
			System.out.println("from server:"+line);
			pw.close();
			is.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		connServer("127.0.0.1", 9080,"come from Client");
	}
}
