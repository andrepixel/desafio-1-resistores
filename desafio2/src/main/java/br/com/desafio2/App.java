package br.com.desafio2;

public class App {

    public static int[] reorderMatrix(int[] matrix) {
        int n = matrix.length;

        // Cria um novo array para armazenar o resultado
        int[] result = new int[n];

        // Preenche o primeiro elemento do resultado com o primeiro elemento da matriz
        result[0] = matrix[0];

        // Reordena os elementos restantes em ordem crescente
        for (int i = 1; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (matrix[j] < matrix[minIndex]) {
                    minIndex = j;
                }
            }
            result[i] = matrix[minIndex];
            // Remove o elemento encontrado do array original
            System.arraycopy(matrix, minIndex, matrix, minIndex - 1, n - minIndex);
            n--;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] matrix = { 1, 2, 3, 6, 9, 8, 7, 4, 5 };
        int[] reorderedMatrix = reorderMatrix(matrix);

        System.out.println("Matriz original: ");
        printArray(matrix);

        System.out.println("\nMatriz reordenada: ");
        printArray(reorderedMatrix);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
