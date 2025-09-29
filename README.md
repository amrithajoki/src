Design Patterns and Mini Project:

This repository contains console-based applications developed to demonstrate various software design patterns, along with a separate mini project. Each module focuses on a specific pattern, its purpose, and a practical example for better understanding.

Behavioural Design Patterns
Iterator Pattern – MaduraiNavigatorLauncher

The Iterator pattern is used to provide a way to access elements of a collection sequentially without exposing its internal representation.
In MaduraiNavigatorLauncher, this pattern is implemented to simulate navigating through tourist locations in Madurai. The application allows users to move through the list of places step by step, showcasing how iteration can simplify traversal logic.

Command Pattern – Banking Transactions

The Command pattern encapsulates a request as an object, separating the request from the invoker. It is particularly useful when you want to support operations like queuing, logging, or undo/redo functionality.
In the Banking Transactions application, this pattern is demonstrated by handling operations such as withdraw, deposit, and undo. Each action is implemented as a command object, and the invoker executes these commands without needing to know the details of how they are carried out. The undo functionality highlights the flexibility of this approach, allowing reversal of the last transaction.

Structural Design Patterns
Facade Pattern – Payment Portal

The Facade pattern provides a simplified interface to a set of complex subsystems.
In the Payment Portal, it is applied to give the user a simple way to choose and perform payments through different methods such as UPI, credit card, and net banking. Instead of directly interacting with each payment system, the user deals with a single facade class that manages the complexity internally and delivers a clean, unified experience.

Decorator Pattern – Pizza

The Decorator pattern allows dynamic addition of responsibilities to an object without modifying its structure.
In the Pizza application, this is demonstrated by allowing users to start with a plain pizza and then add multiple toppings. Each topping is a decorator that enhances the base pizza with additional functionality and updated cost.

Creational Design Patterns
Singleton Pattern – PrintSpooler

The Singleton pattern ensures that only one instance of a class is created and provides a global access point to it.
In PrintSpooler, the singleton implementation ensures that all print jobs are managed through a single spooler instance, avoiding conflicts and maintaining a controlled queue.

Factory Method Pattern – Payment Portal

The Factory Method pattern defines an interface for creating objects but lets subclasses decide which class to instantiate.
In the Payment Portal, the factory method is used to create different payment types (credit card, UPI, net banking). This removes the need to tightly couple the client code with specific payment implementations.

Mini Project:
Rocket Launch Simulator

The Rocket Launch Simulator is a console-based mini project that combines object-oriented principles with design patterns. It simulates a rocket launch sequence with features like system checks, launch commands, abort operations, and mission summary.

The project demonstrates the use of multiple design patterns:

Command Pattern to handle operations such as launch, abort, and fast-forward.

State Pattern to represent different phases of the mission (checks, launched, mission success, mission failure).

Singleton Pattern to ensure a single mission control/logger instance manages the overall flow.

This combination results in a modular, maintainable, and extensible simulation.

How to Run:

Clone the repository to your local system.

Open the project in your IDE or terminal.

Navigate to the respective package or launcher class.

Compile the Java files using javac and run the main launcher file using java.

Each pattern or project has a dedicated launcher class that serves as the entry point.
