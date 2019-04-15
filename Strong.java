

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;




public class Strong {



	public static Map<Integer, Set<Integer>> edges = new HashMap<Integer, Set<Integer>>();

	public static Map<Integer, Set<Integer>> reversed = new HashMap<Integer, Set<Integer>>();


	public static Map<Integer, Integer> newOrder = new HashMap<Integer, Integer>();

	public static Map<Integer, Integer> newOrderTwo = new HashMap<Integer, Integer>();


	public static Set<Integer> visited = new HashSet<Integer>();

	public static Stack stack = new Stack();

	public static Set<Set<Integer>> sccs = new HashSet<Set<Integer>>();


	public static int LARGEST = 875714;

	public static int SMALLEST = 1;


	public static void secondDFS(){

		visited = new HashSet<Integer>();

		stack = new Stack();

		for(int i=LARGEST; i>= SMALLEST; i--){

			int x = newOrderTwo.get(i);

			Set<Integer> scc = new HashSet<Integer>();

			if(!visited.contains(x)){

				stack.push(x);
				visited.add(x);
				scc.add(x);


				while(!stack.empty()){


					int j = (int)stack.pop();

					Set<Integer> ends = edges.get(j);

					if(ends == null){
						continue;
					}


					for(int y: ends){
						if(!visited.contains(y)){
							stack.push(j);
							visited.add(j);
							scc.add(j);

							stack.push(y);
							visited.add(y);
							scc.add(y);

							break;
						}
					}
				}
			}

			sccs.add(scc);

		}

		List<Integer> sizes = new ArrayList<Integer>();

		for(Set<Integer> s : sccs){
			System.out.println("SCC");

			sizes.add(s.size());

			for(Integer h : s){
				System.out.println(h);
			}
		}

		Collections.sort(sizes);

		for(int u: sizes){
			System.out.println(u);
		}
	}





	public static void firstDFS(){
		int finishedCount = 1;

		for(int i=LARGEST; i>= SMALLEST; i--){

			if(!visited.contains(i)){

				stack.push(i);
				visited.add(i);


				while(!stack.empty()){

					int j = (int)stack.pop();

					//System.out.println()

					Set<Integer> ends = reversed.get(j);

					if(ends == null){
						newOrder.put(j, finishedCount);
						newOrderTwo.put(finishedCount, j);
						finishedCount++;
						continue;
					}


					for(int x: ends){
						if(!visited.contains(x)){
							stack.push(j);
							visited.add(j);

							stack.push(x);
							visited.add(x);

							break;
						}
					}

					if(stack.search(j) == -1){
						// j is done
						newOrder.put(j, finishedCount);
						newOrderTwo.put(finishedCount, j);
						finishedCount++;
					}
				}


			}
		}

		System.out.println("NEW");
		
		for(int i: newOrder.keySet()){


			System.out.println(i + " " + newOrder.get(i));
		}

	}

	public static void createReversed(){
		for(int key: edges.keySet()){

			for(int end: edges.get(key)){

				if(reversed.keySet().contains(end)){
					Set<Integer> set = reversed.get(end);
					set.add(key);
					reversed.put(end, set);
				}else{
					Set<Integer> set = new HashSet<Integer>();
					set.add(key);
					reversed.put(end, set);
				}
			}
		}

		// System.out.println("REVERSED");
		// 		for(int i: reversed.keySet()){
		// 	System.out.println("start " + i);

		// 	for(int j: reversed.get(i)){
		// 		System.out.println("end " + j);
		// 	}
		// }

	}


    public static void main (String[] args) {

		try {
			File file = new File("scc.txt");
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

                int one = Integer.parseInt(parts[0]);
                int two = Integer.parseInt(parts[1]);

                if(edges.keySet().contains(one)){
                	Set<Integer> ends = edges.get(one);
                	ends.add(two);
                	edges.put(one, ends);
                }else{
                	Set<Integer> ends = new HashSet<Integer>();
                	ends.add(two);
                	edges.put(one, ends);
                }
           

			}


			fileReader.close();
			//System.out.println("Contents of file:");
			//System.out.println(stringBuffer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}


		// for(int i: edges.keySet()){
		// 	System.out.println("start " + i);

		// 	for(int j: edges.get(i)){
		// 		System.out.println("end " + j);
		// 	}
		// }


		createReversed();

		firstDFS();


		secondDFS();



	}

}
