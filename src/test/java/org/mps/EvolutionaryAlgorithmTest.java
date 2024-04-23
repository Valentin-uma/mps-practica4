/*
nombre del grupo:

- Valentin Pecqueux
- José Canto
*/

package org.mps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mps.crossover.CrossoverOperator;
import org.mps.crossover.OnePointCrossover;
import org.mps.mutation.MutationOperator;
import org.mps.mutation.SwapMutation;
import org.mps.selection.SelectionOperator;
import org.mps.selection.TournamentSelection;

import static org.junit.jupiter.api.Assertions.*;

public class EvolutionaryAlgorithmTest {

    public EvolutionaryAlgorithm algorithm;

    @BeforeEach
    public void setup_algorithm() {
        try {
            SelectionOperator selectionOperator = new TournamentSelection(5);
            MutationOperator mutationOperator = new SwapMutation();
            CrossoverOperator crossoverOperator = new OnePointCrossover();
            algorithm = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
        } catch (EvolutionaryAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void setup_nullArguments() {
            SelectionOperator selectionOperator = null;
            MutationOperator mutationOperator = null;
            CrossoverOperator crossoverOperator = null;

            assertThrows(EvolutionaryAlgorithmException.class, () -> {
                algorithm = new EvolutionaryAlgorithm(selectionOperator, mutationOperator, crossoverOperator);
            });
    }

    @Test
    @DisplayName("get/setMutationOperator function after inicialize algorithm")
    public void setGetMutationOperator_inicializedAlgorithm_returnOperator() {
        MutationOperator newOperator = new SwapMutation();
        MutationOperator oldReturnedOperator = algorithm.getMutationOperator();

        algorithm.setMutationOperator(newOperator);
        MutationOperator newReturnedOperator = algorithm.getMutationOperator();

        assertEquals(newOperator, newReturnedOperator);
        assertNotEquals(oldReturnedOperator, newReturnedOperator);
    }

    @Test
    @DisplayName("get/setSelectionOperator function after inicialize algorithm")
    public void setGetSelectionOperator_inicializedAlgorithm_returnOperator() {
        try {
            SelectionOperator newOperator = new TournamentSelection(5);
            SelectionOperator oldReturnedOperator = algorithm.getSelectionOperator();

            algorithm.setSelectionOperator(newOperator);
            SelectionOperator newReturnedOperator = algorithm.getSelectionOperator();

            assertEquals(newOperator, newReturnedOperator);
            assertNotEquals(oldReturnedOperator, newReturnedOperator);
        } catch (EvolutionaryAlgorithmException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    @DisplayName("get/setCrossoverOperator function after inicialize algorithm")
    public void setGetCrossoverOperator_inicializedAlgorithm_returnOperator() {
        CrossoverOperator newOperator = new OnePointCrossover();
        CrossoverOperator oldReturnedOperator = algorithm.getCrossoverOperator();

        algorithm.setCrossoverOperator(newOperator);
        CrossoverOperator newReturnedOperator = algorithm.getCrossoverOperator();

        assertEquals(newOperator, newReturnedOperator);
        assertNotEquals(oldReturnedOperator, newReturnedOperator);
    }

    @Test
    public void testOptimize_ValidPopulation() throws EvolutionaryAlgorithmException {
        // Arrange
        int[][] population = {{1, 2, 3}, {4, 5, 6}};

        // Act
        int[][] result = algorithm.optimize(population);

        // Assert
        assertNotNull(result);
        assertEquals(population.length, result.length);
        for (int i = 0; i < population.length; i++) {
            assertArrayEquals(population[i], result[i]);
        }
    }

    @Test
    public void testOptimize_NullPopulation() throws EvolutionaryAlgorithmException {
        // Arrange
        int[][] population = null;

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            algorithm.optimize(population);

        });
        // Act & Assert
    }
}
