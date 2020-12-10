package net.webApp.dao;

import net.webApp.model.Player;
import net.webApp.model.Progress;

import java.util.List;

public interface ProgressDao {
   boolean add(Progress progress);
   List<Progress> getAll();

   public List<Player> getPlayerProgress(Long playerId);
}
