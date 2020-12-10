package net.webApp.model;

public class Exercise {
   private Long id;
   private String name;
   private int met;

   public Exercise() {}

   public Exercise(Long id, String name, int met) {
      this.id = id;
      this.name = name;
      this.met = met;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setMet(int met) {
      this.met = met;
   }

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public int getMet() {
      return met;
   }

   @Override
   public String toString() {
      return "Exercise{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", met=" + met +
              '}';
   }
}

