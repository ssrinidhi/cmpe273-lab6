package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
    	System.out.println("Starting Cache Client...");
    	List<DistributedCacheService> listOfServers = new ArrayList<DistributedCacheService>();

        String [] val = {"0","BMW - S class", "BMW - E class", "Audi - 2005", "Toyoto-Prius - 2008", "Toyoto-Corola - 2011", "Porche - 2012", 
        		            "Ferrari-Red - 2014", "Ford-Taurus - 2014", "Honda-Accord - 2011"};
        
      
        listOfServers.add(new DistributedCacheService("http://localhost:3000"));
        listOfServers.add(new DistributedCacheService("http://localhost:3001"));
        listOfServers.add(new DistributedCacheService("http://localhost:3002"));
        
        for(int key=1; key<10; key++) 
        {
            int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(key)), listOfServers.size());
            listOfServers.get(bucket).put(key, val[key]);
        }

        for(int key=1; key<10; key++) 
        {
            int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(key)), listOfServers.size());
        }

        System.out.println(" !!<---  HASHING Done. exit--->!!"); 
    }

}
