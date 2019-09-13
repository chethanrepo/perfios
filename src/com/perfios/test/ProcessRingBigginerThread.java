package com.perfios.test;

import java.util.concurrent.BlockingQueue;

public class ProcessRingBigginerThread implements Runnable {

	protected BlockingQueue<String> blockingQueueCurrent;
	protected BlockingQueue<String> blockingQueueNext;
	
	private int numberOfThreadsInRing;
	private int totalNumberOfRounds;
	private static final String PROCESS_DONE = "#";
	
	public ProcessRingBigginerThread(BlockingQueue<String> queueCurrent, BlockingQueue<String> queueNext, int numberOfThreadsInRing,  int totalNumberOfRounds) {
		this.blockingQueueCurrent = queueCurrent;
		this.blockingQueueNext = queueNext;
		this.numberOfThreadsInRing = numberOfThreadsInRing;
		this.totalNumberOfRounds = totalNumberOfRounds;
	}

	@Override
	public void run() {
		int numberOfRoundsDone = 0;
		long startTime = System.currentTimeMillis();
		while (true) {
			try {
				String data = blockingQueueCurrent.take();					
					if(numberOfRoundsDone == totalNumberOfRounds) {							
						long endTime = System.currentTimeMillis();
						System.out.println("Time taken to create a ring of "+numberOfThreadsInRing+" threads"
								+ " and sending a message "+ totalNumberOfRounds+" times around it in millis : "+(endTime - startTime));					
						//take the data and pass process end key	
						blockingQueueNext.put(PROCESS_DONE);	
						break;
					 } else {					 
						//System.out.println(Thread.currentThread().getName()+ " take(): " + data);					
					    //Same data getting passed to next consumer thread
						blockingQueueNext.put(data);	
						numberOfRoundsDone ++;
					    //System.out.println("Round started : "+numberOfRoundsDone);
					}				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
		//System.out.println(Thread.currentThread().getName()+" End");
		
	}

}