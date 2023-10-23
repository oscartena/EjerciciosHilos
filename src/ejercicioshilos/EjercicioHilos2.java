/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioshilos;

import java.util.Random;

/**
 *
 * @author oscar
 */
public class EjercicioHilos2 {    
    public static void main(String[] args) {
    

    Thread tortuga = new Thread(new Animal("Tortuga", 5, 5));
    Thread liebre = new Thread(new Animal("Liebre", 12, 10));
    Thread caballo = new Thread(new Animal("Caballo", 20, 25));
    Thread perro = new Thread(new Animal("Perro", 15, 15));

    tortuga.setPriority (1);
    liebre.setPriority (2);
    caballo.setPriority (4); 
    perro.setPriority (3);

    tortuga.start();
    liebre.start();
    caballo.start();
    perro.start();
}

}

class Animal implements Runnable {

    private String nombre;
    private int metrosAvance;
    private int probabilidadResbalar;

    public Animal(String nombre, int metrosAvance, int probabilidadResbalar) {
        this.nombre = nombre;
        this.metrosAvance = metrosAvance;
        this.probabilidadResbalar = probabilidadResbalar;
    }

    @Override
    public void run() {
        int distancia = 0;
        int meta=1000;
        Random random = new Random();

        for (int vuelta = 1; vuelta <= 1000; vuelta++) {
            if (random.nextInt(100) < probabilidadResbalar) {
                distancia -= 5;

                try {
                    Thread.sleep(1000); // Si resbala se suspende el hilo durante un segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                distancia += metrosAvance;
            }
            if (distancia >= meta) {
                System.out.println(nombre + " llegó a la meta.");
                break;
            }
        }

    }
}
