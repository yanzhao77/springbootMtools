package com.yz.toolscommon.soft.sort.ant;

// Import necessary packages
import java.util.ArrayList;
import java.util.Random;

// Define Ant class
class Ant {
	int currentCity;
	ArrayList<Integer> visitedCities;
	double tourLength;

	// Constructor
	Ant(int startCity) {
		currentCity = startCity;
		visitedCities = new ArrayList<Integer>();
		visitedCities.add(startCity);
		tourLength = 0.0;
	}

	// Move to next city based on pheromone levels and distances
	void move(double[][] pheromoneLevels, double[][] distances, double alpha, double beta) {
		int numCities = pheromoneLevels.length;
		double[] probabilities = new double[numCities];
		double totalProbability = 0.0;

		// Calculate probabilities for each city
		for (int i = 0; i < numCities; i++) {
			if (!visitedCities.contains(i)) {
				probabilities[i] = Math.pow(pheromoneLevels[currentCity][i], alpha)
						* Math.pow(1.0 / distances[currentCity][i], beta);
				totalProbability += probabilities[i];
			}
		}

		// Choose next city based on probabilities
		Random rand = new Random();
		double randNum = rand.nextDouble() * totalProbability;
		int nextCity = -1;
		while (randNum >= 0.0) {
			nextCity++;
			if (!visitedCities.contains(nextCity)) {
				randNum -= probabilities[nextCity];
			}
		}

		// Update tour length and visited cities
		tourLength += distances[currentCity][nextCity];
		visitedCities.add(nextCity);
		currentCity = nextCity;
	}
}

// Define AntColony class
class AntColony {
	int numAnts;
	int numCities;
	double[][] pheromoneLevels;
	double[][] distances;
	ArrayList<Ant> ants;
	Ant bestAnt;

	// Constructor
	AntColony(int numAnts, double[][] distances, double initialPheromoneLevel) {
		this.numAnts = numAnts;
		this.numCities = distances.length;
		this.pheromoneLevels = new double[numCities][numCities];
		this.distances = distances;
		this.ants = new ArrayList<Ant>();
		this.bestAnt = null;

		// Initialize pheromone levels
		for (int i = 0; i < numCities; i++) {
			for (int j = 0; j < numCities; j++) {
				pheromoneLevels[i][j] = initialPheromoneLevel;
			}
		}

		// Create ants
		for (int i = 0; i < numAnts; i++) {
			ants.add(new Ant(i % numCities));
		}
	}

	// Run ant colony optimization algorithm
	void runACO(int numIterations, double alpha, double beta, double evaporationRate) {
		for (int i = 0; i < numIterations; i++) {
			// Move ants
			for (Ant ant : ants) {
				while (ant.visitedCities.size() < numCities) {
					ant.move(pheromoneLevels, distances, alpha, beta);
				}
				ant.tourLength += distances[ant.currentCity][ant.visitedCities.get(0)];
				if (bestAnt == null || ant.tourLength < bestAnt.tourLength) {
					bestAnt = ant;
				}
			}

			// Update pheromone levels
			for (int j = 0; j < numCities; j++) {
				for (int k = 0; k < numCities; k++) {
					pheromoneLevels[j][k] *= (1.0 - evaporationRate);
				}
			}
			for (int j = 0; j < numCities; j++) {
				int city1 = bestAnt.visitedCities.get(j);
				int city2 = bestAnt.visitedCities.get((j + 1) % numCities);
				pheromoneLevels[city1][city2] += 1.0 / bestAnt.tourLength;
				pheromoneLevels[city2][city1] += 1.0 / bestAnt.tourLength;
			}

			// Reset ants
			for (Ant ant : ants) {
				ant.currentCity = ant.visitedCities.get(0);
				ant.visitedCities.clear();
				ant.visitedCities.add(ant.currentCity);
				ant.tourLength = 0.0;
			}
		}
	}
}

// Example usage
public class AntColonyOptimization {
	public static void main(String[] args) {
		// Define distances between cities
		double[][] distances = { { 0.0, 1.0, 2.0, 3.0 }, { 1.0, 0.0, 4.0, 5.0 }, { 2.0, 4.0, 0.0, 6.0 },
				{ 3.0, 5.0, 6.0, 0.0 } };

		// Define parameters
		int numAnts = 10;
		int numIterations = 100;
		double alpha = 1.0;
		double beta = 2.0;
		double initialPheromoneLevel = 0.1;
		double evaporationRate = 0.5;

		// Run ant colony optimization algorithm
		AntColony colony = new AntColony(numAnts, distances, initialPheromoneLevel);
		colony.runACO(numIterations, alpha, beta, evaporationRate);

		// Print best tour found
		System.out.println("Best tour found: " + colony.bestAnt.visitedCities.toString());
		System.out.println("Tour length: " + colony.bestAnt.tourLength);
	}
}