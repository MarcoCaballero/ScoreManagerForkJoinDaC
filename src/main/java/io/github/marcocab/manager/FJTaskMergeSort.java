package io.github.marcocab.manager;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class FJTaskMergeSort extends RecursiveTask<Contestant[]> {

	private static final long serialVersionUID = 1L;

	private static final int TH_SEQ = 2;

	private Contestant[] items;
	private int low;
	private int high;

	public FJTaskMergeSort(Contestant[] items, int low, int high) {
		this.items = items;
		this.low = low;
		this.high = high;
	}

	@Override
	protected Contestant[] compute() {
		if (high - low < TH_SEQ) {
			return items;
		} else {
			int middle = getMiddle();
			FJTaskMergeSort left = new FJTaskMergeSort(items, low, middle);
			FJTaskMergeSort right = new FJTaskMergeSort(items, middle, high);
			left.fork();
			Contestant[] itemsRight = right.compute();
			Contestant[] itemsLeft = left.join();
			for (int i = 0; i < items.length; i++) {
				if (i <= middle) {
					items[i] = itemsLeft[i];
				} else {
					items[i] = itemsRight[i];
				}
			}
			merge(items, low, high);
		}
		return items;
	}

	private void merge(Contestant[] structure, int min, int max) {
		Contestant[] s = Arrays.copyOf(structure, structure.length);
		int middle = getMiddle();
		int left = min;
		int right = middle;
		for (int i = min; i < max; i++) {
			if (left >= middle) {
				structure[i] = s[right++];
			} else if (right >= max) {
				structure[i] = s[left++];
			} else if (s[left].isHiggherScoredThan(s[right])) {
				structure[i] = s[left++];
			} else {
				structure[i] = s[right++];
			}
		}
	}

	private int getMiddle() {
		return low + (high - low) / 2;
	}
}
