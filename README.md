## Secure Data Processing Sequencer

** Approach**

I approached the problem by simulating the real-world flow of Data Blocks through three stages:

**Input Queue → Security Filter → Final Vault**

The system processes blocks one step at a time, ensuring all rules are followed at every move.

**At each iteration:**

1. First, I try moving a block from Filter → Vault (if allowed)

2. If not possible, I try moving from Input Queue → Filter

3. If neither move is possible, the system stops (invalid state)

**specific data structures**

 **1.Queue (LinkedList)** for Input Queue

→ Maintains FIFO order.

**2. ArrayList for Filter**
→ Easy iteration and conditional removal

**3. ArrayList for Vault**

→ Simple storage of processed blocks

These structures provide a balance between:

1.Simplicity

2.Readability

3.Efficient operations for this problem size

4.Logic Thinking

## Logic

Each rule was translated into conditions:

**1. One-by-One Movement**

Implemented using a loop that performs only one valid move per iteration

**2. Filter Capacity Rule**
```
if (f.size() < 2)
```
Ensures no more than 2 blocks enter the filter.

**3. Priority Rule**
```
if (!(hasHighPriority && incomingBlock is Standard))
```
-> If a High-Priority block exists in the filter:

-> Standard blocks are blocked from entering

-> High-Priority blocks are always allowed

**4. Link Rule**
```
if (countInFilter < 2 && stillInQueue)cannot move
```
A block with a Link ID:

-> Must wait until its partner is also in the filter

-> Prevents premature movement to the vault

**Challenges Faced**

**1. Handling Linked Blocks**

Ensuring one block waits for its partner

**Solved by:**

-> Counting matching blocks in filter.

-> Checking if partner still exists in queue.

**2. Avoiding Infinite Loops**

Some cases may block all movements

**Solved by:**

-> Tracking if any move occurred in an iteration

-> If not → terminate with "invalid state"

**3. Correct Interpretation of Priority Rule**

Initially ambiguous

**Clarified that:**

-> **Standard is blocked only after High-Priority is present**

-> **High-Priority can still enter if Standard is already inside**
