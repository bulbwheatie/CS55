import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RemoveHeader {
	
	public RemoveHeader(String control_path, String test_path, DataOutputStream test_out) {

		this.compareFiles(control_path, test_path, test_out);
	}
	
	public void compareFiles(String control_path, String test_path, DataOutputStream test_out) {
		FileInputStream control_fin = null;
		FileInputStream test_fin = null;

		try {
			control_fin = new FileInputStream(control_path);
			test_fin = new FileInputStream(test_path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataInputStream control_in = new DataInputStream(control_fin);
		DataInputStream test_in = new DataInputStream(test_fin);
		
		Byte control;
		Byte test;
		Boolean valid_data = false;
		int count = 0;
		
		try {
			while (!valid_data && (control = control_in.readByte()) != null) {
				test = test_in.readByte();
				if (control == test) {
					count++;
				}
				else {
					valid_data = true;
				}
			}
			
			while((test = test_in.readByte()) != null) {
				test_out.writeByte(test);
			}
			
			control_in.close();
			control_fin.close();
		} catch (IOException e) {

		}

		
		System.out.println("Matching bytes = " + count);
	}
	
	public static void main(String args[]) {
		
		FileOutputStream test_fout = null;
		String output_path = "C:\\Users\\Bulbwheatie\\Desktop\\test2.txt";

		try {
			test_fout = new FileOutputStream(output_path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataOutputStream test_out = new DataOutputStream(test_fout);

		String control_path = "C:\\Users\\Bulbwheatie\\Documents\\CS 55\\images\\1";
		String base_path= "C:\\Users\\Bulbwheatie\\Documents\\CS 55\\images\\";
		int start_file = 2;
		int max_file = 1267;
		
		for (int i = start_file; i < max_file; i++) {
			String test_path = base_path + i;
			System.out.println("File: " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RemoveHeader test = new RemoveHeader(control_path, test_path, test_out);
		}
		
		
		try {
			test_out.close();
			test_fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}