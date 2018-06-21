package br.com.caiomoreiradev.services;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Services {
	private Scanner in = new Scanner(System.in);
	private Socket socket;
	private DataOutputStream dataOut;
	private FileInputStream file;

	public int selectOption() {
		int num = -1;
		try {
			num = in.nextInt();
			if(num >= 0 & num <= 3) return num;
		} catch (NumberFormatException e) {
			System.out.println("Error [NumberFormatException]: "+e);
			return -1;
		}	
		return -1;
	}
	
	public void sendFile(String ip, int port, String path) {
		try {
			socket = new Socket(ip, port);
            setDataOut(new DataOutputStream(socket.getOutputStream()));
            
            file = new FileInputStream(path);
            
            System.out.println("Please enter the file name and format.");
            String nameFile = in.next();
            dataOut.writeUTF(nameFile);

            byte[] buffer = new byte[2048];

            int length = 0;
            while (true){
                length = file.read(buffer);
                if(length != -1) dataOut.write(buffer, 0, length);
            }
		} catch (UnknownHostException e) {
			System.out.println("Error [NumberFormatException]: "+e);
		} catch (SocketException e) {
			System.out.println("Error [SocketException]: "+e);
		} catch (IOException e) {
			System.out.println("Error [IOException]: "+e);
		}
	}

	public DataOutputStream getDataOut() {
		return dataOut;
	}

	public void setDataOut(DataOutputStream dataOut) {
		this.dataOut = dataOut;
	}
}
