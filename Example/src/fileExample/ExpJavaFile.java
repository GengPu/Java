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
 * 通过指定文件路径列表，获取对应java文件或class文件（若为jsp和js文件则不同的模式均导出一样的路径）
 * 当项目名称出现变化时此程序退出，不允许出现两个项目同时导出
 * 
 * @author ligen
 * 
 */
public class ExpJavaFile {

	private static String errMsg = "";
	private static String errFile = "";// 出错的文件名
	private static final String fileurlList = "D:/Myeclipse/fileList.txt";// 修改程序列表
	private static final String workspace = "D:\\Myeclipse\\workspace";// 项目所在工作空间
	private static final String target = "D:\\Myeclipse\\FileList";// 目标目录
	private static final String projectName = "TestWeb"; // 项目名称
	private static final String filelistLinkType = "/";// 在fileNameList对应的文件连接符
	private static List<String> srclist = new ArrayList<String>();// 该属性仅java代码以及非配置类xml文件使用
																	// 源文件目录的根
	private static String output = "";// 该属性仅java代码以及非配置类xml文件使用 编译到目录的根

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
	 * 根据规则获取对应class文件与class目标文件
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
				System.out.println("请选择……");
				System.out.println("0、退出。");
				System.out.println("1、生成源代码。");
				System.out.println("2、生成class代码。");
				System.out.println("3、生成源代码及class代码。");

				str = stdin.readLine();

				if (str.equals(""))
					continue;

				if (str.equals("0")) {
					System.out.println("程序退出！");
					stdin.close();
					System.exit(0);
				}else{
					flag = false;
				}

			}

			ArrayList<String> list = new ArrayList<String>();
			String djpath = "";// 登记的文件路径
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
				String fileurl = workspace+File.separator+projectName+File.separator+(list.get(i).replaceFirst(filelistLinkType+projectName+filelistLinkType, ""));//源代码路径
				System.out.println("源文件："+fileurl);
				String targeturl = target+File.separator+projectName+File.separator+(list.get(i).replaceFirst(filelistLinkType+projectName+filelistLinkType, ""));//目标存放源代码路径
				if (!"2".equals(str)) {// 若不没有选择2则必然要出java代码
					copyFile(fileurl, targeturl);
				}
				if (!"1".equals(str)) {// 若不选择1则必然要出class代码
					String classurl = getClassUrl(fileurl);//class文件路径（仅java代码与非配置xml文件有变化）
					System.out.println("class文件："+classurl);
					String classtargeturl = getClassUrl(targeturl);//目标class文件路径（仅java代码与非配置xml文件有变化）
					copyFile(classurl, classtargeturl);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 设置编译路径
	 * 
	 * @param workspace
	 *            工作空间
	 * @param projectName
	 *            项目名称
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
			nodeList = doc.getElementsByTagName("classpathentry");// 获取所有节点名称为classpathentry的节点
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
			NamedNodeMap attrsList = node.getAttributes();// 一个节点上的属性节点
			for (int j = 0; j < attrsList.getLength(); j++) {
				Node attr = attrsList.item(j);
				if ((attr.getNodeName().equals("kind") && attr.getNodeValue()
						.equals("src"))
						|| (attr.getNodeName().equals("kind") && attr
								.getNodeValue().equals("output"))) {// 获取符合项目部署规则的节点
					nodeLists.add(node);
				} else {
					break;
				}
			}
		}

		for (int i = 0; i < nodeLists.size(); i++) {
			Node node = nodeLists.get(i);
			NamedNodeMap attrsList = node.getAttributes();// 一个节点上的属性节点
			// System.out.println("节点属性数量为："+attrsList.getLength());
			// System.out.println("属性名称1：" + attrsList.item(0).getNodeName());
//			System.out.println("属性值为1：" + attrsList.item(0).getNodeValue());
			// System.out.println("属性名称2：" + attrsList.item(1).getNodeName());
//			System.out.println("属性值为2：" + attrsList.item(1).getNodeValue());
			if (attrsList.item(0).getNodeValue().equals("src")) {// 一个项目src级别的目录可能很多但是output目录只有一个
				srclist.add(attrsList.item(1).getNodeValue());
			} else {
				output = attrsList.item(1).getNodeValue();
			}
		}

	}

	private static void copyFile(String path1, String path2) throws IOException {
		long modifytime = 0;// 上次修改时间
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
		// 打包内部类 hanbin
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
							System.out.println("内部类：" + internalClassPath);
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
