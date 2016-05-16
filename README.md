# MChallenge

## 1. TreeNode findFirstCommonAncestor method

- Estimate amount of time: proportional to Lg N comparisons of TreeNode objects (consider Lg = Log base 2).
- Estimate amount of memory: (Lg N)*2 of total amount of TreeNode object references.

## 2. SeatingManager 

- arrives
  - Estimate amount of time: propotional to N comparisons of Table (N number of available tables)
  - Estimate amount of memory: proportional to 1 Table reference
  
- leaves
  - Estimate amount of time: propotional to N comparisons of CustomerGroup (N number of waiting CustomerGroups)
  - Estimate amount of memory: proportional to 1 CustomerGroup reference.
