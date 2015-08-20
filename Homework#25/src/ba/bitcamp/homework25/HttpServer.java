package ba.bitcamp.homework25;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents the HTTP server.
 * 
 * @author Zeljko Miljevic
 *
 */
public class HttpServer {

	public static final int PORT = 8000;

	public static void main(String[] args) {

		ServerSocket server;
		try {
			server = new ServerSocket(PORT);

			while (true) {
				Socket client = server.accept();
				BufferedReader reader = new BufferedReader(new FileReader(
						new File("src/index.html")));

				String fileText = "";
				while (reader.ready()) {
					fileText += reader.readLine();
				}

				BufferedReader fileReader = new BufferedReader(new FileReader(
						new File("src/link.txt")));

				while (fileReader.ready()) {

					String line = fileReader.readLine();
					fileText += "<h3 align='center'><a href = "
							+ line.split(" ")[0] + ">" + line.split(" ")[1]
							+ "</a></h3>";
				}

				fileText += "</body></html>";

				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(client.getOutputStream()));

				writer.write(fileText);
				writer.newLine();
				writer.close();

				reader.close();
				fileReader.close();

				client.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
