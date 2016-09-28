package firstgroovyproject;

public class JavaGreetings {

    public static void main(String[] args) {
        // Mixer of java and groovy. --- Can't use groovy code in java class, but vice-versa is possible.
        //def myJavaClass = new MyJavaClass()
        //myJavaClass.name="Tushar"
        
        MyGroovyClass groovyClass = new MyGroovyClass();
        // Can't access private members in java, but in groovy you can. I see it as an advantage as well as disadvantage.
        // Advantage - You can test private/protected java methods easily in JUnits using Groovy
        // Disadvantage - basic oops concept of data hiding is supported by Groovy
        //myClass.defaultValueExample(3);  
        
        groovyClass.runClosure(5, groovyClass.exciteClosure);
    }
    
 
}
