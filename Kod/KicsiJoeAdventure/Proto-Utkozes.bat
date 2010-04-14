@echo off
java -jar dist\proto.jar UtkozesTesztek.txt
java -jar dist\proto.jar UtkozesTesztek2.txt

echo CivilVsCivil
java -jar ..\Compare\dist\compare.jar "../../Teszt/Utkozes - CivilVsCivil - OUTPUT.txt" "../../Teszt/Utkozes - CivilVsCivil - RESULT.txt"

echo CivilVsNyul
java -jar ..\Compare\dist\compare.jar "../../Teszt/Utkozes - CivilVsNyul - OUTPUT.txt" "../../Teszt/Utkozes - CivilVsNyul - RESULT.txt"

echo RabloVsCivil
java -jar ..\Compare\dist\compare.jar "../../Teszt/Utkozes - RabloVsCivil - OUTPUT.txt" "../../Teszt/Utkozes - RabloVsCivil - RESULT.txt"

echo RabloVsNyul
java -jar ..\Compare\dist\compare.jar "../../Teszt/Utkozes - RabloVsNyul - OUTPUT.txt" "../../Teszt/Utkozes - RabloVsNyul - RESULT.txt"

echo RabloVsRendor(Bank nincs kirabolva)
java -jar ..\Compare\dist\compare.jar "../../Teszt/Utkozes - RabloVsRendor(B-Off) - OUTPUT.txt" "../../Teszt/Utkozes - RabloVsRendor(B-Off) - RESULT.txt"

echo RabloVsRendor(Bank kirabolva)
java -jar ..\Compare\dist\compare.jar "../../Teszt/Utkozes - RabloVsRendor(B-On) - OUTPUT.txt" "../../Teszt/Utkozes - RabloVsRendor(B-On) - RESULT.txt"

echo RendorVsRablo(Bank nincs kirabolva)
java -jar ..\Compare\dist\compare.jar "../../Teszt/Utkozes - RendorVsRablo(B-Off) - OUTPUT.txt" "../../Teszt/Utkozes - RendorVsRablo(B-Off) - RESULT.txt"

echo RendorVsRablo(Bank kirabolva)
java -jar ..\Compare\dist\compare.jar "../../Teszt/Utkozes - RendorVsRablo(B-On) - OUTPUT.txt" "../../Teszt/Utkozes - RendorVsRablo(B-On) - RESULT.txt"

pause