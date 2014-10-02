@ECHO OFF
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;../../../lib.*.jar

"%JAVA_HOME%\bin\javac" Main.java
"%JAVA_HOME%\bin\java" -Xms128m -Xmx384m -Xnoclassgc Main