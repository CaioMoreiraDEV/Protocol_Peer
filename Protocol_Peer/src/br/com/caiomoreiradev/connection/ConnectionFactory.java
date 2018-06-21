package br.com.caiomoreiradev.connection;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ConnectionFactory {
	private DatagramSocket datagramSocket = null;
	private InetAddress address = null;
	private int port = 0;
	
	public ConnectionFactory() {
		try {
			this.datagramSocket = new DatagramSocket(0);
			this.address = InetAddress.getLocalHost();
			this.port = datagramSocket.getLocalPort();
		} catch (SocketException e) {
			System.out.println("Connection Error [SocketException] - "+e);
		} catch (UnknownHostException e) {
			System.out.println("Connection Error [UnknownHostException] - "+e);
		}
	}
	
	public DatagramSocket getDatagramSocket() {
		return this.datagramSocket;
	}
	
	public InetAddress getAddress() {
		return this.address;
	}
	
	public String getName() {
		return this.address.getHostName();
	}
	
	public String getIP() {
		return this.address.getHostAddress();
	}
	
	public int getPort() {
		return this.port;
	}
	
	public void info() {
		System.out.println("Address: "+getAddress()+" - Port: "+getPort()+"\n");
	}
}
