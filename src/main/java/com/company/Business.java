package com.company;

import java.io.IOException;

public class Business extends Traveller{
	double lat;
	double lon;

    public Business(String name,int age,String city,int museums,int cafes,int restaurants, int bars) throws CityNotFoundException{
  	    super(name,age,museums,cafes,restaurants,bars);

  	    City curCity = new City(city);
		this.lat = curCity.getLat();
		this.lon = curCity.getLon();
	}
    public Business(){
    	super();
    }

	public double getLat() {
		return lat;
	}

	public void setLat(String city) throws CityNotFoundException {
    	City curCity =new City(city);
		this.lat = curCity.getLat();
	}

	public double getLon() {
		return lon;
	}

	public void setLon(String city) throws CityNotFoundException{
    	City curCity = new City(city);
		this.lon = curCity.getLon();
	}

	@Override
    public int Similarity(City a){
    	double b=distance(lat,lon,a.getLat(),a.getLon());
    	return (int)(b/20000*100);
    }
    public static double distance(double lat1,double lon1,double lat2,double lon2){
		if((lat1==lat2)&&(lon1==lon2)){
			return 0;
		}else{
			double theta=lon1-lon2;
            double dist=Math.sin(Math.toRadians(lat1))*Math.sin(Math.toRadians(lat2))+Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*Math.cos(Math.toRadians(theta));
			dist=Math.acos(dist);
			dist=Math.toDegrees(dist);
			dist=dist*60*1.609344;
			dist=dist*0.8684;
			return(dist);
		}


	}
}