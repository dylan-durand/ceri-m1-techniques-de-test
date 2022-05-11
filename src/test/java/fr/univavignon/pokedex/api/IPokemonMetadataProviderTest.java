package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider pokemonMetadataProvider;

    @BeforeEach
    void setUp() {
        pokemonMetadataProvider = new PokemonMetadataProvider();
    }

    @Test
    void getPokemonMetadata() {
        assertDoesNotThrow(() -> {
            PokemonMetadata pokemonMetadata = pokemonMetadataProvider.getPokemonMetadata(0);
            assertNotNull(pokemonMetadata);
            assertNotNull(pokemonMetadataProvider.getPokemonMetadata(133));

            assert(pokemonMetadata.getIndex() >= 0 && pokemonMetadata.getIndex() <= 150);
            assert(pokemonMetadata.getName() != null && !pokemonMetadata.getName().isEmpty());
            assert(pokemonMetadata.getAttack() >= 0);
            assert(pokemonMetadata.getDefense()>=0);
            assert(pokemonMetadata.getStamina()>=0);
        });
        assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(-1);
            pokemonMetadataProvider.getPokemonMetadata(151);
        });
    }
}