# CS 4450 Semester-Project
- Project developed for **CS 4450.01 – Computer Graphics**
- Authors: Kenneth Chau, Tina Arezoomanians, Kelly Lwin
- Date last modified: 05/04/2025

This is a 3D OpenGL-based MineCraft-like world written in Java using **LWJGL**.

![Program Screenshot](https://github.com/tinaarezoomanian/Semester-Project/blob/main/screenshots/screenshot1.png)
![Program Screenshot](https://github.com/tinaarezoomanian/Semester-Project/blob/main/screenshots/screenshot3.png)
---

## 🎮 Controls

| Key                | Action                                     |
|--------------------|---------------------------------------------|
| `W` / `UP`         | Move forward                                |
| `S` / `DOWN`       | Move backward                               |
| `A` / `LEFT`       | Move left                                   |
| `D` / `RIGHT`      | Move right                                  |
| `SPACE`            | Fly up                                      |
| `LEFT SHIFT`       | Fly down                                    |
| `M`                | Switch to **Day** skybox                    |
| `N`                | Switch to **Night** skybox                  |
| `H`                | Switch to **HELL MODE** skybox              |
| `ESC`              | Exit program                                |
| `Mouse Movement`   | Look around (first-person view)             |

---

## 🌤️ Day/Night Modes

- The scene switches between day and night by toggling a boolean flag (isDay), which changes the skybox textures and lighting to simulate time of day. 
- Users can control this by pressing the N key for night and M key for day.

## ⭕ Hell Mode

- Hell mode transforms the environment into a darker, more intense version by changing terrain textures to lava and altering the skybox. 
- It can be toggled using the H key and plays a distinct sound effect when activated.

## 🐔 Chickens with Audio

- Chickens spawn randomly on top of the terrain. They like to randomly walk across terrain but never fall off the edge.
- Audio is used to enhance immersion with ambient chicken clucks and a sound cue for entering hell mode. Chickens randomly emit sounds from their 3D positions.

---

## 🛠 How to Run

1. Make sure you have **JDK 17+** and **LWJGL 2.9.2**
2. Place all required `.png` textures in the `textures/` folder
3. Compile and run `Basic.java`
4. Ensure working directory is set to project root
