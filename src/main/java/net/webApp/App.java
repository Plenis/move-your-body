package net.webApp;
import net.webApp.impl.ExerciseDaoImpl;
import net.webApp.impl.PlayerDaoImpl;
import net.webApp.model.Exercise;
import net.webApp.model.Intensity;
import net.webApp.model.Player;
import net.webApp.model.Progress;
import org.jdbi.v3.core.Jdbi;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URI;
import java.net.URISyntaxException;
//import java.sql.SQLException;
import java.util.*;
//import java.util.List;

import static spark.Spark.*;

public class App {
   private static Jdbi jdbi;

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4566;
    }

    static Jdbi getJdbiDatabaseConnection(String defaultJdbcUrl) throws URISyntaxException {
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

    public static void main(String[] args) throws URISyntaxException {


       //try {


            port(getHerokuAssignedPort());
       staticFiles.location("/public");

       jdbi = getJdbiDatabaseConnection("jdbc:postgresql://localhost/exercise_db?username=thando&password=thando123");

            ExerciseDaoImpl exerciseDao = new ExerciseDaoImpl(jdbi);
            PlayerDaoImpl playerDao = new PlayerDaoImpl(jdbi);
            Map<String, Object> player = new HashMap<>();

        get("/", (request, response) -> {

            return new ModelAndView(player, "index.handlebars");

        }, new HandlebarsTemplateEngine());

        get("/timer", (request, response) -> {
            return new ModelAndView(player, "timer.handlebars");
        }, new HandlebarsTemplateEngine());

        get("/move", (request, response) -> {
            return new ModelAndView(player, "move.handlebars");
        }, new HandlebarsTemplateEngine());

        get("/progress", (request, response) -> {
            return new ModelAndView(player, "progress.handlebars");
        }, new HandlebarsTemplateEngine());


        get("/player/:id", (request, response) -> {
          Map<String, Object> map = new HashMap<>();

          List<Intensity> intensityLevels = exerciseDao.getAllIntensity();
          System.out.println("NUMBER OF LEVELS: " + intensityLevels.size());
          map.put("intensityLevels", intensityLevels);
          return new ModelAndView(map, "tester.handlebars");

       }, new HandlebarsTemplateEngine());

       post("/player/:id", (request, response) -> {
          String labelVal = request.queryParams("labelVal");
          int time = Integer.parseInt(request.queryParams("timer"));
          String intensity = request.queryParams("intensity");

          //System.out.println();
          Long playerId = Long.parseLong(request.params("id"));
          Long exerId = exerciseDao.getIdByName(labelVal.trim());
          Long intensityId = exerciseDao.getIntensityByName(intensity);

          Player player1 = playerDao.getById(playerId);
          Exercise exercise = exerciseDao.getById(exerId);

          double weight = player1.getWeight();
          double calories_lost = weight * exercise.getMet();

          System.out.println("calories_lost: " + calories_lost);

          Progress progress = new Progress();
          progress.setTimeCompleted(time);
          progress.setCaloriesLost(3);
          progress.setIntensityId(intensityId);
          progress.setPlayerId(playerId);
          progress.setExerciseId(exerId);

          System.out.println("POSTING SOME DATA");
          System.out.println(progress.getTimeCompleted());
          System.out.println(progress.getCaloriesLost());
          System.out.println(progress.getIntensityId());
          System.out.println(progress.getPlayerId());
          System.out.println(progress.getExerciseId());

          exerciseDao.addProgress(progress);


          return new ModelAndView(player, "tester.handlebars");

       }, new HandlebarsTemplateEngine());

       get("/", (request, response) -> {
          Map<String, Object> map = new HashMap<>();
          return new ModelAndView(map, "index.handlebars");
       }, new HandlebarsTemplateEngine());

            get("/motion", (request, response) -> {
                return new ModelAndView(player, "motion.handlebars");
            }, new HandlebarsTemplateEngine());

    }
}
