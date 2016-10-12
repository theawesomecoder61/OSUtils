# OSUtils
A Java class for returning system properties, setting the Mac menubar title/dock icon, and more!

## Installation
Drop the *.java* wherever you please. Be sure to include `package` with the parent package.

## Methods
### `isWindows()` - returns boolean
Detects if the OS is Windows.

### `isMac()` - returns boolean
Detects if the OS is Mac OS X.

### `isLinux()` - returns boolean
Detects if the OS is Linux-based.

### `get(OSUtils.type type, Object object)` - returns String
This is the most complicated method I included. More information on this will come soon.

## Examples
### Printing out if the user is on Windows
```java
public static void main(String[] args) {
	System.out.println(OSUtils.isWindows()); // will return true or false
}
```
### More coming soon
