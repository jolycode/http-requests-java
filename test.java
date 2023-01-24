package lab2;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class test {
	
	public static void main(String[] args) throws Exception {
		
	
		Socket connectionSocket = new Socket("10.2.11.136", 80);
		
		System.out.println("Connection is established!");
		
		System.out.println("Please enter the URL address of the web page. ");
		
		Scanner scanner = new Scanner(System.in);
		
		String urls = "http://" + scanner.nextLine() + "/";
		
		URL url = new URL(urls);
				
		String host = url.getHost();
		String path = url.getPath();
		
		BufferedReader bf = new BufferedReader(
				new InputStreamReader(connectionSocket.getInputStream()));
		
		DataOutputStream dos = new DataOutputStream(connectionSocket.getOutputStream());
				
		dos.writeBytes("GET " + path+" HTTP/1.0\r\n"
				+ "Host: "+host
				+ "\r\n\r\n");
		
		String loop = "";
		while(true) {
			String anotherString = bf.readLine();
			
			if(anotherString == null) {
				connectionSocket.close();
				break;
			}
			
			loop += anotherString + "\n";
		}
		System.out.println(loop);
	}

}
//http://10.2.11.136/path/anotherPath/
