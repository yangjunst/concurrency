package com.roocon.thread.ta5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Demo {

	public static void main(String[] args) {
		Demo demo=new Demo();
		demo.testB();
		new Thread(()->{demo.testA();}).start();
	}

	private Map<String, Object> map = new HashMap<>();

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();
	public void testA(){
		r.lock();
		System.out.println("testA r.lock()..begin.");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("testA r.lock()..end.");
	}

	public void testB(){

		w.lock();
		System.out.println("w.lock()...");
		r.lock();
		System.out.println("testB r.lock()..begin.");
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("testB r.lock()..end.");
		r.unlock();
		System.out.println("r.unlock()...");
		w.unlock();
		System.out.println("w.unlock()...");

	}

	private volatile boolean isUpdate=true;

	public void readWrite() {
		r.lock(); // Ϊ�˱�֤isUpdate�ܹ��õ����µ�ֵ
		System.out.println("r.lock()...");
		if (isUpdate) {
			r.unlock();
			System.out.println("r.unlock()...");
			w.lock();
			System.out.println("w.lock()...");
			map.put("xxx", "xxx");
			r.lock();
			System.out.println("r.lock()...");
			w.unlock();
			System.out.println("w.unlock()...");
		}

		Object obj = map.get("xxx");

		System.out.println(obj);
		r.unlock();

	}

	public Object get(String key) {
		r.lock();
		System.out.println(Thread.currentThread().getName() + " ��������ִ��..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			r.unlock();
			System.out.println(Thread.currentThread().getName() + " ����ִ�����..");
		}
	}

	public void put(String key, Object value) {
		w.lock();
		System.out.println(Thread.currentThread().getName() + " д������ִ��..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		} finally {
			w.unlock();
			System.out.println(Thread.currentThread().getName() + " д����ִ�����..");
		}
	}

}
