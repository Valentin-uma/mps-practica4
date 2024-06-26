/*
nombre del grupo:

- Valentin Pecqueux
- José Canto
*/

package org.mps.crossover;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mps.EvolutionaryAlgorithmException;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OnePointCrossoverTest {

    public OnePointCrossover crossover;

    @Mock
    Random randomMock = new Random();

    @InjectMocks
    OnePointCrossover crossoverInjectedMock;

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

    @Test
    @DisplayName("Crossover when Random Value is determined")
    public void crossover_WhenParentsAreValidAndRandomValueDetermined_returnedCorrectArray() {
        int[] parent1 = {1, 2, 3};
        int[] parent2 = {4, 5, 6};
        int[][] expectedValue = {{1, 2, 6}, {4, 5, 3}};
        int[][] returnedValue = null;
        when(randomMock.nextInt(parent1.length - 1)).thenReturn(1);

        try {
            returnedValue = crossoverInjectedMock.crossover(parent1, parent2);
        } catch (EvolutionaryAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        assertArrayEquals(expectedValue, returnedValue);
    }
}
