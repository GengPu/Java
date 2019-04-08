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
//		unicode:����������ת��
//		unicode����Խ��������Ϣ��ʾΪ�κ�������ʽ�������ļ����������������֡������java��д�ĳ���Ҫ�������������������������������ĵġ���Ҫ��unicode�룬���ֻ��Ӣ�ģ���ascii��Ϳ���
			String s = "abcdefghijklmn";
			System.out.println(s.length());//14
			// ����ָ���������� char ֵ�� 
	        char result = s.charAt(0);//a
	       // char result1 = s.charAt(-1);//StringIndexOutOfBoundsException�����±�Խ��
	      // char result2 = s.charAt(14);//StringIndexOutOfBoundsException�����±�Խ��
//	        System.out.println(result);
	        
		// ����ָ�����������ַ���Unicode ����㣩
	     int i= s.codePointAt(0);//97
	     int i2= s.codePointAt(1);//98
//	     int i3= s.codePointAt(-1);//StringIndexOutOfBoundsException���±�Խ��
//	     System.out.println(i);

	    
		
		//����ָ������֮ǰ���ַ���Unicode ����㣩����ǰ�±��ǰһ��
//	     int j=s.codePointBefore(0);//StringIndexOutOfBoundsException���±�Խ��
	     int j2=s.codePointBefore(1);//98-1=97
//	     int j3=s.codePointBefore(15);//�±�Խ��,�ַ�������ĳ���û��15������Խ��
//	     int j4=s.codePointBefore(-1);//�±�Խ��,�ַ�������ĳ��Ȳ���-1
	    // System.out.println(j2);//

	     
	     // ���ش� String ��ָ���ı���Χ�е� Unicode ��������� ������ڷ�Χ��ֵ���±�Խ��
	     int k=s.codePointCount(0,s.length());//14
	     int k2=s.codePointCount(0,0);//0    
//	     System.out.println(k2);
	     
	     //���ֵ�˳��Ƚ������ַ�����unicodeֵ�Ƚ�
	     //���û���ַ���ͬ������λ�ã���϶��ַ������ֵ�˳���ڽϳ��ַ���֮ǰ������������£�compareTo �����������ַ������ȵĲ�
	     //Ҫô��ĳ�����������ַ���ͬ���������Զ��߾�Ϊ��Ч��������Ҫô���Ȳ�ͬ������ͬʱ�߱����������,k ��������������Сֵ,this.charAt(k)-anotherString.charAt(k)
	  int com=s.compareTo(s);//���Ϊ0
	  int com2=s.compareTo("abcdefghijklmn");//0
	  int com3=s.compareTo("abcdefghijk");//3�����������ַ������ȵĲ�'s'-abcdefghijk
	  int com4=s.compareTo("abcdefghijkamn");//11   l-a=108-97=11
	  int com5=s.compareTo("abcdefghijklmnopq");//-3�����������ַ������ȵĲ�abcdefghijklmnopq-"s"
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
	  
	  // ���ֵ�˳��Ƚ������ַ����������Ǵ�Сд��(�������ʹ�ù淶�����ַ������� compareTo ���÷�����ͬunicodeֵ�Ƚ�)
	  int comp=s.compareToIgnoreCase("abcdefghijklmn");//0
	  int comp2=s.compareToIgnoreCase("abc");//11  �ַ���ͬ�����Ȳ�ͬ���ַ����������
	  int comp3=s.compareToIgnoreCase("hij");//-7  a-h=97-104=-7
	  int comp4=s.compareToIgnoreCase("ddefgh");//-3  a-d=97-100=-3
//	  System.out.println(comp);
	
	 // ��ָ���ַ������ӵ����ַ����Ľ�β//str - ���ӵ��� String ��β�� String�� 
	 String con= s.concat("123");//abcdefghijklmn123
//	 System.out.println(con);
	 
	 //���ҽ������ַ�������ָ���� char ֵ����ʱ������ true��  //NullPointerException - ��� s Ϊ null
	 boolean contains=s.contains("a");//true
	 boolean contains2=s.contains("ac");//false
	// boolean contains3=s.contains(null);//NullPointerException
//	 System.out.println(contains2);
	 
	 //�����ַ�����ָ���� CharSequence �Ƚϡ�???????������ʲô��һ��
	 //�����ַ�����ָ���� StringBuffer �Ƚϡ� ???????
	 boolean contentEquals=s.contentEquals("ab");//false
	 boolean contentEquals2=s.contentEquals("abcdefghijklmn");//true
//	 System.out.println(contentEquals2);
	  
	 //���Դ��ַ����Ƿ���ָ���ĺ�׺����
	 boolean endsWith =s.endsWith("n");//true
	 boolean endsWith2 =s.endsWith("6");//false
	 boolean endsWith3 =s.endsWith("mn");//true
//	 System.out.println(endsWith);
	 
	 //  �����ַ�����ָ���Ķ���Ƚϡ�//��˶����ʾ��ͬ�ַ����е� String ����ʱ�������Ϊ true��
	 boolean equals2=s.equals("abcdefghijklmn");//true 
	 //System.out.println(equals2);
	 
	 //���� String ����һ�� String �Ƚϣ������Ǵ�Сд��
	 boolean equalsIgnoreCase=s.equalsIgnoreCase("abcdefghijklmn");//true
	 boolean equalsIgnoreCase2=s.equalsIgnoreCase("Abcdefghijklmn");//true
	 boolean equalsIgnoreCase3=s.equalsIgnoreCase("bcdefghijklmn");//false
//	 System.out.println(equalsIgnoreCase);
	
	 // ʹ��ƽ̨��Ĭ���ַ������� String ����Ϊ byte ���У���������洢��һ���µ� byte �����С� 
	 byte[] getBytes=s.getBytes();//
//	 System.out.println(getBytes[0]);//97 byte[] �����д�ŵ����ַ�����Ӧλ�ö�Ӧ����ĸ�Ĺ�ϣֵ
	 
	 // ʹ�ø����� charset ���� String ���뵽 byte ���У���������洢���µ� byte ����-----����
	/* try {
		byte[] getBytes2=s.getBytes("z");
		for (byte b : getBytes2) {
            System.out.println(b);
        }
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}*/
	 
	 //   ���ַ��Ӵ��ַ������Ƶ�Ŀ���ַ����顣 
	 char[] Str2 = new char[6];
//	 s.getChars(0, 1, Str2, 2);//"  a"ǰ���±�0,1���ÿո��ʾ
//	 s.getChars(0, 1, Str2, -1);//ArrayIndexOutOfBoundsException
//	 s.getChars(0, 15, Str2, 0);//StringIndexOutOfBoundsException
//	 s.getChars(0, 8, Str2, 0);//ArrayIndexOutOfBoundsException
//	 s.getChars(0, 6, Str2, 0);//abcdef
	 s.getChars(0, 6, Str2, 0);//�� 
//	 System.out.println(Str2);
	 
	 // ���ش��ַ����Ĺ�ϣ�롣
	 int hashCode=s.hashCode();//2093908199
	 int hashCode2="b".hashCode();//a 97 , b 98
//	 System.out.println(hashCode2);
	 
	 //����ָ���ַ��ڴ��ַ����е�һ�γ��ִ���������
	 int indexOf=s.indexOf("z");//-1�ַ���û�е� ��ʾΪ-1  z��0����  -1
	 int indexOf2=s.indexOf("c");//2
//	 System.out.println(indexOf2);
	 
	 //�����ڴ��ַ����е�һ�γ���ָ���ַ�������������ָ����������ʼ����
	 //fromIndex ��ֵû�����ơ������Ϊ����������Ϊ 0 ��Ч��ͬ���������������ַ�������������ڴ��ַ����ĳ��ȣ����������ڴ��ַ������ȵ�Ч����ͬ������ -1�� 
	 //indexOf(int ch,  int fromIndex)ch - һ���ַ���Unicode ����㣩��fromIndex - ��ʼ������������
	 String ind="saaccaasswsac";
//	 int indexOfa=ind.indexOf(97, 0);//1
//	 int indexOfa=ind.indexOf(97, 3);//5
//	 int indexOfa=ind.indexOf(97, -1);//1  
//	 int indexOfa=ind.indexOf(97, 55);//-1
	 
//	 System.out.println(indexOfa);
	 
//	 ����ָ�����ַ����ڴ��ַ����е�һ�γ��ִ��������� 
	 int indexOfb=ind.indexOf("ac");//2
//	 System.out.println(indexOfb);
	 
	 // ����ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�����������ָ����������ʼ�� 
	 int indexOfa=ind.indexOf("a", 3);//5
	 
	 //  �����ַ�������Ĺ淶����ʾ��ʽ�� 
	 
	 
	 
	 
	  // ���ҽ��� length() Ϊ 0 ʱ���� true��
	 boolean isEmpty =s.isEmpty();
	 String a="";
	 String b=null;
//	 boolean aa=a.isEmpty();
//	 boolean bb=b.isEmpty();
//	 System.out.println(isEmpty);//false
//	 System.out.println(aa);//true
//	 System.out.println(bb);//NullPointerException��ָ���쳣
	 
	 //����ָ���ַ��ڴ��ַ��������һ�γ��ִ��������� int lastIndexOf(int ch) 
	 int lastIndexOf=s.lastIndexOf(00);//-1
	 int lastIndexOf2=s.lastIndexOf(99);//2
//	 System.out.println(lastIndexOf2);
	 
	 //����ָ���ַ��ڴ��ַ��������һ�γ��ִ�����������ָ������������ʼ���з��������� int lastIndexOf(int ch, int fromIndex) 
	 int lastIndexOfa=s.lastIndexOf(99, 5);//2
	 int lastIndexOfb=s.lastIndexOf(99, 1);//-1û�е�   //��������
//	 System.out.println(lastIndexOfb);
	 
	 // ����ָ�����ַ����ڴ��ַ��������ұ߳��ִ���������  int lastIndexOf(String str) 
	 String lastInd="asbcdbef";
	 int lastIndexOfc=lastInd.lastIndexOf("b");//5 //���ұ߳��ִ�������
//	 System.out.println(lastIndexOfc);
	 
	 // ����ָ�����ַ����ڴ��ַ��������һ�γ��ִ�����������ָ����������ʼ�������� int lastIndexOf(String str, int fromIndex) 
	 String lastIn="asbcdbef";
	 int lastIndexOfd=lastIn.lastIndexOf("b",1);//-1
	 int lastIndexOfe=lastIn.lastIndexOf("b",7);//5��������
//	 System.out.println(lastIndexOfe);
	 
//     ���ش��ַ����ĳ��ȡ� 
	 int length =s.length();
//	 System.out.println(length);//14
	 
	 // ��֪���ַ����Ƿ�ƥ�������������ʽ�� 
	 boolean matches=s.matches("(.*)cd(.*)");//true
//	 System.out.println(matches);
	 
	 //���ش� String �дӸ����� index ��ƫ�� codePointOffset �������������� 
	 
	 
	 
	 //  ���������ַ��������Ƿ���ȡ� regionMatches(boolean ignoreCase, int toffset,  String other,  int ooffset,  int len)
     //	 ignoreCase - ���Ϊ true����Ƚ��ַ�ʱ���Դ�Сд
	 //���� toffset , ���� ooffset ����ʼ������Ϊ len��
	 // ���������ַ��������Ƿ���ȡ�  boolean regionMatches(int toffset, String other, int ooffset, int len) 
	 String Stra1 = new String("www.runoob.com");
     String Stra2 = new String("runoob");
     String Stra3 = new String("RUNOOB");
//     System.out.println(Stra1.regionMatches(4, Stra2, 0, 5));//true
//     System.out.println(Stra1.regionMatches(4, Stra3, 0, 5));//false
//     System.out.println(Stra1.regionMatches(true, 4, Stra3, 0, 5));//true
//     System.out.println(Stra1.regionMatches(false, 4, Stra3, 0, 5));//false
//
//     System.out.println(Stra1.regionMatches(4, Stra2, 0, 6));//true
	 
    //  ����һ���µ��ַ���������ͨ���� newChar �滻���ַ����г��ֵ����� oldChar �õ��ġ� 
     String replace=s.replace('b', 'c');//accdefghijklmn
     String replace2=s.replace('z', 'c');//abcdefghijklmn����ɵ��ַ������ڣ������ԭ��������
//     System.out.println(replace2);//
     
     //ʹ�ø����� replacement �滻���ַ�������ƥ�������������ʽ�����ַ�����
     String replaceAll=s.replaceAll("(.*)ef(.*)", "aaa");//aaa
     String replaceAll2=s.replaceAll("(.*)ef", "aaa");//aaaghijklmn
     String replaceAll3=s.replaceAll("(.*)efaa(.*)", "aaa");//abcdefghijklmn
//     System.out.println(replaceAll3);
	 
//ʹ�ø����� replacement �滻���ַ���ƥ�������������ʽ�ĵ�һ�����ַ�����   String replaceFirst(String regex, String replacement) 
     //replacement -- �����滻��һ��ƥ������ַ�����
     String Strf = new String("hello runoob��I am from runoob��...");
//     System.out.println(Strf.replaceFirst("runoob", "google" ));//hello google��I am from runoob��...
//     System.out.println(Strf.replaceFirst("(.*)runoob(.*)", "google" ));//google
//     System.out.println(Strf.replaceFirst("(.*)runoob", "google" ));//google��... //
//     System.out.println(Strf.replaceFirst("runoob(.*)", "google" ));//hello google   //
//     System.out.println(Strf.replaceFirst("runoob.*", "google" ));//hello google
	 
	 //   ���ݸ���������ʽ��ƥ���ִ��ַ����� 
     String spl = new String("Welcome-to-Runoob");
//     String[] split=spl.split("-");
//     for (String retval: split){
//         System.out.println(retval);
//     }
//     String spl2 = new String("acount=? and uu =? or n=?");
//     System.out.println("����ָ�������ֵ :" );
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
//     }//�ָ������
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
	  
     //���Դ��ַ����Ƿ���ָ����ǰ׺��ʼ��
     boolean startsWith=s.startsWith("a");
     boolean startsWith2=s.startsWith("ab");
     boolean startsWith3=s.startsWith("b");
//     System.out.println(startsWith);//true
//     System.out.println(startsWith2);//true
//     System.out.println(startsWith3);//false
    
     //���Դ��ַ�����ָ��������ʼ�����ַ����Ƿ���ָ��ǰ׺��ʼ��
     boolean startsWithb=s.startsWith("a",0);//true
     boolean startsWithb2=s.startsWith("a",1);//false
     boolean startsWithb3=s.startsWith("ab",0);//true
     boolean startsWithb4=s.startsWith("c",1);//false
//     System.out.println(startsWithb);
//     System.out.println(startsWithb2);
//     System.out.println(startsWithb3);
//     System.out.println(startsWithb4);
     
     //����һ���µ��ַ����У����Ǵ����е�һ�������С� abcdefghijklmn
//     beginIndex - ��ʼ��������������
//     endIndex - ��������������������
     CharSequence subSequence=s.subSequence(1, 3);//bc
//     CharSequence subSequence2=s.subSequence(-1, 3);//StringIndexOutOfBoundsException
//     CharSequence subSequence3=s.subSequence(1, 55);//StringIndexOutOfBoundsException
//     System.out.println(subSequence);

     //   ����һ���µ��ַ��������Ǵ��ַ�����һ�����ַ�����
     String substring=s.substring(3);
//     String substring2=s.substring(-1);//StringIndexOutOfBoundsException
//     String substring3=s.substring(20);//StringIndexOutOfBoundsException
//     System.out.println(substring);//defghijklmn
     
     //  ����һ�����ַ��������Ǵ��ַ�����һ�����ַ����� 
     String substringb=s.substring(3,5);//de
//     String substringc=s.substring(3,99);//StringIndexOutOfBoundsException
//     System.out.println(substringc);//
     
     //�����ַ���ת��Ϊһ���µ��ַ����顣char[] toCharArray() 
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
     
   // ʹ��Ĭ�����Ի����Ĺ��򽫴� String �е������ַ���ת��ΪСд��
     String aa="aAAAc255ssQQ";
     String toLowerCase =aa.toLowerCase();
//     System.out.println(toLowerCase);//aaaac255ssqq
     
     // String toLowerCase(Locale locale) //locale - ʹ�ô����Ի����Ĵ�Сдת������
//     ʹ�ø��� Locale �Ĺ��򽫴� String �е������ַ���ת��ΪСд�� ---------?????????????
     
     //     ���ش˶��������Ѿ���һ���ַ�������������ֵ:�ַ�������
     String toString=s.toString();
//     System.out.println(toString);//abcdefghijklmn
     
//     ʹ��Ĭ�����Ի����Ĺ��򽫴� String �е������ַ���ת��Ϊ��д��
     String toUpperCase=aa.toUpperCase(); 
//     System.out.println(toUpperCase);//AAAAC255SSQQ
     

     
//     String trim() 
//     �����ַ����ĸ���������ǰ���հ׺�β���հס� 
     String bb="  sss  aaq  ";
     String trim=bb.trim();
//     System.out.println(trim);//sss  aaq
     
     String st="1";
     String st2=st+"-1";
     System.out.println(st2);//1-1
     
     
     
     
     
     
     
	}
}
