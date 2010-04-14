@echo off
java -jar dist\proto.jar MovementTests.txt

echo Kanyarodas
java -jar ..\Compare\dist\compare.jar "../../Teszt/Kanyarodas_output.txt" "../../Teszt/Kanyarodas_res.txt"

echo TMove
java -jar ..\Compare\dist\compare.jar "../../Teszt/TMove_output.txt" "../../Teszt/TMove_res.txt"

echo RendorAlarm
java -jar ..\Compare\dist\compare.jar "../../Teszt/RendorAlarm_output.txt" "../../Teszt/RendorAlarm_res.txt"

echo NyulTimeOut
java -jar ..\Compare\dist\compare.jar "../../Teszt/NyulTimeOut_output.txt" "../../Teszt/NyulTimeOut_res.txt"

pause
