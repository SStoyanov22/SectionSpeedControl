package org.sectionspeedcontrol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class VehicleDataProcessor implements DataProcessor {

    @Override
    public List<Vehicle> read(String fileName) {
        List<Vehicle> vehicles = new ArrayList<>();
        InputStream is = getFileFromResourceAsStream(fileName);
        try (InputStreamReader streamReader =
                new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" ");
                String licensePlate = data[0];
                LocalTime entryTime = LocalTime.of(
                        Integer.parseInt(data[1]), Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]), Integer.parseInt(data[4])
                );
                LocalTime exitTime = LocalTime.of(
                        Integer.parseInt(data[5]), Integer.parseInt(data[6]),
                        Integer.parseInt(data[7]), Integer.parseInt(data[8])
                );
                vehicles.add(new Vehicle(licensePlate, entryTime, exitTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
    @Override
    public void write(String output, String fileName) throws IOException {
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(output);
            writer.close();


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
