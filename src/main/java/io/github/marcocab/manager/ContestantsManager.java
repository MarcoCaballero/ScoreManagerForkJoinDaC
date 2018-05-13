package io.github.marcocab.manager;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class ContestantsManager {
	private Contestant[] contestants;
	int nContestants;

	public ContestantsManager(int nContestants) {
		this.nContestants = nContestants;
		contestants = new Contestant[nContestants];
	}

	public ContestantsManager readFile(String filename) {
		int n = 0;
		try {
			Scanner scanner = new Scanner(new FileReader(filename));
			String line = (String) scanner.nextLine();
			while (scanner.hasNextLine()) {
				line = (String) scanner.nextLine();
				int score = Integer.valueOf(line.split(":")[1].trim());
				String name = line.split(":")[0];
				contestants[n] = new Contestant(name, score);
				n++;
			}
			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Initial: ");
		for (Contestant contestant : contestants) {
			System.out.println(contestant);
		}
		return this;
	}

	public ContestantsManager generateContestants() {
		try (PrintWriter pw = new PrintWriter("contestants.txt")) {
			pw.println(nContestants);
			for (int i = 0; i < nContestants; i++) {
				int score = new Random().nextInt(nContestants * 10);
				String name = "Contestant " + (i + 1) + ": ";
				pw.println(name + score);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}

	public void writeContestantsSolution(Contestant[] solution) {
		try (PrintWriter pw = new PrintWriter("contestants_solution.txt")) {
			pw.println(nContestants);
			for (int i = 0; i < nContestants; i++) {
				pw.println(solution[i].toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Contestant[] getStructure() {
		return this.contestants;
	}

	public int getContestantsNumber() {
		return nContestants;
	}
}