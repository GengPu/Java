package SocketExample.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketExample {

	public static void createServer(int port) {
		try {
			if (port < 0) {
				throw new NumberFormatException("port��������");
			}
			// ����һ��ServerSocket ����port�˿�
			ServerSocket server = new ServerSocket(port);
			// �ȴ�����
			Socket socket = server.accept();
			// ���յ������ʹ��socket����ͨ�ţ�����BufferedReader���ڶ�ȡ����
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = is.readLine();
			System.out.println("�ӿͻ��˻�ȡ���ַ���Ϊ��" + line);
			// ��������������
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println("���ظ��ͻ��˵��ַ���Ϊ��" + line);
			pw.flush();
			//�ر�����
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
