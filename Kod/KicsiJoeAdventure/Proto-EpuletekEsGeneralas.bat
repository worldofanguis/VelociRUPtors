@echo off
java -jar dist\proto.jar EpuletekEsGeneralas.txt

echo Rablo vs. Bank (nincs kirabolva)
java -jar ..\Compare\dist\compare.jar ../../Teszt/RabloVsBankOff_out.txt ../../Teszt/RabloVsBankOff_res.txt

echo Rablo vs. Bank (ki van rabolva)
java -jar ..\Compare\dist\compare.jar ../../Teszt/RabloVsBankOn_out.txt ../../Teszt/RabloVsBankOn_res.txt

echo Rablo vs. Rejtekhely (nincs kirabolva)
java -jar ..\Compare\dist\compare.jar ../../Teszt/RabloVsHideoutOff_out.txt ../../Teszt/RabloVsHideoutOff_res.txt


echo Rablo vs. Rejtekhely (ki van rabolva)
java -jar ..\Compare\dist\compare.jar ../../Teszt/RabloVsHideoutOn_out.txt ../../Teszt/RabloVsHideoutOn_res.txt

echo Civil vs. Epulet


echo Civil generalas es eltuntetes
java -jar ..\Compare\dist\compare.jar ../../Teszt/CivilGenExit_out.txt ../../Teszt/CivilGenExit_res.txt
