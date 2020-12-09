package net.webApp.dao;


import net.webApp.model.Player;

import java.util.List;

public interface PlayerDao {
   boolean add(Player player);
   List<Player> getAll();
   Player getById(Long id);
   boolean delete(Long playerId);
   public boolean update(Long playerId, Player player);

   public List<Player> getPlayerExercises(Long playerId);
}
