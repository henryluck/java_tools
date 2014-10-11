1.set classpath to com;
2.execute "rmiregistry";
3.execute "java com.jlx.rmi.server.ProductServer" or "java -Djava.rmi.server.logCalls=true com.jlx.rmi.server.ProductServer";
4.execute "java -Djava.security.policy=RMIclientsecurity.txt com.jlx.rmi.client.ProductClient"
5.OK!
