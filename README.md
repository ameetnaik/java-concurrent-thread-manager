# Java Thread Pool Manager
Java Thread Pool Manager

<p align="center">
  <a href="https://travis-ci.org/ameetnaik/java-concurrent-thread-manager">
    <img src="https://travis-ci.org/ameetnaik/java-concurrent-thread-manager.svg?branch=master">
  </a>
  <a href="https://github.com/ameetnaik/java-concurrent-thread-manager/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/ameetnaik/java-concurrent-thread-manager.svg">
  </a>
</p>

This project is configured with Maven.

## Usage

Create an List of Callable<V> tasks

```java

	public class Task implements Callable<String> [

		@Override
		public String call() throws Exception {	
			// implement code to run concurrently
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

## Contributor
Ameet Naik

## License
[MIT License](LICENSE)
