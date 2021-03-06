package algorithms;

import java.util.ArrayList;
import java.util.List;

import model.Measure;

public class GeneticAlgorithm {
	
	private double mutationProbability;
	private int tournamentSize;
	private int noteRange;
	
	public GeneticAlgorithm(double mutationProbability, int tournamentSize, int noteRange) {
		super();
		this.mutationProbability = mutationProbability;
		this.tournamentSize = tournamentSize;
		this.noteRange = noteRange;
	}
	
	
	public List<Measure> produceNewIndividuals(List<Measure> population, int num) {
		List<Measure> result = new ArrayList<>();
		
		for (int i=0; i<num; i++) {
			List<Measure> candidates = RandomGenerator.getGenerator().randomFromPopulation(population, tournamentSize);
			Measure parent1 = new Tournament(candidates).getWinner();
			candidates = RandomGenerator.getGenerator().randomFromPopulation(population, tournamentSize);
			Measure parent2 = new Tournament(candidates).getWinner();
			
			Measure child = SimpleCrossOver.getInstance().apply(parent1, parent2);
			
			Measure childMutated = SimpleMutation.getInstance().mutate(child, mutationProbability, noteRange);
			
			result.add(childMutated);
		}
		
		return result;
	}

}
