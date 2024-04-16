package org.mps.crossover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OnePointCrossoverTest {

    public OnePointCrossover crossover;

    @BeforeEach
    public void onePointCrossoverSetup () {
        crossover = new OnePointCrossover();
    }

    @Test
    @DisplayName("Crossover when parent1 is null")
    public void crossover_Parent1Null_returnsException() {
        int[] parent1 = null;
        int[] parent2 = {1, 2, 3};

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            crossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("Crossover when parent2 is null")
    public void crossover_Parent2Null_returnsException() {
        int[] parent1 = {1, 2, 3};
        int[] parent2 = null;

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            crossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("Crossover when parent1 length is zero")
    public void  crossover_WhenParent1LengthZero_returnException() {
        int[] parent1 = {};
        int[] parent2 = {1, 2, 3};

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            crossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("Crossover when parent1 length is equal to parent2 length")
    public void  crossover_WhenParent1LengthEqualParent2Length_returnException() {
        int[] parent1 = {1, 2, 3};
        int[] parent2 = {1, 2};

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            crossover.crossover(parent1, parent2);
        });
    }

    @Test
    @DisplayName("Crossover when parents are valid")
    public void crossover_WhenParentsAreValid_returnNewArray() {
        int[] parent1 = {1, 2, 3};
        int[] parent2 = {4, 5, 6};
        int[][] unexpectedResult = null;
        int[][] returnedResult = null;

        try {
            returnedResult = crossover.crossover(parent1, parent2);
        } catch (EvolutionaryAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        assertNotEquals(unexpectedResult, returnedResult);
    }
}
