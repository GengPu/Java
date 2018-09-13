package ImageExample;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * ��������ͼƬ
 * 
 * @author yuki_ho
 * 
 */
public class ImageExample {
	// Ĭ�ϸ�ʽ
	private static final String FORMAT_NAME = "JPG";
	// Ĭ�� ���
	private static final int WIDTH = 100;
	// Ĭ�� �߶�
	private static final int HEIGHT = 100;

	/**
	 * ����ͼƬ
	 * 
	 * @param content
	 *            ����
	 * @param font
	 *            ����
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 * @return
	 */
	private static BufferedImage createImage(String content, Font font,
			Integer width, Integer height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) bi.getGraphics();
		g2.setBackground(Color.BLUE);
		g2.clearRect(0, 0, width, height);
		g2.setPaint(Color.WHITE);

		FontRenderContext context = g2.getFontRenderContext();
		
		System.out.println(font.toString());
		Rectangle2D bounds = font.getStringBounds(content, context);
//		font.
		System.out.println(font.toString());
		double x = (width - bounds.getWidth()) / 2;
		double y = (height - bounds.getHeight()) / 2;
		double ascent = -bounds.getY();
		double baseY = y + ascent;

		g2.drawString(content, (int) x, (int) baseY);

		return bi;
	}

	/**
	 * ��ȡ ͼƬ ��������
	 * 
	 * @param content
	 *            ����
	 * @param font
	 *            ����
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 * @return
	 */
	public static BufferedImage getImage(String content, Font font,
			Integer width, Integer height) {
		width = width == null ? WIDTH : width;
		height = height == null ? HEIGHT : height;
		if (null == font)
			font = new Font("����", Font.BOLD, 24);
		return createImage(content, font, width, height);
	}

	/**
	 * ��ȡ ͼƬ
	 * 
	 * @param content
	 *            ����
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 * @return
	 */
	public static BufferedImage getImage(String content, Integer width,
			Integer height) {

		return getImage(content, null, width, height);
	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @param content
	 *            ����
	 * @return
	 */
	public static BufferedImage getImage(String content) {

		return getImage(content, null, null);
	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @param content
	 *            ����
	 * @param font
	 *            ����
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 * @param destPath
	 *            ���·��
	 * @throws IOException
	 */
	public static void getImage(String content, Font font, Integer width,
			Integer height, String destPath) throws IOException {
		mkdirs(destPath);
		String file = "a.jpg";//UUID.randomUUID().toString() + ".jpg";
		ImageIO.write(getImage(content, font, width, height), FORMAT_NAME,
				new File(destPath + "/" + file));
	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @param content
	 *            ����
	 * @param font
	 *            ����
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 * @param output
	 *            �����
	 * @throws IOException
	 */
	public static void getImage(String content, Font font, Integer width,
			Integer height, OutputStream output) throws IOException {
		ImageIO.write(getImage(content, font, width, height), FORMAT_NAME,
				output);
	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @param content
	 *            ����
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 * @param destPath
	 *            ���·��
	 * @throws IOException
	 */
	public static void getImage(String content, Integer width, Integer height,
			String destPath) throws IOException {
		getImage(content, null, width, height, destPath);
	}

	/**
	 * ��ȡͼƬ
	 * 
	 * @param content
	 *            ����
	 * @param width
	 *            ��
	 * @param height
	 *            ��
	 * @param output
	 *            �����
	 * @throws IOException
	 */
	public static void getImage(String content, Integer width, Integer height,
			OutputStream output) throws IOException {
		getImage(content, null, width, height, output);
	}

	/**
	 * ���� Ŀ¼
	 * 
	 * @param destPath
	 */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		// ���ļ��в�����ʱ��mkdirs���Զ��������Ŀ¼��������mkdir��(mkdir�����Ŀ¼����������׳��쳣)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	public static void main(String[] args) throws Exception {
		getImage("��", 100, 100, "d:/JavaImageExample");

	}
}
