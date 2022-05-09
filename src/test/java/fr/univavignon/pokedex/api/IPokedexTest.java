package fr.univavignon.pokedex.api;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class IPokedexTest {

    @Mock
    IPokemonMetadataProvider pokemonMetadataProvider;

    @Mock
    IPokemonFactory pokemonFactory;

    private Pokemon bulbizarre;
    private Pokemon aquali;
    private IPokedex pokedex;


    @BeforeEach
    void setUp() {
        this.pokedex = new Pokedex(pokemonFactory, pokemonMetadataProvider);

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
    void size() {
        //test adding all the pokemon
        assertEquals(0, pokedex.size());
        pokedex.addPokemon(bulbizarre);
        System.out.println(pokedex);
        assertEquals(1, pokedex.size());
        pokedex.addPokemon(aquali);
        assertEquals(2, pokedex.size());
        // Adding the same pokemon should not change the size
        pokedex.addPokemon(aquali);
        assertEquals(2, pokedex.size());
    }

    @Test
    void addPokemon() {

        int index0 = pokedex.addPokemon(bulbizarre);
        int index1 = pokedex.addPokemon(aquali);
        int index2 = pokedex.addPokemon(aquali);

        assertEquals(2, pokedex.size());

        // each pokemon has a unique pokedex id
        assertEquals(0, index0);
        assertEquals(1, index1);
        assertEquals(index1, index2);
    }

    @Test
    void getPokemon() {

        int index0 = pokedex.addPokemon(bulbizarre);
        int index1 = pokedex.addPokemon(aquali);

        assertDoesNotThrow(() -> {
            assertNotNull(pokedex.getPokemon(index0));
            assertEquals(bulbizarre, pokedex.getPokemon(index0));
            assertNotNull(pokedex.getPokemon(index1));
            assertEquals(aquali, pokedex.getPokemon(index1));
        });
        assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(-1);
            pokedex.getPokemon(3);
        });
    }

    @Test
    void getPokemons() {
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(0, pokemons.size());

        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(aquali);
        assertEquals(2, pokemons.size());
    }

    @Test
    void getPokemonsInSpecificOrder() {

        pokedex.addPokemon(aquali);
        pokedex.addPokemon(bulbizarre);

        Comparator<Pokemon> orderById = new Comparator<Pokemon>() {
            @Override
            public int compare(Pokemon pokemon, Pokemon t1) {
                if (pokemon.getIndex() < t1.getIndex()) return 1;
                else return -1;
            }
        };

        List<Pokemon> pokemons = pokedex.getPokemons(orderById);
        assertNotNull(pokemons);
        assertEquals(bulbizarre, pokemons.get(1));
        assertEquals(aquali, pokemons.get(0));
        assertEquals(2, pokemons.size());
    }
}