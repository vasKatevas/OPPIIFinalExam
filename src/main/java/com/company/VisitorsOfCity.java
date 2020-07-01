package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class VisitorsOfCity extends City implements java.io.Serializable{

    private int travellerCount =0;
    private LinkedList<String> travellerNames = new LinkedList<String>();

    public VisitorsOfCity(int museums, int cafes, int restaurants, int bars, double lat, double lon, String weather, String mediaWiki,int travellerCount, LinkedList<String> travellerNames ){
        super(museums, cafes, restaurants, bars, lat, lon, weather, mediaWiki);
        this.travellerNames = travellerNames;
        this.travellerCount = travellerCount;
    }

    public VisitorsOfCity(String city,String travellerName) throws CityNotFoundException {
        super(city);
        travellerNames.add(travellerName);

    }

    public void increaseCount(){
        travellerCount++;
    }


    public static void saveToFile(LinkedList<VisitorsOfCity> vOC){

        Iterator<VisitorsOfCity> itr = vOC.iterator();
        try {
            new FileWriter("VisitorsOfCity.dat", false).close();
            ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("VisitorsOfCity.dat"));
            while (itr.hasNext()){
                Object element = itr.next();
                objOut.writeObject(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<VisitorsOfCity> getFromFile(){
        LinkedList<VisitorsOfCity> inVOC = new LinkedList<VisitorsOfCity>();
        try {
            ObjectInputStream inObj = new ObjectInputStream(new FileInputStream("VisitorsOfCity.dat"));
            boolean cont = true;
            while(cont){
                Object obj = null;
                    obj = inObj.readObject();

                if(obj != null)
                    inVOC.add((VisitorsOfCity) obj);
                else
                    cont = false;

            }

        } catch (IOException | ClassNotFoundException e) {
        }

        return inVOC;
    }

    public int getTravellerCount() {
        return travellerCount;

    }

    public void setTravellerCount(int travellerCount) {
        this.travellerCount = travellerCount;
    }

    public LinkedList<String> getTravellerNames() {
        return travellerNames;
    }

    public void setTravellerNames(LinkedList<String> travellerNames) {
        this.travellerNames = travellerNames;
    }

    @Override
    public int hashCode(){
        int hash = 3;
        hash = 53 * (getCityName().hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o == this))
            return true;
        return this.getCityName().equals(((VisitorsOfCity) o).getCityName());
    }
}
