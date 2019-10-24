# BareBones
Java built interpreter for the BareBones language designed by Brookshear

# Usage

```bash
java Main test.bare
```

# Additions

### Comments

Comments can be achieved by using `#`, after which, the interpreter will ignore everything on that line.
e.g. 
```
clear X; # Sets X to 0
```


# Example

Example program using the BareBones syntax. Results in `X=0`

```
# Example program that results in X=0

clear X;
incr X;
incr X;
incr X;
while X not 0 do;
   decr X;
end;
```
