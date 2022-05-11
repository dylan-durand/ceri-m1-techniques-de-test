package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IPokemonTrainerFactoryTest {

    @Mock
    private PokedexFactory pokedexFactory;

    @Mock
    private IPokedex pokedex;

    private IPokemonTrainerFactory pokemonTrainerFactory;

    private Pokemon aquali;

    @BeforeEach
    void setUp() {
        this.pokemonTrainerFactory = new PokemonTrainerFactory();
        aquali = new Pokemon(
                133,
                "Aquali",
                186,
                168,
                260,
                2729,
                202,
                5000,
                4,
                100
        );
    }

    @Test
    void createTrainer() {
        Mockito.when(pokedexFactory.createPokedex(Mockito.any(), Mockito.any())).thenReturn(pokedex);
        assertDoesNotThrow(() -> {
                    Mockito.when(pokedex.getPokemon(Mockito.anyInt())).thenReturn(aquali);
        });

        PokemonTrainer pokemonTrainer1 = pokemonTrainerFactory.createTrainer("Sacha", Team.INSTINCT, pokedexFactory);
        assertNotNull(pokemonTrainer1);
        assertEquals("Sacha", pokemonTrainer1.getName());
        assertEquals(Team.INSTINCT, pokemonTrainer1.getTeam());
        assertDoesNotThrow(() -> {
            assertEquals(aquali, pokemonTrainer1.getPokedex().getPokemon(0));
        });
        Mockito.verify(pokedexFactory).createPokedex(Mockito.any(), Mockito.any());
    }
}