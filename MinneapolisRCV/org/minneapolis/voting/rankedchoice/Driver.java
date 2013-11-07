package org.minneapolis.voting.rankedchoice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {

	private static final Map<String, Candidate> candidates = new HashMap<String, Candidate>();
	private static final List<Vote> votes = new ArrayList<Vote>();
	
	public static void main(String[] args) throws IOException {
		if(args.length != 1) {
			System.err.println("Must enter CSV file path as first argument");
		}
		
		File f = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String input;
		while((input = br.readLine()) != null){
			String[] parts = input.split(",");
			ArrayList<Candidate> rankedVotes = new ArrayList<Candidate>();
			for(String part: parts){
				if(part.trim().length() < 1){
					continue;
				}
				if(candidates.get(part) == null){
					candidates.put(part, new Candidate(part));
				}
				rankedVotes.add(candidates.get(part));
			}
			Vote vote = new Vote(rankedVotes);
			votes.add(vote);
		}
		
		br.close();
		
		Ranker ranker = new Ranker(votes, candidates.values());
		ranker.produceRanking();
	}

}
