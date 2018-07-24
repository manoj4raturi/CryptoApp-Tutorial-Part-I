package com.chain;

import java.util.Date;

public class Block {
	
	public String hash;
	public String previousHash; 
	private String data; //any text message of your choice.
	private long timeStamp; //milliseconds since 01/01/1970.
	private int tmp;
	
	//Block Constructor.  
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime(); //current timestamp		
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(tmp) + 
				data 
				);
		return calculatedhash;
	}
	
	public void mineBlock(int level) {
		String target = new String(new char[level]).replace('\0', '0'); // replace null character value with "0"
		while(!hash.substring( 0, level).equals(target)) {
			tmp ++;
			hash = calculateHash();
		}
		System.out.println("Block Mined : " + hash);
	}
}