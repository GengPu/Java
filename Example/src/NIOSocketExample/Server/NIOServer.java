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

		//创建ServerSocketChannel，监听8080端口
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(9080));
		
		//设置为非阻塞模式
		ssc.configureBlocking(false);
		
		//为ssc注册选择器
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		//创建处理器
		Handler handler = new Handler(1024);
		while(true) {
			//等待请求，每次等待阻塞3s，超过3s后现成继续向下运行
			//，如果传入0或者不传入参数将一直阻塞
			if(selector.select(3000)==0) {
				System.out.println("等待请求超时。。。。");
				continue;
			}
			
			System.out.println("处理请求");
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
	 * 处理器内部类
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
			SocketChannel sc = ((ServerSocketChannel)key.channel()).accept();//什么IO异常？
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
				//将buffer转换为读形态
				buffer.flip();
				//接收到的值按照localCharset格式编码后保存到receivedString
				String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();//
				System.out.println("来自客户端的请求："+receivedString);
				
				//返回数据给客户端
				String sendString = "返回给客户端的数据："+ receivedString;
				buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
				sc.write(buffer);
				
				//关闭Socket
				sc.close();
			}
			
		}
	}
}
