package io.github.marcocab.manager;

public class Contestant {
	private String name;
	private long score;

	public Contestant(String name, long score) {
		this.name = name;
		this.score = score;
	}

	public boolean isHiggherScoredThan(Contestant contestant) {
		return this.score >= contestant.score;
	}

	@Override
	public String toString() {
		return name + ":" + score;
	}

}
