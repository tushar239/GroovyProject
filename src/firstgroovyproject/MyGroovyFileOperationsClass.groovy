package firstgroovyproject
// http://mrhaki.blogspot.com/2009/08/groovy-goodness-working-with-files.html

class MyGroovyFileOperationsClass {
    
    def void createFile() {
        def file1 = new File('groovy1.txt')
        def file2 = new File('groovy2.txt')
        def file3 = new File('groovy3.txt')
        // Writing to the files with the write method:
        file1.write 'Working with files the Groovy way is easy.\n'
        // Using the leftShift operator:
        file1 << 'See how easy it is to add text to a file.\n'
        // Using the text property:
        file2.text = '''We can even use the text property of a file to set a complete block of text at once.'''
        // Or a writer object:
        file3.withWriter('UTF-8') { writer ->
            writer.write('We can also use writers to add contents.')
        }
         
    }
    def void readFile() {
        // eachLine method internally creates BufferedReader. It reads a file line by line. line is passed as arguement to a closure.
        // Whatever is passed as an argument to a closure, can be accessed while writing closure code.
        // If for whatever reason the println() method were to throw an exception, the eachLine() method ensures that the file resource is correctly closed. 
        // Similarly if an exception occurs while reading, the resource will be closed too.
        
        new File("foo.txt").eachLine { line -> println(line) }
    }
    
    // If you wish to use a reader/writer object or an input/output stream object there are helper methods to handle the resource for you via a closure - which will automatically close down any resource if an exception occurs.
    def void withReader() {
        def count=0, MAXSIZE=100
        // withReader method created BufferedReader object and passes it as an argument to a closure.
        // Whatever is passed as an argument to a closure, can be accessed while writing closure code.
        new File("foo.txt").withReader { reader ->
          while (reader.readLine() != null) {
            if (++count > MAXSIZE) throw new RuntimeException('File too large!')
          }
        }
    }
    
    def void withWriter() {
        def fields = ["a":"1", "b":"2", "c":"3"]
        // withWriter( ) method creates BufferedWriter object and passes it as an argument to a closure.
        // withWriter( ) flushes and closes the stream automatically when you return from the closure.
        // Whatever is passed as an argument to a closure, can be accessed while writing closure code.
        new File("foo.txt").withWriter { writer ->
            fields.each() { key, value ->
                writer.writeLine("${key}=${value}")
            }
        }
    }

    // reads foo.txt and filters record based on condition mentioned inside closure and writes filtered lines to foo1.txt    
    def void filterLine() {
        new File("foo1.txt").withWriter ({ writer -> 
            new File("foo.txt").filterLine(writer, {line -> !line.startsWith("b=")})
        })
    }
}
