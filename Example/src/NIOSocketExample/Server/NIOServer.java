package NIOSocketExample.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NIOServer {

	public static void main(String[] args) throws Exception{

		//����ServerSocketChannel������8080�˿�
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(9080));
		
		//����Ϊ������ģʽ
		ssc.configureBlocking(false);
		
		//Ϊsscע��ѡ����
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		//����������
		Handler handler = new Handler(1024);
		while(true) {
			//�ȴ�����ÿ�εȴ�����3s������3s���ֳɼ�����������
			//���������0���߲����������һֱ����
			if(selector.select(3000)==0) {
				System.out.println("�ȴ�����ʱ��������");
				continue;
			}
			
			System.out.println("��������");
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
			
			while(keyIter.hasNext()){
				SelectionKey key = keyIter.next();
				if(key.isAcceptable()) {
					handler.handleAccept(key);
				}
				if(key.isReadable()) {
					handler.handleRead(key);
				}
				keyIter.remove();
			}
		}
	}

	/**
	 * �������ڲ���
	 * @author ligen
	 *
	 */
	private static class Handler {
		private int bufferSize = 1024;
		private String localCharset = "UTF-8";

		public Handler() {
		}

		public Handler(int bufferSize) {
			this(bufferSize, null);
		}

		public Handler(String localCHarset) {
			this(-1, localCHarset);
		}

		public Handler(int bufferSize, String localCHarset) {
			if (bufferSize > 0) {
				this.bufferSize = bufferSize;
			}
			if (localCHarset != null) {
				this.localCharset = localCHarset;
			}
		}
		
		public void handleAccept(SelectionKey key) throws IOException {
			SocketChannel sc = ((ServerSocketChannel)key.channel()).accept();//ʲôIO�쳣��
			sc.configureBlocking(false);
			sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
		}
		
		public void handleRead(SelectionKey key) throws IOException{
			SocketChannel sc = (SocketChannel)key.channel();
			ByteBuffer buffer = (ByteBuffer)key.attachment();
			buffer.clear();
			if (sc.read(buffer) == -1) {
				sc.close();
			}else {
				//��bufferת��Ϊ����̬
				buffer.flip();
				//���յ���ֵ����localCharset��ʽ����󱣴浽receivedString
				String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();//
				System.out.println("���Կͻ��˵�����"+receivedString);
				
				//�������ݸ��ͻ���
				String sendString = "���ظ��ͻ��˵����ݣ�"+ receivedString;
				buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
				sc.write(buffer);
				
				//�ر�Socket
				sc.close();
			}
			
		}
	}
}
