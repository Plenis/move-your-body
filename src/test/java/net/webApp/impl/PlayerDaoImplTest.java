//package net.webApp.impl;
//
//
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
////class PlayerDaoImplTest {
//
//   private Jdbi jdbi;
////   private PlayerDaoImpl playerDao;
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
//         playerDao = new PlayerDaoImpl(jdbi);
//      } catch (URISyntaxException e) {
//         e.printStackTrace();
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//   }
//
//   @Test
//   @DisplayName("Should be able to add new player")
//   public void addNewPlayer() {
//      Player player =  new Player(100L, "Thando", 2.4, 3.3, 21);
//      playerDao.add(player);
//      assertEquals(1, playerDao.getAll());
//   }
//
//   @Test
//   @DisplayName("Should be able to return all players")
//   public void getAllPlayers() {
//      assertEquals(1, playerDao.getAll().size());
//   }
//
//   @Test
//   @DisplayName("Should be able to return player by id")
//   public void getById() {
//      Player player = playerDao.getById(1L);
//      assertEquals("Thando", player.getName());
//   }
//
//   @Test
//   @DisplayName("Should be able to update player record")
//   public void updatePlayer() {
//      Player player =  new Player(1L, "New Player", 2, 3, 21);
//      playerDao.update(1L, player);
//      Player player2 = playerDao.getById(1L);
//
//      assertEquals("New Player", player2.getName());
//   }
//
//   @Test
//   @DisplayName("Should be able to all player exercise")
//   public void getPlayerExercises() {
//      assertEquals(1, playerDao.getPlayerExercises(1L).size());
//   }
//
//
//}