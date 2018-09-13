package SocketExample.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketExample {

	public static void createServer(int port) {
		try {
			if (port < 0) {
				throw new NumberFormatException("port参数错误");
			}
			// 创建一个ServerSocket 监听port端口
			ServerSocket server = new ServerSocket(port);
			// 等待请求
			Socket socket = server.accept();
			// 接收到请求后使用socket进行通信，创建BufferedReader用于读取数据
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = is.readLine();
			System.out.println("从客户端获取的字符串为：" + line);
			// 创建发送数据器
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println("返回给客户端的字符串为：" + line);
			pw.flush();
			//关闭数据
			pw.close();
			is.close();
			socket.close();
			server.close();
		} catch (IOException | NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}
}
