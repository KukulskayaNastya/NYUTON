import java.util.ArrayList;
import java.util.Arrays;

public class Main2 {

    static ArrayList<Double> points = new ArrayList<>();

    public static void main(String[] args) {
        double[] vector =  {5, 2, -15, 6};
                            //{1, 101, 425, 0, -425, -101, -1};
        double k1=k1(vector);
        double k2=k2(vector);
        double k3=k3(vector);
        double k4=k4(vector);
        findRoots(k2,k1,vector);
        findRoots(k3,k4,vector);
    }

    static double k1(double[] vector) {
        int m = 0;
        while (vector[m] >= 0) m++;
        double a = 0;
        for (int i = 0; i < vector.length; i++) {
            if (vector[i] < 0 && vector[i] < a) a = vector[i];
        }
        return (1 + Math.pow((-a) / vector[0], 1.0 / m));
    }

    static double k2(double[] vector) {
        double[] p1 = Arrays.copyOf(vector, vector.length);
        int m = 0;
        int j = vector.length - 1;
        if (p1[p1.length - 1] < 0) {
            for (int i = 0; i < p1.length; i++) {
                p1[i] = -p1[i];
            }
        }
        while (p1[j] >= 0) {
            m++;
            j--;
        }
        double a = 0;
        for (int i = 0; i < p1.length; i++) {
            if (p1[i] < 0 && p1[i] < a) a = p1[i];
        }
        return (1.0 / (1 + Math.pow((-a) / p1[p1.length - 1], 1.0 / m)));
    }

    static double k3(double[] vector) {
        double[] p1 = Arrays.copyOf(vector, vector.length);
        for (int i = p1.length - 2; i >= 0; i -= 2) {
            p1[i] = -p1[i];
        }
        if (p1[0] < 0) {
            for (int i = 0; i < p1.length; i++) {
                p1[i] = -p1[i];
            }
        }
        int m = 0;
        while (p1[m] >= 0) m++;
        double a = 0;
        for (int i = 0; i < p1.length; i++) {
            if (p1[i] < 0 && p1[i] < a) a = p1[i];
        }
        return ((-(1 + Math.pow((-a) / p1[0], 1.0 / m))));
    }

    static double k4(double[] vector) {
        double[] p1 = Arrays.copyOf(vector, vector.length);
        for (int i = 1; i < p1.length; i += 2) {
            p1[i] = -p1[i];
        }
        if (p1[p1.length - 1] < 0) {
            for (int i = 0; i < p1.length; i++) {
                p1[i] = -p1[i];
            }
        }
        int m = 0;
        int j = p1.length - 1;
        while (p1[j] >= 0) {
            m++;
            j--;
        }
        double a = 0;
        for (int i = 0; i < p1.length; i++) {
            if (p1[i] < 0 && p1[i] < a) a = p1[i];
        }
        return ((-1.0 / (1 + Math.pow((-a) / p1[p1.length - 1], 1.0 / m))));
    }

    static void findRoots(double A, double B, double[] vector){
        int s = sign(value_in_point(A, vector));
        for (double i = A; i<=B; i=i+0.001){
            if (sign(value_in_point(i,vector))!=s && sign(value_in_point(i,vector))!=0) {
                points.add(i);
            }
            s = sign(value_in_point(i,vector));
        }
        for (double i : points){
            int k = 0 ;
            double x0 = i;
            double x1 = derivative(x0,vector);
            while (Math.abs(x1-x0)>0.0000000001){
                x0=x1;
                x1=derivative(x1,vector);
                k++;
            }
            System.out.println("Сделано шагов: " + k);
            System.out.println("Точка(корень): " + x0);
            System.out.println("Значение: " + value_in_point(x0,vector));
            System.out.println(" ");
        }
        points.clear();
    }

    static double derivative(double x0, double[] vector){
        double[] vector1 = new double[vector.length-1];
        for (int i=0; i<vector1.length ;i++){
            vector1[i] = vector[i]*(vector1.length-i);
        }
        return x0 - (value_in_point(x0,vector)/value_in_point(x0,vector1));
    }

    static double value_in_point(double j, double[] pol){
        double res = 0.0;
        for (int i = 0; i<pol.length-1; i++){
            if (pol[i]!=0) res+= Math.pow(j,pol.length-1-i)*pol[i];
        }
        return res+pol[pol.length-1];
    }

    static int sign(double x) {
        if (x > 0)
            return 1;
        else if (x < 0)
            return -1;
        return 0;
    }
}

