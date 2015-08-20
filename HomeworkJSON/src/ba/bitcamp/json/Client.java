package ba.bitcamp.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *This class represents client that connects to the local server.
 * 
 * @author Zeljko Miljevic
 */
public class Client {

	// Declaring constants for host and port.
	public static final String HOST = "localhost";
	public static final int PORT = 2424;

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<BitResponse> responses = new ArrayList<>();

		try {

			Socket client = new Socket(HOST, PORT);
			System.out.println("client connected...");
			// Declaring a reader.
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			// Declaring writer that will save received message.
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
					"files/received.json"), true));

			writer.write(reader.readLine());
			writer.newLine();
			writer.close();

			// Declaring a readerJson.
			BufferedReader readerJson = new BufferedReader(new FileReader(
					new File("files/received.json")));
			while (readerJson.ready()) {
				BitResponse response = mapper.readValue(readerJson.readLine(),
						BitResponse.class);
				responses.add(response);
			}

			readerJson.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
