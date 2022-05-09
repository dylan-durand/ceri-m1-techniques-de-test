package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IPokedexFactoryTest {

    @Mock
    private IPokemonMetadataProvider pokemonMetadataProviderMock;

    @Mock
    private IPokemonFactory pokemonFactoryMock;

    private Pokemon bulbizarre;

    private Pokemon aquali;

    private IPokedexFactory pokedexFactory;

    @BeforeEach
    void setUp() {

        pokedexFactory = new PokedexFactory();

        bulbizarre = new Pokemon(
                0,
                "Bulbizarre",
                126,
                126,
                90,
                613,
                64,
                4000,
                4,
                56
        );

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
    void createPokedex() {
        assertDoesNotThrow(() -> {
                Mockito.when(pokemonFactoryMock.createPokemon(
                    Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
                    .thenReturn(bulbizarre);
            Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(0)).thenReturn(aquali);
        });

        // test function
        IPokedex pokedex = this.pokedexFactory.createPokedex(pokemonMetadataProviderMock, pokemonFactoryMock);
        assertNotNull(pokedex);

        assertDoesNotThrow(() -> {
            // verify that the pokedex uses the pokemon metadata from the pokemonMetadataProvider
            assertNotNull(pokedex.getPokemonMetadata(0));
            assertEquals(aquali, pokedex.getPokemonMetadata(0));
            Mockito.verify(pokemonMetadataProviderMock, Mockito.times(2)).getPokemonMetadata(0);
        });

        // verify that the created pokedex uses the pokemon created by the pokemonFactory
        Pokemon createdPokemon = pokedex.createPokemon(0,0,0,0,0);
        assertEquals(bulbizarre, createdPokemon);
        Mockito.verify(pokemonFactoryMock).createPokemon(0,0,0,0,0);

    }

}