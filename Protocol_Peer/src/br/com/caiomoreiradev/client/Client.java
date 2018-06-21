package br.com.caiomoreiradev.client;

import java.util.Scanner;

import br.com.caiomoreiradev.connection.ConnectionFactory;
import br.com.caiomoreiradev.connection.DocsManipulador;
import br.com.caiomoreiradev.connection.Listen;
import br.com.caiomoreiradev.services.Services;

public class Client {
	private static String pathPeers = "C:/Users/caio/workspace/Protocol_Peer/docs/peers.txt";
	private static ConnectionFactory conn;
	private static DocsManipulador docs;
	private static Services services;
	private static Scanner in;
	
	public static void main(String[] args) {
		docs = new DocsManipulador();
		services = new Services();
		in = new Scanner(System.in);
		conn = new ConnectionFactory();
		conn.info();
		
		String line = ""+conn.getName()+":"+conn.getIP()+":"+conn.getPort();
		DocsManipulador.writeFile(pathPeers, line.trim());
		System.out.println("LISTA DOS PEERS ATIVOS:\n");
		DocsManipulador.readFile(pathPeers);
		
		new Listen(conn.getPort());
		
		int op = -1;
		do {
			System.out.println("__________________________________________________________________\n"
							+"1 - Refresh list of users.\n"
							+"2 - Send file.\n"
							+"0 - Exit.\n"
							+"__________________________________________________________________\n");
			op = services.selectOption();
		
			switch (op) {
				case 0:
					docs.removeLine(pathPeers, line);
					System.out.println("Bye!");
					System.exit(0);
					break;
				
				case 1:
					DocsManipulador.readFile(pathPeers);
					break;
	
				case 2:
					System.out.println("Sending format: [IP, Port, PathFile]");
					String ip = in.next();
					int port = in.nextInt();
					String pathFile = in.next();
					
					services.sendFile(ip, port, pathFile);
					break;
					
				default:
					System.out.println("Please choose another option.");
					break;
			}
		} while(op != 0);
	}
}
