## Player-Manager
**TTRPG initiative and health management system** A Java command-line tool for managing initiative order and hit points in tabletop RPG encounters.

## Requirements
- Java 11+

## How to Compile and Run

```bash
javac -d out -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" src/*.java test/*.java
java -cp "out:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" Main
```

## How to Run Tests

```bash
java -cp "out:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore Tests
```


## Usage Overview

1. Enter number of players

2. Enter the name and initiative roll of each player

3. Choose whether enemies are present:
    - **No** - start encounter
    - **Yes**,
        1. enter enemy amount and their initiative, or press "0" to cancel.
        2. If there is only one enemy, you will be asked whether to give it a custom name, or press "0" to cancel.
        3. Choose whether enemies have individually specified maximum HP.
            - **No**-enter HP once.
            - **Yes**-enter HP for each enemy.
            - **0**-Cancel.
4. Encounter is run, choose action.
    - 1\. end the encounter
    - 2\. (if enemies are present) choose an enemy and hit points to be removed
    - 3\. (if enemies are present) choose an enemy and hit points to be added
    - 4\. add enemies to the encounter.
    - 0\. take no action and the turn advances to the next entity

## Features
- **Variable Player Entry:** Add any number of unique player names and initiative rolls
- **Automatic Sorting of Initiative:** Sorts players and enemies in descending initiative order
- **Single or Multiple Enemy Handling:** Supports custom naming for single enemies and HP tracking for all enemies
- **Dynamic Enemy Entry:** Add enemies before or during an encounter
- **Enemy Integration:** Enemies are automatically inserted into the correct initiative position
- **Turn Advancement:** “No action” advances to the next entity in the order
- **Encounter Management:** End encounters, modify enemy HP, add more enemies, or continue rotation
- **Simple Text-Based Interface:** Clear prompts guiding users through setup and management

## Example


```text
---------------------------------------
Enter number of players: 2
Input player name:
Fred
Input player initiative:
14
Input player name:
Gord
Input player initiative:
12
Are there enemies in this encounter? Y/N 
Y
Add enemy amount and initiative (eg. 2, 14) 0 to cancel: 
2, 13
Will every enemy have their own HP (Y/N)? 0 to cancel: 
N
Enter maximum hit points for all enemies, 0 to cancel: 
25
 
/-----------------Current Order--------------------/
Fred          | init:  14 
Enemy2        | init:  13 | HP:  25 
Enemy1        | init:  13 | HP:  25 
Gord          | init:  12 
Actions: 
End encounter: 1
Remove enemy health: 2
Increase enemy health: 3
Add more enemies: 4
No action: 0
```

## Technologies
- Java
- Object-Oriented Programming (OOP)
- Command-line Interface (CLI)

## License
Open source, available under the MIT License.