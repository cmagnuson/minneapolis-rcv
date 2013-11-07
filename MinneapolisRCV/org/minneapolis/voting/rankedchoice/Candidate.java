package org.minneapolis.voting.rankedchoice;

public class Candidate {

	private final String identifier;
	private boolean eliminated = false;

	public Candidate(String identifier) {
		this.identifier = identifier;
	}

	public void setEliminated() {
		eliminated = true;
	}

	public boolean isEliminated() {
		return eliminated;
	}

	@Override
	public String toString() {
		return identifier;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Candidate){
			Candidate c = (Candidate) o;
			return c.identifier == null ? identifier == null : c.identifier.equals(identifier);
		}
		return false;
	}
}
