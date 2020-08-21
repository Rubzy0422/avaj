package com.wtc.avaj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.wtc.avaj.Aircrafts.AircraftFactory;
import com.wtc.avaj.Aircrafts.Flyable;


import java.util.Scanner;

public class Simulator {

    public static void main(final String args[]) throws FileNotFoundException 
    { 
        final PrintStream o = new PrintStream(new File("Simulation.txt")); 
        // Backup
        final PrintStream console = System.out;
        System.setOut(o);  //Edit
       
        if (args.length != 1) {
            System.err.println("Usage: Simulator <Simulation File>");
            System.exit(0);
        }
        else {
            readfile(args[0]);
        }
  
        // Reset
        System.setOut(console);
    } 

    // public static void main(String[] args) {
    //     
    // }

    private static void readfile(final String filename) {
        final WeatherTower CTAU = new WeatherTower(); //Controll Tower Air Units
        try {
            long ln = 2L;
            final File fsim = new File(filename);
            if (!fsim.canRead()) {
                throw new Exception("Simulation file can not be read!");
            }

            if (countFile(fsim) < 2)
                throw new Exception("Simulation file is empty Please add Flyables!");

            final Scanner sr = new Scanner(fsim);
            if (sr.hasNextLine() ) {
//              SEA :: Simulation Execution Amount
                final String r_SEA = sr.nextLine();
                final long SEA = Long.parseLong(r_SEA.trim());
                if (SEA <= 0)
                {
                    sr.close();
                    throw new Exception("Simulation file needs a valid Simulation amount!");
                }
                while (sr.hasNextLine())
                    ParseFlyable(sr.nextLine(), ln++, CTAU);
                
                    // Write.WriterOpen("Simulation.txt");
                    long i_Simc = 1L;
                    while (i_Simc <= SEA)
                    {
                        
                        CTAU.UpdateWeather();
                        i_Simc++;
                    }
                    // Write.CloseWriter();
            }
            else {
                sr.close();
                throw new Exception("Simulation file needs to have two or more lines!");
            }
            sr.close();

        }
        catch (final NumberFormatException nfe) {
            System.err.println("Fist line of simulation needs to be a valid long!");
            System.exit(-1);
        }
        catch (final Exception ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
    }

    private static void ParseFlyable(final String r_flyable, final long ln, final WeatherTower CTAU) {
        boolean WarnSet = false;
        boolean allNumsin = true;

        String ErrorMessage = "[ERROR]\t could not register flyable on line: " + ln;
        final String[] FlyableParts = r_flyable.split("\\s+");
        if (FlyableParts.length != 5) {
            System.err.println( ErrorMessage + "\t\t Insufficiant arguments");
            System.exit(-1);
            return;
        }

        final String type = FlyableParts[0];
        final String name = FlyableParts[1];
        int longitude = 0;
        int latitude = 0;
        int height = 0;

       if (FlyableParts[0].equals("Baloon") || FlyableParts[0].equals("JetPlane") || FlyableParts[0].equals("Helicopter")) {
           try {
               longitude = Integer.parseInt(FlyableParts[2].trim());
           }
           catch (final Exception ex) {
               ErrorMessage += "\n\tCould not parse Longitude to Integer format!";
               WarnSet = true;
               allNumsin = false;
           }

           try {
               latitude = Integer.parseInt(FlyableParts[3].trim());
           }
           catch (final Exception ex) {
               ErrorMessage += "\n\tCould not parse Latitude to Integer format!";
               WarnSet = true;
               allNumsin = false;
           }

           try {
               height = Integer.parseInt(FlyableParts[4].trim());
           }
           catch (final Exception ex) {
               ErrorMessage += "\n\tCould not parse height to Integer format!";
               WarnSet = true;
               allNumsin = false;
           }
       }
       else {
           ErrorMessage += "\n\t" + FlyableParts[0] +  " on line " + ln + " is NOT a known TYPE!";
           WarnSet = true;
        }

        if ((longitude < 0 || latitude < 0 || height < 0) && allNumsin)
        {
            ErrorMessage += "\n\t" + "Negative integers are not supported!";
            WarnSet = true;
        }
       if (WarnSet)
       {
           System.err.println(ErrorMessage);
           System.exit(-1);
           return;
       }
       else {
        //    Register New Air Unit
           Flyable nAU;
           nAU = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
           nAU.registerTower(CTAU);
       } 

    }

    private static long countFile(final File file) throws FileNotFoundException {
        long lineCount = 0L;

        final Scanner sl = new Scanner(file);
        while (sl.hasNextLine())
        {
            lineCount++;
            sl.nextLine();
        }
        sl.close();
        return lineCount;
    }
}
