package com.company;

public class Tourist extends Traveller{
    public Tourist(String name,int age,int museums,int cafes, int restaurants, int bars){
 	   super(name,age,museums,cafes,restaurants,bars);
    }
    public Tourist(){
    	super();
    }


    @Override
    public int Similarity(City a){

        int similarity = 0,m = 0,c = 0,r = 0,b = 0;
        int words = a.getWorldCount();

        if (a.getCafes() > 0 && getCafes() > 0){
            similarity += 1;
        }
        if (a.getRestaurants() > 0 && getRestaurants() > 0){
            similarity += 1;
        }
        if (a.getBars() > 0 && getBars() > 0){
            similarity += 1;
        }
        if (a.getMuseums() > 0 && getMuseums() > 0){
            similarity += 1;
        }
        if (a.getMuseums() > 0 && words >0){
            m = a.getMuseums()/words;
        }
        if (a.getCafes() > 0 && words > 0){
            c = a.getCafes()/words;
        }
        if (a.getRestaurants() > 0 && words > 0){
            r = a.getRestaurants()/words;
        }
        if (a.getBars() > 0 && words > 0){
            b = a.getBars()/words;
        }
        similarity = similarity*b + similarity*c + similarity*m + similarity*r;
        return  similarity;
    }

    private static int countTotalWords(String str) {
        String[] s =str.split(" ");
        return 	s.length;
    }
}