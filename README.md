# Alexandria Board Game Simulation

## 🌟 Overview
Welcome to the **Alexandria** board game simulation! In this game, 2-4 players compete against each other in a race to the 30th field on a straight road. 
Players roll dice to advance along the board, encountering both standard and special fields that impact their journey. 
The goal? Be the first player to reach the 30th field and claim victory!

---

## 🔧 Setup Instructions

### 📋 Requirements
To run the backend of the Alexandria board game, ensure you have the following installed:
- **Java JDK**
- **Maven**
- **IDE** (e.g., IntelliJ IDEA, Eclipse)

---

## 🎲 Game Rules

### 🏁 General Gameplay
- **Number of Players**: 2 to 4 players.
- **Objective**: Be the first player to reach the 30th field.
- **Gameplay**:
    - Players take turns rolling a dice to move forward by the number shown on the die.
    - The player roll and advances based on the dice roll. The players are selected randomly i.e. it is possible that the same player can have consecutive chances to roll the dice.
    - If a player lands on a **standard field**, their turn ends, and the next player takes their turn.
    - Landing on a **special field** (either a bonus or trap field) activates a special effect that must be followed.
    - Players may land on the same field as other players without any issue.

---

### 💥 Special Fields

#### 1. **Bonus Fields** (5 on the board)
Players can land on one of 5 special bonus fields, each offering a unique advantage. **Only one bonus field** can be activated per turn.

**Types of Bonus Fields**:
- **Type 1**: Move forward 2 additional fields.
- **Type 2**: All other players move 2 fields back (moving backward does not trigger any bonus or trap effects).
- **Type 3**: "Joker" – This saves the player from the next trap they land on (only one Joker can be held at a time).

#### 2. **Trap Fields** (5 on the board)
Trap fields present setbacks for the player who lands on them. **Only one trap field** can be activated per turn.

**Types of Trap Fields**:
- **Type 1**: Move 2 fields back.
- **Type 2**: All other players move forward 2 fields (moving forward does not trigger any bonus or trap effects).
- **Type 3**: Skip the next round.

#### 3. **Special Field Activation**:
- Special fields (both bonus and trap) can only be activated once during each player’s turn.
- The types of bonus and trap fields are **randomly assigned** at the start of the game, but they remain fixed throughout the game. For example, the 5th field might be a trap, and the type of trap (e.g., Type 2) will remain consistent for all players landing on that field.

---

### 📍 Field Layout

The game board consists of **30 fields**:
- **5 Bonus Fields**
- **5 Trap Fields**
- **20 Standard Fields**

---

### 🎮 Player Actions

- **Rolling the Dice**: Players advance by rolling the dice, and their turn ends when they land on a standard field.
- **Landing on Special Fields**:
    - **Bonus Fields**: Trigger the corresponding bonus immediately.
    - **Trap Fields**: Trigger the trap effect immediately, causing a setback for the player.

---

## 👤 Author
**Kshitij Harsh**
