package chpater02;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReceiver	extends Thread {
	
	Socket socket;
	DataInputStream in;
	
	public ClientReceiver(Socket socket) {
		this.socket = socket;
		try {
			in = new DataInputStream(socket.getInputStream());
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void run() {
		while( in != null) {
			try {
				System.out.println(in.readUTF());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}
