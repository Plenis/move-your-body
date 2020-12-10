package net.webApp.model;

public class Progress {
   private Long id;
   private int timeCompleted;
   private Long intensityId;
   private double caloriesLost;
   private Long playerId;
   private Long exerciseId;

   public Progress () {}

   public Progress(Long id, int timeCompleted, Long intensityId, double weightLost, Long playerId, Long exerciseId) {
      this.id = id;
      this.timeCompleted = timeCompleted;
      this.intensityId = intensityId;
      this.caloriesLost = weightLost;
      this. playerId = playerId;
      this.exerciseId = exerciseId;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return id;
   }

   public void setTimeCompleted(int timeCompleted) {
      this.timeCompleted = timeCompleted;
   }

   public int getTimeCompleted() {
      return timeCompleted;
   }

   public void setIntensityId(Long intensityId) {
      this.intensityId = intensityId;
   }

   public Long getIntensityId() {
      return intensityId;
   }

   public void setCaloriesLost(double caloriesLost) {
      this.caloriesLost = caloriesLost;
   }

   public double getCaloriesLost() {
      return caloriesLost;
   }

   public void setPlayerId(Long playerId) {
      this.playerId = playerId;
   }

   public Long getPlayerId() {
      return playerId;
   }

   public void setExerciseId(Long exerciseId) {
      this.exerciseId = exerciseId;
   }

   public Long getExerciseId() {
      return exerciseId;
   }

   @Override
   public String toString() {
      return "Progress{" +
              "id=" + id +
              ", timeCompleted=" + timeCompleted +
              ", intensity='" + intensityId + '\'' +
              ", weightLost=" + caloriesLost +
              ", playerId=" + playerId +
              ", exerciseId=" + exerciseId +
              '}';
   }
}

