# tcp-ip-sockets

Asynchronus communication using Java Sockets

###Compiling the code###
*Navigate to your project directory ${dir}/tcp-ip-sockets and execute the following line*
```
  > mvn clean compile
```

###Running the server###
*Navigate to your project directory ${dir}/tcp-ip-sockets/socket-server and execute the following line*
```
  > java -cp ../commons/target/commons-1.0-SNAPSHOT.jar:target/socket-server-1.0-SNAPSHOT.jar com.java.examples.SocketServer 4040
```
###Running the client###
*Navigate to your project directory ${dir}/tcp-ip-sockets/socket-client and execute the following line*
```
  > java -cp ../commons/target/commons-1.0-SNAPSHOT.jar:target/socket-server-1.0-SNAPSHOT.jar com.java.examples.SocketServer 4040
```
