@echo off

echo Removing previous builds...
rd /s /q build
rd /s /q dist

echo Creating new directories...
md build
md build\classes
md dist

echo Updating the PATH...
PATH=D:\Program Files\Java\jdk1.6.0_14\bin;C:\Program Files\Java\jdk1.6.0_12\bin\;C:\Program Files\Java\jdk1.6.0_18\bin;C:\Program Files\Java\jdk1.6.0_16\bin

echo Creating the class files...
javac -d build\classes src\*.java

echo Creating the executable...
cd build\classes
echo Main-Class: Main>manifest.mf
jar mcf manifest.mf ..\..\dist\proto.jar *.class
del /q manifest.mf
cd ..
cd ..
echo Build finished

pause
