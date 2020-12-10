package net.webApp.model;

public class Intensity {
   private Long id;
   private String name;

   public Intensity() {}

   public Intensity(Long id, String name) {
      this.id = id;
      this.name = name;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   @Override
   public String toString() {
      return "Intensity{" +
              "id=" + id +
              ", name='" + name + '\'' +
              '}';
   }
}
