package ba.bitcamp.task2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Represents main panel that contains image in positive form.
 * 
 * @author Zeljko Miljevic
 */

public class ChangerGui extends JPanel implements ActionListener {
	private static final long serialVersionUID = 3215043364123019049L;

	// Declaring elements.
	private JPanel panel = new MyPanel();
	private JButton button = new JButton("Negative image");

	private LinkedBlockingQueue<Producer> tasks = new LinkedBlockingQueue<>();
	private ArrayList<Consumer> consumers = new ArrayList<>();

	private BufferedImage image;

	public ChangerGui() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e2) {
			e2.printStackTrace();
		}

		try {
			image = ImageIO.read(new File("files/image.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not load picture. Please try again.");
		}

		button.addActionListener(this);
		button.setFont(new Font("Serif", Font.BOLD, 20));

		setLayout(new BorderLayout());
		add(panel);
		add(button, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < image.getHeight(); i++) {
			Producer p = new Producer(image.getWidth(), i);
			tasks.add(p);
		}

		for (int i = 0; i < 4; i++) {
			Consumer con = new Consumer();
			con.start();
			consumers.add(con);
		}

		if (button.getText().equals("Negative image")) {
			button.setText("Positive image");
		} else {
			button.setText("Negative image");
		}
	}

	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Inner class represents JPanel that draws image.
	 * 
	 * @author Zeljko Miljevic
	 *
	 */
	private class MyPanel extends JPanel {

		private static final long serialVersionUID = -722794451587508696L;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);
			repaint();
		}
	}

	/**
	 * Inner class that represents task that is need to be executed.
	 * 
	 * @author Zeljko Miljevic
	 *
	 */
	private class Producer implements Runnable {

		private int row;
		private int column;

		/**
		 * Constructor that creates new producer.
		 */
		public Producer(int row, int column) {
			this.row = row;
			this.column = column;
		}

		/**
		 * Subtracts all pixels in row for 255.
		 */
		public void run() {
			for (int i = 0; i < row; i++) {
				image.setRGB(i, column, 255 - image.getRGB(i, column));
			}
		}
	}

	/**
	 * Inner class that represents thread that will execute tasks.
	 * 
	 * @author Zeljko Miljevic
	 *
	 */
	private class Consumer extends Thread {

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
}
