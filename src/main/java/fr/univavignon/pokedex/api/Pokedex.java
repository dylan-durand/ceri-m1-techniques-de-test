package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex {

    private final List<Pokemon> pokemonList;
    private final IPokemonFactory pokemonFactory;
    private final IPokemonMetadataProvider pokemonMetadataProvider;

    public Pokedex(IPokemonFactory pokemonFactory, IPokemonMetadataProvider pokemonMetadataProvider) {
        this.pokemonFactory = pokemonFactory;
        this.pokemonMetadataProvider = pokemonMetadataProvider;
        this.pokemonList = new ArrayList<>();
    }

    @Override
    public int size() {
        return pokemonList.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        if (!pokemonList.contains(pokemon)) pokemonList.add(pokemon);
        return pokemonList.indexOf(pokemon);
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        try {
            return pokemonList.get(id);
        }
        catch (IndexOutOfBoundsException e){
            throw new PokedexException("Index out of bounds in pokedex");
        }
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemonList;
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        List<Pokemon> sortedPokemonList = new ArrayList<>(pokemonList);
        sortedPokemonList.sort(order);
        return sortedPokemonList;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return pokemonMetadataProvider.getPokemonMetadata(index);
    }
}
