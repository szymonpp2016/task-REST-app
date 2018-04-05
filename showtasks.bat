echo. START RUNCRUD.BAT
call runcrud
if "%ERRORLEVEL%" == "0" goto opera
echo.
echo RUNCRUD.BAT has errors - breaking work
goto fail

:opera
echo #2_1 RUNCRUD succes
start "" "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto end
echo.
echo Opera has errors - breaking work
goto fail

:fail
echo.
echo There were errors

:end
echo #2_2 OPEN OPERA success
echo.
echo showtask script finieshed succesfull
echo.