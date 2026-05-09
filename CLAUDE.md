# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run

Plain Java — no build tool. All source files are in the project root.

```bash
# Compile everything
javac *.java

# Run the application
java Test
```

`Test.java` contains `main()` and is the application entry point, not a test suite.

## Architecture

### Class Hierarchy

```
Displayable (interface: displayInfo())
├── Gym
├── Machine
└── Person (abstract)
    ├── Member
    └── Staff (abstract)
        └── Coach
```

**`Person`** validates that `id` is exactly 4 characters in its constructor; throws `InvalidIdException` if not. All subclass constructors must propagate this checked exception.

**`Member`** stores membership length in months; price = months × 100 RS. Membership must be > 0 or `IllegalArgumentException` is thrown.

**`Coach extends Staff`** tracks `numLessons`; income = lessons × 100. `doCoachingLesson()` increments the count and recalculates income.

**`Gym`** holds three custom `LinkedList` instances (members, staff, machines). Max 3 `Gym` objects exist at runtime (enforced in `Test.java`), max 10 members per gym. `Gym` tracks `coachCount` separately from `staffcount` since `Staff` can be extended beyond `Coach`.

### Data Structure

`LinkedList` is a custom singly linked list backed by `Node` objects (generic `Object` data). Used for all three gym collections. Traversal is always manual via `getHead()` / `getNext()` — there is no iterator or index-based access in `LinkedList` itself; `Gym` provides `getMember(int index)` and `getCoach(int index)` that traverse internally.

### Persistence

Every mutation to a gym writes the full gym state to `{gymName}_Gym.txt` via `writeGymFile()`. "Display Info" reads from this file, falling back to `displayInfo()` in memory if the file is missing.

### UI Structure (Test.java)

All UI is built inline in `main()` using Swing/AWT. Navigation flow:

```
Main Window
├── Create Gym → JDialog (name, maxMembers ≤ 10, maxStaff, maxMachines)
├── Manage Gym → Choose Gym JDialog → Manage Menu JFrame
│   ├── Sign Up Member → JDialog (name, id, months)
│   ├── Remove Member  → JOptionPane dropdown
│   ├── Workout        → JOptionPane dropdown → calls m.Workout()
│   ├── Add Machine    → JOptionPane input
│   ├── Add Coach      → JDialog (name, id)
│   ├── Coaching Lesson → JOptionPane dropdown → calls C2.doCoachingLesson()
│   └── Display Info   → non-modal JDialog with JScrollPane + JTextArea
└── Delete Gym → JDialog with per-gym delete buttons
```

Two shared factory helpers in `Test`:
- `makeBtn(String label, boolean primary)` — primary = blue filled, secondary = white outlined
- `makeField()` — consistent styled `JTextField`

The gym array `GymList[3]` and its count are shared across all listeners via `final int[] countRef = {0}` (array wrapper to allow mutation from lambdas).
