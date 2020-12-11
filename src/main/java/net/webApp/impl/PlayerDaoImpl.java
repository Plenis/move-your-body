package net.webApp.impl;

import net.webApp.dao.PlayerDao;
import net.webApp.model.*;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PlayerDaoImpl implements PlayerDao {
   private Jdbi jdbi;
   private List<Player> list = new ArrayList<>();

   public PlayerDaoImpl(){}

   public PlayerDaoImpl(Jdbi jdbi){
      this.jdbi = jdbi;
   }

   @Override
   public boolean add(Player player) {
      jdbi.useHandle(handle -> handle.execute("INSERT INTO player(name, weight, height, age) VALUES(?,?,?,?)",
              player.getName(),
              player.getWeight(),
              player.getHeight(),
              player.getAge())
      );
      return true;
   }

   @Override
   public List<Player> getAll() {
      return jdbi.withHandle(handle -> handle.createQuery("SELECT id, name, weight, height, age FROM player")
         .mapToBean(Player.class)
         .list() );
   }

   @Override
   public Player getById(Long id) {
      return jdbi.withHandle(handle -> handle.createQuery("SELECT id, name, weight, height, age FROM player WHERE id = :id")
              .bind("id", id)
              .mapToBean(Player.class)
              .findOnly() );
   }

   @Override
   public boolean update(Long id, Player player) {
      String sql = "UPDATE player SET name=?, weight=?, height=?, age=? WHERE id=?";

      jdbi.useTransaction(handle -> handle.createUpdate(sql)
              .bind(0, player.getName())
              .bind(1, player.getWeight())
              .bind(2, player.getHeight())
              .bind(3, player.getAge())
              .bind(4, player.getId())
              .execute() );
      System.out.println("UPDATE SUCCESS...");

      return true;
   }


   @Override
   public boolean delete(Long playerId) {
      return false;
   }


   @Override
   public List<Player> getPlayerExercises(Long playerId) {
      String sql = "SELECT p.id p_id, p.name p_name, p.weight p_weight, p.height p_height, p.age p_age, " +
              "ex.id ex_id, ex.name ex_name, ex.met ex_met, " +
              "pex.id pex_id, pex.player_id pex_player_id, pex.exercise_id pex_exercise_id " +
              "FROM player p " +
              "INNER JOIN player_exercise pex " +
              "ON p.id = pex.player_id " +
              "INNER JOIN exercise ex " +
              "ON pex.exercise_id = ex.id " +
              "WHERE p.id = " + playerId;

      return jdbi.withHandle( handle -> {
         list = handle.createQuery(sql)
                 .registerRowMapper(BeanMapper.factory(PlayerExercise.class, "pex"))
                 .registerRowMapper(BeanMapper.factory(Exercise.class, "ex"))
                 .registerRowMapper(BeanMapper.factory(Player.class, "p"))
                 .reduceRows(new LinkedHashMap<Long, Player>(), (map, rowView) -> {
                    Player player = map.computeIfAbsent(
                            rowView.getColumn("p_id", Long.class),
                            id -> rowView.getRow(Player.class)
                    );

                    if (rowView.getColumn("pex_id", Long.class) != null)
                       player.addPlayerExercise(rowView.getRow(PlayerExercise.class));

                    if (rowView.getColumn("ex_id", Long.class) != null)
                       player.addExercise(rowView.getRow(Exercise.class));

                    return map;

                 })
                 .values()
                 .stream()
                 .collect(toList());

         return list;
      });
   }



   //FIRST THING IN THE MORNING
   @Override
   public List<Player> getProgress(Long playerId) {
      String sql = "SELECT p.id p_id, p.name p_name, p.weight p_weight, p.height p_height, p.age p_age, " +
              "ex.id ex_id, ex.name ex_name, ex.met ex_met, " +
              "i.id i_id, i.name i_name, " +
              "pro.id pro_id, pro.time_completed pro_time_completed, pro.calories_lost pro_calories_lost, pro.intensity_id pro_intensity_id, pro.player_id pro_player_id, pro.exercise_id pro_exercise_id " +
              "FROM player p " +
              "INNER JOIN progress pro " +
              "ON p.id = pro.player_id " +
              "INNER JOIN exercise ex " +
              "ON pro.exercise_id = ex.id " +
              "INNER JOIN intensity i " +
              "ON pro.intensity_id = i.id " +
              "WHERE p.id = " + playerId;
      System.out.println("DOES NOT REACH THIS LINE");
      return jdbi.withHandle( handle -> {
         list = handle.createQuery(sql)
                 .registerRowMapper(BeanMapper.factory(Progress.class, "pro"))
                 .registerRowMapper(BeanMapper.factory(Exercise.class, "ex"))
                 .registerRowMapper(BeanMapper.factory(Intensity.class, "i"))
                 .registerRowMapper(BeanMapper.factory(Player.class, "p"))
                 .reduceRows(new LinkedHashMap<Long, Player>(), (map, rowView) -> {
                    Player player = map.computeIfAbsent(
                            rowView.getColumn("p_id", Long.class),
                            id -> rowView.getRow(Player.class)
                    );

                    if (rowView.getColumn("pro_id", Long.class) != null)
                       player.addProgress(rowView.getRow(Progress.class));

                    if (rowView.getColumn("ex_id", Long.class) != null)
                       player.addExercise(rowView.getRow(Exercise.class));

                    if (rowView.getColumn("i_id", Long.class) != null)
                       player.addIntensity(rowView.getRow(Intensity.class));

                    return map;

                 })
                 .values()
                 .stream()
                 .collect(toList());

         return list;
      });
   }


   @Override
   public Long getIdByName(String name) {
      return jdbi.withHandle(handle -> handle.createQuery("SELECT id FROM player WHERE name = ?")
              .bind(0, name)
              .mapTo(Long.class)
              .findOnly() );
   }
}
