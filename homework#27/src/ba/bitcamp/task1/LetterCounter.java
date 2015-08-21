package ba.bitcamp.task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author Zeljko Miljevic
 *
 */
public class LetterCounter {

	private static int counter = 1;
	private static LinkedBlockingQueue<Producer> tasks;
	private static ArrayList<Consumer> consumers;
	private static Object lock = new Object();

	public static void main(String[] args) {

		tasks = new LinkedBlockingQueue<>();
		consumers = new ArrayList<>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(
					"files/file.txt")));
			char firstChar = (char) reader.read();
			firstChar = Character.toLowerCase(firstChar);
			System.out.println(firstChar);

			String line = "";

			while (reader.ready()) {
				line = reader.readLine().toLowerCase();
				Producer p = new Producer(line, firstChar);
				tasks.add(p);
			}

			reader.close();

			long start = System.currentTimeMillis();

			for (int i = 0; i < 4; i++) {
				Consumer con = new Consumer();
				con.start();
				consumers.add(con);
			}

			for (Consumer c : consumers) {
				try {
					c.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			System.out.println("Time: " + (System.currentTimeMillis() - start)
					+ " [ms]");
			System.out.printf("Number of repetitions of letter '%s': %d",
					firstChar, counter);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class Consumer extends Thread {

		public void run() {
			while (!tasks.isEmpty()) {
				try {
					Producer job = tasks.take();
					job.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Producer implements Runnable {

		private String line;
		private Character letter;

		public Producer(String line, Character letter) {
			this.line = line;
			this.letter = letter;
		}

		public void run() {
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == letter) {
					synchronized (lock) {
						counter++;
					}
				}
			}
		}
	}
}