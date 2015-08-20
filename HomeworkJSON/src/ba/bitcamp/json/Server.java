package ba.bitcamp.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *This class represents local server that sends message to the client.
 *
 * @author Zeljko Miljevic
 */
public class Server {

	// Declaring constants for port.
	public static final int PORT = 2424;

	public static void main(String[] args) {
		
		Random rand = new Random();
		ArrayList<String> lines = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		ServerSocket server;
		
		try {

			server = new ServerSocket(PORT);
			System.out.println("server started...");

			while (true) {
				Socket client = server.accept();
				//Reader that will read from file.
				BufferedReader reader = new BufferedReader(new FileReader(new File("files/text.txt")));
				//Writer that will send message to the client.
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
	
				String line = "";
				while ((line = reader.readLine()) != null) {
					if (!line.equals("")) {
						lines.add(line);
					}
				}

				int randomLine = rand.nextInt(lines.size());
				String message = lines.get(randomLine);
		
				BitResponse response = new BitResponse(message);
				mapper.writeValue(new File("files/file.json"), response);
			
				BufferedReader readerJson = new BufferedReader(new FileReader(new File("files/file.json")));
		
				writer.write(readerJson.readLine());
				writer.newLine();
				writer.flush();

				writer.close();
				reader.close();
				readerJson.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
