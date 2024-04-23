/*
nombre del grupo:

- Valentin Pecqueux
- José Canto
*/

package org.mps.selection;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mps.EvolutionaryAlgorithmException;
import org.mps.mutation.SwapMutation;
import org.mps.selection.TournamentSelection;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TournamentSelectionTest {

    TournamentSelection tournamentSelection;

    @Mock
    Random randomMock = new Random();

    @InjectMocks
    TournamentSelection tournamentSelectionInjected = new TournamentSelection(5);

    public TournamentSelectionTest() throws EvolutionaryAlgorithmException {
    }

    @BeforeEach
    public void setUp() throws EvolutionaryAlgorithmException {

        tournamentSelection = new TournamentSelection(5);

    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -42})
    public void TournamentSelectionCreateWithInvalidSizeThrowException(int size) {

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            TournamentSelection tournamentSelection = new TournamentSelection(size);
        });

    }

    @Test
    public void select_population_null_throw_error_test() {
        int[] population = null;
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            tournamentSelection.select(population);
        });
    }

    @Test
    public void select_population_empty_throw_error_test() {
        int[] population = {};
        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            tournamentSelection.select(population);
        });
    }

}
