package nl.maastrichtuniversity.dke.libreason.genetics.reproduction;

import nl.maastrichtuniversity.dke.libreason.genetics.GeneticIndividual;
import nl.maastrichtuniversity.dke.libreason.genetics.GeneticPopulation;

public class CrossReproduction implements ReproductionMethod
{
	private GeneticPopulation pop;
	
	public GeneticPopulation getResult()
	{
		GeneticPopulation newGeneration = new GeneticPopulation();
		for(GeneticIndividual i : pop)
		{
			for(GeneticIndividual j: pop)
			{
				GeneticIndividual newGI = i.reproduceWith(j);
				newGI.mutate();
				newGeneration.add(newGI);
			}
		}
		return newGeneration;
	}

	public int getResultSize()
	{
		if(pop == null)
		{
		return 0;
		}
		return pop.size()*pop.size();
	}

	public void setPopulation(GeneticPopulation pop)
	{
		this.pop = pop;
	}

}
