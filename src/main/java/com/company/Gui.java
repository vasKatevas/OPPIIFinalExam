package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class Gui extends JFrame{
    private JTextField tName,tAge,tCities,tMuseums,tCafes,tRestaurants,tBars;
    private JButton b;
    private DefaultListModel<String> category= new DefaultListModel<>();
    private JList<String> tCategory=new JList<>();
    private DefaultListModel<String> tt10= new DefaultListModel<>();
    private JList <String> tWeather=new JList<>();



    private JTextField ctName,ctAge,ctCities,ctMuseums,ctCafes,ctRestaurants,ctBars;
    private JButton cb;
    private DefaultListModel<String> ccategory= new DefaultListModel<>();
    private JList<String> ctCategory=new JList<>();
    private DefaultListModel<String> ctt10= new DefaultListModel<>();
    private JList <String> ctWeather=new JList<>();

    public boolean pressed = false;
    public boolean pressed2 = false;


    private JFrame frame ;
    private String choice;
    private String name;
    private int age;
    private String io;
    private int museums;
    private int cafes;
    private int restaurants;
    private int bars;
    private String weather;
    private boolean closed = false;
    private String CurrCity;


    public boolean isClosed() {
        return closed;
    }

    public String getChoice() {
        return choice;
    }

    public String getName() {
        return name;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public boolean isPressed() {
        return pressed;
    }



    public void setPressed2(boolean pressed) {
        this.pressed2 = pressed;
    }

    public boolean isPressed2() {
        return pressed2;
    }

    public String getCurrCity() {
        return CurrCity;
    }

    public void setCurrCity(String currCity) {
        CurrCity = currCity;
    }

    public int getAge() {
        return age;
    }

    public String getIo() {
        return io;
    }

    public int getMuseums() {
        return museums;
    }

    public int getCafes() {
        return cafes;
    }

    public int getRestaurants() {
        return restaurants;
    }

    public int getBars() {
        return bars;
    }

    public String getWeather() {
        return weather;
    }

    public Gui(){}



    JPanel mainPanel;
    JPanel collaboPanel;

    public void mainGUI(){


        frame = new JFrame("Java SWING Examples");
        frame.setSize(900,800);
        frame.setLayout(null);


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                closed = true;
                frame.dispose();
            }
        });



        ioInit();

       frame.setVisible(true);




    }




    private final JLabel resultNotification =new JLabel("",SwingConstants.CENTER);




    public void ioInit(){



        tt10= new DefaultListModel<>();
        tt10.addElement("true");
        tt10.addElement("false");
        tWeather=new JList<>(tt10);

        tCategory.setBounds(100,100, 75,75);

        tName=new JTextField(10);

        tAge=new JTextField(2);

        tCities=new JTextField(30);
        tMuseums=new JTextField(2);

        tCafes=new JTextField(2);

        tRestaurants=new JTextField(2);

        tBars=new JTextField(2);

        DefaultListModel<Boolean> rain= new DefaultListModel<>();
        rain.addElement(true);
        rain.addElement(false);
        tWeather.setBounds(100,100, 75,75);

        b=new JButton("Submit");
        cb=new JButton("Submit");


        mainPanel = new JPanel();


        collaboPanel = new JPanel();

        JPanel resultPanel = new JPanel();
        resultPanel.setBounds(40,550,800,200);



        JLabel lCategory=new JLabel("<html><h3>Choose a category:a )Tourist,<br>b )Traveler,c )business:</h3></html>");
        category=new DefaultListModel<>();
        category.addElement("a");
        category.addElement("b");
        category.addElement("c");
        tCategory=new JList<>(category);

        tt10= new DefaultListModel<>();
        tt10.addElement("true");
        tt10.addElement("false");
        tWeather=new JList<>(tt10);

        tCategory.setBounds(100,100, 75,75);
        JLabel lName=new JLabel("<html><h3>Give name:</h3></html>");

        tName=new JTextField(10);
        JLabel lAge=new JLabel("<html><h3>Give age:</h3></html>");

        tAge=new JTextField(2);
        JLabel lCities=new JLabel("<html><h3>give cities (format:GR Athens,FR Paris):</h3></html>");

        tCities=new JTextField(30);
        JLabel lTips=new JLabel("<html><h3>for the next fields give a number greater than zero if you want it in your destination:</h3></html>");
        JLabel lMuseums=new JLabel("<html><h3>Do you want museums?</h3></html>");
        tMuseums=new JTextField(2);

        JLabel lCafes=new JLabel("<html><h3>Do you want Cafes?</h3></html>");
        tCafes=new JTextField(2);

        JLabel lRestaurants=new JLabel("<html><h3>Do you want restaurants?</h3></html>");
        tRestaurants=new JTextField(2);

        JLabel lBars=new JLabel("<html><h3>Do you want bars?</h3></html>");
        tBars=new JTextField(2);

        JLabel lWeather=new JLabel("<html><h3>Do you want to exclude cities in which rains at this moment. Write true or false:</h3></html>");
         rain= new DefaultListModel<>();
        rain.addElement(true);
        rain.addElement(false);
        tWeather.setBounds(100,100, 75,75);


        b=new JButton("Submit");








        ctCategory.setBounds(100,100, 75,75);
        JLabel clName=new JLabel("<html><h3>Give name:</h3></html>");

        ctName=new JTextField(10);
        JLabel clAge=new JLabel("<html><h3>Give age:</h3></html>");

        ctAge=new JTextField(2);

        JLabel clTips=new JLabel("<html><h3>for the next fields give a number greater than zero if you want it in your destination:</h3></html>");
        JLabel clMuseums=new JLabel("<html><h3>Do you want museums?</h3></html>");
        ctMuseums=new JTextField(2);

        JLabel clCafes=new JLabel("<html><h3>Do you want Cafes?</h3></html>");
        ctCafes=new JTextField(2);

        JLabel clRestaurants=new JLabel("<html><h3>Do you want restaurants?</h3></html>");
        ctRestaurants=new JTextField(2);

        JLabel clBars=new JLabel("<html><h3>Do you want bars?</h3></html>");
        ctBars=new JTextField(2);




        cb=new JButton("Submit");






        mainPanel.add(lCategory);
        mainPanel.add(tCategory);
        mainPanel.add(lName);
        mainPanel.add(tName);
        mainPanel.add(lAge);
        mainPanel.add(tAge);
        mainPanel.add(lCities);
        mainPanel.add(tCities);
        mainPanel.add(lTips);
        mainPanel.add(lMuseums);
        mainPanel.add(tMuseums);
        mainPanel.add(lCafes);
        mainPanel.add(tCafes);
        mainPanel.add(lRestaurants);
        mainPanel.add(tRestaurants);
        mainPanel.add(lBars);
        mainPanel.add(tBars);
        mainPanel.add(lWeather);
        mainPanel.add(tWeather);

        mainPanel.add(b);
        mainPanel.setLayout(new FlowLayout());




        collaboPanel.add(clName);
        collaboPanel.add(ctName);
        collaboPanel.add(clAge);
        collaboPanel.add(ctAge);
        collaboPanel.add(clTips);
        collaboPanel.add(clMuseums);
        collaboPanel.add(ctMuseums);
        collaboPanel.add(clCafes);
        collaboPanel.add(ctCafes);
        collaboPanel.add(clRestaurants);
        collaboPanel.add(ctRestaurants);
        collaboPanel.add(clBars);
        collaboPanel.add(ctBars);
        collaboPanel.add(cb);
        collaboPanel.setLayout(new FlowLayout());


        resultPanel.add(resultNotification);

        JTabbedPane tp=new JTabbedPane();
        tp.setBounds(40,30,800,500);
        mainPanel.setLayout(new FlowLayout());
        tp.add("Collaborative filtering",collaboPanel);
        tp.add("Content based filtering",mainPanel);
        frame.add(tp);
        frame.add(resultPanel);




        b.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                try {
                    choice      = tCategory.getSelectedValue();
                    name        = tName.getText();
                    age         = Integer.parseInt(tAge.getText());
                    io          = tCities.getText();
                    museums     = Integer.parseInt(tMuseums.getText());
                    cafes       = Integer.parseInt(tCafes.getText());
                    restaurants = Integer.parseInt(tRestaurants.getText());
                    bars        = Integer.parseInt(tBars.getText());
                    weather     = tWeather.getSelectedValue();


                    if (choice == "c") {
                        CurrCity = JOptionPane.showInputDialog(frame,
                                "What is your current city?", null);
                    }

                    if(age <= 18 || age > 90){
                        resultNotification.setText("<html><h1>Please give valid age</h1></html>");
                    }else {
                        pressed = true;
                    }


            }catch(NumberFormatException e1){
                    resultNotification.setText("<html><h1>Please give a number</h1></html>");
                }
            }
        });




        cb.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                try {
                    name        = ctName.getText();
                    age         = Integer.parseInt(ctAge.getText());
                    museums     = Integer.parseInt(ctMuseums.getText());
                    cafes       = Integer.parseInt(ctCafes.getText());
                    restaurants = Integer.parseInt(ctRestaurants.getText());
                    bars        = Integer.parseInt(ctBars.getText());

                    if(age <= 18 || age > 90){
                        resultNotification.setText("<html><h1>Please give valid age</h1></html>");
                    }else {
                        pressed2 = true;
                    }

                }catch(NumberFormatException e1){
                    resultNotification.setText("<html><h1>Please give a number</h1></html>");
                }
            }
        });



    }


    void resultNotification(String s){
        resultNotification.setText("<html><h1>"+s +"</h1></html>");
    }

}