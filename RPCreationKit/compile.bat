@echo off
echo Compiling...
rem --- Change this below.
set classname=RichPresence
javac %classname%.java
echo Compiling done.
echo You need to move the %classname%.class into C:/DREP/richpresences/%classname%.class now.
pause