/**
 * 
 */
package cn.com.sure.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.sure.km.KmApplicationexception;


/**
 * @author Limin
 *
 */
public class SocketThread extends Thread{
	
	private static final Log LOG = LogFactory.getLog(SocketThread.class);

	private SocketService socketService;
	
	private Socket socket;
	

	public SocketThread(Socket socket,SocketService socketService) {
		this.socket=socket;
		this.socketService=socketService;
	}
	
	
	public void run(){
		
		try {
			 
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			dis = new DataInputStream(socket.getInputStream());  
	         
			byte[] reqlenbyte = new byte[4];//请求的byte的长度
			int reqlen = 0;
			byte[] buffer = new byte[1024];//缓存数据，每次读取byte的一部分
			int bufbuflength = 0;
			
			int bufferlength = dis.read(reqlenbyte);
			
			if(bufferlength == 4){
				reqlen =byteToInt(reqlenbyte);
			}
			
			byte[] reqinfo = new byte[reqlen];				
			System.out.println("begin receive need:" + reqlen);								
			
			while(reqlen > 0){
				bufferlength = dis.read(buffer);
				if(bufferlength > 0){						
					//源数组,源数组要复制的起始位置,目的数组,目的数组放置的起始位置,复制的长度
					System.arraycopy(buffer, 0, reqinfo, bufbuflength, bufferlength);
					bufbuflength = bufbuflength + bufferlength;
					reqlen = reqlen - bufferlength;							
				}
			}
			
			byte[] responseByte=null;
 	        
 	        try {
				responseByte = socketService.handleSocket(reqinfo);
				
				
			} catch (InvalidKeyException | NoSuchAlgorithmException
					| InvalidKeySpecException | NoSuchPaddingException
					| IllegalBlockSizeException | BadPaddingException
					| ParseException | KmApplicationexception 
					|CertificateException e) {
				e.printStackTrace();
			}
	        
	        
	        
	        
	        //返回 
	        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			byte[] byt = responseByte;
	        
	        dos.write(byt);
	        dos.flush();
	        
	        
	        dos.close();
	        dis.close();
		    socket.close();
		} catch (IOException e) {
			System.out.println("error==");
			e.printStackTrace();
		}
		
		
	}
	public static int byteToInt(byte[] byaValue) {         
		int nValue = 0; 
		for (int i = 0; i < byaValue.length; i++){          
			nValue += (byaValue[i] & 0xFF) << (8 * (3 - i)); 
		}
		return nValue;
	}
}
