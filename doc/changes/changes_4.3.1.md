# SQL Statement Builder 4.3.1, released 2020-XX-XX

Code Name: Refactoring

## Refactoring
 
* #98: Refactored comparison and like class structure
   The refactoring changed the internal representation of the `Comparison`.
   The public API from `BooleanTerm` did however not change.
   
* #95: Refactored `ValueExpressionVisitor` 
