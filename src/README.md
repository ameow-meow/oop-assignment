# objects.Pet Adoption Management System

## Project Description
A pet adoption management system that allows managing different types of pets (dogs, cats), sorting them by age, filtering young pets, and managing adopters and shelters.

## Project Structure

### Classes:
- objects.Pet (abstract class) - base class for all pets
- petTypes.Dog - dog class (inherits from objects.Pet)
- petTypes.Cat - cat class (inherits from objects.Pet)
- objects.Adopter - class for people who want to adopt pets
- objects.Shelter - class for animal shelters

### Interfaces:
- objects.Soundable - interface for animals that can make sounds

## How to Run

1. Ensure you have Java installed (JDK 8 or higher)
2. Open the project in IntelliJ IDEA
3. Locate the Main.java file
4. Right-click on Main.java → Run 'Main.main()'
5. Program execution results will appear in the console

## Assignment Requirements Completed

### OOP Concepts:
- Inheritance: petTypes.Dog and petTypes.Cat inherit from objects.Pet
- Encapsulation: All fields are private with getter/setter methods
- Polymorphism: makeSound() method is overridden in subclasses
- Abstraction: Abstract objects.Pet class with abstract makeSound() method

### Additional Features:
- objects.Soundable interface with makeSound() method
- equals() and hashCode() methods overridden
- toString() method overridden
- Final method usage (printInfo)
- Method overloading (setAge with different parameters)
- objects.Pet sorting by age
- Young pets filtering (age <= 2)

## Usage Examples

### Creating Pets:

petTypes.Dog pet1 = new petTypes.Dog("Archi", 3);
objects.Pet pet2 = new petTypes.Cat("Fiona", 1);
Making Sounds:
undefined
petTypes.Dog dog = new petTypes.Dog("Sharik", 4);
dog.makeSound(); // Output: "Woof"

petTypes.Cat cat = new petTypes.Cat("Murka", 2);
cat.makeSound(); // Output: "Meow"
Creating objects.Adopter and objects.Shelter:
undefined
objects.Adopter adopter = new objects.Adopter("Amina", "Karaganda");
objects.Shelter shelter = new objects.Shelter("Happy Tails", "Astana");
Setting Age with Units:
undefined
pet1.setAge(5, "months"); // Converts months to years
Output Example
undefined
All Pets:
objects.Pet{name='Fiona', age=1}
objects.Pet{name='Rex', age=2}
objects.Pet{name='Archi', age=3}

Young Pets:
objects.Pet{name='Fiona', age=1}
objects.Pet{name='Rex', age=2}

objects.Adopter Information:
objects.Adopter{name='Amina', city='Karaganda'}

objects.Shelter Information:
objects.Shelter{name='Happy Tails', address='Astana'}

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
Different pet types can make their specific sounds through the objects.Soundable interface implementation.

Author
Group: SE-2507
Date: January 2026