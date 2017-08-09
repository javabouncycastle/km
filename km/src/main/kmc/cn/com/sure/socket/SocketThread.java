/**
 * 
 */
package cn.com.sure.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.sure.km.ResourceBundleSocketMessage;


/**
 * @author Limin
 *
 */
public class SocketThread extends Thread{
	
	private static final Log LOG = LogFactory.getLog(SocketThread.class);

	private ServerSocket serverSocket;
	private SocketService socketService;
	
	public SocketThread(ServerSocket serverSocket){
		this.serverSocket=serverSocket;
	}
	public SocketThread(SocketService socketService){
		this.socketService=socketService;
	}

	public void run(){
		
		 try {  
            if(null == serverSocket){  
            	//1.启动一个新的socketServer
            	ResourceBundleSocketMessage rbem = ResourceBundleSocketMessage.getInstance();
                this.serverSocket = new ServerSocket(Integer.parseInt(rbem.getMessage("port",  new Object[]{ "",""})));  
                LOG.debug("socket端口"+Integer.parseInt(rbem.getMessage("port",  new Object[]{ "",""})));
                LOG.debug("socket start");
                
                while (true) {
                    // 侦听并接受到此Socket的连接,请求到来则产生一个Socket对象，并继续执行
                    Socket socket = serverSocket.accept();
      
                    /** 获取客户端传来的信息 */
                    // 由Socket对象得到输入流，并构造相应的BufferedReader对象
                    BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // 获取从客户端读入的字符串
                    String requestInfo = bufferedReader.readLine();
                    
                    byte[] responseByte = socketService.handleSocket(requestInfo);
                    
                    System.out.println("Client say : " + requestInfo);
                    
                    String result = new String(responseByte);
                    
                    System.out.println(result);
      
                    /** 发送服务端准备传输的 */
                    // 由Socket对象得到输出流，并构造PrintWriter对象
                    PrintWriter printWriter =new PrintWriter(socket.getOutputStream());
                    printWriter.print(result);
                    printWriter.flush();
      
                    /** 关闭Socket*/
                    printWriter.close();
                    bufferedReader.close();
                    socket.close();
                } 
                          
                   
                }  
            
	     }catch (Exception e) {  
	            System.out.println("SocketThread创建socket服务出错");  
	            e.printStackTrace();  
	      }
	}

}
