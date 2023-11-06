# Simple Product Inventory System

## Part 1: Project Overview

This project is a simple product inventory system designed in Java. It facilitates the management of products by offering operations to insert, remove, display, and find products by their unique product numbers. The system keeps the products ordered by their product numbers and can also list available products along with their total count.

### Operations:

- **insert()**: Add a new product with a unique product number.
- **remove()**: Remove a product using its product number.
- **display()**: List all products by product number.
- **displayAvailable()**: List all in-stock products with totals.
- **find()**: Look up a product by its product number.
- **topSearched()**: List top 5 recently searched products (not numbers).

Errors are handled gracefully to ensure the system is user-friendly.

### Extra Credit Functionality:

A simple text-based menu is implemented that allows continuous performance of operations until the user exits with 'quit' or 'q'. The menu is designed for clarity and ease of use.

## Part 2: Programming Requirements

Two main classes are implemented:

### `Product` Class:

- Holds product details such as name, number, category, and availability.
- Default and parametrized constructors using `this` keyword.
- Methods, setters, and getters support operations in Part 1.
- Implements the `Comparable` interface for product comparison by number.
- `toString()` method for product display.
- Private helper methods.
- Javadoc and in-line comments for documentation.

### `ProductInventory` Class:

- Based on the PositionalList ADT.
- Manages a collection of products with operations from Part 1.
- A variable for the company name using the inventory system.
- Default and parametrized constructors.
- A main method with a text-based menu for user interaction.
- Error handling for the program and menu operations.
- Use of an Iterator for traversing the product collection.
- `toString()` method for displaying all products.
- Private helper methods.
- Javadoc and in-line comments for documentation.

### Dependencies:

The system is built using ADTs provided in `project1.jar`. The following files are included:

- `Deque.java`
- `DoublyLinkedList.java`
- `LinkedPositionalList.java`
- `LinkedQueue.java`
- `Stack.java`
- `LinkedStack.java`
- `Position.java`
- `PositionalList.java`
- `Queue.java`
- `SinglyLinkedList.java`

To extract the files from the `project1.jar`, use:

```bash
$ jar xvf project1.jar
