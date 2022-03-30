package com.exasol.sql.expression.function.exasol;

import com.exasol.sql.expression.function.FunctionName;

/**
 * This class is a list of Scalar Functions that the Exasol database supports.
 * 
 * Currently unsupported functions: POSITION, DATE_TRUNC, EXTRACT, CAST, CONVERT, CASE, SPACE, Functions for
 * Hierarchical Queries. Keywords inside function's body are also not supported. See
 * <a href="https://github.com/exasol/sql-statement-builder/issues/68"> github issue # 68</a>.
 */
public enum ExasolScalarFunction implements FunctionName {
    // Numeric Functions
    /** Absolute value of a number */
    ABS,
    /** Arccosine */
    ACOS,
    /** Arcsine */
    ASIN,
    /** Arctangens */
    ATAN,
    /** Arctanges of two numbers (instead of fration) */
    ATAN2,
    /** Ceiling */
    CEIL,
    /** Cosine */
    COS,
    /** Hyperbolic cosine */
    COSH,
    /** Cotangens */
    COT,
    /** Radians to degrees */
    DEGREES,
    /** Integer division */
    DIV,
    /** Exponent */
    EXP,
    /** Floor */
    FLOOR,
    /** Logarithm to base e */
    LN,
    /** Logarithm with user-defined base */
    LOG,
    /** Logarithm to base 10 */
    LOG10,
    /** Logrithm to base 2 */
    LOG2,
    /** Modulo */
    MOD,
    /** Pi */
    PI,
    /** Power */
    POWER,
    /** Degrees to radians */
    RADIANS,
    /** Random number */
    RANDOM,
    /** Round */
    ROUND,
    /** Sign */
    SIGN,
    /** Sine */
    SIN,
    /** Hyperbolic sine */
    SINH,
    /** Square root */
    SQRT,
    /** Tangens */
    TAN,
    /** Hyperbolic tangens */
    TANH,
    /** Trim decimal places */
    TRUNC,

    // String Functions
    /** ASCII code of character */
    ASCII,
    /** String size in bits */
    BIT_LENGTH,
    /** String size in characters */
    CHARACTER_LENGTH,
    /** Character for number */
    CHAR,
    /** Phonetic representation of a string */
    COLOGNE_PHONETIC,
    /** String concatenation */
    CONCAT,
    /** String dump in various notations */
    DUMP,
    /** Edit distance of two strings */
    EDIT_DISTANCE,
    /** Capitalize first letter of each word */
    INITCAP,
    /** Insert string into other string */
    INSERT,
    /** Find occurrence in string */
    INSTR,
    /** Lower case */
    LCASE,
    /** Left part of string */
    LEFT,
    /** String length in characters (differs from bytes for UTF)*/
    LENGTH,
    /** Find position in string */
    LOCATE,
    /** Lower case */
    LOWER,
    /** Pad left */
    LPAD,
    /** Trim left */
    LTRIM,
    /** Take a part from the mid of the string */
    MID,
    /** String length in bytes */
    OCTET_LENGTH,
    /** Find occurrence of regular expression in string */
    REGEXP_INSTR,
    /** Replace occurrence of regular expression in string */
    REGEXP_REPLACE,
    /** Return substring that matches regular expression */
    REGEXP_SUBSTR,
    /** Repeat string sequence */
    REPEAT,
    /** Replace substring */
    REPLACE,
    /** Reverse string */
    REVERSE,
    /** Right part of a string */
    RIGHT,
    /** Pad right */
    RPAD,
    /** Trim right */
    RTRIM,
    /** Sounds like */
    SOUNDEX,
    /** Substring */
    SUBSTR,
    /** Replace characters in string */
    TRANSLATE,
    /** Remove surrounding white spaces */
    TRIM,
    /** Upper case */
    UCASE,
    /** Numeric unicode representation of character */
    UNICODE,
    /** Character for numeric unicode */
    UNICODECHR,
    /** Upper case */
    UPPER,
    // Date/Time Functions
    /** Add days */
    ADD_DAYS,
    /** Add hours */
    ADD_HOURS,
    /** Add minutes */
    ADD_MINUTES,
    /** Add months */
    ADD_MONTHS,
    /** Add seconds */
    ADD_SECONDS,
    /** Add weeks */
    ADD_WEEKS,
    /** Add years */
    ADD_YEARS,
    /** Convert timezone */
    CONVERT_TZ,
    /** Current date */
    CURRENT_DATE,
    /** Current timestamp */
    CURRENT_TIMESTAMP,
    /** Day part of date */
    DAY,
    /** Days between two dates */
    DAYS_BETWEEN,
    /** Database-wide Timezone */
    DBTIMEZONE,
    /** Posix time to timestamp */
    FROM_POSIX_TIME,
    /** Hour part of a date / time */
    HOUR,
    /** Hours between */
    HOURS_BETWEEN,
    /** Timestamp in local time */
    LOCALTIMESTAMP,
    /** Minute part of a date / time */
    MINUTE,
    /** Minutes between */
    MINUTES_BETWEEN,
    /** Month part of a date */
    MONTH,
    /** Months between two dates */
    MONTHS_BETWEEN,
    /** Current timestamp */
    NOW,
    /** Number to days-to-second interval */
    NUMTODSINTERVAL,
    /** Number to years-to-month interval */
    NUMTOYMINTERVAL,
    /** Seconds since Epoch */
    POSIX_TIME,
    /** Second part of a date / time */
    SECOND,
    /** Seconds between two dates / times */
    SECONDS_BETWEEN,
    /** Session timezone */
    SESSIONTIMEZONE,
    /** Current date */
    SYSDATE,
    /** Current timestamp */
    SYSTIMESTAMP,
    /** Week part of a date */
    WEEK,
    /** Year part of a date */
    YEAR,
    /** Years between two dates */
    YEARS_BETWEEN,

    // Geospatial Functions
    /** Area of (multi-)polygon */
    ST_AREA,
    /** Geometric boundary */
    ST_BOUNDARY,
    /** Points of maximal distance */
    ST_BUFFER,
    /** Center of mass */
    ST_CENTROID,
    /** Object fully contains another object */
    ST_CONTAINS,
    /** Convex hull */
    ST_CONVEXHULL,
    /** Object crosses another object */
    ST_CROSSES,
    /** Difference */
    ST_DIFFERENCE,
    /** Dimmension of object: Points / Lines / Poligons */
    ST_DIMENSION,
    /** Object disjoint of another object */
    ST_DISJOINT,
    /** Minimal distance between two objects */
    ST_DISTANCE,
    /** Endpoint of line */
    ST_ENDPOINT,
    /** Smallest rectangle containing object */
    ST_ENVELOPE,
    /** Objects equal */
    ST_EQUALS,
    /** Outer ring of object */
    ST_EXTERIORRING,
    /** Turn to 2D object */
    ST_FORCE2D,
    /** The n-th object of a GeometryCollection */
    ST_GEOMETRYN,
    /** Type as string */
    ST_GEOMETRYTYPE,
    /** Inner ring */
    ST_INTERIORRINGN,
    /** Intersection of two objects */
    ST_INTERSECTION,
    /** Objects intersect */
    ST_INTERSECTS,
    /** Object is closed */
    ST_ISCLOSED,
    /** Empty set */
    ST_ISEMPTY,
    /** Start point = end point */
    ST_ISRING,
    /** Simple object */
    ST_ISSIMPLE,
    /** Length */
    ST_LENGTH,
    /** Number of objects in collection of geometry objects */
    ST_NUMGEOMETRIES,
    /** Number of holes within a Polygon */
    ST_NUMINTERIORRINGS,
    /** Points in line string */
    ST_NUMPOINTS,
    /** Objects overlap */
    ST_OVERLAPS,
    /** Set SRID */
    ST_SETSRID,
    /** n-th point */
    ST_POINTN,
    /** Start point */
    ST_STARTPOINT,
    /** Symmetric difference */
    ST_SYMDIFFERENCE,
    /** Object touches another object */
    ST_TOUCHES,
    /** Coordinate transformation */
    ST_TRANSFORM,
    /** Union of two objects */
    ST_UNION,
    /** Object fully contained in another object */
    ST_WITHIN,
    /** X coordinate of point */
    ST_X,
    /** Y coordinate of point */
    ST_Y,

    // Bitwise Function
    /** Bitwise and */
    BIT_AND,
    /** Check bit set */
    BIT_CHECK,
    /** Rotate left */
    BIT_LROTATE,
    /** Shift left */
    BIT_LSHIFT,
    /** Bitwise not */
    BIT_NOT,
    /** Bitwise or */
    BIT_OR,
    /** Rotate right */
    BIT_RROTATE,
    /** Shift right */
    BIT_RSHIFT,
    /** Set bit */
    BIT_SET,
    /** Bitmask to number */
    BIT_TO_NUM,
    /** Bitwise exclusive or */
    BIT_XOR,

    // Conversion Functions
    /** Is a number */
    IS_NUMBER,
    /** Is a date */
    IS_DATE,
    /** Is a timestamp */
    IS_TIMESTAMP,
    /** Is a boolean */
    IS_BOOLEAN,
    /** Is an interval with second precision */
    IS_DSINTERVAL,
    /** Is an interval with month precision */
    IS_YMINTERVAL,
    /** Convert to character string */
    TO_CHAR,
    /** Convert to date */
    TO_DATE,
    /** Convert to interval with second precision */
    TO_DSINTERVAL,
    /** Convert to number */
    TO_NUMBER,
    /** Convert to timestamp +/
    TO_TIMESTAMP,
     /** Convert to interval with month precision */
     TO_YMINTERVAL,

    // Other Scalar Functions
    /** First non-null value */
    COALESCE,
    /** Current database schema */
    CURRENT_SCHEMA,
    /** Current session */
    CURRENT_SESSION,
    /** Current statement */
    CURRENT_STATEMENT,
    /** Current user */
    CURRENT_USER,
    /** String-based case selection */
    DECODE,
    /** Greatest value */
    GREATEST,
    /** MD5 hash */
    HASH_MD5,
    /** SHA1 hash */
    HASH_SHA,
    /** SHA256 hash */
    HASH_SHA256,
    /** SHA512 hash */
    HASH_SHA512,
    /** Tiger hash */
    HASH_TIGER,
    /** Data node number */
    IPROC,
    /** Least value */
    LEAST,
    /** Null if */
    NULLIF,
    /** Null if zero */
    NULLIFZERO,
    /** Number of cluster nodes */
    NPROC,
    /** Replace null with expression */
    NVL,
    /** Replace null with expression. Return other expression if not null */
    NVL2,
    /** Row counter */
    ROWNUM,
    /** Row ID */
    ROWID,
    /** Scope user */
    SCOPE_USER,
    /** Unique system wide ID */
    SYS_GUID,
    /** Current user */
    USER,
    /** Number of the node a value is on */
    VALUE2PROC,
    /** Zero if null */
    ZEROIFNULL
}