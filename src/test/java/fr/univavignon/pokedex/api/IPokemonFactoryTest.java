package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IPokemonFactoryTest {

    private IPokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() {
        pokemonFactory = new PokemonFactory();
    }

    @Test
    void createPokemon() {
        Pokemon bulbizarre = pokemonFactory.createPokemon(0,613,64, 4000, 4 );
        Pokemon aquali = pokemonFactory.createPokemon(133,2729,202, 5000, 4 );
        Pokemon pokemonWithNegatifAttributes = pokemonFactory.createPokemon(-1,-1,-1, -1, -1);
        Pokemon pokemonWithIdSuperiorThanOneHundredAndFifty = pokemonFactory.createPokemon(151,2729,202, 5000, 4 );

        assertNotNull(bulbizarre);
        assertEquals(0, bulbizarre.getIndex());
        assertEquals(613, bulbizarre.getCp());
        assertEquals(64, bulbizarre.getHp());
        assertEquals(4000, bulbizarre.getDust());
        assertEquals(4, bulbizarre.getCandy());

        assertNotNull(aquali);
        assertEquals(133, aquali.getIndex());
        assertEquals(2729, aquali.getCp());
        assertEquals(202, aquali.getHp());
        assertEquals(5000, aquali.getDust());
        assertEquals(4, aquali.getCandy());

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