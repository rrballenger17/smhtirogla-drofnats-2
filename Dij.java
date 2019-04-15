import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;

public class Dij {


	public static Map< Integer, Map<Integer, Integer> > graph = new HashMap< Integer, Map<Integer, Integer> >();


	public static Map<Integer, Integer> reached = new HashMap<Integer, Integer>();


	public static void printEdges(){

		Map<Integer, Integer> edges = graph.get(5);


		for(int i: edges.keySet()){
			System.out.println(i + " " + edges.get(i));
		}

	}


	public static void doDijkstra(){

		reached.put(1, 0);

		while(reached.keySet().size() < graph.size()){

			int node = -1;

			int shortest = Integer.MAX_VALUE;

			//int adjacentNode = -1;


			// for all nodes
			for(int x: graph.keySet()){

				// if it hasn't been reached yet
				if(!reached.keySet().contains(x)){

					// for all reached nodes
					for(int y: reached.keySet()){

						// if reached node has path to unreached node
						Map<Integer, Integer> edges = graph.get(y);
						if(edges.keySet().contains(x)){

							int dist = edges.get(x) + reached.get(y);


							if(dist < shortest){

								node = x;
								shortest = dist ;
								//adjacentNode = y;
							}
						}
					}
				}
			}

			reached.put(node, shortest);
			System.out.println(node + " " + shortest);

		}
	}


    public static void main (String[] args) {

		try {
			
			File file = new File("data.txt");
			
			FileReader fileReader = new FileReader(file);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			StringBuffer stringBuffer = new StringBuffer();
			
			String line;


			while ((line = bufferedReader.readLine()) != null) {
				//stringBuffer.append(line);
				//stringBuffer.append("\n");
				//int num = Integer.parseInt(line);
				//q.list.add(num);

				String[] parts = line.split("\\s");

                int point = Integer.parseInt(parts[0]);

                Map<Integer, Integer> edges = new HashMap<Integer, Integer>();

                for(int i=1; i<parts.length; i++){

                	String edge = parts[i];

                	String[] nums = edge.split(",");

                	edges.put(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));



                }

                graph.put(point, edges);

			}


			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}


		doDijkstra();


		String answer = reached.get(7)+","+reached.get(37)+","+reached.get(59)+","+reached.get(82)+","+reached.get(99)+","+reached.get(115)+","+reached.get(133)+","+reached.get(165)+","+reached.get(188)+","+reached.get(197);

		System.out.println(answer);
//		printEdges();




	}

}
