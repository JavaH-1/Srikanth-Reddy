package fileOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReaderClass {

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\ISoft\\EmployeeList.txt");
		
		FileInputStream fis = new FileInputStream(file);
		int asciiCode;
		while((asciiCode = fis.read()) != -1) { //Loops the code until the ascci code becomes -1
			System.out.print((char)asciiCode);	
		}
		fis.close(); // To avoid the data leakage close method is used
	}
}

