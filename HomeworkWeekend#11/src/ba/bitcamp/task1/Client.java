package ba.bitcamp.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *This class represents the client.
 * 
 * @author Zeljko Miljevic
 */
public class Client {

	public static final int PORT = 2424;
	public static final String HOST = "localhost";

	public static void main(String[] args) {
		try {
			Socket client = new Socket(HOST, PORT);
			System.out.println("client connected");
	
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
	
			Scanner in = new Scanner(System.in);
			System.out.println("Enter file path: ");
			
			writer.write(in.nextLine());
			writer.newLine();
			writer.flush();

			System.out.println(reader.readLine());
	
			
			writer.close();
			in.close();
			client.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}