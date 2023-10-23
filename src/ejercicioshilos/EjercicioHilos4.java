/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicioshilos;


/**
 *
 * @author oscar
 */
public class EjercicioHilos4 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread productor = new Thread(new Productor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));

        productor.start();
        consumidor.start();
    }
}

class Buffer {
    private int dato;
    private boolean disponible = false;

    public synchronized void escribir(int valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        dato = valor;
        disponible = true;
        notify();
    }

    public synchronized int leer() {
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        int valor = dato;
        disponible = false;
        notify();
        return valor;
    }
}

class Productor implements Runnable {
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            buffer.escribir(i);
            System.out.println("Productor produce: " + i);
        }
    }
}

class Consumidor implements Runnable {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            int valor = buffer.leer();
            System.out.println("Consumidor consume: " + valor);
        }
    }
}
