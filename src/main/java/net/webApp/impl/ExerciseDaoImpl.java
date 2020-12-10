package net.webApp.impl;

import net.webApp.dao.ExerciseDao;
import net.webApp.model.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExerciseDaoImpl implements ExerciseDao {
   private Jdbi jdbi;

   public ExerciseDaoImpl(){}

   public ExerciseDaoImpl(Jdbi jdbi){
      this.jdbi = jdbi;
   }

   @Override
   public boolean add(Exercise exercise) {
      jdbi.useHandle(handle -> handle.execute("INSERT INTO exercise(name, met) VALUES(?,?)",
              exercise.getName(),
              exercise.getMet() )
      );
      return true;
   }

   @Override
   public List<Exercise> getAll() {
      return jdbi.withHandle(handle -> handle.createQuery("SELECT id, name, met FROM exercise")
              .mapToBean(Exercise.class)
              .list() );
   }

   @Override
   public Exercise getById(Long id) {
      return jdbi.withHandle(handle -> handle.createQuery("SELECT id, name, met FROM exercise WHERE id = :id")
              .bind("id", id)
              .mapToBean(Exercise.class)
              .findOnly() );
   }

   @Override
   public boolean update(Long id, Exercise exercise) {
      String sql = "UPDATE exercise SET name=?, met=? WHERE id=?";

      jdbi.useTransaction(handle -> handle.createUpdate(sql)
              .bind(0, exercise.getName())
              .bind(1, exercise.getMet())
              .bind(2, exercise.getId())
              .execute() );
      System.out.println("UPDATE SUCCESS...");

      return true;
   }

   @Override
   public boolean addPlayerExercise(Long playerId, Long exerciseId) {
      jdbi.useHandle( handle -> handle.execute("INSERT INTO player_exercise(player_id, exercise_id) VALUES(?, ?)",
              playerId, exerciseId) );
      return true;
   }

   @Override
   public boolean delete(Long playerId) {
      return false;
   }

   @Override
   public Long getIdByName(String theName) {
      return jdbi.withHandle(handle -> handle.createQuery("SELECT id FROM exercise WHERE name = ?")
              .bind(0, theName)
              .mapTo(Long.class)
              .findOnly() );
   }

   @Override
   public List<Intensity> getAllIntensity() {
      return jdbi.withHandle(handle -> handle.createQuery("SELECT id, name FROM intensity")
              .mapToBean(Intensity.class)
              .list() );
   }

   @Override
   public boolean addProgress(Progress progress) {
      jdbi.useHandle(handle -> handle.execute("INSERT INTO progress(time_completed, calories_lost, intensity_id, player_id, exercise_id ) VALUES(?,?,?,?,?)",
              progress.getTimeCompleted(),
              progress.getCaloriesLost(),
              progress.getIntensityId(),
              progress.getPlayerId(),
              progress.getExerciseId())
      );
      return true;
   }


   @Override
   public Long getIntensityByName(String theName) {
      return jdbi.withHandle(handle -> handle.createQuery("SELECT id FROM intensity WHERE name = ?")
              .bind(0, theName)
              .mapTo(Long.class)
              .findOnly() );
   }
}
