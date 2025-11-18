## Player-Manager
**TTRPG initiative and health management system** A java tool for managing initiative and health in tabletop RPG encounters.

## Requirements
- Java 11

## How to Compile and Run

```bash
javac src/*.java
java -cp src Main
```

## Usage

1. Enter amount of players
2. Enter the name and initiative roll of each player

3. Choose whether enemies are present:
    - **No** - start encounter
    - **Yes**,
        1. enter enemy amount and their initiative
        2. If there is only one enemy, you will be asked whether to give it a custom name
        3. Choose whether enemies have individually specified health range or not
            - **No**-enter health once.
            - **Yes**-enter health for each enemy
4. Encounter is run, choose action.
    - 1\. end the encounter
    - 2\. (if enemies are present) choose an enemy and hit points to be removed
    - 3\. (if enemies are present) choose an enemy and hit points to be added
    - 4\. add enemies to the encounter.
    - 0\. take no action and initiative order is rotated

## Features
- **Variable Player Entry:** Add variable amount of unique player names and their initiative rolls to encounters
- **Automatic Sorting of Initiative:** Sort players and enemies in descending order based on their initiative rolls
- **Single or Multiple Enemy Handling:** Supports individual naming and HP tracking for single enemies
- **Dynamic Enemy Entry:** Allows for the user to pick whether encounter has enemies present or not, then specify amount
- **Enemy Integration:** Enemies are automatically integrated into initiative order
- **Automatic Rotation:** Initiative is rotated based on current action entity
- **Encounter Management:** User has options to end the encounter, alter enemy hit points, add more enemies and to simply continue rotation
- **Simple Text-Based Interface:** Easy-to-use interface guiding users through encounter setup and management

## Example


```text
Enter number of players: 2
Input player name:
Bob
Input player initiative:
15
Input player name:
Alice
Input player initiative:
16
Are there enemies in this encounter? Y/N 
Y
Add enemy amount and initiative (eg. 2, 14): 
2 14
Will every enemy have their own HP? Y/N: 
N
Enter maximum hit points for all enemies: 
20
 
/-----------------Current Order--------------------/
Alice         | init:  16 
Bob           | init:  15 
Enemy1        | init:  14 | HP:  20 
Enemy2        | init:  14 | HP:  20 
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