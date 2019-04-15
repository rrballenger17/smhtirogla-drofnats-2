import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Median {


	

    public static void main (String[] args) {


    	List<Integer> list = new ArrayList<Integer>();


    	long medianSum = 0;



		try {
			
			File file = new File("median.data.txt");
			
			FileReader fileReader = new FileReader(file);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			StringBuffer stringBuffer = new StringBuffer();
			
			String line;


			while ((line = bufferedReader.readLine()) != null) {
				//stringBuffer.append(line);
				//stringBuffer.append("\n");
				//int num = Integer.parseInt(line);
				//q.list.add(num);

				int newNum = Integer.parseInt(line);

				list.add(newNum);

				Collections.sort(list);


				if(list.size() % 2 == 0){
					medianSum += list.get(list.size()/2 -1);
				}else{
					medianSum += list.get(list.size()/2);
				}



			}


			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}


		System.out.println(medianSum);

		System.out.println(medianSum % 10000);




	}

}
