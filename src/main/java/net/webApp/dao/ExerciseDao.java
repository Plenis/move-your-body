package net.webApp.dao;

import net.webApp.model.Exercise;
import net.webApp.model.Intensity;
import net.webApp.model.Player;
import net.webApp.model.Progress;

import java.util.List;

public interface ExerciseDao {
   boolean add(Exercise exercise);
   List<Exercise> getAll();
   Exercise getById(Long id);
   Long getIdByName(String name);
   Long getIntensityByName(String name);
   boolean delete(Long exerciseId);
   public boolean update(Long exerciseId, Exercise exercise);
   public boolean addPlayerExercise(Long playerId, Long exerciseId);

   List<Intensity> getAllIntensity();
   boolean addProgress(Progress progress);
}
