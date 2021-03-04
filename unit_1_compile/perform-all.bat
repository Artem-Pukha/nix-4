@echo //////////////////////////////////////////////////////////////////////
@echo ---------------------------Project 'COMPILE'--------------------------
@echo \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
@echo.
@cd compile
@call run.bat
@echo _________________
@cd ..
@echo.
@echo ///////////////////////////////////////////////////////////////////
@echo ---------------------------Project 'ANT'---------------------------
@echo \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
@echo.
@cd ant
@echo --------Compiling--------
@echo.
@call compile.bat
@echo.
@echo --------Creating jar--------
@echo.
@call create-jar.bat
@echo.
@echo --------Program output('ANT')--------
@echo.
@call run-ant.bat
@cd ..
@echo.
@echo /////////////////////////////////////////////////////////////////////
@echo ---------------------------Project 'MAVEN'---------------------------
@echo \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
@echo.
@cd maven
@echo.
@echo --------Clean-install--------
@echo.
@call clean-install
@echo.
@echo --------Program output('MAVEN')--------
@echo.
@call run.bat
@cd ..