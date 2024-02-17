import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sorting {
    public static void main(String[] args) {
        try {
            int[] unsortedDataset = chooseDataset();
            int[] datasetCopy = Arrays.copyOf(unsortedDataset, unsortedDataset.length);
            chooseSort(datasetCopy, unsortedDataset);
        } catch (FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void printDatasetOptions() {
        System.out.println("Please select a dataset:");
        System.out.println("1. Prototype");
        System.out.println("2. Prototype Reversed");
        System.out.println("3. Prototype Random");
        System.out.println("4. Large");
        System.out.println("5. Large Reversed");
        System.out.println("6. Large Random");
    }

    public static void printSortOptions() {
        System.out.println("Please select a sorting method:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Radix Sort");
        System.out.println("5. Quick Sort");
        System.out.println("6. Heap Sort");
    }

    public static int[] chooseDataset() throws FileNotFoundException {
        printDatasetOptions();
        Scanner input = new Scanner(System.in);
        int[] dataset = null;

        while (dataset == null) {
            String dataSelection = input.nextLine();

            switch (dataSelection) {
                case "1":
                    dataset = readDatasetFromFile("prototype.txt", 10);
                    break;
                case "2":
                    dataset = readDatasetFromFile("prototype_reversed.txt", 10);
                    break;
                case "3":
                    dataset = readDatasetFromFile("prototype_random.txt", 10);
                    break;
                case "4":
                    dataset = readDatasetFromFile("large.txt", 2000);
                    break;
                case "5":
                    dataset = readDatasetFromFile("large_reversed.txt", 2000);
                    break;
                case "6":
                    dataset = readDatasetFromFile("large_random.txt", 2000);
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    printDatasetOptions();
            }
        }

        return dataset;
    }

    public static int[] readDatasetFromFile(String filename, int size) throws FileNotFoundException {
        File file = new File(filename);
        Scanner datafile = new Scanner(file);
        int[] dataset = new int[size];
        int i = 0;

        while (datafile.hasNextInt() && i < size) {
            dataset[i] = datafile.nextInt();
            i++;
        }

        return dataset;
    }

    public static void chooseSort(int[] dataset, int[]unsortedDataset) {
        printSortOptions();
        Scanner input = new Scanner(System.in);

        while (true) {
            String user_sort = input.nextLine();

            switch (user_sort) {
                case "1":
                    bubbleSort(dataset, unsortedDataset);
                    break;
                case "2":
                    selectionSort(dataset, unsortedDataset);
                    break;
                case "3":
                    insertionSort(dataset, unsortedDataset);
                    break;
                case "4":
                    radixSort(dataset, unsortedDataset);
                    break;
                case "5":
                    int n = dataset.length;
                    quickSort(dataset, 0, n - 1);
                    System.out.println("Unsorted Data: " + Arrays.toString(unsortedDataset));
                    System.out.println("Quick Sort");
                    System.out.println("Sorted Data: " + Arrays.toString(dataset));
                    System.out.println("Comparisons: " + quickComparisons);
                    System.out.println("Exchanges: " + quickExchanges);
                    break;
                case "6":
                    heapSort(dataset, unsortedDataset);
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    printSortOptions();
            }

            if (user_sort.equals("1") || user_sort.equals("2") || user_sort.equals("3") || user_sort.equals("4") || user_sort.equals("5") || user_sort.equals("6")) {
                break;
            }
        }
    }

    public static void bubbleSort(int[] array, int[] unsortedDataset) {
        int comparisons = 0;
        int exchanges = 0;
        int temp;
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int index = 0; index < n - i - 1; index++) {
                comparisons++;

                if (array[index] > array[index + 1]) {
                    exchanges++;
                    temp = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        System.out.println("Unsorted Data: " + Arrays.toString(unsortedDataset));
        System.out.println("Bubble Sort");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Exchanges: " + exchanges);
        System.out.println("Sorted Data: " + Arrays.toString(array));
    }

    public static void selectionSort(int[] array, int[] unsortedDataset) {
        int comparisons = 0;
        int exchanges = 0;
        int temp;
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (array[j] < array[min])
                    min = j;
            }

            exchanges++;
            temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }

        System.out.println("Unsorted Data: " + Arrays.toString(unsortedDataset));
        System.out.println("Selection Sort");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Exchanges: " + exchanges);
        System.out.println("Sorted Data: " + Arrays.toString(array));
    }

    public static void insertionSort(int[] array, int[] unsortedDataset) {
        int comparisons = 0;
        int exchanges = 0;
        int n = array.length;

        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
                comparisons++;
                exchanges++;
            }

            array[j + 1] = key;
        }

        System.out.println("Unsorted Data: " + Arrays.toString(unsortedDataset));
        System.out.println("Insertion Sort");
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Exchanges: " + exchanges);
        System.out.println("Sorted Data: " + Arrays.toString(array));
    }

    public static void radixSort(int[] array, int[] unsortedDataset) {
        int n = array.length;
        int max = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i] > max)
                max = array[i];
        int divide = 1;
        int[] output = new int[n];

        while (max / divide > 0) {
            int[] count = new int[10];

            Arrays.fill(count, 0);

            for (int i = 0; i < n; i++) {
                int digit = (array[i] / divide) % 10;
                count[digit]++;
            }

            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int digit = (array[i] / divide) % 10;
                output[count[digit] - 1] = array[i];
                count[digit]--;
            }

            System.arraycopy(output, 0, array, 0, n);

            divide *= 10;
        }

        System.out.println("Unsorted Data: " + Arrays.toString(unsortedDataset));
        System.out.println("Radix Sort");
        System.out.println("Sorted Data: " + Arrays.toString(array));
    }

    public static int quickExchanges;
    public static int quickComparisons;
    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(array, left, right);

            quickSort(array, left, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, right);
        }

    }
    public static int partition(int[] array, int left, int right) {
        int middle = left + (right - left) / 2;
        int pivot = array[middle];

        swap(array, middle, right);

        int i = left - 1;

        for (int j = left; j < right; j++) {
            quickComparisons++;
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
                quickExchanges++;
            }
        }

        swap(array, i + 1, right);
        quickExchanges++;
        return i + 1;
    }
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int heapExchanges;
    public static int heapComparisons;
    public static void heapSort(int[] array, int[] unsortedDataset) {
        int[] heap = new int[array.length + 1];
        for (int i = 1; i <= array.length; i++) {
            heap[i] = array[i - 1];
        }

        int length = heap.length - 1;
        int y = length / 2;

        while (y > 0) {
            downHeap(heap, y, length);
            y--;
        }

        y = length;
        while (y > 1) {
            swap(heap, 1, y);
            heapExchanges++;
            y--;
            downHeap(heap, 1, y);
        }

        System.out.println("Unsorted Data: " + Arrays.toString(unsortedDataset));
        System.out.println("Heap Sort");
        System.out.println("Comparisons: " + heapComparisons);
        System.out.println("Exchanges: " + heapExchanges);
        System.out.println("Sorted Data: " + Arrays.toString(heap));
    }

    public static void downHeap(int[] array, int j, int length) {
        boolean foundSpot = false;
        int i = j;
        int key = array[i];
        int k = 2 * i;

        while (k <= length && !foundSpot) {
            heapComparisons++;
            if (k < length && array[k + 1] > array[k]) {
                k = k + 1;
            }
            heapComparisons++;
            if (array[k] > key) {
                array[i] = array[k];
                i = k;
                k = 2 * i;
                heapExchanges++;
            }
            else {
                foundSpot = true;
            }
        }
        array[i] = key;
    }

}
