package com.bingofx.bingofx;

public class Hilo extends Thread{
    private volatile boolean pausado = false;
    private final Object lock = new Object();

    @Override
    public void run() {
        while(true){
            synchronized(lock){
                while(pausado){
                    try{
                        lock.wait();
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Hilo");
            try {
                // Pausar el hilo durante 5 segundos (5000 milisegundos)
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                // Manejo de la excepción si el hilo es interrumpido mientras está dormido
                e.printStackTrace();
            }
        }
    }

    public void pausar() {
        pausado = true;
    }

    public void reanudar() {
        pausado = false;
        synchronized (lock) {
            lock.notify(); // Notificar al hilo para que reanude su ejecución
        }
    }
}
