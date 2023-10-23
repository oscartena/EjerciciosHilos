/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicioshilos;

/**
 *
 * @author oscar
 */
public class EjerciciosHilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int contador = 0;

        Thread hilo1 = new Thread(new Incrementador(contador, 500));
        Thread hilo2 = new Thread(new Incrementador(contador, 500));
        Thread hilo3 = new Thread(new Incrementador(contador, 500));
        Thread hilo4 = new Thread(new Incrementador(contador, 500));

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Valor final del contador: " + contador);
    }
}

class Incrementador implements Runnable {

    private int contador;
    private int incrementos;

    public Incrementador(int contador, int incrementos) {
        this.contador = contador;
        this.incrementos = incrementos;
    }

    @Override
    public void run() {
        for (int i = 0; i < incrementos; i++) {
            contador++;
        }
    }
}
