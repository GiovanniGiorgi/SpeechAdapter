import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Principale {
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do{
			String stringa = br.readLine();
			System.out.println(SpeechAdapter.recognize(stringa)[0]);
		}while(true);
	}

}
