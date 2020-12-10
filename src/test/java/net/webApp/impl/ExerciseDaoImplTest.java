<<<<<<< HEAD
package net.webApp.impl;

import net.webApp.model.Exercise;
import net.webApp.model.Player;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseDaoImplTest {
   private Jdbi jdbi;
   private ExerciseDaoImpl exerciseDao;

   public Jdbi getJdbiDatabaseConnection(String defaultJdbcUrl) throws URISyntaxException, SQLException {
      ProcessBuilder processBuilder = new ProcessBuilder();
      String database_url = processBuilder.environment().get("DATABASE_URL");
      if (database_url != null) {
         URI uri = new URI(database_url);
         String[] hostParts = uri.getUserInfo().split(":");
         String username = hostParts[0];
         String password = hostParts[1];
         String host = uri.getHost();
         int port = uri.getPort();
         String path = uri.getPath();
         String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);
         return Jdbi.create(url, username, password);
      }
      return Jdbi.create(defaultJdbcUrl);
   }

   @BeforeEach
   public void beforeEach() {
      try {
         jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/exercise_db?user=thando&password=thando123");
         exerciseDao = new ExerciseDaoImpl(jdbi);
      } catch (URISyntaxException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   @Test
   @DisplayName("Should be able to add new exercise")
   public void addNew() {
      Exercise exercise =  new Exercise(100L, "Jog", 200);
      exerciseDao.add(exercise);
      assertEquals(1, exerciseDao.getAll().size());
   }

   @Test
   @DisplayName("Should be able to return all exercises")
   public void getAll() {
      assertEquals(1, exerciseDao.getAll().size());
   }


   @Test
   @DisplayName("Should be able to return exercise by id")
   public void getById() {
      Exercise exercise = exerciseDao.getById(1L);
      assertEquals("Jog", exercise.getName());
   }

   @Test
   @DisplayName("Should be able to update exercise record")
   public void update() {
      Exercise exercise =  new Exercise(1L, "Walk", 200);
      exerciseDao.update(1L, exercise);
      Exercise exercise1 = exerciseDao.getById(1L);
      assertEquals("New Exercise", exercise1.getName());
   }


   @Test
   @DisplayName("Should be able to add player exercise")
   public void addPlayerExercise() {
      exerciseDao.addPlayerExercise(1L, 1L);
   }

   @Test
   @DisplayName("Should be able to return all intensity levels")
   public void getIntensityLevels() {
      assertEquals(3, exerciseDao.getAllIntensity().size());
   }

   @Test
   @DisplayName("Should be able to return exercise by id")
   public void getExerciseId() {
      assertEquals(7, exerciseDao.getIdByName("Boxing"));
   }

   @Test
   @DisplayName("Should be able to return exercise by id")
   public void getIntensityId() {
      assertEquals("High", exerciseDao.getIntensityByName("Boxing"));
   }

}
=======
//package net.webApp.impl;
//
//import net.webApp.model.Exercise;
//import net.webApp.model.Player;
//import org.jdbi.v3.core.Jdbi;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ExerciseDaoImplTest {
//   private Jdbi jdbi;
//   private ExerciseDaoImpl exerciseDao;
//
//   public Jdbi getJdbiDatabaseConnection(String defaultJdbcUrl) throws URISyntaxException, SQLException {
//      ProcessBuilder processBuilder = new ProcessBuilder();
//      String database_url = processBuilder.environment().get("DATABASE_URL");
//      if (database_url != null) {
//         URI uri = new URI(database_url);
//         String[] hostParts = uri.getUserInfo().split(":");
//         String username = hostParts[0];
//         String password = hostParts[1];
//         String host = uri.getHost();
//         int port = uri.getPort();
//         String path = uri.getPath();
//         String url = String.format("jdbc:postgresql://%s:%s%s", host, port, path);
//         return Jdbi.create(url, username, password);
//      }
//      return Jdbi.create(defaultJdbcUrl);
//   }
//
//   @BeforeEach
//   public void beforeEach() {
//      try {
//         jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/exercise_db?user=thando&password=thando123");
//         exerciseDao = new ExerciseDaoImpl(jdbi);
//      } catch (URISyntaxException e) {
//         e.printStackTrace();
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//   }
//
//   @Test
//   @DisplayName("Should be able to add new exercise")
//   public void addNew() {
//      Exercise exercise =  new Exercise(100L, "Jog", 200);
//      exerciseDao.add(exercise);
//      assertEquals(1, exerciseDao.getAll().size());
//   }
//
//   @Test
//   @DisplayName("Should be able to return all exercises")
//   public void getAll() {
//      assertEquals(1, exerciseDao.getAll().size());
//   }
//
//
//   @Test
//   @DisplayName("Should be able to return exercise by id")
//   public void getById() {
//      Exercise exercise = exerciseDao.getById(1L);
//      assertEquals("Jog", exercise.getName());
//   }
//
//   @Test
//   @DisplayName("Should be able to update exercise record")
//   public void update() {
//      Exercise exercise =  new Exercise(1L, "Walk", 200);
//      exerciseDao.update(1L, exercise);
//      Exercise exercise1 = exerciseDao.getById(1L);
//      assertEquals("New Exercise", exercise1.getName());
//   }
//
//
//   @Test
//   @DisplayName("Should be able to add player exercise")
//   public void addPlayerExercise() {
//      exerciseDao.addPlayerExercise(1L, 1L);
//   }
//
//}
>>>>>>> ababf854046687a0df700e95a4cfbcd52899d7d6
