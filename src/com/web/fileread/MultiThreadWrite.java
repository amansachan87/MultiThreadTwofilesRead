package com.web.fileread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MultiThreadWrite {
	BufferedWriter bw;
	MultiThreadWrite() throws IOException
	{
		bw = new BufferedWriter(new FileWriter("file3.txt"));
	}
	void WriteFile(String a) throws IOException
	{		
	    bw.write(a);
	    bw.newLine();
	}
	void CloseFile() throws IOException
	{		
		bw.close();

	}

}
