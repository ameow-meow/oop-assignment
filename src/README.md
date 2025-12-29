# Pet Adoption Management System

## Project Description
A pet adoption management system that allows managing different types of pets (dogs, cats), sorting them by age, filtering young pets, and managing adopters and shelters.

## Project Structure

### Classes:
- Pet (abstract class) - base class for all pets
- Dog - dog class (inherits from Pet)
- Cat - cat class (inherits from Pet)
- Adopter - class for people who want to adopt pets
- Shelter - class for animal shelters

### Interfaces:
- Soundable - interface for animals that can make sounds

## How to Run

1. Ensure you have Java installed (JDK 8 or higher)
2. Open the project in IntelliJ IDEA
3. Locate the Main.java file
4. Right-click on Main.java → Run 'Main.main()'
5. Program execution results will appear in the console

## Assignment Requirements Completed

### OOP Concepts:
- Inheritance: Dog and Cat inherit from Pet
- Encapsulation: All fields are private with getter/setter methods
- Polymorphism: makeSound() method is overridden in subclasses
- Abstraction: Abstract Pet class with abstract makeSound() method

### Additional Features:
- Soundable interface with makeSound() method
- equals() and hashCode() methods overridden
- toString() method overridden
- Final method usage (printInfo)
- Method overloading (setAge with different parameters)
- Pet sorting by age
- Young pets filtering (age <= 2)

## Usage Examples

### Creating Pets:

Dog pet1 = new Dog("Archi", 3);
Pet pet2 = new Cat("Fiona", 1);
Making Sounds:
undefined
Dog dog = new Dog("Sharik", 4);
dog.makeSound(); // Output: "Woof"

Cat cat = new Cat("Murka", 2);
cat.makeSound(); // Output: "Meow"
Creating Adopter and Shelter:
undefined
Adopter adopter = new Adopter("Amina", "Karaganda");
Shelter shelter = new Shelter("Happy Tails", "Astana");
Setting Age with Units:
undefined
pet1.setAge(5, "months"); // Converts months to years
Output Example
undefined
All Pets:
Pet{name='Fiona', age=1}
Pet{name='Rex', age=2}
Pet{name='Archi', age=3}

Young Pets:
Pet{name='Fiona', age=1}
Pet{name='Rex', age=2}

Adopter Information:
Adopter{name='Amina', city='Karaganda'}

Shelter Information:
Shelter{name='Happy Tails', address='Astana'}

Sounds:
Woof
Meow

Comparing Pets:
false
false
Features
Sorting
The system automatically sorts pets by age (youngest first) using bubble sort algorithm.

Filtering
Young pets (age <= 2 years) are filtered into a separate list for easier adoption management.

Polymorphism
Different pet types can make their specific sounds through the Soundable interface implementation.

Author
Group: SE-2507
Date: January 2026