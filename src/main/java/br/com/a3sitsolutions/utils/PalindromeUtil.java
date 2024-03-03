package br.com.a3sitsolutions.utils;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import java.util.ArrayList;
import java.util.List;

public class PalindromeUtil {

    public static List<String> findPalindromes(MatrixDTO matrix, Integer minLen) {
        List<String> palindromes = new ArrayList<>();
        int rows = matrix.getMatrix().size();
        int cols = matrix.getMatrix().get(0).size();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                checkDirections(matrix, i, j, rows, cols, palindromes, minLen);
            }
        }

        return palindromes;
    }

    private static boolean isPalindrome(String str, Integer minLen) {
        if (str.length() < minLen) {
            return false;
        }
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static void findPalindromesInDirection(MatrixDTO matrix, int x, int y, int dx, int dy, int rows, int cols, List<String> palindromes, Integer minLen) {
        StringBuilder sb = new StringBuilder();
        int curX = x;
        int curY = y;

        while (curX >= 0 && curX < rows && curY >= 0 && curY < cols) {
            sb.append(matrix.getMatrix().get(curX).get(curY));
            if (sb.length() > 1 && isPalindrome(sb.toString(), minLen) && !palindromes.contains(sb.toString())) {
                palindromes.add(sb.toString());
            }
            curX += dx;
            curY += dy;
        }
    }

    private static void checkDirections(MatrixDTO matrix, int x, int y, int rows, int cols, List<String> palindromes, Integer minLen) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                findPalindromesInDirection(matrix, x, y, dx, dy, rows, cols, palindromes, minLen);
            }
        }
    }
}
