package chpater02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReceiver extends Thread {
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	
	public ServerReceiver(Socket socket) {
		this.socket = socket;
		try {
			
		
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}catch(IOException e) {
		e.printStackTrace();
	}
}
	public void run() {
		String name = "";
		
		try {
		name = in.readUTF();
		
		TcpMultichatServer.sendToAll("#" + name + "���� �����̽��ϴ�.");
		
		TcpMultichatServer.clients.put(name, out);
		System.out.println("���� ä�� ���� ������ ���� " + TcpMultichatServer.clients.size() + "���Դϴ�.");
		
		while(in != null) {
			TcpMultichatServer.sendToAll(in.readUTF());
		}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			TcpMultichatServer.sendToAll("# " + name + "���� �����̽��ϴ�.");
			
			TcpMultichatServer.clients.remove(name);
			System.out.println("���� ä�� ���� ������ ���� " + TcpMultichatServer.clients.size() + "���Դϴ�.");
		}//end try
		}//end run
	}

