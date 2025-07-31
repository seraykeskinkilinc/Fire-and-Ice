# Fire and Ice

**Project 2**  
**2023-2024 Spring Semester**  
**Duration:** 6 weeks  
**Team Members:**  
- Seray Keskinkılınç  
- Atakan Sezer  
- Selim Tarık  
- Tuna Yavuz  

## 🧊🔥 Game Overview

**Fire and Ice** is a 2D maze-based action game where a human player competes against computer-controlled robots to collect treasures and survive elemental hazards. The goal is to achieve the highest possible score by strategically navigating the maze, avoiding fire, deploying ice, and collecting valuable treasures.

---

## 🎮 Gameplay Mechanics

### Game Field
- Size: **23 x 53** including walls
- Read from file: `maze.txt`

### Characters
- **P (Player):** Human-controlled, 1000 life points
- **C (Computer):** AI-controlled robots, 1000 life points

### Controls
- **Arrow Keys:** Move player
- **Space Bar:** Deploy ice (if @ is available)

### Movement Timing
- Player: 1 square per 100ms (1 time unit)
- Computer: 1 square per 400ms (4 time units)

---

## 🪙 Game Elements

| Symbol | Type     | Description                                                                 |
|--------|----------|-----------------------------------------------------------------------------|
| 1      | Treasure | 3 points for player, 9 for computer                                         |
| 2      | Treasure | 10 points for player, 30 for computer                                       |
| 3      | Treasure | 30 points for player, 90 for computer                                       |
| -      | Fire     | Spreads using **Queue** (BFS), harms **player**, max 50 blocks             |
| @      | Ice Pack | Can be collected and used by the player                                     |
| +      | Ice      | Spreads using **Stack** (DFS), harms **computer**, max 25 blocks           |
| C      | Robot    | Moves towards nearest treasure, avoids obstacles                           |

---

## 🧠 Input Queue System

- Size: 10 elements
- Auto-filled continuously
- Every 2 seconds, a new element is randomly added to the maze
- Queue content probabilities:
  - 1: 5/30
  - 2: 5/30
  - 3: 5/30
  - `-` (fire): 6/30
  - `@` (ice pack): 6/30
  - `C` (robot): 3/30

---

## ⚔️ Combat & Damage Rules

| Attacker | Target | Damage / Effect               |
|----------|--------|-------------------------------|
| Fire (-) | Player | 1 life per neighboring block  |
| Ice (+)  | Robot  | 50 life per neighboring block |
| Robot (C)| Player | 50 life if adjacent           |

- Robots are **unaware** of ice.
- Player can gain **100 points** if a robot dies from ice.
- If the player’s life reaches **0**, the game ends.

---

## 🧾 High Score System

- Displayed at the end of the game in descending order
- Saved in `highscore.txt`
- Sample default entries:
  - Eda Fırat – 200
  - Tarık Yolcu – 325
  - Ali Okyanus – 16
  - Deniz Vadi – 90

---

## 🛠️ Development Timeline

| Week | Tasks                                                                 |
|------|-----------------------------------------------------------------------|
| 1    | Planning, class design, task distribution                            |
| 2    | Implement player movement and timing logic                           |
| 3    | Input queue and fire spreading using BFS                             |
| 4    | Ice spreading using DFS                                              |
| 5    | AI movement, damage mechanics (P, C, -, +)                           |
| 6    | High score table, polish, testing, final submission                  |

**First Evaluation:** 3.5.2024  
**Final Evaluation (Report + Presentation):** 24.5.2024  

---

## 📂 Files

- `maze.txt`: Initial map and layout
- `highscore.txt`: Score table saved post-game
- `main.cpp` or equivalent: Game logic implementation

---

## ✅ How to Play

1. Compile and run the project.
2. Use **arrow keys** to move.
3. Use **space bar** to deploy ice when `@` is available.
4. Avoid fire and robots. Collect numbers for points.
5. Survive and aim for the **highest score**!

---

## 👨‍💻 Technologies

- Language: C++
- Data Structures: Stack (for ice), Queue (for fire and input queue)
- Algorithms: BFS (fire), DFS (ice)


---

Enjoy the game! ❄️🔥  
