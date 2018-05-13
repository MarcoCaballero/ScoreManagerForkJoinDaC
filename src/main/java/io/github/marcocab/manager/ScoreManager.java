package io.github.marcocab.manager;

import java.util.concurrent.ForkJoinPool;

public class ScoreManager {
	private static final String DEFAULT_CONTESTANTS_DOC = "contestants.txt";
	private static final int DEFAULT_CONTESTANT_NUMBER = 1000000;
	ContestantsManager cm;
	private Contestant[] contestants;

	public ScoreManager(int n) {
		cm = new ContestantsManager(n);
	}

	public ScoreManager() {
		this(DEFAULT_CONTESTANT_NUMBER);
	}

	public void sortContestants() {
		generateContestants();
		Contestant[] sol = mergeSort();
		System.out.println();
		for (Contestant contestant : sol) {
			System.out.println(contestant);
		}
		cm.writeContestantsSolution(sol);
	}

	private Contestant[] mergeSort() {
		ForkJoinPool pool = new ForkJoinPool(20);
		return pool.invoke(new FJTaskMergeSort(contestants, 0, contestants.length));
	}

	private void generateContestants() {
		generateContestants(DEFAULT_CONTESTANTS_DOC);
	}

	private void generateContestants(String filename) {
		contestants = cm
				.generateContestants()
				.readFile(filename)
				.getStructure();
	}
}
