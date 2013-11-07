package org.minneapolis.voting.rankedchoice;

import java.util.ArrayList;
import java.util.List;

public class Vote {

	private List<Candidate> rankedCandidates = new ArrayList<Candidate>();
	
	public Vote(List<Candidate> candidates) {
		for(Candidate candidate: candidates) {
			if(!rankedCandidates.contains(candidate)){
				rankedCandidates.add(candidate);
			}
		}
	}
	
	public Candidate getCandidateVotedFor() {
		for(Candidate potentialCandidate: rankedCandidates) {
			if(!potentialCandidate.isEliminated()){
				return potentialCandidate;
			}
		}
		return null;
	}
}
