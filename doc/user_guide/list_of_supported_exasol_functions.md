# List of Supported Exasol Functions

Here you can find a list of supported(or partly supported) functions for the Exasol database. See also the [alphabetical list of functions supported by Exasol](https://docs.exasol.com/sql_references/functions/all_functions.htm).

- [Scalar Functions](#scalar-functions)
  - [Numeric Functions](#numeric-functions)
  - [String Functions](#string-functions)
  - [Date/Time Functions](#datetime-functions)
  - [Geospatial Functions](#geospatial-functions)
  - [Bitwise Function](#bitwise-function)
  - [Conversion Functions](#conversion-functions)
  - [Other Scalar Functions](#other-scalar-functions)
- [Aggregate and Analytic Functions](#aggregate-and-analytic-functions)
- [Aggregate Functions](#aggregate-functions)
- [Analytic Functions](#analytic-functions)

## Scalar Functions

Also see the list of [scalar functions](https://docs.exasol.com/sql_references/functions/scalarfunctions.htm) supported by Exasol.

### Numeric Functions

- `ABS`
- `ACOS`
- `ASIN`
- `ATAN`
- `ATAN2`
- `CEIL`
- `COS`
- `COSH`
- `COT`
- `DEGREES`
- `DIV`
- `EXP`
- `FLOOR`
- `LN`
- `LOG`
- `LOG10`
- `LOG2`
- `MOD`
- `PI`
- `POWER`
- `RADIANS`
- `RANDOM`
- `ROUND`
- `SIGN`
- `SIN`
- `SINH`
- `SQRT`
- `TAN`
- `TANH`
- `TRUNC`

### String Functions

- `ASCII`
- `BIT_LENGTH`
- `CHARACTER_LENGTH`
- `CHAR`
- `COLOGNE_PHONETIC`
- `CONCAT`
- `DUMP`
- `EDIT_DISTANCE`
- `INITCAP`
- `INSERT`
- `INSTR`
- `LCASE`
- `LEFT`
- `LENGTH`
- `LOCATE`
- `LOWER`
- `LPAD`
- `LTRIM`
- `MID`
- `OCTET_LENGTH`
- `REGEXP_INSTR`
- `REGEXP_REPLACE`
- `REGEXP_SUBSTR`
- `REPEAT`
- `REPLACE`
- `REVERSE`
- `RIGHT`
- `RPAD`
- `RTRIM`
- `SOUNDEX`
- `SUBSTR`
- `TRANSLATE`
- `TRIM`
- `UCASE`
- `UNICODE`
- `UNICODECHR`
- `UPPER`

### Date/Time Functions

- `ADD_DAYS`
- `ADD_HOURS`
- `ADD_MINUTES`
- `ADD_MONTHS`
- `ADD_SECONDS`
- `ADD_WEEKS`
- `ADD_YEARS`
- `CONVERT_TZ`
- `CURRENT_DATE`
- `CURRENT_TIMESTAMP`
- `DAY`
- `DAYS_BETWEEN`
- `DBTIMEZONE`
- `FROM_POSIX_TIME`
- `HOUR`
- `HOURS_BETWEEN`
- `LOCALTIMESTAMP`
- `MINUTE`
- `MINUTES_BETWEEN`
- `MONTH`
- `MONTHS_BETWEEN`
- `NOW`
- `NUMTODSINTERVAL`
- `NUMTOYMINTERVAL`
- `POSIX_TIME`
- `SECOND`
- `SECONDS_BETWEEN`
- `SESSIONTIMEZONE`
- `SYSDATE`
- `SYSTIMESTAMP`
- `WEEK`
- `YEAR`
- `YEARS_BETWEEN`

### Geospatial Functions

Also see the [documentation for geospatial data](https://docs.exasol.com/sql_references/geospatialdata/geospatialdata_overview.htm).

- `ST_AREA`
- `ST_BOUNDARY`
- `ST_BUFFER`
- `ST_CENTROID`
- `ST_CONTAINS`
- `ST_CONVEXHULL`
- `ST_CROSSES`
- `ST_DIFFERENCE`
- `ST_DIMENSION`
- `ST_DISJOINT`
- `ST_DISTANCE`
- `ST_ENDPOINT`
- `ST_ENVELOPE`
- `ST_EQUALS`
- `ST_EXTERIORRING`
- `ST_FORCE2D`
- `ST_GEOMETRYN`
- `ST_GEOMETRYTYPE`
- `ST_INTERIORRINGN`
- `ST_INTERSECTION`
- `ST_INTERSECTS`
- `ST_ISCLOSED`
- `ST_ISEMPTY`
- `ST_ISRING`
- `ST_ISSIMPLE`
- `ST_LENGTH`
- `ST_NUMGEOMETRIES`
- `ST_NUMINTERIORRINGS`
- `ST_NUMPOINTS`
- `ST_OVERLAPS`
- `ST_SETSRID`
- `ST_POINTN`
- `ST_STARTPOINT`
- `ST_SYMDIFFERENCE`
- `ST_TOUCHES`
- `ST_TRANSFORM`
- `ST_UNION`
- `ST_WITHIN`
- `ST_X`
- `ST_Y`

### Bitwise Function

- `BIT_AND`
- `BIT_CHECK`
- `BIT_LROTATE`
- `BIT_LSHIFT`
- `BIT_NOT`
- `BIT_OR`
- `BIT_RROTATE`
- `BIT_RSHIFT`
- `BIT_SET`
- `BIT_TO_NUM`
- `BIT_XOR`

### Conversion Functions

- `IS_NUMBER`
- `IS_DATE`
- `IS_TIMESTAMP`
- `IS_BOOLEAN`
- `IS_DSINTERVAL`
- `IS_YMINTERVAL`
- `TO_CHAR`
- `TO_DATE`
- `TO_DSINTERVAL`
- `TO_NUMBER`
- `TO_TIMESTAMP`
- `TO_YMINTERVAL`

### Other Scalar Functions

- `COALESCE`
- `CURRENT_SCHEMA`
- `CURRENT_SESSION`
- `CURRENT_STATEMENT`
- `CURRENT_USER`
- `DECODE`
- `GREATEST`
- `HASH_MD5`
- `HASH_SHA`
- `HASH_SHA256`
- `HASH_SHA512`
- `HASH_TIGER`
- `IPROC`
- `LEAST`
- `NULLIF`
- `NULLIFZERO`
- `NPROC`
- `NVL`
- `NVL2`
- `ROWNUM`
- `ROWID`
- `SCOPE_USER`
- `SYS_GUID`
- `USER`
- `VALUE2PROC`
- `ZEROIFNULL`

## Aggregate and Analytic Functions

Some analytic functions use special syntax. See the [documentation](https://docs.exasol.com/sql_references/functions/analyticfunctions.htm) for details. Also see the [list of aggregate functions](https://docs.exasol.com/sql_references/functions/aggregatefunctions.htm).

- `ANY`
- `AVG`
- `CORR`
- `COUNT`
- `COVAR_POP`
- `COVAR_SAMP`
- `EVERY`
- `FIRST_VALUE`
- `GROUP_CONCAT`
- `LAST_VALUE`
- `LISTAGG`
- `MAX`
- `MEDIAN`
- `MIN`
- `MUL`
- REGR_FUNCTIONS
  - `REGR_AVGX`
  - `REGR_AVGY`
  - `REGR_COUNT`
  - `REGR_INTERCEPT`
  - `REGR_R2`
  - `REGR_SLOPE`
  - `REGR_SXX`
  - `REGR_SXY`
  - `REGR_SYY`
- `SOME`
- `STDDEV`
- `STDDEV_POP`
- `STDDEV_SAMP`
- `SUM`
- `VAR_POP`
- `VAR_SAMP`
- `VARIANCE`

## Aggregate Functions

- `APPROXIMATE_COUNT_DISTINCT`
- `GROUPING`
- `GROUPING_ID`

## Analytic Functions

- `CUME_DIST`
- `DENSE_RANK`
- `LAG`
- `LEAD`
- `NTH_VALUE`
- `NTILE`
- `PERCENT_RANK`
- `PERCENTILE_CONT`
- `PERCENTILE_DISC`
- `RANK`
- `RATIO_TO_REPORT`
- `ROW_NUMBER`
