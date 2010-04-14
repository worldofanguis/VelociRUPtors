@echo off
java -jar dist\proto.jar ..\..\Teszt\TesztOsszeallitasok\TrafficControllerTests.txt

echo CivilVsStop
java -jar ..\Compare\dist\compare.jar "../../Teszt/CivilVsStop_output.txt" "../../Teszt/CivilVsStop_res.txt"

echo CivilVsLampa
java -jar ..\Compare\dist\compare.jar "../../Teszt/CivilVsLampa_output.txt" "../../Teszt/CivilVsLampa_res.txt"

echo RabloVsExit
java -jar ..\Compare\dist\compare.jar "../../Teszt/RabloVsExit_output.txt" "../../Teszt/RabloVsExit_res.txt"

echo RendorVsExit
java -jar ..\Compare\dist\compare.jar "../../Teszt/RendorVsExit_output.txt" "../../Teszt/RendorVsExit_res.txt"

echo RendorVsStopOff
java -jar ..\Compare\dist\compare.jar "../../Teszt/RendorVsStopOff_output.txt" "../../Teszt/RendorVsStopOff_res.txt"

echo RendorVsLampaOff
java -jar ..\Compare\dist\compare.jar "../../Teszt/RendorVsLampaOff_output.txt" "../../Teszt/RendorVsLampaOff_res.txt"

echo RendorVsLampaOn
java -jar ..\Compare\dist\compare.jar "../../Teszt/RendorVsLampaOn_output.txt" "../../Teszt/RendorVsLampaOn_res.txt"

pause