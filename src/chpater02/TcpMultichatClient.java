package chpater02;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class TcpMultichatClient  {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage : java chapter02.TcmpMultichatClient ���̵�");
			System.exit(0);
		}
		
		try {
		String serverIp = "127.0.0.1";
		
		Socket socket = new Socket(serverIp, 7777);
		System.out.println("������ ����Ǿ����ϴ�.");
		
		Thread sender = new Thread(new ClientSender(socket, args[0]));
		Thread receiver = new Thread(new ClientReceiver(socket));
		
		sender.start();
		receiver.start();
	}catch(ConnectException e) {
		e.printStackTrace();
	   }catch(IOException e) {
		   e.printStackTrace();
	   }
	}
}
