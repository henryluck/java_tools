set curPath=%~dp0
cd %curPath%\jar
set path=%curPath%jar;%curPath%..\jre7\bin;%path%
java -Xms64m -Xmx512m -DChannelNo=50 -DProxyPort=8888 -DServerIP=jlx.f3322.org -DServerPort=443 -cp %curPath%jar\reverseServer.jar jlx.mima.backproxy.client.ClientMain