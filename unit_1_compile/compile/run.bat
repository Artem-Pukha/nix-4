@echo --------Creating 'MANIFEST'--------
echo Main-Class: com.spnsolo.Main>MANIFEST.MF
echo Class-Path: libs/commons-lang3-3.11.jar libs/commons-text-1.9.jar>>MANIFEST.MF
@echo.
@echo --------Compiling--------
javac -sourcepath ./src -d build/classes -cp ./libs/commons-lang3-3.11.jar;./libs/commons-text-1.9.jar src/com/spnsolo/similarity/Similarity.java src/com/spnsolo/print/Print.java src/com/spnsolo/Main.java
@echo.
@echo --------Creating jar--------
jar cfm Hi.jar MANIFEST.MF -C build/classes/ .
@echo.
@echo --------Program output('COMPILE')--------
@echo _________________
@java -jar Hi.jar