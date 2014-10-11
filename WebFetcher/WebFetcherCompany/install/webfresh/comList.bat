set curPath=%~dp0
cd %curPath%
set path=%curPath%\..\jre7\bin;%path%
java -cp webfresh.jar jlx.tools.research.ComNameListFrame