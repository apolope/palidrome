package br.com.a3sitsolutions.utils;

import br.com.a3sitsolutions.dtos.MatrixDTO;
import java.util.List;

public class Matrix {

    public static Boolean checkMatrixLength(MatrixDTO matrix, Integer minLen, Integer maxLen) {
        return matrix.getMatrix().size() >= minLen && matrix.getMatrix().size() <= maxLen;
    }

    public static Boolean checkMatrixRowLenght(MatrixDTO matrix) {
        for (List<Character> row : matrix.getMatrix()) {
            if (row != null && row.size() != matrix.getMatrix().get(0).size()) {
                return false;
            }
        }
        return true;
    }

    public static Boolean isValideMatrix(MatrixDTO matrix, Integer minLen, Integer maxLen) {
        return  checkMatrixLength(matrix, minLen, maxLen) && checkMatrixRowLenght(matrix);
    }
}
