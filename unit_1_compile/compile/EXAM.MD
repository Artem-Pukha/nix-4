### prepare manifest ###
`$ echo Main-Class: com.spnsolo.Main>MANIFEST.MF
echo Class-Path: libs/commons-lang3-3.11.jar libs/commons-text-1.9.jar>>MANIFEST.MF'

### prepare jar output ###
`$ md build\jar`
'$ mkdir -p build/jar'

### Run compile ###
`$ javac -sourcepath ./src -d build/classes -cp ./libs/commons-lang3-3.11.jar;./libs/commons-text-1.9.jar src/com/spnsolo/similarity/Similarity.java src/com/spnsolo/print/Print.java src/com/spnsolo/Main.java`

### Create jar file ###
`$ jar cfm build/jar/Hi.jar MANIFEST.MF -C build/classes/ .`

### Run program ###
`$ java -cp build/classes/;./libs/commons-lang3-3.11.jar;./libs/commons-text-1.9.jar; com.spnsolo.Main`

### Run program (jar)###
`$ java -jar build/jar/Hi.jar`

### Remove ###
'$ rd /s /q build'
'$ del /q Hi.jar'
'$ del /q MANIFEST.MF'