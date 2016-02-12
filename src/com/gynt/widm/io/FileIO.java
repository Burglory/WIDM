package com.gynt.widm.io;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileIO {

	public static void getGameFromFile(File file) {
		try {
			ZipFile zip = new ZipFile(file);
			while(zip.entries().hasMoreElements()) {
				ZipEntry z = zip.entries().nextElement();
				
			}
			zip.close();
		} catch (ZipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
