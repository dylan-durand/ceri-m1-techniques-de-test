package fr.univavignon.pokedex.api;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {
    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        String name = String.format("defaultPokemon%d", index);
        if (index >= 0 && index <= 150)
            return new PokemonMetadata(index, name, 100,100,100);
        else throw new PokedexException("bad index when searching metadata");
    }
}
