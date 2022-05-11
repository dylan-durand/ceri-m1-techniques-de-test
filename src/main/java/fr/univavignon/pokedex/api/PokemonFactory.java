package fr.univavignon.pokedex.api;

import java.util.Random;

public class PokemonFactory implements IPokemonFactory {

    private final static int MAX_STAT_FOR_INDIVIDUAL = 15;
    private final IPokemonMetadataProvider pokemonMetadataProvider;

    public PokemonFactory(IPokemonMetadataProvider pokemonMetadataProvider) {
        this.pokemonMetadataProvider = pokemonMetadataProvider;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        Random random = new Random();
        double iv = random.nextDouble();
        String name;
        int attack;
        int defense;
        int stamina;

        try {
            PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(index);
            name = metadata.getName();
            attack = metadata.getAttack() + (int) Math.round(iv * MAX_STAT_FOR_INDIVIDUAL);
            defense = metadata.getDefense() + (int) Math.round(iv * MAX_STAT_FOR_INDIVIDUAL);
            stamina = metadata.getStamina() + (int) Math.round(iv * MAX_STAT_FOR_INDIVIDUAL);
            if (index < 0 || index > 150 || cp < 0 || hp < 0 || dust < 0 || candy < 0 ){
                throw new PokedexException("wrong pokemon data");
            }

        } catch (PokedexException e) {
            index = 0;
            name = "defaultPokemon";
            attack = 0;
            defense = 0;
            stamina = 0;
            cp = 0;
            hp = 0;
            dust = 0;
            candy = 0;
            iv = 0;
        }

        return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
    }
}
