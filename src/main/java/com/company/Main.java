package com.company;

import java.io.*;
import java.util.*;
import java.sql.*;

import static com.company.City.loadingCitesFromDB;
import static com.company.City.loadingCitiesToDB;
import static com.company.Traveller.loadingTravellersFromFiles;
import static com.company.Traveller.writeTravellerToFile;
import static com.company.VisitorsOfCity.getFromFile;
import static com.company.VisitorsOfCity.saveToFile;

public class Main {

    public static Connection dbConnection(){
        Connection db_con_obj = null;

        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");

            db_con_obj = DriverManager.getConnection("jdbc:oracle:thin:@oracle12c.hua.gr:1521:orcl","it21840","it21840");
            if (db_con_obj != null) {
                System.out.println("Connection Successful");
            } else {
                System.out.println("Failed to make connection!");
            }
        }catch (Exception e){
            System.out.println("Connection with database failed");
        }

        return db_con_obj;
    }
    public static void ticketWinner() {
        ArrayList<Traveller> a=new ArrayList<Traveller>();
        a.add(new Tourist("Merry",21,4,10,15,6));
        a.add(new Tourist("Larry",40,15,4,15,3));
        a.add(new Traveller("Bob",30,2,20,5,20));
        a.add(new Traveller("Adam",25,0,15,5,10));
        try {
            a.add(new Business("Martin",28,"Amsterdam",0,7,10,20));
            a.add(new Business("Nikos",28,"Athens",0,20,5,29));
        } catch (Exception e) {
            e.printStackTrace();
        }


        City ticket = null;
        try {
            ticket = new City("New York");
        } catch (Exception e) {
        }
        Integer[] similarity = new Integer[5];

        similarity[0] = a.get(0).Similarity(ticket);
        similarity[1] = a.get(1).Similarity(ticket);
        similarity[2] = a.get(2).Similarity(ticket);
        similarity[3] = a.get(3).Similarity(ticket);
        similarity[4] = a.get(4).Similarity(ticket);

        int max = Collections.max(Arrays.asList(similarity));
        System.out.println( a.get(Arrays.asList(similarity).indexOf(max)).getName() +" Won a free ticket");
    }



    public static void main(String[] args) {


       final Gui ui = new Gui();

// #############  Ερωτημα #3  ################
     //   ticketWinner();


// ############## Load objects from file ###############
        LinkedList<Traveller> traveller= new LinkedList<Traveller>();
        traveller.addAll(loadingTravellersFromFiles());



// ############## add content from database #############
        Connection db_con_obj = dbConnection();
        HashMap<String,City> cityHash = new HashMap<String,City>();
        try {
            cityHash.putAll(loadingCitesFromDB(db_con_obj));
        } catch (SQLException | NullPointerException throwable) {
           System.out.println("there will not be preloaded cities");
        }

        LinkedList<VisitorsOfCity> vOC = new LinkedList<>(getFromFile());

        PrintStream original = System.out;
        ui.mainGUI();
        while (!ui.isClosed()) {


           System.setOut(new PrintStream(new OutputStream() {
               public void write(int b) {
                   //DO NOTHING
               }
           }));

           boolean cityExists = true;
           while (!ui.isClosed()) {


               System.out.println(ui.isPressed());
               if (ui.isPressed()) {
                   System.setOut(original);


                   ArrayList<City> inputCities = new ArrayList<City>();
                   String io = ui.getIo();

                   String[] cities = io.split(","); //χωριζω την ισωδο με βαση το ','

                   try {
                       for (String s : cities) {
                           String[] fWord = s.split(" ", 2);        //Tα αρχικα τις καθε χωρας δεν μου χριαζονται πουθενα
                           //οποτε χωριζω καθενα απο τα s στα δυο και αγνοω το πρωτο
                           String city = fWord[1].trim();
                           if (!cityHash.containsKey(city)) {
                               City given = new City(city);
                               given.join();
                               cityHash.put(city, given);
                           }
                           inputCities.add(cityHash.get(city));
                       }

                   } catch (CityNotFoundException| InterruptedException e) {
                       ui.resultNotification("City doesn't exist ");
                       cityExists = false;
                   }

                   if(cityExists) {
                       Traveller user = null;
                       if (ui.getChoice().equalsIgnoreCase("a")) {
                           user = new Tourist(ui.getName(), ui.getAge(), ui.getMuseums(), ui.getCafes(), ui.getRestaurants(), ui.getBars());
                           user.setVisit(user.CompareCities(inputCities).toString());
                           ui.resultNotification(user.getVisit() + " from your input fits best");
                       } else if (ui.getChoice().equalsIgnoreCase("b")) {
                           user = new Traveller(ui.getName(), ui.getAge(), ui.getMuseums(), ui.getCafes(), ui.getRestaurants(), ui.getBars());
                           user.setVisit(user.CompareCities(inputCities).toString());
                           ui.resultNotification(user.getVisit() + " from your input fits best");
                       } else if (ui.getChoice().equalsIgnoreCase("c")) {
                           try {
                               user = new Business(ui.getName(), ui.getAge(), ui.getCurrCity(), ui.getMuseums(), ui.getCafes(), ui.getRestaurants(), ui.getBars());
                           } catch (CityNotFoundException e) {
                               ui.resultNotification("current city doesn't exist ");
                               ui.setPressed(false);;
                               break;
                           }
                           user.setVisit(user.CompareCities(inputCities).toString());
                               ui.resultNotification(user.getVisit() + " fits best based on other users input");


                       }
                       try {
                           boolean found = false;
                           for (int j = 0; j < vOC.size();j++) {
                               VisitorsOfCity userVOC = new VisitorsOfCity(user.getVisit(), user.getName());
                                 if(vOC.get(j).getCityName().equals(userVOC.getCityName())) {
                                   System.out.println(vOC.get(j).getCityName());

                                   vOC.get(j).getTravellerNames().add(user.getName());
                                   vOC.get(j).increaseCount();
                                   System.out.println(vOC.get(j).getTravellerCount());
                                   found = true;
                                   break;
                               }
                           }

                           if(!found){
                               vOC.add(new VisitorsOfCity(user.getVisit(), user.getName()));
                           }

                           if(vOC.size() == 0){
                               vOC.add(new VisitorsOfCity(user.getVisit(), user.getName()));
                           }
                       } catch (CityNotFoundException e) {
                           e.printStackTrace();
                       }
                       traveller.add(user);
                   }
                   ui.setPressed(false);
                   break;
               }

               if(ui.isPressed2()){
                   System.setOut(original);
                    Traveller user  = new Traveller(ui.getName(), ui.getAge(), ui.getMuseums(), ui.getCafes(), ui.getRestaurants(), ui.getBars());


                   ui.resultNotification(user.CollaborativeFiltering(traveller) + " from your input fits best");
                   ui.setPressed2(false);
                   break;
               }


           }


       }

        System.setOut(original);


// ###########  εγγραφη των traveller και τον VisitorsOfCity στο αρχειο traveller.dat και στο VisitorsOfCity.dat
        saveToFile(vOC);
        writeTravellerToFile(traveller);

/*
// #############  κανω τους travellers μοναδικους  ####################
      Set<Traveller> set = new HashSet<Traveller>(traveller);
      traveller.clear();
      traveller.addAll(set);

//#########  εμαφανιση των traveller ταξινομημενους χωρις διπλοτυπα  ##########
      ListIterator itr2 = traveller.listIterator();
      Collections.sort(traveller);
      while(itr2.hasNext()){
          Object element = itr2.next();
          System.out.println(element + " ");
      }

*/
// ########  αποθηκευση των city στην βαση δεδομενων  ###############
      loadingCitiesToDB(cityHash,db_con_obj);

    }
}