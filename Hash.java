import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Hash{

    public static void main (String[] args) {

    	Set<Long> set = new HashSet<Long>();

		try {
			
			File file = new File("hash.txt");
			
			FileReader fileReader = new FileReader(file);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			StringBuffer stringBuffer = new StringBuffer();
			
			String line;


			while ((line = bufferedReader.readLine()) != null) {

				Long newNum = Long.parseLong(line);

				set.add(newNum);

				//System.out.println(newNum);


			}


			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}


		Set<String> matches = new HashSet<String>();

		Set<Long> targets = new HashSet<Long>();

		int u = 0;

		// x + y = [-10000, 10000]
		for(Long x: set){

			u++;
			if(u % 10000 == 0){
				System.out.println("progress " + u);
			}

			Long min = -10000 - x;
			Long max =  10000 - x;

			for(Long i = min; i<= max; i++ ){

				if(set.contains(i)){
					

					if( x != i){
						//Long least = x < i ? x : i;
						//Long most = x > i ? x : i;
					
						//matches.add(least + "," + most);

						targets.add(x + i);

					}

				}


			}



		}


		for(long k: targets){
			System.out.println(k);
		}

		System.out.println(targets.size());



		//System.out.println(matches.keySet().size()/ 2);




	}

}
