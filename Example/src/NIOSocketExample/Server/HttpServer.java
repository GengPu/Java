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

public class HttpServer {

	public static void main(String[] args) throws Exception {

		// ����ServerSocketChannel������8080�˿�
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(9080));

		// ����Ϊ������ģʽ
		ssc.configureBlocking(false);

		// Ϊsscע��ѡ����
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);

		// ����������
//		HttpHandler handler = new HttpHandler(1024);
		while (true) {
			// �ȴ�����ÿ�εȴ�����3s������3s���ֳɼ�����������
			// ���������0���߲����������һֱ����
			if (selector.select(3000) == 0) {
				System.out.println("�ȴ�����ʱ��������");
				continue;
			}

			System.out.println("��������");
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

			while (keyIter.hasNext()) {
				SelectionKey key = keyIter.next();
				new Thread(new HttpHandler(key)).run();
				keyIter.remove();
			}
		}
	}

	/**
	 * �������ڲ���
	 * 
	 * @author ligen
	 *
	 */
	private static class HttpHandler implements Runnable {
		private int bufferSize = 1024;
		private String localCharset = "UTF-8";
		private SelectionKey key;

		public HttpHandler(SelectionKey key) {
			this.key = key;
		}

		public HttpHandler(int bufferSize) {
			this(bufferSize, null);
		}

		public HttpHandler(String localCHarset) {
			this(-1, localCHarset);
		}

		public HttpHandler(int bufferSize, String localCHarset) {
			if (bufferSize > 0) {
				this.bufferSize = bufferSize;
			}
			if (localCHarset != null) {
				this.localCharset = localCHarset;
			}
		}

		public void handleAccept() throws IOException {
			SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();// ʲôIO�쳣��
			sc.configureBlocking(false);
			sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
		}

		public void handleRead() throws IOException {
			SocketChannel sc = (SocketChannel) key.channel();
			ByteBuffer buffer = (ByteBuffer) key.attachment();
			buffer.clear();
			if (sc.read(buffer) == -1) {
				sc.close();
			} else {
				// ��bufferת��Ϊ����̬
				buffer.flip();
				// ���յ���ֵ����localCharset��ʽ����󱣴浽receivedString
				String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();//
				System.out.println("���Կͻ��˵�����" + receivedString);

				String[] requestMessage = receivedString.split("\r\n");
				for (String s : requestMessage) {
					System.out.println(s);
					// ��������˵���Ѿ���ӡ��
					if (s.isEmpty())
						break;
				}

				String[] firstLine = requestMessage[0].split(" ");
				System.out.println();
				System.out.println("Method:\t" + firstLine[0]);
				System.out.println("url:\t" + firstLine[1]);
				System.out.println("HTTP Version:\t" + firstLine[2]);
				System.out.println();

				// �������ݸ��ͻ���
				StringBuilder builder = new StringBuilder();
				builder.append("HTTP/1.1 200 OK\r\n");
				builder.append("Content-Type:text/html;charset=" + localCharset + "\r\n");
				builder.append("\r\b");// ����ͷ�������һ������
				builder.append("<html><head><title>��ʾ����</title></hedar><body>");
				builder.append("���յ�����������:<br/>");
				for (String s : requestMessage) {
					builder.append(s + "<br/>");
				}
				builder.append("</body></html>");
				buffer = ByteBuffer.wrap(builder.toString().getBytes(localCharset));
				sc.write(buffer);

				// �ر�Socket
				sc.close();
			}

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				if (key.isAcceptable()) {
					handleAccept();
				}
				if (key.isReadable()) {
					handleRead();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public SelectionKey getKey() {
			return key;
		}

		public void setKey(SelectionKey key) {
			this.key = key;
		}
	}
}
