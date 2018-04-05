call ./gradlew build
if "%ERRORLEVEL%"=="0" goto rename
echo.
echo Gradle Problem

:rename
echo #1_1 gradle success
del build\libs\crud.war
ren build\libs\tasks-0.0.1-SNAPSHOT.war crud.war
if "%ERRORLEVEL%"=="0" goto stoptomcat
echo Error renaming file
goto fail

:stoptomcat
echo #1_2 rename `war` file success
call %CATALINA_HOME%\bin\shutdown.bat
goto copyfile

:copyfile
echo #1_3 shoutdown success
copy build\libs\crud.war %CATALINA_HOME%\webapps
if "%ERRORLEVEL%"=="0" goto runtomcat
echo Cannot copy file
goto fail

:runtomcat
echo #1_4 copy `war` file success
call %CATALINA_HOME%\bin\startup.bat
if "%ERRORLEVEL%"=="0" goto end

:fail
echo.
echo There were error

:end
echo #1_5 runtomcat success
echo.
echo runcrud script finieshed succesfull
echo.