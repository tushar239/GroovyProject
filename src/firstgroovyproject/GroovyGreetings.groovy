package firstgroovyproject

// You can use Java code in Groovy file, but vice-versa is not true. You can instantiate Groovy class in Java class and use Groovy methods also as far as they are implemented with basic java rules.
// Groovy can run on JVM. If you don't want Java in Groovy project, you can run groovy project on GVM.
// Groovy is Java without 
// - types (String, Integer, int etc). Use optional typing ('def') and type inference happens internally. 
// - mandatory modifier (private/protected/public). Default modifier is public. You can access private and protected modifiers also from other class. Groovy doesn't differentiate between private/public/protected. 
// - return type. You don't have to type 'return' at the end of the method. Last sentence of method considered with 'return'.
// - iterators. collection.each(...) or map.each(...) is used in groovy. You don't have to code iterators. It iterates internally.
// - semi-colons.
// - getters and setters. You can access set and get the value of instance variables using obj.property=value/obj.property
// - contructors. You don't have to write constructors, but you can still instantiate a class using constructor with any number of arguements.
// - import statements for collection classes. You can use List without importing 'java.util.List'   

// Groovy has support of
// - code without class. You can write groovy script(code) directly inside groovy file without creating Groovy class.
// - closures. Block of code that can be passed as a method parameter and can be executed at some point of time during method execution.
// - less coding
// - everything is object. Number is also an object. You can use methods on numbers e.g. 3.times(Closure)
// - use variables inside string using ${variable}
// - setting default value of method parameter  
// - using java code and calling java class in groovy file
// - null checking operator (?)
// - ranges. For loop can be used with ranges of numbers or characters. range can also be used to create a Collection.
// - special support for Collection. 
//      - Collection can be instantiated like [...,...,...] and accessed like an array. special <<,+ operator is available to add an element to it.  - operator is available to remove element(s) from it.
// - special support for Map.
//      - Map can be instantiated like [key:value, key:value, key:value]
//      - Map can be accessed like map[key] or map.key. 

// Groovy is highly preferable to use for JUnits.

class GroovyGreetings { // no need to provide a public modifier, by default it is public
    static void main(args) {// no need of argument type in methods

        // no need of semi-colon.
        // No need of explicit type defining. you can define a variable with 'def'. Groovy will automatically do type inference.
        // As value of the variable is inside "", it will automatically inferred as String.
        def anything="Hello World"
        println anything.class // O/P:class java.lang.String
        println anything // No need of System.out.println.

        println 1.1.getClass().name // 1.1 as an instance of java.math.BigDecimal
        
        // Everything is an object in Groovy. 
        // Any number is considered as an instance of Number class. 
        // You can use methods will numbers also. Here, you are passing a closure to times() method.
        // if you want to access a variable inside "", use use ${variable}. No need to use + operator like java code.
        println "Trying number.times(Closure)....."
        3.times ({println "${anything}"}) // it will print variable 'anything' 3 times
        println "Trying number.upto(toNumber, Closure)....."
        1.upto(5, {println "${anything}"})  // Iterates the closure 1 to 5 times, inclusive (total 5 times)
        println "Trying number.downto(toNumber, Closure)....."
        5.downto(1, {println "${anything}"}) // Iterates from this number down to the given number, inclusive,
        println "Trying number.step(toNumber, step, Closure)....."
        1.step(5, 2, {println "${anything}"}) // Iterates from this number up to the given number using a step increment.
             
        testMyGroovyClass()
        
        testMyGroovyFileOperationsClass()

    }

    def static void testMyGroovyClass() {
        // you can use constructor without defining it in class
        def myClass = new MyGroovyClass(name:"Tushar")
        println "Name in uppercase: ${myClass.nameInUppercase}"
        assert myClass.name == "Tushar"
        //myClass.setCity("Redmond") // city is private, so cannot use myClass.setCity and myClass.getCity on it, but can use myClass.city
        //assert myClass.getCity() == "Redmond"
        myClass.street = "170th PL"
        assert myClass.street == "170th PL"

        // http://groovy.codehaus.org/Things+you+can+do+but+better+leave+undone
        // IMP: Surprised to see that you can access private members --- I see it as a big disadvantage in groovy
        // Currently Groovy doesn't distinguish properly between public, private, and protected members
        myClass.defaultValueExample(5)
        myClass.defaultValueExample(5,4)
        myClass.repeat(5)
        myClass.testCollection();
        myClass.testMap();
        myClass.testClosure();

        def myClass2 = new MyGroovyClass()
        println "Name in uppercase: ${myClass2.nameInUppercase}"

        // Mixer of java and groovy.
        MyGroovyClass myClass3 = new MyGroovyClass(name:"Tushar");
        myClass3.name = "Tushar"

        // Mixer of java and groovy.
        MyJavaClass myJavaClass = new MyJavaClass(name:"Tushar")
        myJavaClass.name="Tushar"

    }
    
    def static void testMyGroovyFileOperationsClass() {
        def classObj = new MyGroovyFileOperationsClass()
        println "\nTrying File reading using Groovy....."
        classObj.createFile()
        classObj.withWriter()
        classObj.readFile()
        classObj.withReader()
        classObj.filterLine()

    }
}
