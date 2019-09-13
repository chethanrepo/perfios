package com.perfios.test;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class ProcessRing {
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		
		int numberOfThreads = 0;
		int numberOfRounds = 0;
		//args = new String[] {"5","2"};
		if(args.length == 0 && args.length > 2)
	    {
	        System.out.println("Proper Usage is: $java com/perfios/test/ProcessRing 10 50");
	        System.exit(0);
	    } else {
	    	try {
	    		numberOfThreads = Integer.parseInt(args[0]);
	    		numberOfRounds = Integer.parseInt(args[1]);
	    		if (numberOfThreads <= 1 || numberOfRounds <= 0) {
	    			System.out.println("Provide valid inputs to args[0] > 1 & args[1] > 0"); 
		            System.exit(0);
	    		}
	    	}catch (NumberFormatException e){ 
	            System.out.println("Provide valid inputs to args[0] & args[1]"); 
	            System.exit(0);
	        } 
	    }		
		final BlockingQueue<String>[] synchronousQueue = new SynchronousQueue[numberOfThreads];
	    
		for (int i = 0; i < numberOfThreads; i++) {
			synchronousQueue[i] = new SynchronousQueue<String>();           
        }
		//data produce thread
		new Thread(new ProcessRingDataProducer(synchronousQueue[0])).start(); 
				
		ProcessRingBigginerThread queueConsumer1 =
				new ProcessRingBigginerThread(synchronousQueue[0], synchronousQueue[1], numberOfThreads, numberOfRounds);
		Thread startingThread = new Thread(queueConsumer1);		
		startingThread.start();
		
		for (int i = 1; i < numberOfThreads-1; i++) {			;
			Thread cosumerthread = new Thread(new ProcessRingQueueConsumer(synchronousQueue[i], synchronousQueue[i+1]));	
			cosumerthread.start();			
		}		
		
		ProcessRingQueueConsumer  queueConsumerNth = 
				new ProcessRingQueueConsumer(synchronousQueue[numberOfThreads-1], synchronousQueue[0]);
		Thread nthThred = new Thread(queueConsumerNth);	
		nthThred.setDaemon(true); //required to kill worker thread
		nthThred.start();		
		
	}
}
