/**
 * 
 */
package com.icstars.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LH
 *
 */
public class Problem1Trains {
	Map<String, RouteDestination> mRteDest = new HashMap<String, RouteDestination>();
	Map<String, List<String>> mBranches = new HashMap<String, List<String>>();


	/**
	 * 
	 */
	public Problem1Trains() {
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Problem1Trains pb = new Problem1Trains();
		
		pb.loadRouteInfo();

		//System.out.println("1.\tWhat is distance of route A-B-C?");
		//System.out.println("2.\tWhat is distance of route A-D?");
		
		pb.question1To5("ABC", "1");
		pb.question1To5("AD", "2");
		pb.question1To5("ADC", "3");
		pb.question1To5("AEBCD", "4");
		pb.question1To5("AED", "5");
		pb.question6("C", "C");
		pb.question7("A", "C");
		pb.question8_9("A", "C", "8");
		pb.question8_9("B", "B", "9");
		pb.question10("C", "C");


	}
	public void question1To5(String rte, String qNum){
		String answer = null;
		char[] aRte = rte.toCharArray();

		int routeTotal = 0;
		
		for(int i=0; i<aRte.length; i++){
			RouteDestination rd = null;
			if(i + 1 < aRte.length){
				if(aRte[i] == 'A'){
					rd = mRteDest.get("A");
					
				} else if(aRte[i] == 'B'){
					rd = mRteDest.get("B");
					
				} else if(aRte[i] == 'C'){
					rd = mRteDest.get("C");
					
				} else if(aRte[i] == 'D'){
					rd = mRteDest.get("D");
					
				} else if(aRte[i] == 'E'){
					rd = mRteDest.get("E");
					
				}
				
				if(rd != null){
					String rteKey = String.valueOf(aRte[i + 1]);
					Integer rteVal = rd.getDest().get(rteKey);
					if(rteVal != null){
						routeTotal += rteVal;
						
					} else {
						routeTotal = 0;
						break;
						
					}
					
				}
				
			}
			
		}
		
		if(routeTotal > 0){
			answer = "Output #" + qNum + ": " + routeTotal;
			
		} else {
			answer = "Output #" + qNum + ": NO SUCH ROUTE";

		}

		System.out.println(answer);
		
		
	}
	public void question6(String start, String end){
		List<String> finalList = new ArrayList<String>();
		List<String> toDo = new ArrayList<String>();
		Map<String, String> cMap = new HashMap<String, String>();
		
		int aKey = 0;
		String daBase = start;

		while(true){
			List<String> l1 = processNode(start);
			for(String s: l1){
				//System.out.println("\nt1: " + daBase + s);
				if(s.equalsIgnoreCase(end)){
					String route = daBase + s;
					if(route.length() <= 4) finalList.add(route);
					
				}
				
				List<String> l2 = processNode(s);
				for(String s1: l2){
					//System.out.println("t2: " + daBase + s + s1);
					
					aKey++;
					cMap.put(s1 + (new Integer(aKey)).toString(), daBase + s + s1);
					
					toDo.add(s1 + (new Integer(aKey)).toString());
					if(s1.equalsIgnoreCase(end)){
						String route = daBase + s + s1;
						if(route.length() <= 4) finalList.add(route);
						
					}
					
				}
				
			}
			
			start = toDo.get(0);
			daBase = cMap.get(start);
			toDo.remove(0);
			
			//System.out.println("ToDo: " + toDo);
			//System.out.println("cMap: " + cMap);
			//System.out.println("finalList: " + finalList);
			
			if(finalList.size() >= 2) break;
			
		}
		
		System.out.println("Output #6: " + finalList.size());

	}
	public void question7(String start, String end){
		List<String> finalList = new ArrayList<String>();
		List<String> toDo = new ArrayList<String>();
		Map<String, String> cMap = new HashMap<String, String>();
		
		int aKey = 0;
		String daBase = start;

		while(true){
			List<String> l1 = processNode(start);
			for(String s: l1){
				//System.out.println("\nprocessNode: " + daBase + s);
				if(s.equalsIgnoreCase(end)){
					String route = daBase + s;
					if(route.length() == 5) finalList.add(route);
					
				}
				
				List<String> l2 = processNode(s);
				for(String s1: l2){
					//System.out.println("t2: " + daBase + s + s1);
					
					aKey++;
					cMap.put(s1 + (new Integer(aKey)).toString(), daBase + s + s1);
					toDo.add(s1 + (new Integer(aKey)).toString());

					if(s1.equalsIgnoreCase(end)){
						String route = daBase + s + s1;
						if(route.length() == 5) finalList.add(route);
						
					}
					
				}
				
			}
			
			start = toDo.get(0);
			daBase = cMap.get(start);
			toDo.remove(0);
			
			//System.out.println("ToDo: " + toDo);
			//System.out.println("cMap: " + cMap);
			//System.out.println("finalList: " + finalList);
			
			if(finalList.size() >= 3) break;
			
		}
		
		System.out.println("Output #7: " + finalList.size());
		
	}
	public void question8_9(String start, String end, String question){
		List<String> finalList = new ArrayList<String>();
		List<String> toDo = new ArrayList<String>();
		Map<String, String> cMap = new HashMap<String, String>();
		
		int shortestLen = 0;
		String daBase = start;
		int aKey = 0;
		
		while(true){
			List<String> l1 = processNode(start);
			for(String s: l1){
				//System.out.println("\nprocessNode: " + daBase + s);
				if(s.equalsIgnoreCase(end)){
					String route = daBase + s;
					int rteLen = computeRouteLen(route);
					//System.out.println(route + " Length = " + rteLen);
					
					finalList.add(route);
					
					if(shortestLen == 0 || rteLen < shortestLen){
						shortestLen = rteLen;
						
					}
					
				}
				
				List<String> l2 = processNode(s);
				for(String s1: l2){
					//System.out.println("t2: " + daBase + s + s1);
					
					aKey++;
					cMap.put(s1 + (new Integer(aKey)).toString(), daBase + s + s1);
					toDo.add(s1 + (new Integer(aKey)).toString());

					if(s1.equalsIgnoreCase(end)){
						String route = daBase + s + s1;
						int rteLen = computeRouteLen(route);
						//System.out.println(route + " Length = " + rteLen);
						
						finalList.add(route);
						
						if(shortestLen == 0 || rteLen < shortestLen){
							shortestLen = rteLen;
							
						}
						
					}
					
				}
				
			}
			
			start = toDo.get(0);
			daBase = cMap.get(start);
			toDo.remove(0);
			
			//System.out.println("ToDo: " + toDo);
			//System.out.println("cMap: " + cMap);
			//System.out.println("finalList: " + finalList);
			//System.out.println("Shortest " + shortestRte + ": " + shortestLen);
			
			if(finalList.size() >= 10) break;
			
		}
		
		System.out.println("Output #" + question + ": " + shortestLen);
		
	}

	public void question10(String start, String end){
		List<String> finalList = new ArrayList<String>();
		List<String> toDo = new ArrayList<String>();
		Map<String, String> cMap = new HashMap<String, String>();
		
		String daBase = start;
		int aKey = 0;

		while(true){
			List<String> l1 = processNode(start);
			for(String s: l1){
				//System.out.println("\nprocessNode1: " + daBase + s);
				if(s.equalsIgnoreCase(end)){
					String route = daBase + s;
					int rteLen = computeRouteLen(route);
					//System.out.println(route + " Length = " + rteLen);
					
					if(rteLen < 30)finalList.add(route);
					
				}
				
				List<String> l2 = processNode(s);
				for(String s1: l2){
					//System.out.println("nprocessNode2: " + daBase + s + s1);
					
					aKey++;
					cMap.put(s1 + (new Integer(aKey)).toString(), daBase + s + s1);
					toDo.add(s1 + (new Integer(aKey)).toString());

					if(s1.equalsIgnoreCase(end)){
						String route = daBase + s + s1;
						int rteLen = computeRouteLen(route);
						//System.out.println(route + " Length = " + rteLen);
						
						if(rteLen < 30)finalList.add(route);
						
					}
					
				}
				
			}
			
			start = toDo.get(0);
			daBase = cMap.get(start);
			toDo.remove(0);
			
			//System.out.println("ToDo: " + toDo);
			//System.out.println("cMap: " + cMap);
			//System.out.println("finalList: " + finalList);
			
			if(finalList.size() >= 7) break;
			
		}
		
		System.out.println("Output #10: " + finalList.size());
		
	}
	
	public List<String> processNode(String node){
		node = node.substring(0,1);
		
		return process(node);
	}
	public int computeRouteLen(String route){
		int total = 0;
		
		char[] stopList = route.toCharArray();
		for(int i=0; i<(stopList.length -1); i++){
			String o = String.valueOf(stopList[i]);
			String d = String.valueOf(stopList[i + 1]);
			
			RouteDestination rdO = mRteDest.get(o);
			total += rdO.getDest().get(d);
			
		}
		
		return total;
		
	}
	
	public List<String> process(String start){
		List<String> lBranches = new ArrayList<String>();
		RouteDestination rd = mRteDest.get(start);
		List<String> list = rd.getDestList();
		for(String s: list){
			StringBuilder sb = new StringBuilder(start);
			sb.append(s);
			
			lBranches.add(s);
			
		}
		
		return lBranches;
	}

	public void loadRouteInfo(){
		// load route information
		RouteDestination rdA = new RouteDestination();
		rdA.getDest().put("B", 5);
		rdA.getDest().put("D", 5);
		rdA.getDest().put("E", 7);
		
		mRteDest.put("A", rdA);
		
		rdA.getDestList().add("B");
		rdA.getDestList().add("D");
		rdA.getDestList().add("E");
		

		RouteDestination rdB = new RouteDestination();
		rdB.getDest().put("C", 4);
		
		mRteDest.put("B", rdB);

		rdB.getDestList().add("C");

		RouteDestination rdC = new RouteDestination();
		rdC.getDest().put("D", 8);
		rdC.getDest().put("E", 2);
		
		mRteDest.put("C", rdC);

		rdC.getDestList().add("D");
		rdC.getDestList().add("E");

		RouteDestination rdD = new RouteDestination();
		rdD.getDest().put("C", 8);
		rdD.getDest().put("E", 6);
		
		mRteDest.put("D", rdD);

		rdD.getDestList().add("C");
		rdD.getDestList().add("E");

		RouteDestination rdE = new RouteDestination();
		rdE.getDest().put("B", 3);
		
		mRteDest.put("E", rdE);
		
		rdE.getDestList().add("B");

	}

}

class RouteDestination{
	private Map<String, Integer> mDest = new HashMap<String, Integer>();
	private List<String> destList = new ArrayList<String>();

	public Map<String, Integer> getDest() {
		return mDest;
	}

	public void setDest(Map<String, Integer> mDest) {
		this.mDest = mDest;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("destinations:\n" + mDest);
		
		return sb.toString();
		
	}

	public List<String> getDestList() {
		return destList;
	}

	public void setDestList(List<String> destList) {
		this.destList = destList;
	}
	
}
