package nl.maastrichtuniversity.dke.libreason.genetics.reproduction;

import java.util.Collections;
import java.util.Random;

import nl.maastrichtuniversity.dke.libreason.genetics.GeneticIndividual;
import nl.maastrichtuniversity.dke.libreason.genetics.GeneticPopulation;


public class KeepBestPairwiseReproduction implements ReproductionMethod
{

	private GeneticPopulation pop;
	private int multiplicator = 2;
	private int keep;

	public KeepBestPairwiseReproduction(int multiplicator, int keepBestGroupSize)
	{
		this.multiplicator = multiplicator;
		keep = keepBestGroupSize;
	}

	public GeneticPopulation getResult()
	{

		GeneticPopulation newGeneration = new GeneticPopulation();
		Random random = new Random();
		for (int i = 0; i<keep;i++)
		{
			// Take the fittest into next generation without mutation
			GeneticIndividual max = pop.getFittest();
			max.setFitness(0);
			newGeneration.add(max);
		}
		if (pop.size() % 2 != 0)
		{
			// if there's an odd number of elements, take the fittest into next
			// generation (but still mutate it)
			GeneticIndividual max = pop.getFittest();
			pop.remove(max);
			max.mutate();
			max.setFitness(0);
			newGeneration.add(max);
		}
		for (int t = 0; t < multiplicator; t++)
		{
			for (int i = 0; i < pop.size(); i += 1)
			{
				
				GeneticIndividual newGI = pop.get(i).reproduceWith(
						pop.get(random.nextInt(pop.size())));
				newGI.mutate();
				newGeneration.add(newGI);
			}
		}

		return newGeneration;
	}

	public int getResultSize()
	{
		if (pop == null)
		{
			return 0;
		}

		return (pop.size() - (pop.size() % 2)) * multiplicator / 2;
	}

	public void setPopulation(GeneticPopulation pop)
	{
		this.pop = pop;
	}

}
