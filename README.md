# Perfios Test

  The docs/processRing.pdf has the problem statement.
  
  The src folder has the source file and class file too. 
  
 # How to run the code
  $ git clone https://github.com/chethanrepo/perfios.git
  
  $ cd /src 
  
  $ java com/perfios/test/ProcessRing 10 5 
  
**Note: You can try verious inputs range (int) with two args[] 
 
 # Q & A 
 **What is a good way to send messages between threads? What mechanism would you use and why?
<chethan>
Typically, wait(), notify() & notifyall() methods are used  to communicate between threads with our own locking mechanism, In order to process the tasks or messages more efficiently, producer consumer mechanism with backup option(writing a copy to disk or db) would be reliable way to process the message(s).
</chethan>
** Try to find the limits - what is the maximum number of threads you can create in your machine? What is the maximum N * M value supported by your machine?
<chethan>
The jvm version(32 bit or 64 bit), memory & data that is being processed has the control over number of threads being created inside jvm. My system had 64bit jvm, single core processor and 8GB RAM, I was able to create upto 100000 (1 lakhs thread) with almost 94% of memory. The single round trip with 100000 threads took 19 secs,(100% cpu usage in the beginning), that means I can have 100k threads without any issue provided message size is less. The number of round trip should not be problem when we have a sufficient memory to create threds, 16GB ram we can have 200k threads. In order to increase the number of threads in the jvm, Increase memory.  I have attached screenshot when 100000 was the input, you can see 100k + threads  under threads section in the screenshot.
![alt text](https://github.com/chethanrepo/perfios/blob/master/docs/processring.png)
</chethan>
  
