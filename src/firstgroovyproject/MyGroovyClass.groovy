// Class is not mandatory in groovy. You can write script without class in the file.
// Everything is object in groovy. even number 1,2,3 etc are also objects. you can call methods on them.

/*
Closures:-

In Groovy, whenever you write a closure, groovy creates anonymous class internally that extends class 'Closure' 

- Implicit method
Closures are considered to have one implicitly defined method, which corresponds to the closure's arguments and
body. You cannot override or redefine this method. This method is always invoked by the call() method on the closure,
or via the special unnamed () syntax. The implicit method name is doCall().

- Closure Arguments
A closure always has at least one argument, which will be available within the body of the closure via the implicit
parameter 'it' if no explicit parameters are defined.

- Closure Return Value
Closures always have a return value. The value may be specified via one or more explicit return statement in the closure
body, or as the value of the last executed statement if returnis not explicitly specified. If the last executed statement has
no value (for example, if the last statement is a call to a void method), then null is returned.

- References to External Variables
Closures may reference variables external to their own definition. This includes local variables, method parameters, and object instance members.

- owner
To access enclosing context variable instead of local variable of same name, use 'owner'

- You can assign a closure to a variable 'def c = { println it}' and call closure 'c()' later on.
The exact type of the closure referenced by c is not defined, we know only that it is a subclass of Closure.

- Invocation of Closure
Closures may be invoked using one of two mechanisms. The explicit mechanism is to use the call() method:
closureVar.call(args);
You may also use the implict nameless invocation approach:
closureVar(args);

- Curried Closure
You can fix the values for one or more arguments to a closure instance using the curry() method from the Closuretype.
Curried closures are very useful for creating generic closure definitions, and then creating several
curried versions of the original with differing parameters bound to them.
When the curry() method is called on a closure instance with one or more arguments, a copy of the closure is first made.
The incoming arguments are then bound permanently to the new closure instance so that the parameters 1..N to the
curry() call are bound to the 1..N parameters of the closure. The new curried closure is then returned the caller.

- Closures as map keys and values
You can add a closure in map also and call it also from a map.
 
*/

package firstgroovyproject

class MyGroovyClass {
  
    // No need to define getter and setter. By default public getter and setter available.
    def private name
    def private city
    def private street  
    def private age=0
    
    // No need to put nasty null checks. ? operator will take care of that. If name is not null, then only it will call toUpperCase() on that.
    def String getNameInUppercase() {
        return name?.toUpperCase()   
    }
    
    def private defaultValueExample(val, defaultvalue=5) {// default value of 'defaultvalue' parameter is 5
        println "NonDefaultValue: ${val}, defaultvalue:${defaultvalue}" // no need of semi-colon, variable can be define in ${...} to be a part of "..."
    }

    def repeat(val) {
        println "\nTrying for loop...."
        for( def i = 0; i < 5; i++){ // no type provided for variable i
            println val
        }

        println "Trying inclusing number range...."
        for( def i in 0..4) { // 0..4 denotes inclusive integers 0,1,2,3,4
            println val;
        }
        println "Trying exclusive number range...."
        for( def i in 0..<5) { // 0..<5 denotes exclusive integers 0,1,2,3,4
            println val;
        }

        println "Trying inclusive characters range...."
        for( def c in 'a'..'e') { // 'a'..'e' denotes inclusive characters a,b,c,d,e
            println "${c} ${val}";
        }

        println "Trying exclusive characters range...."
        for( def c in 'a'..<'e') { // 'a'..'e' denotes inclusive characters a,b,c,d
            println "${c} ${val}";
        }
    }

    def testCollection() {
        println "\nTesting Range as a Collection......."
        def range = 0..4; // It's an IntRange that extends AbstractList
        println range.class; //class groovy.lang.IntRange
        println range.get(0);
        assert range instanceof List // no need o import statement
        println range[0];

        // You can define a ArrayList like java array and you can add an element to a list in multiple ways
        println "Testing array like values......"
        def cols=[
            "a",
            "b",
            "c"] // java array style to define an ArrayList in groovy
        println "type of cols ${cols.class}" // type of cols class java.util.ArrayList
        cols.add("d") // java style to add an element in a collection
        cols << "e" // groovy's built in operation << to add an element to a collection
        cols[5]="f" // java array like adding an element to a collection
        cols[6]=9 // can be added different types objects in groovy collection
        cols += true // you can use + operator to add an element to a collection and  - operator to remove
        cols -= "e"
        println "cols collection: ${cols}" // O/P: cols collection: [a, b, c, d, e, f, 9]

        if(cols == [
            "a",
            "b",
            "c",
            "d",
            "f",
            9,
            true
        ]) {//true
            println "comparing lists"
        }
        if(cols[4] == "f" && cols[5] == 9) {// true
            println "string equality can be done using =="
        }
        if(cols[4].equals("f")) {// true
            println "java style string equality"
        }


        def numbers = [1, 2, 3, 4]
        numbers += 5
        assert numbers == [1, 2, 3, 4, 5] // true
        assert numbers - [2, 3]== [1, 4, 5] // true
        println "Trying join() on collection ${numbers.join(',')}"
        println "Trying count() on collection ${numbers.count(4)}" // Counts the number of occurrences of the given value from the

    }

    def testMap() {
        println "\nTrying LinkedHashMap in Groovy....."
        def hash = [name:"Andy", "VPN-#":45]
        assert hash.getClass() == java.util.LinkedHashMap
        hash.put("city", "Redmond") // Java style adding key-value parin in map
        hash.dob = "01/29/76" // you can use . operator to add/get key-value pair in a map
        hash["state"] = "Washingon" // another way to add key-value pair in a map
        println "LinkedHashMap in groovy: ${hash}" // LinkedHashMap in groovy: [name:Andy, VPN-#:45, city:Redmond, dob:01/29/76]

        println "Retreiving dob from a map: ${hash.dob}"
        println "Retreiving name from a map: ${hash['name']}"
    }

    def testClosure() {
        println "\nTrying Closures in Groovy....."
        
        println "Trying each() function with collection"
        // collection.each(closure)
        // a new syntax is introduced —{, and then some code, followed by }.
        // The block of code signified by the {} is what's known as a closure.
        def numbers = [1, 2, 3, 4, 5]
        // If you look into each(...) method, each element of numbers collection is passed as argument to a closure
        // whatever arguments are passed to closure can be referred directly in closure (here 'value' is referred as numbers collection element)
        // If there is only one parameter is passed to a closure, then that parameter can be refered as 'it'
        numbers.each({value -> println value})
        // OR
        numbers.each({println it})
        
        // If you look into eachWithIndex(...) method, value and i are passed as arguments to a closure
        numbers.eachWithIndex ({value,i -> println "index ${i}: value ${value}"})
        
        println "Trying each() function with map"
        def hash = [name:"Andy", "VPN-#":45]
        hash.each({ key, value ->
            println "${key} : ${value}"
        })
        
        println "Trying each() function with string"
        "ITERATION".each{
            println it.toLowerCase()
            }

        
        println "Trying passing closure to a method"
        // passing a closure to a method
        // If a closure is the last argument, however, there is an elegant syntax, as shown here:
        // pickEven(10) { println it }
        pickEven(10, { println it } )

        // What makes closures interesting?
        // Other than the syntactic elegance, closures provide a simple and easy way for a function to delegate part of its implementation logic.
        println "Trying passing custom closure to a method"
        runClosure(exciteClosure)// passing closure as a parameter

        // Like Javascript, closure can be returned from a method and assigned to a variable.
        println "Trying Javascript like closure"
        def closureVar = publicMethod("Tushar");
        closureVar();
        
        // To access enclosing context variable instead of local variable of same name, use 'owner'
        println "Trying owner in closure"
        name="Tushar"
        def ownerTest = useOwner()
        ownerTest("Miral")
        
        println "Trying to test Curried closure"
        testCurriedClosure()
        
        println "Trying to test closure as a map key"
        testClosureAsMapKey()
        
    }
    
    def void testClosureAsMapKey() {
        def someClosure = {println "success"}
        def map = [(someClosure) : 123]
        println map.get(someClosure) // O/P: 123
        println map[someClosure] // O/P: 123
        println map.someClosure // O/P: null --- map.someClosure will treat someClosure as a string.
        
        map = [someOtherClosure : {println "success"}]
        map.someOtherClosure() // O/P: success --- someOtherClosure called
    }
    
    def void testCurriedClosure() {
        /*
        The below code defines a closure c, and then calls c.curry("foo"). This returns a curried closure with the arg1 value
        permanently bound to the value "foo". On the invocation d("bar"), the "bar" parameter comes into the closure in the arg2
        argument. The resulting output would be foo bar.
        */
        
        def c = { arg1, arg2-> println "${arg1} ${arg2}" }
        def d = c.curry("foo")
        d("bar") // O/P: foo bar
    }
    
    def String method()
    {
        return "hello";
    }
    
    def publicMethod (String name_)
    {
        def localVar = age + 33;
        def localVar2 = "Parameter: ${name_}";
        // closure can access enclosing context's members
        // When a closure references variables in this way, they are bound to the closure. At the same time, the variables are still
        // available normally to the enclosing scope, so the closure may read/change any such values, and code from the outer
        return { // javascript style closure 
            println "${age} ${name_} ${localVar} ${localVar2} ${method()}" 
            }
    }
    def useOwner() {
        return { name -> println ("Closure Argument: ${name}, Enclosing Context: ${owner.name}")}
    }
   
    def pickEven(n, block)
    {
        for(def i = 2; i <= n; i += 2)
        {
            block(i)
        }
    }
    // It's a custom closure
    // This closure takes one parameter (named word) and returns a String with the word variable along with two exclamation points
    // Like javascript, you cannot pass a function variable to another function as a parameter. You can pass a closure as a parameter.
    def protected exciteClosure = { number="unknown", word ->
        return "${number} : ${word}!!" // for any method or closure in groovy, 'return' is optional
        // or 
        // "${number} : ${word}!!" // return is optional even in closure
        }
    
    
    def protected void runClosure(number=5, Closure someClosure) {// or runClosure(someClosure)
        for(def i=0; i<number; i++) {
            println someClosure(i, "Hello")
            // or 
            //println someClosure.call(i, "Hello") // you can call closure using call(args) also
        }
    }

    // For Java Class
    def protected void runClosure(number, Object someClosure) {
        for(def i=0; i<number; i++) {
            println someClosure(i, "Hello")
            // or 
            //println someClosure.call(i, "Hello") // you can call closure using call(args) also
        }
        
    }
    
    
    
}