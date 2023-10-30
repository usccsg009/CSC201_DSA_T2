package com.example.csc201_dsa_t2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RatingCounter {
    public static void main(String[] args) {
        calculateAndOutputAverageRatings();
    }

    public static void calculateAndOutputAverageRatings() {
        // Maps to store the sum of ratings and count of ratings for each user
        Map<Integer, Double> sumRatings = new HashMap<>();
        Map<Integer, Integer> countRatings = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("your-data-file.csv"));
             FileWriter writer = new FileWriter("Rating-Results.csv")) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t"); // Adjust delimiter as needed

                int sourceUser = Integer.parseInt(parts[0]);
                int targetUser = Integer.parseInt(parts[1]);
                int rating = Integer.parseInt(parts[2]);

                // Update sumRatings and countRatings for the targetUser
                sumRatings.put(targetUser, sumRatings.getOrDefault(targetUser, 0.0) + rating);
                countRatings.put(targetUser, countRatings.getOrDefault(targetUser, 0) + 1);
            }

            // Calculate average ratings and write to Rating-Results.csv
            for (Map.Entry<Integer, Double> entry : sumRatings.entrySet()) {
                int user = entry.getKey();
                double sumRating = entry.getValue();
                int count = countRatings.get(user);
                double averageRating = sumRating / count;

                // Write to the output CSV file
                writer.write(user + "," + String.format("%.3f", averageRating) + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

