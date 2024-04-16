package org.mps.mutation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mps.EvolutionaryAlgorithmException;
import org.mps.mutation.SwapMutation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SwapMutationTest {

    SwapMutation swapMutation;

    @Mock
    Random randomMock = new Random();

    @InjectMocks
    SwapMutation swapMutationInjected;

    @BeforeEach
    public void setUp() {

        swapMutation = new SwapMutation();

    }

    @Test
    public void mutate_individual_null_throw_error_test() {
        int[] individual = null;
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            swapMutation.mutate(individual);
        });
    }

    @Test
    public void mutate_individual_empty_throw_error_test() {
        int[] individual = {};
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            swapMutation.mutate(individual);
        });
    }

    @Test
    public void mutate_individual_1_element_return_same_list_test() throws EvolutionaryAlgorithmException {
        int[] individual = {3};
        int[] individualReturn = swapMutation.mutate(individual);
        assertArrayEquals(individual, individualReturn);

    }

    @Test
    public void mutate_individual_2_element_known_return_inversed_list_test() throws EvolutionaryAlgorithmException {
        int[] individual = {3,5};
        when(randomMock.nextInt(individual.length)).thenReturn(0,1);
        int[] individualReturn = swapMutationInjected.mutate(individual);
        assertArrayEquals(new int[]{5, 3}, individualReturn);

    }

    @Test
    public void mutate_individual_2_element_known_same_indice_return_same_list_test() throws EvolutionaryAlgorithmException {
        int[] individual = {3,5};
        when(randomMock.nextInt(individual.length)).thenReturn(1,1);
        int[] individualReturn = swapMutationInjected.mutate(individual);
        assertArrayEquals(individual, individualReturn);

    }


}
