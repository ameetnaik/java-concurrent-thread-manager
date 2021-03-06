# Java Thread Pool Manager

<p align="center">
  <a href="https://travis-ci.org/ameetnaik/java-concurrent-thread-manager">
    <img src="https://travis-ci.org/ameetnaik/java-concurrent-thread-manager.svg?branch=master">
  </a>
  <a href="https://github.com/ameetnaik/java-concurrent-thread-manager/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/ameetnaik/java-concurrent-thread-manager.svg">
  </a>
</p>


## Usage

Create a List of Callable tasks

```java
	
	import java.util.concurrent.Callable;

	public class Task implements Callable<String> {

		@Override
		public String call() throws Exception {	
			// implement code to run concurrently
			return "";
		}
	}

```

```java
	
	List<Task> taskList = new ArrayList<Task>();
	taskList.add(new Task());
	taskList.add(new Task());
	taskList.add(new Task());
	
	ThreadPoolManager threadPoolManager = new ThreadPoolManager();
	threadPoolManager.setTaskList(taskList);
	threadPoolManager.runManager();

```

## Controlling number of threads


```java

	ThreadPoolManager threadPoolManager = new ThreadPoolManager(20);

	// OR
	
	threadPoolManager.setPoolSize(20);	

```

## Controlling messaging after threads complete

```java

	ThreadPoolManager threadPoolManager = new ThreadPoolManager();
	threadPoolManager.setCallback(new Callable<String>(){
		@Override
		public String call() throws Exception {	
			// implement code to be called when threads are done running
			System.out.println("Thread done.");
			return "";
		}
	});

```

## Contributor
Ameet Naik


## License
[MIT License](LICENSE)
