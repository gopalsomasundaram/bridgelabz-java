/**
 * This program demonstrates the use of SuppressWarning
 * annotation
 */
package ioprogramming.annotationsandreflection.annotations;

import java.util.ArrayList;

public class SuppressExample {
    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        ArrayList newarr = new ArrayList();
        newarr.add("Thing");
        newarr.add(67);
    }
}
