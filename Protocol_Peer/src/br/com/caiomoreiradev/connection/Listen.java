package br.com.caiomoreiradev.connection;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listen implements Runnable {
	protected ServerSocket server;
	protected FileInputStream file;
	protected int port;
	
	public Listen(int port) {
		try {
			this.port = port;
			this.server = new ServerSocket(port);
			new Thread().start();
		} catch (IOException e) {
			System.out.println("Error [IOException] - "+e);
		}
	}

	public void run() {
		try {
			System.out.println(""+server.getLocalSocketAddress()+":"+server.getLocalPort());
			
			while(true) {
				Socket client = server.accept();
				DataInputStream dataIn = new DataInputStream(client.getInputStream());
				
				System.out.println("Receiving file of the user "+client.getInetAddress().getHostName());
				
				String nameFile = dataIn.readUTF();
				File archive = new File(nameFile);
	            archive.createNewFile();
	            
				setFile(new FileInputStream("C:/Users/caio/workspace/Protocol_Peer/received/"+nameFile));

				byte[] buffer = new byte[4096];
				
	            int length = 0;
	            while(true) {
	                length = dataIn.read(buffer);
	                if (length != -1) dataIn.read(buffer, 0, length);
	            }
			}
		} catch (IOException e) {
			System.out.println("Error [IOException] - "+e);
		}
	}

	public FileInputStream getFile() {
		return file;
	}

	public void setFile(FileInputStream file) {
		this.file = file;
	}
}
