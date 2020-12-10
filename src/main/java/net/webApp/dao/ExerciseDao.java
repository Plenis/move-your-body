package net.webApp.dao;

import net.webApp.model.Exercise;
import net.webApp.model.Player;

import java.util.List;

public interface ExerciseDao {
   boolean add(Exercise exercise);
   List<Exercise> getAll();
   Exercise getById(Long id);
   boolean delete(Long exerciseId);
   public boolean update(Long exerciseId, Exercise exercise);
   public boolean addPlayerExercise(Long playerId, Long exerciseId);

}
