package audio;

import java.io.FileInputStream;
import java.io.InputStream;

public class MinimHelper {

	public String sketchPath(String fileName){
		String s = MinimHelper.class.getResource("/sounds/" + fileName).toString().substring(5);
		if(s != null)
			return s;
		else System.out.println(System.getProperty("user_dir"));
			return null;
	}
	
	public InputStream createInput(String fileName){
		InputStream is = null;
		try {
			is = new FileInputStream(sketchPath(fileName));
		} catch(Exception e){
			System.out.println(e.toString());
		}
		return is;
	}
}
