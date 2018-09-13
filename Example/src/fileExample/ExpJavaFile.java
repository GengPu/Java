package fileExample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * ͨ��ָ���ļ�·���б���ȡ��Ӧjava�ļ���class�ļ�����Ϊjsp��js�ļ���ͬ��ģʽ������һ����·����
 * ����Ŀ���Ƴ��ֱ仯ʱ�˳����˳������������������Ŀͬʱ����
 * 
 * @author ligen
 * 
 */
public class ExpJavaFile {

	private static String errMsg = "";
	private static String errFile = "";// ������ļ���
	private static final String fileurlList = "D:/Myeclipse/fileList.txt";// �޸ĳ����б�
	private static final String workspace = "D:\\Myeclipse\\workspace";// ��Ŀ���ڹ����ռ�
	private static final String target = "D:\\Myeclipse\\FileList";// Ŀ��Ŀ¼
	private static final String projectName = "TestWeb"; // ��Ŀ����
	private static final String filelistLinkType = "/";// ��fileNameList��Ӧ���ļ����ӷ�
	private static List<String> srclist = new ArrayList<String>();// �����Խ�java�����Լ���������xml�ļ�ʹ��
																	// Դ�ļ�Ŀ¼�ĸ�
	private static String output = "";// �����Խ�java�����Լ���������xml�ļ�ʹ�� ���뵽Ŀ¼�ĸ�

	public static void main(String[] args) {
		try {
			setSrcAndOutput(workspace,projectName);
			getFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ݹ����ȡ��Ӧclass�ļ���classĿ���ļ�
	 * @param fileurl
	 * @return
	 */
	private static String getClassUrl(String fileurl){
		String classurl = "";
		for (int i = 0; i < srclist.size(); i++) {
			if(fileurl.indexOf(srclist.get(i)+filelistLinkType) > -1){
				classurl = fileurl.replaceFirst(srclist.get(i)+filelistLinkType, output+filelistLinkType).replaceFirst(".java", ".class");
				return classurl;
			}
		}
		classurl = fileurl;
		return classurl;
	}
	
	
	private static void getFile() {
		boolean flag = true;
		String str = "";
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			while (flag) {
				System.out.println("��ѡ�񡭡�");
				System.out.println("0���˳���");
				System.out.println("1������Դ���롣");
				System.out.println("2������class���롣");
				System.out.println("3������Դ���뼰class���롣");

				str = stdin.readLine();

				if (str.equals(""))
					continue;

				if (str.equals("0")) {
					System.out.println("�����˳���");
					stdin.close();
					System.exit(0);
				}else{
					flag = false;
				}

			}

			ArrayList<String> list = new ArrayList<String>();
			String djpath = "";// �Ǽǵ��ļ�·��
			RandomAccessFile openFile = new RandomAccessFile(fileurlList, "r");
			djpath = openFile.readLine();
			djpath = new String(djpath.getBytes("8859_1"), "UTF-8");
			djpath = djpath.trim();
			while (djpath != null) {
				list.add(djpath);
				djpath = openFile.readLine();
				if (djpath != null) {
					djpath = new String(djpath.getBytes("8859_1"), "GBK");
					djpath = djpath.trim();
				}
			}
			openFile.close();

			for (int i = 0; i < list.size(); i++) {
				String fileurl = workspace+File.separator+projectName+File.separator+(list.get(i).replaceFirst(filelistLinkType+projectName+filelistLinkType, ""));//Դ����·��
				System.out.println("Դ�ļ���"+fileurl);
				String targeturl = target+File.separator+projectName+File.separator+(list.get(i).replaceFirst(filelistLinkType+projectName+filelistLinkType, ""));//Ŀ����Դ����·��
				if (!"2".equals(str)) {// ����û��ѡ��2���ȻҪ��java����
					copyFile(fileurl, targeturl);
				}
				if (!"1".equals(str)) {// ����ѡ��1���ȻҪ��class����
					String classurl = getClassUrl(fileurl);//class�ļ�·������java�����������xml�ļ��б仯��
					System.out.println("class�ļ���"+classurl);
					String classtargeturl = getClassUrl(targeturl);//Ŀ��class�ļ�·������java�����������xml�ļ��б仯��
					copyFile(classurl, classtargeturl);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ���ñ���·��
	 * 
	 * @param workspace
	 *            �����ռ�
	 * @param projectName
	 *            ��Ŀ����
	 */
	private static void setSrcAndOutput(String workspace, String projectName) {
		NodeList nodeList = null;
		try {
			File file = new File(workspace + File.separator + projectName
					+ File.separator + ".classpath");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			nodeList = doc.getElementsByTagName("classpathentry");// ��ȡ���нڵ�����Ϊclasspathentry�Ľڵ�
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Node> nodeLists = new ArrayList<Node>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			NamedNodeMap attrsList = node.getAttributes();// һ���ڵ��ϵ����Խڵ�
			for (int j = 0; j < attrsList.getLength(); j++) {
				Node attr = attrsList.item(j);
				if ((attr.getNodeName().equals("kind") && attr.getNodeValue()
						.equals("src"))
						|| (attr.getNodeName().equals("kind") && attr
								.getNodeValue().equals("output"))) {// ��ȡ������Ŀ�������Ľڵ�
					nodeLists.add(node);
				} else {
					break;
				}
			}
		}

		for (int i = 0; i < nodeLists.size(); i++) {
			Node node = nodeLists.get(i);
			NamedNodeMap attrsList = node.getAttributes();// һ���ڵ��ϵ����Խڵ�
			// System.out.println("�ڵ���������Ϊ��"+attrsList.getLength());
			// System.out.println("��������1��" + attrsList.item(0).getNodeName());
//			System.out.println("����ֵΪ1��" + attrsList.item(0).getNodeValue());
			// System.out.println("��������2��" + attrsList.item(1).getNodeName());
//			System.out.println("����ֵΪ2��" + attrsList.item(1).getNodeValue());
			if (attrsList.item(0).getNodeValue().equals("src")) {// һ����Ŀsrc�����Ŀ¼���ܺܶ൫��outputĿ¼ֻ��һ��
				srclist.add(attrsList.item(1).getNodeValue());
			} else {
				output = attrsList.item(1).getNodeValue();
			}
		}

	}

	private static void copyFile(String path1, String path2) throws IOException {
		long modifytime = 0;// �ϴ��޸�ʱ��
		errFile = path1;
		FileInputStream fi = new FileInputStream(path1);
		errFile = path2;
		File filemik = new File(path2.replaceFirst(path2.split("/")[path2.split("/").length-1], ""));
		if(!filemik.exists()){
			filemik.mkdirs();
		}
		File file2 = new File(path2);
		if(!file2.exists()){
			try {
				file2.createNewFile();
		    } catch (IOException e) {
		        // TODO Auto-generated catch block    
		        e.printStackTrace();    
		    }
		}
		FileOutputStream fo = new FileOutputStream(path2);
		byte data[] = new byte[fi.available()];
		fi.read(data);
		fo.write(data);
		fi.close();
		fo.close();
		File sourceFi = new File(path1);
		File destFi = new File(path2);
		modifytime = sourceFi.lastModified();
		destFi.setLastModified(modifytime);
		// ����ڲ��� hanbin
		String currName = path1.substring(path1.lastIndexOf("/") + 1);
		if (currName.lastIndexOf(".class") != -1)
			currName = currName.substring(0, currName.lastIndexOf(".class"));
		path1 = path1.substring(0, path1.lastIndexOf("/") + 1);
		path2 = path2.substring(0, path2.lastIndexOf("/") + 1);
		File currFolder = new File(path1);
		if (currFolder.exists()) {
			if (currFolder.isDirectory()) {
				File[] broFiles = currFolder.listFiles();
				for (File bro : broFiles) {
					String broName = bro.getName();
					if (broName != null && !"".equals(broName)) {
						if (broName.contains(currName)
								&& broName.indexOf("$") != -1
								&& !"".equals(currName)) {
							String internalClassPath = path1 + broName;
							String targetClassPath = path2 + broName;
							System.out.println("�ڲ��ࣺ" + internalClassPath);
							errFile = internalClassPath;
							fi = new FileInputStream(internalClassPath);
							fo = new FileOutputStream(targetClassPath);
							data = new byte[fi.available()];
							fi.read(data);
							fo.write(data);
							fi.close();
							fo.close();
							sourceFi = new File(internalClassPath);
							destFi = new File(targetClassPath);
							modifytime = sourceFi.lastModified();
							destFi.setLastModified(modifytime);
						}
					}
				}
			}
		}
	}
}
