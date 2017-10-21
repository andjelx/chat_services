What have been done
===

* Maven's pom.xml and some code sligthly updated to support paremetrized runs (like passing MQTT credentials and URL).
I do believe this can be better done through spring parameters.

* Each component has Jenkinsfile for CI - build and dockerrize (release version is not parametrized - so master branch now built)

* Message queue built into dockerfile by packer ( from Hashicorp ) `packer build build.json` to reuse existing Anisible routine. Not the best way but quick. Some cleanup in Ansible need to be done to reduce resulting docker image size.

* Minikube manifests (*.yml) for each component do deploy by `kubectl create -f *.yml`

* Nginx site config in the root dir to confugure all components (SLL not added but it's just few lines)

* I can show setup on my own Jenkins + minikube server

Some thoughts
===
* Setup app monitoring can be done with Prometheus JVM
* Rewrite ansible code to fit better 
* Update Java code to fully support parametrization 

Issues I have faced and still open
===
* *chat_broadcast_sse* not starting properly: I have fixed pom.xml and it builds not but start's with an error

```
2017-10-21 07:57:19.464:INFO:oejs.Server:main: jetty-9.4.7.v20170914
java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:53)
	at java.lang.Thread.run(Thread.java:748)
Caused by: MultiException[java.lang.StringIndexOutOfBoundsException: String index out of range: -1, java.lang.NoSuchMethodError: org.eclipse.jetty.util.thread.strategy.ExecuteProduceConsume.<init>(Lorg/eclipse/jetty/util/thread/ExecutionStrategy$Producer;Ljava/util/concurrent/Executor;Lorg/eclipse/jetty/util/thread/Invocable$InvocationType;)V]
	at org.eclipse.jetty.server.Server.doStart(Server.java:416)
	at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
	at de.affinitas.chat.service.ChatBroadcastSSEService.start(ChatBroadcastSSEService.java:41)
	at de.affinitas.chat.service.ChatBroadcastSSEService.main(ChatBroadcastSSEService.java:19)
	... 6 more
	Suppressed: java.lang.NoSuchMethodError: org.eclipse.jetty.util.thread.strategy.ExecuteProduceConsume.<init>(Lorg/eclipse/jetty/util/thread/ExecutionStrategy$Producer;Ljava/util/concurrent/Executor;Lorg/eclipse/jetty/util/thread/Invocable$InvocationType;)V
		at org.eclipse.jetty.io.ManagedSelector.<init>(ManagedSelector.java:79)
		at org.eclipse.jetty.io.SelectorManager.newSelector(SelectorManager.java:251)
		at org.eclipse.jetty.io.SelectorManager.doStart(SelectorManager.java:236)
		at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
		at org.eclipse.jetty.util.component.ContainerLifeCycle.start(ContainerLifeCycle.java:131)
		at org.eclipse.jetty.util.component.ContainerLifeCycle.doStart(ContainerLifeCycle.java:105)
		at org.eclipse.jetty.server.AbstractConnector.doStart(AbstractConnector.java:270)
		at org.eclipse.jetty.server.AbstractNetworkConnector.doStart(AbstractNetworkConnector.java:81)
		at org.eclipse.jetty.server.ServerConnector.doStart(ServerConnector.java:236)
		at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
		at org.eclipse.jetty.server.Server.doStart(Server.java:431)
		... 9 more
Caused by: java.lang.StringIndexOutOfBoundsException: String index out of range: -1
	at java.lang.String.charAt(String.java:658)
	at org.eclipse.jetty.util.log.AbstractLogger.condensePackageString(AbstractLogger.java:211)
	at org.eclipse.jetty.util.log.StdErrLog.<init>(StdErrLog.java:217)
	at org.eclipse.jetty.util.log.StdErrLog.<init>(StdErrLog.java:201)
	at org.eclipse.jetty.util.log.StdErrLog.newLogger(StdErrLog.java:635)
	at org.eclipse.jetty.util.log.AbstractLogger.getLogger(AbstractLogger.java:48)
	at org.eclipse.jetty.util.log.Log.getLogger(Log.java:317)
	at org.eclipse.jetty.server.handler.ContextHandler.doStart(ContextHandler.java:764)
	at org.eclipse.jetty.servlet.ServletContextHandler.doStart(ServletContextHandler.java:261)
	at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.start(ContainerLifeCycle.java:131)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.doStart(ContainerLifeCycle.java:113)
	at org.eclipse.jetty.server.handler.AbstractHandler.doStart(AbstractHandler.java:113)
	at org.eclipse.jetty.server.handler.ContextHandlerCollection.doStart(ContextHandlerCollection.java:161)
	at org.eclipse.jetty.util.component.AbstractLifeCycle.start(AbstractLifeCycle.java:68)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.start(ContainerLifeCycle.java:131)
	at org.eclipse.jetty.server.Server.start(Server.java:452)
	at org.eclipse.jetty.util.component.ContainerLifeCycle.doStart(ContainerLifeCycle.java:105)
	at org.eclipse.jetty.server.handler.AbstractHandler.doStart(AbstractHandler.java:113)
	at org.eclipse.jetty.server.Server.doStart(Server.java:419)
	... 9 more
```

