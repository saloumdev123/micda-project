<?php

class HelloWorld
{
    // A method to print "Hello, World!"
    public function sayHello()
    {
        return "Hello, World!";
    }
}

// Create an instance of the class
$hello = new HelloWorld();

// Call the method and echo the result
echo $hello->sayHello();

?>
