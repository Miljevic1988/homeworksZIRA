package ba.bitcamp.homework25;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

/**
 * This class represents the client.
 * 
 * @author Zeljko Miljevic
 *
 */
public class Client {

	public static final String HOST = "localhost";
	public static final int PORT = 2424;

	public static void main(String[] args) {

		try {
			Socket client = new Socket(HOST, PORT);

			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));

			Scanner in = new Scanner(System.in);

			while (true) {
				try {
					System.out.println("Please enter the link address: ");
					URL url = validateURL(in.nextLine());
					writer.write(url + " ");

					System.out.println("Please enter the name of the link: ");
					writer.write(in.nextLine());
					break;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

			writer.newLine();
			writer.close();
			client.close();
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is checking if the inputed string is URL link.
	 */
	public static URL validateURL(String url) throws MalformedURLException {
		URL link;
		if (url.contains("http")) {
			link = new URL(url);
		} else if (url.contains("www")) {
			link = new URL("http://" + url);
		} else {
			link = new URL(url);
		}
		return link;
	}
}
