@echo off
set ANT_OPTS=-Xmx2G -Dfile.encoding=UTF-8
set M2_HOME=%~dp0apache-maven-3.6.3
set PATH=%M2_HOME%\bin;%PATH%
rem deleting CLASSPATH as a workaround for PLA-8702
set CLASSPATH= 
echo Setting ant home to: %M2_HOME%