package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @Mock
    private IPokemonMetadataProvider pokemonMetadataProvider;

    @Mock
    private PokemonMetadata pokemonMetadataMock;

    @BeforeEach
    void setUp() {
        //pokemonFactory = new PokemonFactory(pokemonMetadataProvider);
        pokemonFactory = new RocketPokemonFactory();
    }

    @Test
    void createPokemon() {

        // test with correct pokemon values
        int baseAttack = 10;
        int baseDefense = 10;
        int baseStamina = 10;
        int max_stat_by_individual = 15;
        assertDoesNotThrow(() -> {
            Mockito.when(pokemonMetadataProvider.getPokemonMetadata(Mockito.anyInt())).thenReturn(pokemonMetadataMock);
        });
        Mockito.when(pokemonMetadataMock.getName()).thenReturn("testName");
        Mockito.when(pokemonMetadataMock.getAttack()).thenReturn(baseAttack);
        Mockito.when(pokemonMetadataMock.getDefense()).thenReturn(baseDefense);
        Mockito.when(pokemonMetadataMock.getStamina()).thenReturn(baseStamina);

        Pokemon pokemonTestInstance = pokemonFactory.createPokemon(0,613,64, 4000, 4 );
        assertNotNull(pokemonTestInstance);
        assertEquals(0, pokemonTestInstance.getIndex());
        assertEquals(613, pokemonTestInstance.getCp());
        assertEquals(64, pokemonTestInstance.getHp());
        assertEquals(4000, pokemonTestInstance.getDust());
        assertEquals(4, pokemonTestInstance.getCandy());

        assertEquals(baseAttack + (int) Math.round(max_stat_by_individual * pokemonTestInstance.getIv()),
                pokemonTestInstance.getAttack());
        assertEquals(baseDefense + (int) Math.round(max_stat_by_individual * pokemonTestInstance.getIv()),
                pokemonTestInstance.getDefense());
        assertEquals(baseStamina + (int) Math.round(max_stat_by_individual * pokemonTestInstance.getIv()),
                pokemonTestInstance.getStamina());
        assertDoesNotThrow(() -> {
            Mockito.verify(pokemonMetadataProvider).getPokemonMetadata(0);
        });


        // test wrong pokemon values
        Pokemon pokemonWithNegatifAttributes = pokemonFactory.createPokemon(-1,-1,-1, -1, -1);
        Pokemon pokemonWithIdSuperiorThanOneHundredAndFifty = pokemonFactory.createPokemon(151,2729,202, 5000, 4 );


        assertNotNull(pokemonWithNegatifAttributes);
        assert(pokemonWithNegatifAttributes.getIndex() >= 0
                && pokemonWithNegatifAttributes.getIndex() >= 0);
        assert(pokemonWithNegatifAttributes.getCp() >= 0);
        assert(pokemonWithNegatifAttributes.getHp() >= 0);
        assert(pokemonWithNegatifAttributes.getDust() >= 0);
        assert(pokemonWithNegatifAttributes.getCandy() >= 0);

        assertNotNull(pokemonWithIdSuperiorThanOneHundredAndFifty);
        assert(pokemonWithIdSuperiorThanOneHundredAndFifty.getIndex() <= 150
                && pokemonWithIdSuperiorThanOneHundredAndFifty.getIndex() >= 0);
    }
}