package com.mp.matchword.GameOperations;

public class CounterSingleton {
    private static CounterSingleton single_instance = null;

    // Declaring a variable of type String
    public int sayac;
    public int dogruSayisi;
    public int yanlisSayisi;

    // Constructor
    // Here we will be creating private constructor
    // restricted to this class itself
    private CounterSingleton()
    {
        sayac = 0;
        dogruSayisi = 0;
        yanlisSayisi = 0;
    }

    // Static method
    // Static method to create instance of Singleton class
    public static CounterSingleton getInstance()
    {
        if (single_instance == null) {
            single_instance = new CounterSingleton();
        }

        return single_instance;
    }

}
