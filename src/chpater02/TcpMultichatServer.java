package chpater02;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TcpMultichatServer {
	public static Map<String, DataOutputStream> clients;
	static {
		// ConcurrentHashMap-> 쓰레드에 안전한 해시맵
		// 쓰레드에 안전하다 -> 한 쓰레드가 이 맵을 사용하고 있을 때
		// 다른 쓰레드들은 이 맵을 사용할 수 없게끔 만드는 것.
		
		clients = new ConcurrentHashMap<>();
	}
	
	
	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			
			while(true) {
				socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + " : " + socket.getPort() + "] 에서 접속하였습니다.");
				
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		}catch(IOException e) {
				e.printStackTrace();
			}
		}
		public static void sendToAll(String msg) {
			Iterator<String> it = clients.keySet().iterator();
			
			while(it.hasNext()) {
				try {
				String key = it.next();
				DataOutputStream out = (DataOutputStream) clients.get(key);
				out.writeUTF(msg);
			}catch(IOException e) {
				e.printStackTrace();
			}//end try
		}//end while
	}//end SendToAll
		
		public static void main(String[] args) {
			new TcpMultichatServer().start();
		}
}

