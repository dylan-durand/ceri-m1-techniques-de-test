package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

import static org.junit.jupiter.api.Assertions.*;

class IPokemonTrainerFactoryTest {

    @Mock
    private PokedexFactory pokedexFactory;

    private IPokemonTrainerFactory pokemonTrainerFactory;


    @BeforeEach
    void setUp() {

        this.pokemonTrainerFactory = new PokemonTrainerFactory();
    }

    @Test
    void createTrainer() {


        PokemonTrainer pokemonTrainer1 = pokemonTrainerFactory.createTrainer("Sacha", Team.INSTINCT, pokedexFactory);
        assertNotNull(pokemonTrainer1);
        assertEquals("Sacha", pokemonTrainer1.getName());
        assertEquals(Team.INSTINCT, pokemonTrainer1.getTeam());
        pokemonTrainer1.getPokedex();
        Mockito.verify(pokedexFactory).createPokedex(Mockito.any(), Mockito.any());
    }
}