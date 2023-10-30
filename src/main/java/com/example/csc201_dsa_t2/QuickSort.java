package com.example.csc201_dsa_t2;

public class QuickSort {
    public static void quickSort(double[] arr, int[] userIDs, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, userIDs, left, right);
            quickSort(arr, userIDs, left, pivotIndex - 1);
            quickSort(arr, userIDs, pivotIndex + 1, right);
        }
    }

    private static int partition(double[] arr, int[] userIDs, int left, int right) {
        double pivotValue = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] >= pivotValue) {
                i++;
                swap(arr, i, j);
                swap(userIDs, i, j);
            }
        }

        swap(arr, i + 1, right);
        swap(userIDs, i + 1, right);

        return i + 1;
    }

    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void get(int rank, double[] arr, int[] userIDs) {
        quickSort(arr, userIDs, 0, arr.length - 1);
        printRankedUsers(rank, arr, userIDs);
    }

    private void printRankedUsers(int rank, double[] arr, int[] userIDs) {
        System.out.println("Rank " + rank + " Users:");
        for (int i = 0; i < rank; i++) {
            System.out.println("User ID: " + userIDs[i] + ", Average Rating: " + arr[i]);
        }
    }
}
