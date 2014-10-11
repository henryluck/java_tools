set curPath=%~dp0
cd %curPath%
set path=%curPath%;%curPath%\..\jre7\bin;%path%
java -jar housefresh.jar