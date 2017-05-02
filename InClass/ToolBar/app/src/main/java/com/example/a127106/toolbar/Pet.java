package com.example.a127106.toolbar;

/**
 * Created by 127106 on 11/18/2016.
 */
public class Pet {
   private String _name;
   private String _type;


   public Pet(String name, String type) {
      _name = name;
      _type = type;
   }

   public void setName(String name){
      _name = name;
   }

   public String getName() {
      return _name;
   }

   public void setType(String type) {
      _type = type;
   }


   public String getType() {
      return _type;
   }
}
