package ballzeroth.astar;

import ballzeroth.main.Screen;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

/**
 * A classe retorna um mapa simples, e um possível caminho, utilizando algoritmo A* pathfinding.
 */
public class Map
{    
	/**
	 * O tamanho de cada quadrado utilizado na rendereização
	 */
        public final int blockSize = 64; // Pixels de cada quadrado
    
	/**
	 * A largura do mapa, em colunas.
	 */
	private int width;

	/**
	 * A altura do mapa, em linhas.
	 */
	private int height;

	/**
	 * Matriz de No utilizada para o algoritmo de Pathfinding.
	 */
	private Node[][] nodes;

	/**
         * Cria um mapa baseado em uma matriz recebida da Classe PAI.
         * Cada No com valor de 0 é transponível, todos os outros números,
         * são intrasponíveis.
	 * 
	 * @param map
	 * A matriz utilizada para criar o mapa.
         * 
	 */
	public Map(int[][] map)
	{                
		this.width = map[0].length;
		this.height = map.length;
                
		nodes = new Node[height][width];
                
                int width = (Screen.screenWidth / 2) - ((this.width * blockSize) / 2);

		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				nodes[x][y] = new Node(width + (x * blockSize), // Largura do tile
                                                       y * blockSize, // Altura do tile
                                                       blockSize, // Tamanho do bloco a ser renderizado
                                                       0, // Id para a imagem a ser renderizada
                                                       map[y][x] == 0); // Define se é andável
			}
		}
	}

	/**
	 * Draws the map, where each walkable node is drawn white, each non-walkable
	 * node drawn black and each node that is in the path in yellow.
	 * 
	 * @param g
	 *            A <code>Graphics</code> object in order to be able to draw
	 *            things.
	 * @param path
	 *            Optional parameter. List containing the nodes to be drawn as
	 *            path nodes.
	 */
	public void drawMap(Graphics g, List<Node> path)
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if (!nodes[x][y].isWalkable())
{
					g.setColor(Color.WHITE);
				}
				else if (path != null && path.contains(new Node(x, y, true)))
				{
					g.setColor(Color.YELLOW);
				}
				else
				{
					g.setColor(Color.BLACK);
				}
				g.fillRect(x * 32, y * 32, 32, 32);
			}
		}
	}

	/**
	 * Prints the map to the standard out, where each walkable node is simply
	 * not printed, each non-walkable node is printed as a '#' (pound sign) and
	 * each node that is in the path as a '@' (at sign).
	 * 
	 * @param path
	 *            Optional parameter. List containing the nodes to be drawn as
	 *            path nodes.
	 */
	public void printMap(List<Node> path)
	{
		for (int j = 0; j < height; j++)
		{
			for (int i = 0; i < width; i++)
			{
				if (!nodes[i][j].isWalkable())
				{
					System.out.print(" #");
				}
				else if (path.contains(new Node(i, j, true)))
				{
					System.out.print(" @");
				}
				else
				{
					System.out.print("  ");
				}
			}
			System.out.print("\n");
		}
	}

	/**
	 * If the X and Y parameters are within the map boundaries, return the node
	 * in the specific coordinates, null otherwise.
	 * 
	 * @param x
	 *            Desired node's X coordinate.
	 * @param y
	 *            Desired node's Y coordinate.
	 * @return The desired node if the parameters are valid, null otherwise.
	 */
	public Node getNode(int x, int y)
	{
		if (x >= 0 && x < width && y >= 0 && y < height)
		{
			return nodes[x][y];
		}
		else
		{
			return null;
		}
	}

	/**
	 * Tries to calculate a path from the start and end positions.
	 * 
	 * @param startX
	 *            The X coordinate of the start position.
	 * @param startY
	 *            The Y coordinate of the start position.
	 * @param goalX
	 *            The X coordinate of the goal position.
	 * @param goalY
	 *            The Y coordinate of the goal position.
	 * @return A list containing all of the visited nodes if there is a
	 *         solution, an empty list ot*herwise.
	 */
	public final List<Node> findPath(int startX, int startY, int goalX, int goalY)
	{
		// Se nossa posição for igual a posição alvo.
		if (startX == goalX && startY == goalY)
		{
			// Retorna um array vazio, pois não precisamos nos mover.
			return new LinkedList<Node>();
		}

		// Os Nodes já visitados.
		List<Node> nodesVisitados = new LinkedList<Node>();
                // Os Nodes já descobertos, que ainda não foram visitados.
		List<Node> nodesDescobertos = new LinkedList<Node>();

		// Adiciona o primeiro Node para a lista.
		nodesVisitados.add(nodes[startX][startY]);

		// O loop encerra assim que a posição atual for igual a
		// posição alvo.
		while (true) {
                        // Retorna o Node com o menos valor de F da lista.
			Node current = lowestFInList(nodesVisitados);
			// Remove o Node atual da lista de visitados.
			nodesVisitados.remove(current);
			// Add current node to closed list.
			nodesDescobertos.add(current);

			// If the current node position is equal to the goal position ...
			if ((current.getX() == goalX) && (current.getY() == goalY))
			{
				// Return a LinkedList containing all of the visited nodes.
				return calcPath(nodes[startX][startY], current);
			}

			List<Node> adjacentNodes = getAdjacent(current, nodesDescobertos);
			for (Node adjacent : adjacentNodes)
			{
				// If node is not in the open list ...
				if (!nodesVisitados.contains(adjacent))
				{
					// Set current node as parent for this node.
					adjacent.setParent(current);
					// Set H costs of this node (estimated costs to goal).
					adjacent.setH(nodes[goalX][goalY]);
					// Set G costs of this node (costs from start to this node).
					adjacent.setG(current);
					// Add node to openList.
					nodesVisitados.add(adjacent);
				}
				// Else if the node is in the open list and the G score from
				// current node is cheaper than previous costs ...
				else if (adjacent.getG() > adjacent.calculateG(current))
				{
					// Set current node as parent for this node.
					adjacent.setParent(current);
					// Set G costs of this node (costs from start to this node).
					adjacent.setG(current);
				}
			}

			// If no path exists ...
			if (nodesVisitados.isEmpty())
			{
				// Return an empty list.
				return new LinkedList<Node>();
			}
			// But if it does, continue the loop.
		}
	}

	/**
	 * @param start
	 *            The first node on the path.
	 * @param goal
	 *            The last node on the path.
	 * @return a list containing all of the visited nodes, from the goal to the
	 *         start.
	 */
	private List<Node> calcPath(Node start, Node goal)
	{
		LinkedList<Node> path = new LinkedList<Node>();

		Node node = goal;
		boolean done = false;
		while (!done)
		{
			path.addFirst(node);
			node = node.getParent();
			if (node.equals(start))
			{
				done = true;
			}
		}
		return path;
	}

	/**
	 * @param list
	 *            The list to be checked.
	 * @return The node with the lowest F score in the list.
	 */
	private Node lowestFInList(List<Node> list)
	{
		Node cheapest = list.get(0);
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).getF() < cheapest.getF())
			{
				cheapest = list.get(i);
			}
		}
		return cheapest;
	}

	/**
	 * @param node
	 *            The node to be checked for adjacent nodes.
	 * @param closedList
	 *            A list containing all of the nodes already visited.
	 * @return A LinkedList with nodes adjacent to the given node if those
	 *         exist, are walkable and are not already in the closed list.
	 */
	private List<Node> getAdjacent(Node node, List<Node> closedList)
	{
		List<Node> adjacentNodes = new LinkedList<Node>();
		int x = node.getX();
		int y = node.getY();

		Node adjacent;

		// Check left node
		if (x > 0)
		{
			adjacent = getNode(x - 1, y);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
			{
				adjacentNodes.add(adjacent);
			}
		}

		// Check right node
		if (x < width)
		{
			adjacent = getNode(x + 1, y);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
			{
				adjacentNodes.add(adjacent);
			}
		}

		// Check top node
		if (y > 0)
		{
			adjacent = this.getNode(x, y - 1);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
			{
				adjacentNodes.add(adjacent);
			}
		}

		// Check bottom node
		if (y < height)
		{
			adjacent = this.getNode(x, y + 1);
			if (adjacent != null && adjacent.isWalkable() && !closedList.contains(adjacent))
			{
				adjacentNodes.add(adjacent);
			}
		}
		return adjacentNodes;
	}
}


/*
 * Possible optimizations:
 * - calculate f as soon as g or h are set, so it will not have to be
 *      calculated each time it is retrieved
 * - store nodes in openList sorted by their f value.
 */