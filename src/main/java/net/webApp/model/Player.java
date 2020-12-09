package net.webApp.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
   private Long id;
   private String name;
   private double weight;
   private double height;
   private int age;

   private List<PlayerExercise> playerExercises = new ArrayList<>();
   private List<Exercise> exercises = new ArrayList<>();

   public Player() {}

   public Player(Long id, String name, double weight, double height, int age){
      this.id = id;
      this.name = name;
      this.weight = weight;
      this.height = height;
      this.age = age;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setWeight(double weight) {
      this.weight = weight;
   }

   public void setHeight(double height) {
      this.height = height;
   }

   public void setAge(int age) {
      this.age = age;
   }

   public Long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public double getWeight() {
      return weight;
   }

   public double getHeight() {
      return height;
   }

   public int getAge() {
      return age;
   }

   public void addPlayerExercise(PlayerExercise exercise) {
      playerExercises.add(exercise);
   }

   public void addExercise(Exercise exercise) {
      exercises.add(exercise);
   }

   public List<PlayerExercise> getPlayerExercises() {
      return playerExercises;
   }

   public List<Exercise> getExercises() {
      return exercises;
   }

   @Override
   public String toString() {
      return "Player{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", weight=" + weight +
              ", height=" + height +
              ", age=" + age +
              ", playerExercises=" + playerExercises +
              ", exercises=" + exercises +
              '}';
   }
}

