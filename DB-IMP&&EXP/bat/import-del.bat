set CLASSPATH=./lib/db.imp.exp-0.5.jar;./lib/mysql-connector-java-5.0.6-bin.jar;./lib/ojdbc14-10.2.0.jar;./resource;
set dataFileDir=%CD%\data
java -classpath %CLASSPATH% -DisDebug=true -DisDel=true -Ddirname=%dataFileDir% MainClass import
pause