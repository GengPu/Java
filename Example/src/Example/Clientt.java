package Example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Clientt {


	public static void main(String[] args) {
//		unicode:作用是中文转换
//		unicode码可以将计算机信息表示为任何语言形式——中文及其它国家语言文字。如果用java编写的程序要进行输入输出，输入输出的内容是中文的。就要用unicode码，如果只是英文，用ascii码就可以
			String s = "abcdefghijklmn";
			System.out.println(s.length());//14
			// 返回指定索引处的 char 值。 
	        char result = s.charAt(0);//a
	       // char result1 = s.charAt(-1);//StringIndexOutOfBoundsException访问下标越界
	      // char result2 = s.charAt(14);//StringIndexOutOfBoundsException访问下标越界
//	        System.out.println(result);
	        
		// 返回指定索引处的字符（Unicode 代码点）
	     int i= s.codePointAt(0);//97
	     int i2= s.codePointAt(1);//98
//	     int i3= s.codePointAt(-1);//StringIndexOutOfBoundsException问下标越界
//	     System.out.println(i);

	    
		
		//返回指定索引之前的字符（Unicode 代码点）。当前下标的前一个
//	     int j=s.codePointBefore(0);//StringIndexOutOfBoundsException问下标越界
	     int j2=s.codePointBefore(1);//98-1=97
//	     int j3=s.codePointBefore(15);//下标越界,字符串本身的长度没有15，所以越界
//	     int j4=s.codePointBefore(-1);//下标越界,字符串本身的长度不能-1
	    // System.out.println(j2);//

	     
	     // 返回此 String 的指定文本范围中的 Unicode 代码点数。 如果不在范围的值会下标越界
	     int k=s.codePointCount(0,s.length());//14
	     int k2=s.codePointCount(0,0);//0    
//	     System.out.println(k2);
	     
	     //按字典顺序比较两个字符串。unicode值比较
	     //如果没有字符不同的索引位置，则较短字符串的字典顺序在较长字符串之前。在这种情况下，compareTo 返回这两个字符串长度的差
	     //要么在某个索引处的字符不同（该索引对二者均为有效索引），要么长度不同，或者同时具备这两种情况,k 是这类索引的最小值,this.charAt(k)-anotherString.charAt(k)
	  int com=s.compareTo(s);//相等为0
	  int com2=s.compareTo("abcdefghijklmn");//0
	  int com3=s.compareTo("abcdefghijk");//3返回这两个字符串长度的差's'-abcdefghijk
	  int com4=s.compareTo("abcdefghijkamn");//11   l-a=108-97=11
	  int com5=s.compareTo("abcdefghijklmnopq");//-3返回这两个字符串长度的差abcdefghijklmnopq-"s"
	  int com6=s.compareTo("abcdefghmn");//-4 this.charAt(k)-anotherString.charAt(k) :i-m=105-109=-4
	  int com7=s.compareTo("eefghijklmn");//-4 a-e=97-101=-4

	  int com8=s.compareTo("Abcdefghijklmn");//32  a-A= 97-65=32
	  int com9=s.compareTo("abcdefghijklmn123");//-3
	  int com10=s.compareTo("1bcdefghijklmn");//48  a-1=97-49=48
	  
	  String s2="abcdefghijklmn";
	  String s3="1bcdefghijklmn";
	  int code= s.codePointAt(0);//97
	  int code2= s2.codePointAt(0);//65
	  int code3= s3.codePointAt(0);//49
	  
//	  int code4= s.codePointAt(12);//
//	  System.out.println(code4);
	  
	  // 按字典顺序比较两个字符串，不考虑大小写。(其符号与使用规范化的字符串调用 compareTo 所得符号相同unicode值比较)
	  int comp=s.compareToIgnoreCase("abcdefghijklmn");//0
	  int comp2=s.compareToIgnoreCase("abc");//11  字符相同，长度不同，字符串长度相减
	  int comp3=s.compareToIgnoreCase("hij");//-7  a-h=97-104=-7
	  int comp4=s.compareToIgnoreCase("ddefgh");//-3  a-d=97-100=-3
//	  System.out.println(comp);
	
	 // 将指定字符串连接到此字符串的结尾//str - 连接到此 String 结尾的 String。 
	 String con= s.concat("123");//abcdefghijklmn123
//	 System.out.println(con);
	 
	 //当且仅当此字符串包含指定的 char 值序列时，返回 true。  //NullPointerException - 如果 s 为 null
	 boolean contains=s.contains("a");//true
	 boolean contains2=s.contains("ac");//false
	// boolean contains3=s.contains(null);//NullPointerException
//	 System.out.println(contains2);
	 
	 //将此字符串与指定的 CharSequence 比较。???????两者有什么不一样
	 //将此字符串与指定的 StringBuffer 比较。 ???????
	 boolean contentEquals=s.contentEquals("ab");//false
	 boolean contentEquals2=s.contentEquals("abcdefghijklmn");//true
//	 System.out.println(contentEquals2);
	  
	 //测试此字符串是否以指定的后缀结束
	 boolean endsWith =s.endsWith("n");//true
	 boolean endsWith2 =s.endsWith("6");//false
	 boolean endsWith3 =s.endsWith("mn");//true
//	 System.out.println(endsWith);
	 
	 //  将此字符串与指定的对象比较。//与此对象表示相同字符序列的 String 对象时，结果才为 true。
	 boolean equals2=s.equals("abcdefghijklmn");//true 
	 //System.out.println(equals2);
	 
	 //将此 String 与另一个 String 比较，不考虑大小写。
	 boolean equalsIgnoreCase=s.equalsIgnoreCase("abcdefghijklmn");//true
	 boolean equalsIgnoreCase2=s.equalsIgnoreCase("Abcdefghijklmn");//true
	 boolean equalsIgnoreCase3=s.equalsIgnoreCase("bcdefghijklmn");//false
//	 System.out.println(equalsIgnoreCase);
	
	 // 使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。 
	 byte[] getBytes=s.getBytes();//
//	 System.out.println(getBytes[0]);//97 byte[] 数组中存放的是字符串响应位置对应的字母的哈希值
	 
	 // 使用给定的 charset 将此 String 编码到 byte 序列，并将结果存储到新的 byte 数组-----不行
	/* try {
		byte[] getBytes2=s.getBytes("z");
		for (byte b : getBytes2) {
            System.out.println(b);
        }
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}*/
	 
	 //   将字符从此字符串复制到目标字符数组。 
	 char[] Str2 = new char[6];
//	 s.getChars(0, 1, Str2, 2);//"  a"前面下标0,1的用空格表示
//	 s.getChars(0, 1, Str2, -1);//ArrayIndexOutOfBoundsException
//	 s.getChars(0, 15, Str2, 0);//StringIndexOutOfBoundsException
//	 s.getChars(0, 8, Str2, 0);//ArrayIndexOutOfBoundsException
//	 s.getChars(0, 6, Str2, 0);//abcdef
	 s.getChars(0, 6, Str2, 0);//空 
//	 System.out.println(Str2);
	 
	 // 返回此字符串的哈希码。
	 int hashCode=s.hashCode();//2093908199
	 int hashCode2="b".hashCode();//a 97 , b 98
//	 System.out.println(hashCode2);
	 
	 //返回指定字符在此字符串中第一次出现处的索引。
	 int indexOf=s.indexOf("z");//-1字符串没有的 显示为-1  z和0都是  -1
	 int indexOf2=s.indexOf("c");//2
//	 System.out.println(indexOf2);
	 
	 //返回在此字符串中第一次出现指定字符处的索引，从指定的索引开始搜索
	 //fromIndex 的值没有限制。如果它为负，则与它为 0 的效果同样：将搜索整个字符串。如果它大于此字符串的长度，则与它等于此字符串长度的效果相同：返回 -1。 
	 //indexOf(int ch,  int fromIndex)ch - 一个字符（Unicode 代码点）。fromIndex - 开始搜索的索引。
	 String ind="saaccaasswsac";
//	 int indexOfa=ind.indexOf(97, 0);//1
//	 int indexOfa=ind.indexOf(97, 3);//5
//	 int indexOfa=ind.indexOf(97, -1);//1  
//	 int indexOfa=ind.indexOf(97, 55);//-1
	 
//	 System.out.println(indexOfa);
	 
//	 返回指定子字符串在此字符串中第一次出现处的索引。 
	 int indexOfb=ind.indexOf("ac");//2
//	 System.out.println(indexOfb);
	 
	 // 返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始。 
	 int indexOfa=ind.indexOf("a", 3);//5
	 
	 //  返回字符串对象的规范化表示形式。 
	 
	 
	 
	 
	  // 当且仅当 length() 为 0 时返回 true。
	 boolean isEmpty =s.isEmpty();
	 String a="";
	 String b=null;
//	 boolean aa=a.isEmpty();
//	 boolean bb=b.isEmpty();
//	 System.out.println(isEmpty);//false
//	 System.out.println(aa);//true
//	 System.out.println(bb);//NullPointerException空指针异常
	 
	 //返回指定字符在此字符串中最后一次出现处的索引。 int lastIndexOf(int ch) 
	 int lastIndexOf=s.lastIndexOf(00);//-1
	 int lastIndexOf2=s.lastIndexOf(99);//2
//	 System.out.println(lastIndexOf2);
	 
	 //返回指定字符在此字符串中最后一次出现处的索引，从指定的索引处开始进行反向搜索。 int lastIndexOf(int ch, int fromIndex) 
	 int lastIndexOfa=s.lastIndexOf(99, 5);//2
	 int lastIndexOfb=s.lastIndexOf(99, 1);//-1没有的   //反向搜索
//	 System.out.println(lastIndexOfb);
	 
	 // 返回指定子字符串在此字符串中最右边出现处的索引。  int lastIndexOf(String str) 
	 String lastInd="asbcdbef";
	 int lastIndexOfc=lastInd.lastIndexOf("b");//5 //最右边出现处的索引
//	 System.out.println(lastIndexOfc);
	 
	 // 返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索 int lastIndexOf(String str, int fromIndex) 
	 String lastIn="asbcdbef";
	 int lastIndexOfd=lastIn.lastIndexOf("b",1);//-1
	 int lastIndexOfe=lastIn.lastIndexOf("b",7);//5反向搜索
//	 System.out.println(lastIndexOfe);
	 
//     返回此字符串的长度。 
	 int length =s.length();
//	 System.out.println(length);//14
	 
	 // 告知此字符串是否匹配给定的正则表达式。 
	 boolean matches=s.matches("(.*)cd(.*)");//true
//	 System.out.println(matches);
	 
	 //返回此 String 中从给定的 index 处偏移 codePointOffset 个代码点的索引。 
	 
	 
	 
	 //  测试两个字符串区域是否相等。 regionMatches(boolean ignoreCase, int toffset,  String other,  int ooffset,  int len)
     //	 ignoreCase - 如果为 true，则比较字符时忽略大小写
	 //索引 toffset , 索引 ooffset 处开始，长度为 len。
	 // 测试两个字符串区域是否相等。  boolean regionMatches(int toffset, String other, int ooffset, int len) 
	 String Stra1 = new String("www.runoob.com");
     String Stra2 = new String("runoob");
     String Stra3 = new String("RUNOOB");
//     System.out.println(Stra1.regionMatches(4, Stra2, 0, 5));//true
//     System.out.println(Stra1.regionMatches(4, Stra3, 0, 5));//false
//     System.out.println(Stra1.regionMatches(true, 4, Stra3, 0, 5));//true
//     System.out.println(Stra1.regionMatches(false, 4, Stra3, 0, 5));//false
//
//     System.out.println(Stra1.regionMatches(4, Stra2, 0, 6));//true
	 
    //  返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。 
     String replace=s.replace('b', 'c');//accdefghijklmn
     String replace2=s.replace('z', 'c');//abcdefghijklmn如果旧的字符不存在，则输出原来的数据
//     System.out.println(replace2);//
     
     //使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。
     String replaceAll=s.replaceAll("(.*)ef(.*)", "aaa");//aaa
     String replaceAll2=s.replaceAll("(.*)ef", "aaa");//aaaghijklmn
     String replaceAll3=s.replaceAll("(.*)efaa(.*)", "aaa");//abcdefghijklmn
//     System.out.println(replaceAll3);
	 
//使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串。   String replaceFirst(String regex, String replacement) 
     //replacement -- 用来替换第一个匹配项的字符串。
     String Strf = new String("hello runoob，I am from runoob。...");
//     System.out.println(Strf.replaceFirst("runoob", "google" ));//hello google，I am from runoob。...
//     System.out.println(Strf.replaceFirst("(.*)runoob(.*)", "google" ));//google
//     System.out.println(Strf.replaceFirst("(.*)runoob", "google" ));//google。... //
//     System.out.println(Strf.replaceFirst("runoob(.*)", "google" ));//hello google   //
//     System.out.println(Strf.replaceFirst("runoob.*", "google" ));//hello google
	 
	 //   根据给定正则表达式的匹配拆分此字符串。 
     String spl = new String("Welcome-to-Runoob");
//     String[] split=spl.split("-");
//     for (String retval: split){
//         System.out.println(retval);
//     }
//     String spl2 = new String("acount=? and uu =? or n=?");
//     System.out.println("多个分隔符返回值 :" );
//     for (String retval: spl2.split("and|or")){
//         System.out.println(retval);
//     }
     /*
      * acount=? 
 		uu =? 
 		n=?
      * */
     String str3 = new String("www.runoob.com");
//     for (String retval: str3.split("\\.", 2)){
//         System.out.println(retval);
//     }//分割分数，
     //www
     //runoob.com
     String str4 = new String("www.runoob.com");
//     for (String retval: str4.split("\\.", 4)){
//         System.out.println(retval);
//     }
	 /*	www
		runoob
		com
	  */
	  
     //测试此字符串是否以指定的前缀开始。
     boolean startsWith=s.startsWith("a");
     boolean startsWith2=s.startsWith("ab");
     boolean startsWith3=s.startsWith("b");
//     System.out.println(startsWith);//true
//     System.out.println(startsWith2);//true
//     System.out.println(startsWith3);//false
    
     //测试此字符串从指定索引开始的子字符串是否以指定前缀开始。
     boolean startsWithb=s.startsWith("a",0);//true
     boolean startsWithb2=s.startsWith("a",1);//false
     boolean startsWithb3=s.startsWith("ab",0);//true
     boolean startsWithb4=s.startsWith("c",1);//false
//     System.out.println(startsWithb);
//     System.out.println(startsWithb2);
//     System.out.println(startsWithb3);
//     System.out.println(startsWithb4);
     
     //返回一个新的字符序列，它是此序列的一个子序列。 abcdefghijklmn
//     beginIndex - 起始索引（包括）。
//     endIndex - 结束索引（不包括）。
     CharSequence subSequence=s.subSequence(1, 3);//bc
//     CharSequence subSequence2=s.subSequence(-1, 3);//StringIndexOutOfBoundsException
//     CharSequence subSequence3=s.subSequence(1, 55);//StringIndexOutOfBoundsException
//     System.out.println(subSequence);

     //   返回一个新的字符串，它是此字符串的一个子字符串。
     String substring=s.substring(3);
//     String substring2=s.substring(-1);//StringIndexOutOfBoundsException
//     String substring3=s.substring(20);//StringIndexOutOfBoundsException
//     System.out.println(substring);//defghijklmn
     
     //  返回一个新字符串，它是此字符串的一个子字符串。 
     String substringb=s.substring(3,5);//de
//     String substringc=s.substring(3,99);//StringIndexOutOfBoundsException
//     System.out.println(substringc);//
     
     //将此字符串转换为一个新的字符数组。char[] toCharArray() 
     char[] toCharArray=s.toCharArray();
//     System.out.println(toCharArray);//toCharArray
//     for(char c :toCharArray){
//    	 System.out.println(c);
//    	 /* a
//			b
//			c
//			d
//			e
//			f
//			g
//			h
//			i
//			j
//			k
//			l
//			m
//			n*/
//     }
     
   // 使用默认语言环境的规则将此 String 中的所有字符都转换为小写。
     String aa="aAAAc255ssQQ";
     String toLowerCase =aa.toLowerCase();
//     System.out.println(toLowerCase);//aaaac255ssqq
     
     // String toLowerCase(Locale locale) //locale - 使用此语言环境的大小写转换规则
//     使用给定 Locale 的规则将此 String 中的所有字符都转换为小写。 ---------?????????????
     
     //     返回此对象本身（它已经是一个字符串！）。返回值:字符串本身。
     String toString=s.toString();
//     System.out.println(toString);//abcdefghijklmn
     
//     使用默认语言环境的规则将此 String 中的所有字符都转换为大写。
     String toUpperCase=aa.toUpperCase(); 
//     System.out.println(toUpperCase);//AAAAC255SSQQ
     

     
//     String trim() 
//     返回字符串的副本，忽略前导空白和尾部空白。 
     String bb="  sss  aaq  ";
     String trim=bb.trim();
//     System.out.println(trim);//sss  aaq
     
     String st="1";
     String st2=st+"-1";
     System.out.println(st2);//1-1
     
     
     
     
     
     
     
	}
}
