import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class loginFileOperation {
	private Formatter lgFileFormatter;
	private Scanner lgFileScanner;
	private FileWriter lgFileWriter;
	
	private String fileName1;
public  loginFileOperation(String fileName1){
		
		this.fileName1 = fileName1;
		
	}
public void openFormatter(boolean append){
	
	try{
		
		lgFileWriter = new FileWriter(fileName1, append);
		lgFileFormatter = new Formatter(lgFileWriter);
		lgFileScanner = new Scanner(new File(fileName1));
		
	}catch(Exception e){
		JOptionPane.showMessageDialog(null, "File Scanner Openning Fialed");
	}
	
}
public  void addlginInfo(loginInfo info){
	
	openFormatter(true);
	lgFileFormatter.format("%s %s\n",info.getName(),info.getPassword());
	lgFileFormatter.close();
		
}
public ArrayList<loginInfo> getLogin(){
	
	openFormatter(true);
	ArrayList<loginInfo> LoginData=new ArrayList<>();
	
	while(lgFileScanner.hasNext()){
		String name = lgFileScanner.next();
		String password = lgFileScanner.next();
		LoginData.add(new loginInfo(name,password));	
	}		
	return LoginData;
}

public void writeMarks(ArrayList<loginInfo> allLoginData) {
	
	openFormatter(false);
	
	for (loginInfo lgin : allLoginData) {
		lgFileFormatter.format("%s %s\n",lgin.getName(),lgin.getPassword());
	}
	lgFileFormatter.close();
}
}