package com.company.designpatterns;

/**
 * Date: 11/29/20
 * Time: 7:10 PM
 */
public class SingletonPatterns {


}

/* Not-thread safe -> check out the thread safe implementation in DoubleCheckLocking class */

class ClassBasedSingleton {
    private static ClassBasedSingleton INSTANCE;
    private String info = "Initial info class";

    private ClassBasedSingleton() {
    }

    public static ClassBasedSingleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ClassBasedSingleton();
        }

        return INSTANCE;
    }
}

/* serializable and thread-safe */
enum EnumSingleton {

    INSTANCE("Singleton class..");

    private String info;
    EnumSingleton(String info) {
        this.info = info;
    }

    public EnumSingleton getInstance() {
        return INSTANCE;
    }

}




