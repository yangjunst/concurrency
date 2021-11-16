package cn.itcast_02;

public class DieLock extends Thread {

	private boolean flag;

	public DieLock(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void run() {
		if (flag) {
			synchronized (MyLock.objA) {
				System.out.println("if objA");
				try {
					Thread.sleep(40_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (MyLock.objB) {
					System.out.println("if objB");
				}
			}
		} else {
			synchronized (MyLock.objB) {
				System.out.println("else objB");
				try {
					Thread.sleep(40_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (MyLock.objA) {
					System.out.println("else objA");
				}
			}
		}
	}
}
