package edu.umb.cs681.FileSystem;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;


public abstract class FSElement {

	public abstract boolean isDirectory();

	protected String name;
	protected int size;
	protected LocalDateTime creationTime;
	protected ReentrantLock locks = new ReentrantLock();
	
	public FSElement(String name, int size, LocalDateTime creationTime) {
		this.name = name;
		this.size = size;
		this.creationTime = creationTime;
	}

	public int getSize() {
		locks.lock();
		try {
			return this.size;
		} finally {
			locks.unlock();
		}
	}

	public String getName() {
		locks.lock();
		try {
			return this.name;
		} finally {
			locks.unlock();
		}
	}

	public String getCreationTime() {
		return this.creationTime.toString();
	}
}
