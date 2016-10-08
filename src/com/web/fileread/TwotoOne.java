package com.web.fileread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TwotoOne{
	static boolean flag = false;
	static String a;
	static String b;
	static String c = "fileread";
	
	static void Read1(String c, BufferedReader br1, MultiThreadWrite mw) throws IOException
	{
		while ((a = br1.readLine()) != null) 
		{			
			synchronized(c)
			{
				while(flag == true)
				{
					try
					{
						c.wait();
					}
					catch(InterruptedException e)
					{
						System.out.println(e);
					}
				}
				flag = true;
				c.notifyAll();	
				mw.WriteFile(a);	
			}
		}
	}
	
	static void Read2(String c, BufferedReader br2, MultiThreadWrite mw) throws IOException
	{
		while ((b = br2.readLine()) != null) 
		{			
			synchronized(c)
			{
				while(flag == false)
				{
					try
					{
						c.wait();
					}
					catch(InterruptedException e)
					{
						System.out.println(e);
					}
				}
				flag = false;
				c.notifyAll();	
				mw.WriteFile(b);	
			}
		}
	}
	public static void main(String[] args) throws Exception{

		final BufferedReader br1 = new BufferedReader(new FileReader("file1.txt"));
		final BufferedReader br2 = new BufferedReader(new FileReader("file2.txt"));
		final MultiThreadWrite mw = new MultiThreadWrite();		
		Thread t1 = new Thread(){
			public void run()
			{
				try
				{
					Read1(c, br1, mw);
				}
				catch(IOException e)
				{
					System.out.println(e);
				}
			}
		};

		Thread t2 = new Thread(){
			public void run()
			{		
				try
				{
					Read2(c, br2, mw);
				}
				catch(IOException e)
				{
					System.out.println(e);
				}
			}
		};
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		br1.close();
		br2.close();			
		mw.CloseFile();
		
	}

}

