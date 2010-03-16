@echo off

echo Removing previous builds...
rd /s /q build
rd /s /q dist

echo Creating new directories...
md build
md build\classes
md dist

echo Updating the PATH...
PATH=D:\Program Files\Java\jdk1.6.0_14\bin

echo Creating the class files...
javac -d build\classes src\*.java

echo Creating the executable...
cd build\classes
echo Main-Class: Main>skeleton.mf
jar mcf skeleton.mf ..\..\dist\skeleton.jar *.class
del /q skeleton.mf
cd ..
cd ..
echo Build finished
