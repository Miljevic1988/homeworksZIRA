package ba.bitcamp.homework25;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents local server.
 * 
 * @author Zeljko Miljevic
 */
public class Server {

	public static final int PORT = 2424;

	public static void main(String[] args) {

		try {
			System.out.println("Server started.");
			ServerSocket server = new ServerSocket(PORT);

			System.out.println("Waiting for the client to connect...");
			Socket client = server.accept();
			System.out.println("Client connected.");

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));

			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					"src/link.txt"), true));

			writer.write(reader.readLine());
			writer.newLine();

			writer.close();
			reader.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}