package com.example.csc201_dsa_t2;

public class HeapSort {
    public void heapSort(double[] arr, int[] userIDs) {
        int n = arr.length;

        // Build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, userIDs, n, i);
        }

        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap the root (maximum) element with the last element
            swap(arr, userIDs, 0, i);

            // Call max heapify on the reduced heap
            heapify(arr, userIDs, i, 0);
        }
    }

    private void heapify(double[] arr, int[] userIDs, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, userIDs, i, largest);
            heapify(arr, userIDs, n, largest);
        }
    }

    private void swap(double[] arr, int[] userIDs, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        int tempID = userIDs[i];
        userIDs[i] = userIDs[j];
        userIDs[j] = tempID;
    }

    public void get(int rank, double[] arr, int[] userIDs) {
        heapSort(arr, userIDs);
        printRankedUsers(rank, arr, userIDs);
    }

    private void printRankedUsers(int rank, double[] arr, int[] userIDs) {
        System.out.println("Rank " + rank + " Users:");
        for (int i = 0; i < rank; i++) {
            System.out.println("User ID: " + userIDs[i] + ", Average Rating: " + arr[i]);
        }
    }
}

