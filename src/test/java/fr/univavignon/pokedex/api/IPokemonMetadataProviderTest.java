package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPokemonMetadataProviderTest {

    private Pokemon bulbizarre;

    private Pokemon aquali;

    private IPokemonMetadataProvider pokemonMetadataProvider;

    @BeforeEach
    void setUp() {
        pokemonMetadataProvider = new PokemonMetadataProvider();
    }

    @Test
    void getPokemonMetadata() {
        assertDoesNotThrow(() -> {
            assertNotNull(pokemonMetadataProvider.getPokemonMetadata(0));
            assertNotNull(pokemonMetadataProvider.getPokemonMetadata(133));
        });
        assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(-1);
            pokemonMetadataProvider.getPokemonMetadata(151);
        });
    }
}