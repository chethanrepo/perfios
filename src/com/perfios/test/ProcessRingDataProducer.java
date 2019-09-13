package com.perfios.test;

import java.util.concurrent.BlockingQueue;

public class ProcessRingDataProducer implements Runnable {

	protected BlockingQueue<String> blockingQueue;
	
	public ProcessRingDataProducer(BlockingQueue<String> queue) {
		this.blockingQueue = queue;
	}

	@Override
	public void run() {
		try {
			String data = "Hello Ring World!";
			System.out.println(Thread.currentThread().getName()+" Data being processed in ProcessRing : " + data);
			blockingQueue.put(data);			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("Producer Ending +"+Thread.currentThread().getName());
	}

}

