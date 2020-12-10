package net.webApp.model;

public class PlayerExercise {
   private Long id;
   private Long playerId;
   private Long exerciseId;

   public PlayerExercise(){}

   public PlayerExercise(Long id, Long playerId, Long exerciseId){
      this.id = id;
      this. playerId = playerId;
      this.exerciseId = exerciseId;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public void setPlayerId(Long playerId) {
      this.playerId = playerId;
   }

   public void setExerciseId(Long exerciseId) {
      this.exerciseId = exerciseId;
   }

   public Long getPlayerId() {
      return playerId;
   }

   public Long getExerciseId() {
      return exerciseId;
   }

   public Long getId() {
      return id;
   }

   @Override
   public String toString() {
      return "PlayerExercise{" +
              "id=" + id +
              ", playerId=" + playerId +
              ", exerciseId=" + exerciseId +
              '}';
   }
}


