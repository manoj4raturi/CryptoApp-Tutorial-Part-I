package com.chain;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Firstblockchain {
	public static ArrayList<Block> firstblockchain = new ArrayList<Block>();
	public static int level = 5;

	public static void main(String[] args) {	
		//add our blocks to the blockchain ArrayList:
		
		firstblockchain.add(new Block("This is my first block", "0"));
		System.out.println("Mining started for first block... ");
		firstblockchain.get(0).mineBlock(level);
		
		firstblockchain.add(new Block("This is my second block",firstblockchain.get(firstblockchain.size()-1).hash));
		System.out.println("Mining started for second block... ");
		firstblockchain.get(1).mineBlock(level);
		
		firstblockchain.add(new Block("This is my third block",firstblockchain.get(firstblockchain.size()-1).hash));
		System.out.println("Mining started for third block... ");
		firstblockchain.get(2).mineBlock(level);	
		
		System.out.println("\nMy First Blockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(firstblockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[level]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < firstblockchain.size(); i++) {
			currentBlock = firstblockchain.get(i);
			previousBlock = firstblockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Oh! Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Oh! Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, level).equals(hashTarget)) {
				System.out.println("Sorry! This block is not mined");
				return false;
			}
		}
		return true;
}
}