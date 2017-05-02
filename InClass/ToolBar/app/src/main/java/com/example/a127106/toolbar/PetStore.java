package com.example.a127106.toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 127106 on 11/18/2016.
 */
public class PetStore {
   private static PetStore instance;
   private List<Pet> _pets;

   public static PetStore getInstance() {
      if (instance == null)
         instance = new PetStore();
      return instance;
   }

   private PetStore() {
      _pets = new ArrayList<>();
      _pets.add(new Pet("Jeff", "Iguana"));
      _pets.add(new Pet("Remmy", "Golden Retriever"));
      _pets.add(new Pet("Hammy", "Squirrel"));
      _pets.add(new Pet("Buttercup", "Bunny"));
   }

   public String showPets(){
      String x = null;
      for (Pet p : _pets){
         x += p.getName() + "\n";
      }
      return x;
   }

   public void addPet(Pet newPet) {
      if (!_pets.contains(newPet))
         _pets.add(newPet);
   }

   public void addPet() {
      _pets.add(new Pet("Doug", "Dog"));
   }

   public void removePet() {
      if (_pets.size() > 0)
         _pets.remove(_pets.size() - 1);
   }
}
