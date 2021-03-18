package edu.umb.cs681.FileSystem;

import java.time.LocalDateTime;

public abstract class ApfsElement extends FSElement {

	protected ApfsDirectory parent;
	protected String owner;
	protected LocalDateTime lastModified;

	public ApfsElement(ApfsDirectory parent, String name, int size, String owner,
			LocalDateTime creationTime, LocalDateTime lastModified) {
		
		super(name, size, creationTime);
		if(this.name.length() < 0 || name.length() > 255) {
			throw new IllegalArgumentException("File name is allowed up to 255 characters");
		}
		this.parent = parent;
		this.owner = owner;
		this.lastModified = lastModified;
	}

	public ApfsDirectory getParent() {
		locks.lock();
		try {
			return this.parent;
		} finally {
			locks.unlock();
		}
	}

	public String getParentToString() {
		locks.lock();
		try {
			return this.parent.getName();
		} finally {
			locks.unlock();
		}
	}

	public void setParent(ApfsDirectory parent) {
		locks.lock();
		try {
			this.parent = parent;
		} finally {
			locks.unlock();
		}
		
	}
	public String getOwner() {
		locks.lock();
		try {
			return this.owner;
		} finally {
			locks.unlock();
		}
	}

	public String getLastModified() {
		locks.lock();
		try {
			return this.lastModified.toString();
		} finally {
			locks.unlock();
		}
		
	}

	public void setLastModified(LocalDateTime lastModified) {
		locks.lock();
		try {
			this.lastModified = lastModified;
		} finally {
			locks.unlock();
		}
		
	}

}
