package com.starter.registration.enumuration;


import com.fasterxml.jackson.annotation.JsonValue;

public enum Breed {
     PERSIAN("Persia cat",Species.CAT),
     AMERICAN_CURL("American curl",Species.CAT),
     BULL_DOG("Bull dog",Species.DOG),
     PUG("American curl",Species.CAT),;


     private String breed;
     private Species species;

     Breed(String breed, Species species) {
         this.breed = breed;
         this.species = species;
     }

     @JsonValue
     public String getBreed() {
         return breed;
     }

    public Species getSpecies() {
        return species;
    }

    public String toString() {
        return breed;
    }

}
