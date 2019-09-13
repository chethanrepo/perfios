package com.perfios.test;

import java.util.concurrent.BlockingQueue;

public class ProcessRingQueueConsumer implements Runnable {

	protected BlockingQueue<String> blockingQueueCurrent;
	protected BlockingQueue<String> blockingQueueNext;
	private static final String PROCESS_DONE = "#";

	public ProcessRingQueueConsumer(BlockingQueue<String> queueCurrent, BlockingQueue<String> queueNext) {
		this.blockingQueueCurrent = queueCurrent;
		this.blockingQueueNext = queueNext;
	}

	@Override
	public void run() {		
		while (true) {
			try {
				String data = blockingQueueCurrent.take();				
				if(data != null && data.equalsIgnoreCase(PROCESS_DONE)) {
					//System.out.println(Thread.currentThread().getName()+"  sending PROCESS_DONE signal");
					blockingQueueNext.put(PROCESS_DONE);							
					break;
				} else {					
					//System.out.println(Thread.currentThread().getName()+ " take(): " + data);
					blockingQueueNext.put(data);					
				}	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}	
		//System.out.println(Thread.currentThread().getName()+"  End");
	}

}