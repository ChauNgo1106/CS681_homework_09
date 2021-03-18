package edu.umb.cs681.FileSystem;

import java.time.LocalDateTime;

public class ApfsLink extends ApfsElement {
	private ApfsElement target;

	public ApfsLink(ApfsDirectory parent, String name, int size, String owner, LocalDateTime creationTime,
			LocalDateTime lastModified, ApfsElement target) {
		super(parent, name, size, owner, creationTime, lastModified);
		if (target == null) {
			throw new NullPointerException("File or Directory do not exist");
		}
		this.target = target;
	}

	@Override
	public boolean isDirectory() {
		return false;
	}

	public ApfsDirectory getParent() {

		return this.parent;
	}

	public String getName() {
		locks.lock();
		try {
			return this.name;
		} finally {
			locks.unlock();
		}
	}

	public int getTargetSize() {
		if (target.isDirectory()) {
			ApfsDirectory temp = (ApfsDirectory) target;
			return temp.getTotalSize();
		} else {
			return target.getSize();
		}
	}

	public String getCreationTime() {
		return this.creationTime.toString();
	}

	public int getSize() {
		locks.lock();
		try {
			return this.size;
		} finally {
			locks.unlock();
		}
	}

	public ApfsElement getTarget() {
		locks.lock();
		try {
			return this.target;
		} finally {
			locks.unlock();
		}
	}

}
