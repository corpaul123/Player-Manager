## Player-Manager
**TTRPG initiative and health management system** built in Java. Allowing GameMasters to run tabletop encounters with variable player count and enemy count.

## Requirements
- Java 8 or higher

## How to Compile and Run

```bash
javac src/*.java
java -cp src Main
```bash

## Usage

1. Enter amount of players
2. Enter the name and initiative roll of each player

3. Choose whether enemies are present:
    a. No - start encounter
    b. Yes,
        1. enter enemy amount and their initiative
        2. If there is only one enemy, you will be asked whether to give it a custom name
        3. Choose whether enemies have individually specified health range or not
        a. No-enter health once.
        b. Yes-enter health for each enemy
4. Encounter is run, choose action.
    1-end the encounter
    2-(if enemies are present) choose an enemy and hit points to be removed
    3-add enemies to the encounter.
    0-take no action and initiative order is rotated

## Features
- **Variable Player Entry:** Add variable amount of unique player names and their initiative rolls to encounters
- **Automatic Sorting of Initiative:** Sort players and enemies in descending order based on their initiative rolls
- **Single or Multiple Enemy Handling:** Supports individual naming and HP tracking for single enemies
- **Dynamic Enemy Entry:** Allows for the user to pick whether encounter has enemies present or not, then specify amount
- **Enemy Integration:** Enemies are automatically integrated into initiative order
- **Automatic Rotation:** Initiative is rotated based on current action entity
- **Encounter Management:** user has options to end the encounter, alter enemy hit points, add more enemies and to simply continue rotation
- **Simple Text-Based Interface:** easy-to-use interface guiding users through encounter setup and management

## Example

```text
Enter number of players: 2
Input name and initiative:
Alice 15
Input name and initiative:
Bob 12
Are there enemies in this encounter? Y/N
Y
Add enemy amount and initiative (e.g., 2 14): 
2 14
Will every enemy have their own HP? Y/N: 
N
Enter HP for all enemies: 
30
/-----------------Current Order--------------------/
Enemy1        | init:  14 | HP:  30 
Enemy2        | init:  14 | HP:  30 
Alice         | init:  15 
Bob           | init:  12