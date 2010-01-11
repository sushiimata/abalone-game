package nl.maastrichtuniversity.dke.libreason.impl.treesearch.queues;

import java.util.LinkedList;

import nl.maastrichtuniversity.dke.libreason.def.treesearch.SearchNode;


/**
 * A priority queue that corresponds to the A*-search strategy
 * 
 * @author Daniel Mescheder
 *
 */
public class BreadthFirstQueue extends LinkedList<SearchNode>
{
	private static final long serialVersionUID = 5060840009767474183L;
	
	public BreadthFirstQueue()
	{
	}
	
	// We map the behaviour of a stack to the
	// behaviour of a queue:
	
	@Override
	public SearchNode element()
	{
		return super.getLast();
	}
	
	@Override
	public SearchNode peek()
	{
		return super.getLast();
	}
	
	@Override
	public SearchNode poll()
	{
		return super.removeLast();
	}
	
	@Override
	public SearchNode remove()
	{
		return super.removeLast();
	}
	
	
}
