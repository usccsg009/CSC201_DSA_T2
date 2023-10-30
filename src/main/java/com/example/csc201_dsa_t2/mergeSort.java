package com.example.csc201_dsa_t2;

public class MergeSort {
    public void mergeSort(double[] arr, int[] userIDs) {
        int n = arr.length;
        if (n > 1) {
            int mid = n / 2;

            // Divide the array into two halves
            double[] leftArr = new double[mid];
            double[] rightArr = new double[n - mid];
            int[] leftUserIDs = new int[mid];
            int[] rightUserIDs = new int[n - mid];

            System.arraycopy(arr, 0, leftArr, 0, mid);
            System.arraycopy(arr, mid, rightArr, 0, n - mid);
            System.arraycopy(userIDs, 0, leftUserIDs, 0, mid);
            System.arraycopy(userIDs, mid, rightUserIDs, 0, n - mid);

            // Recursively sort each half
            mergeSort(leftArr, leftUserIDs);
            mergeSort(rightArr, rightUserIDs);

            // Merge the sorted halves
            merge(arr, userIDs, leftArr, rightArr, leftUserIDs, rightUserIDs);
        }
    }

    private void merge(double[] arr, int[] userIDs, double[] leftArr, double[] rightArr, int[] leftUserIDs, int[] rightUserIDs) {
        int leftLength = leftArr.length;
        int rightLength = rightArr.length;
        int i = 0, j = 0, k = 0;

        while (i < leftLength && j < rightLength) {
            if (leftArr[i] >= rightArr[j]) {
                arr[k] = leftArr[i];
                userIDs[k] = leftUserIDs[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                userIDs[k] = rightUserIDs[j];
                j++;
            }
            k++;
        }

        while (i < leftLength) {
            arr[k] = leftArr[i];
            userIDs[k] = leftUserIDs[i];
            i++;
            k++;
        }

        while (j < rightLength) {
            arr[k] = rightArr[j];
            userIDs[k] = rightUserIDs[j];
            j++;
            k++;
        }
    }

    public void get(int rank, double[] arr, int[] userIDs) {
        mergeSort(arr, userIDs);
        printRankedUsers(rank, arr, userIDs);
    }

    private void printRankedUsers(int rank, double[] arr, int[] userIDs) {
        System.out.println("Rank " + rank + " Users:");
        for (int i = 0; i < rank; i++) {
            System.out.println("User ID: " + userIDs[i] + ", Average Rating: " + arr[i]);
        }
    }

    public static void main(String[] args) {
        // Example usage
        double[] averageRatings = {4.5, 3.2, 4.8, 2.6, 5.0};
        int[] userIDs = {1, 2, 3, 4, 5};

        MergeSort mergeSort = new MergeSort();
        int rank = 3;
        mergeSort.get(rank, averageRatings, userIDs);
    }
}

