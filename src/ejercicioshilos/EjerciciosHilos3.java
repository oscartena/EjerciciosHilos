/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioshilos;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author oscar
 */
public class EjerciciosHilos3 {

    public static void main(String[] args) {
        AtomicInteger contador = new AtomicInteger(0);

        Thread hilo1 = new Thread(new IncrementadorV2(contador, 500));
        Thread hilo2 = new Thread(new IncrementadorV2(contador, 500));
        Thread hilo3 = new Thread(new IncrementadorV2(contador, 500));
        Thread hilo4 = new Thread(new IncrementadorV2(contador, 500));

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

        System.out.println("Valor final del contador: " + contador.get());
    }
}

class IncrementadorV2 implements Runnable {
    private AtomicInteger contador;
    private int incrementos;

    public IncrementadorV2(AtomicInteger contador, int incrementos) {
        this.contador = contador;
        this.incrementos = incrementos;
    }

    @Override
    public void run() {
        for (int i = 0; i < incrementos; i++) {
            contador.incrementAndGet();
        }
    }
}
