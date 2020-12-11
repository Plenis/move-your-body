package net.webApp.dao;


import net.webApp.model.Player;

import java.util.List;

public interface PlayerDao {
   boolean add(Player player);
   List<Player> getAll();
   Player getById(Long id);
   boolean delete(Long playerId);
   boolean update(Long playerId, Player player);

   List<Player> getPlayerExercises(Long playerId);
   Long getIdByName(String name);
   List<Player> getProgress(Long playerId);

}
