package ba.bitcamp.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents the server.
 * 
 * @author Zeljko Miljevic
 */
public class Server {
	
	public static final int PORT = 2424;

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("server started");
			Socket client = server.accept();
	
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

			File file = new File(reader.readLine());
			if (file.exists()) {
				writer.write("1");
				writer.newLine();
				writer.flush();
			} else {
				writer.write("0");
				writer.newLine();
				System.out.println("There is no such file.");
				writer.flush();
			}
			
			writer.close();
			client.close();
			server.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
