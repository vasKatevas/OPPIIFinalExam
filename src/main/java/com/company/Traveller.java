package com.company;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Traveller implements java.io.Serializable, Comparable<Traveller> {
    private   String name;
    private   int    age;
    protected double lat;
    protected double lon;
    private   String visit;
    static    int    counter;
    private   int    museums, cafes, restaurants, bars;
    private int[] criteria = new int[4];


    public Traveller(String name, int age, int museums, int cafes, int restaurants, int bars) {
        this.name        = name;
        this.age         = age;
        this.museums     = museums;
        this.cafes       = cafes;
        this.restaurants = restaurants;
        this.bars        = bars;
        this.criteria[0] = museums;
        this.criteria[1] = cafes;
        this.criteria[2] = restaurants;
        this.criteria[3] = bars;

        counter++;
    }

    public Traveller() {
        counter++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVisit() {
        return visit;
    }

    public int[] getCriteria() {
        return criteria;
    }

    public void setCriteria(int[] criteria) {
        this.criteria = criteria;
    }

    public void setVisit(String visit) {
        this.visit = visit;
    }

    public int getCafes() {
        return cafes;
    }

    public void setCafes(int cafes) {
        this.cafes = cafes;
    }

    public int getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(int restaurants) {
        this.restaurants = restaurants;
    }

    public int getBars() {
        return bars;
    }

    public void setBars(int bars) {
        this.bars = bars;
    }

    public int getAge() {
        return age;
    }

    public void setMuseums(int museums) {
        this.museums = museums;
    }

    public int getMuseums() {
        return museums;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        Traveller.counter = counter;
    }


    public static LinkedList loadingTravellersFromFiles(){

        LinkedList<Traveller> traveller= new LinkedList<Traveller>();
        ObjectInputStream dpg7001;
        try{
            dpg7001=new ObjectInputStream(new FileInputStream("traveler.dat"));
            Scanner input=new Scanner("traveler.dat");
            while(input.hasNextLine()){
                traveller.add((Traveller) dpg7001.readObject());
            }
        }catch(Exception l){
        }


        return traveller;
    }

public static void writeTravellerToFile(LinkedList<Traveller> traveller){
    ListIterator itr = traveller.listIterator();
    try{
        FileOutputStream out=new FileOutputStream(new File("traveler.dat"));
        ObjectOutputStream dpg7000=new ObjectOutputStream(out);
        while(itr.hasNext()){
            Object element = itr.next();
            dpg7000.writeObject(element);
        }
        dpg7000.close();
    }catch(Exception c){
        System.out.println("error handling file");
    }
}


    public int Similarity(City a) {
        int similarity = 0;
        if (a.getCafes() > 0 && cafes > 0) {
            similarity += 1;
        }
        if (a.getRestaurants() > 0 && restaurants > 0) {
            similarity += 1;
        }
        if (a.getBars() > 0 && bars > 0) {
            similarity += 1;
        }
        if (a.getMuseums() > 0 && museums > 0) {
            similarity += 1;
        }

        return similarity;
    }

    public City CompareCities(ArrayList<City> a) {
        ArrayList<Integer> b = new ArrayList<Integer>();
        int j = 0;
        for (City i : a) {
            b.add(Similarity(i));
            j++;
        }
        int max = 0;
        for (int i = 0; i < j; i++) {
            if (b.get(i) > max) {
                max = b.get(i);
            }
        }
        return a.get(b.indexOf(max));
    }

    public void CompareCities(boolean weather) {
        if (weather) {
            System.out.println("No cities when the weather is rain!");
        } else {
            System.out.println("All cities accepted no matter the weather!");
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        final Traveller trvlr = (Traveller)obj;
        return this.name.equals(trvlr.name);
    }

    @Override
    public int hashCode(){
    int hash = 3;
    hash = 53 * (this.age + this.name.hashCode());
    return hash;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }




    public int compareTo(Traveller o) {
        return this.age - o.age;
    }

    public String CollaborativeFiltering(LinkedList<Traveller> traveller){
        List<Traveller> filtered = traveller.stream().filter(distinctByKey(p -> p.getVisit())).collect(Collectors.toList());   //χωρης την filter αν υπαρχουν ιδια Visit κανει exception
        Map <String,Integer> cityToRank = filtered.stream().collect(Collectors.toMap(i->i.getVisit(), i->innerDot(i.getCriteria(),this.criteria)));


        Optional<RecommendedCity> recommendedCity=
                traveller.stream().map(i-> new RecommendedCity(i.getVisit(),innerDot(i.getCriteria(),this.criteria))).max(Comparator.comparingInt(RecommendedCity::getRank));


        return recommendedCity.get().getCity();
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)  //Το βρηκα απο το internet εχασα το link μπωρουσα και με αλλο τροπο αλλα
                                                                                            // προτίμησα να χρησιμοποιήσω streams
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private static int innerDot(int[] currentTraveller, int[] candidateTraveller)     {
        int sum=0;
        for (int i=0; i<currentTraveller.length;i++)
             sum+=currentTraveller[i]*candidateTraveller[i];
        return sum;

    }
}


