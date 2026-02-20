/**
 * This program demonstrates the use of deprecated
 * annotation
 */

package ioprogramming.annotationsandreflection.annotations;

class LegacyAPI{
    @Deprecated
    void oldFeature(){
        System.out.println("This is an old feature");
    }
    void newFeature(){
        System.out.println("This is a new feature");
    }
}
public class DeprecatedExample {
    public static void main(String[] args){
        LegacyAPI obj = new LegacyAPI();
        obj.oldFeature();
        obj.newFeature();
    }
}
