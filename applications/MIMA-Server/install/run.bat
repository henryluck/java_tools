set curPath=%~dp0
cd %curPath%\jar
set path=%curPath%\jar;%curPath%..\jre7\bin;%path%
java -Xms64m -Xmx512m -jar reverseServer.jar