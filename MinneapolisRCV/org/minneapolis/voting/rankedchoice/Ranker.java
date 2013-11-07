package org.minneapolis.voting.rankedchoice;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Ranker {

	private final List<Vote> votes;
	private final Collection<Candidate> candidates;
	private final int totalVotes;
	private final int votesNeededToWin;

	public Ranker(List<Vote> votes, Collection<Candidate> candidates) {
		this.votes = votes;
		this.candidates = candidates;
		totalVotes = votes.size();
		votesNeededToWin = (int) Math.floor(totalVotes / 2 + 1);
	}

	public void produceRanking() {
		int round = 1;

		while(true) {
			System.out.println("Results for round "+round);

			//accumulate votes
			Multiset<Candidate> votesThisRound = HashMultiset.create();
			for(Vote vote: votes) {
				Candidate candidateVotedFor = vote.getCandidateVotedFor();
				if(candidateVotedFor != null) {
					votesThisRound.add(candidateVotedFor);
				}
			}

			int maxVotes = -1;
			Candidate topCandidate = null;
			int minVotes = Integer.MAX_VALUE;
			Candidate lowCandidate = null;

			//eliminate all candidates with 0 votes
//			for(Candidate c: candidates) {
//				if(c.isEliminated()){
//					continue;
//				}
//				if(votesThisRound.count(c) == 0){
//					c.isEliminated();
//					System.out.println("Candidate eliminated for receiving no votes in this round: "+c);
//				}
//			}
			
			//print vote counts		
			for(Candidate c: candidates) {
				if(c.isEliminated()){
					continue;
				}
				System.out.println("\t"+c+" "+votesThisRound.count(c));
				if(votesThisRound.count(c) > maxVotes) {
					maxVotes = votesThisRound.count(c);
					topCandidate = c;
				}
				if(votesThisRound.count(c) < minVotes){
					minVotes = votesThisRound.count(c);
					lowCandidate = c;
				}
			}

			//see if we have a winner
			if(topCandidate != null) {
				if(maxVotes >= votesNeededToWin){
					System.out.println();
					System.out.println("***************");
					System.out.println("Winner Found!");
					System.out.println(topCandidate);
					System.out.println("***************");
					return;
				}
			}
			else{
				System.err.println("Error calculating votes - no top candidate found");
				return;
			}

			//eliminate lowest ranked candidate
			if(lowCandidate != null) {
				lowCandidate.setEliminated();
				System.out.println("Lowest vote candidate eliminated: "+lowCandidate+" with "+minVotes+" votes");
			}
			else{
				System.err.println("Error calculating votes - no lowest candidate found");
				return;
			}
			
			System.out.println();
			round++;
		}

	}
}
