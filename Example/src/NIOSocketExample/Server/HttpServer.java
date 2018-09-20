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

		// 创建ServerSocketChannel，监听8080端口
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(9080));

		// 设置为非阻塞模式
		ssc.configureBlocking(false);

		// 为ssc注册选择器
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);

		// 创建处理器
//		HttpHandler handler = new HttpHandler(1024);
		while (true) {
			// 等待请求，每次等待阻塞3s，超过3s后现成继续向下运行
			// ，如果传入0或者不传入参数将一直阻塞
			if (selector.select(3000) == 0) {
				System.out.println("等待请求超时。。。。");
				continue;
			}

			System.out.println("处理请求");
			Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();

			while (keyIter.hasNext()) {
				SelectionKey key = keyIter.next();
				new Thread(new HttpHandler(key)).run();
				keyIter.remove();
			}
		}
	}

	/**
	 * 处理器内部类
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
			SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();// 什么IO异常？
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
				// 将buffer转换为读形态
				buffer.flip();
				// 接收到的值按照localCharset格式编码后保存到receivedString
				String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();//
				System.out.println("来自客户端的请求：" + receivedString);

				String[] requestMessage = receivedString.split("\r\n");
				for (String s : requestMessage) {
					System.out.println(s);
					// 遇见空行说明已经打印完
					if (s.isEmpty())
						break;
				}

				String[] firstLine = requestMessage[0].split(" ");
				System.out.println();
				System.out.println("Method:\t" + firstLine[0]);
				System.out.println("url:\t" + firstLine[1]);
				System.out.println("HTTP Version:\t" + firstLine[2]);
				System.out.println();

				// 返回数据给客户端
				StringBuilder builder = new StringBuilder();
				builder.append("HTTP/1.1 200 OK\r\n");
				builder.append("Content-Type:text/html;charset=" + localCharset + "\r\n");
				builder.append("\r\b");// 报文头结束后加一个空行
				builder.append("<html><head><title>显示报文</title></hedar><body>");
				builder.append("接收到的请求报文是:<br/>");
				for (String s : requestMessage) {
					builder.append(s + "<br/>");
				}
				builder.append("</body></html>");
				buffer = ByteBuffer.wrap(builder.toString().getBytes(localCharset));
				sc.write(buffer);

				// 关闭Socket
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
