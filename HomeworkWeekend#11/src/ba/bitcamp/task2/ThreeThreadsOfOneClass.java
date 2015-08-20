package ba.bitcamp.task2;

/**
 * This class represents three threads.
 * 
 * @author Zeljko Miljevic
 *
 */
public class ThreeThreadsOfOneClass {

	public static void main(String[] args) {
		
		// declaring threads.
		Thread t1 = new Thread(new MyThread("First"));
		Thread t2 = new Thread(new MyThread("Second"));
		Thread t3 = new Thread(new MyThread("Third"));


		try {

			t1.start(); // starting first thread.
			t1.join(); // waiting first thread to finish.
			t2.start(); // starting second thread.
			t2.join(); // waiting second thread to finish.
			t3.start(); // starting third thread.

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This inner class implements Runnable interface.
	 */
	private static class MyThread implements Runnable {

		private String name;

		/**
		 * Constructor.
		 */
		public MyThread(String name) {
			this.name = name;
		}

		/**
		 * This method determines how thread will work.
		 */
		public void run() {
			if (name.equals("First")) {
				for (int i = 1; i <= 10; i++) {
					System.out.println(i);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (name.equals("Second")) {
				for (int i = 1; i <= 4; i++) {
					System.out.println("BitCamp");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else if (name.equals("Third")) {
				for (int i = 1; i <= 5; i++) {
					System.out.println((int) (Math.random() * 4 + 1));
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}

	}
}
