public class Main1 {

    static double x0 = 0.0;

    static double function(double x){
        return Math.pow(x-1,3) + 0.5*Math.exp(x);
    }
    public static void main(String[] args) {
        double x1 = x0 - function(x0) / ((function(x0 + 0.000001) - function(x0)) / 0.000001);
        int k = 0;
        while (Math.abs(x1 - x0) > 0.00000001) {
            x0 = x1;
            x1 = x0 - function(x0) / ((function(x0 + 0.000001) - function(x0)) / 0.000001);
            k++;
        }
        System.out.println("Сделано шагов: " + k);
        System.out.println("Точка(корень): " + x0);
        System.out.println("Значение: " + function(x0));
    }
}
