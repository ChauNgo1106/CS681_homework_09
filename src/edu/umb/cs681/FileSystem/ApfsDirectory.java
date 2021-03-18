package edu.umb.cs681.FileSystem;

import java.time.LocalDateTime;



import java.util.LinkedList;


public class ApfsDirectory extends ApfsElement {
	private LinkedList<ApfsElement> children = new LinkedList<>();

	public ApfsDirectory(ApfsDirectory parent, String name, int size,String owner,
			LocalDateTime creationTime, LocalDateTime lastModified) {
		super(parent, name, size, owner, creationTime ,lastModified);
	}

	// this is directory class
	@Override
	public boolean isDirectory() {
		return true;
	}

	public LinkedList<ApfsElement> getChildren() {
		return this.children;
	}

	public void appendChild(ApfsElement child) {
		children.add(child);
		child.setParent(this);
	}

	public int getSize() {
		locks.lock();
		try {
			return this.size;
		} finally {
			locks.unlock();
		}
	}

	public int countChildren() {
		locks.lock();
		try {
			return this.children.size();
		} finally {
			locks.unlock();
		}
	}

	public LinkedList<ApfsElement> getFiles() {
		LinkedList<ApfsElement> files = new LinkedList<>();
		for (ApfsElement child : children) {
			if (!child.isDirectory()) {
				files.add(child);
			}
		}
		return files;
	}

	public int getTotalSize() {
		int total = 0;
		for (ApfsElement child : children) {
			// if this is not a Directory ==> must check whether it is Link or File
			if (!child.isDirectory()) {
				if (child instanceof ApfsLink) {
					// Link will get its actual size rather than target's size
					total += ((ApfsLink) child).getSize();
				} else {
					// actual File size.
					total += child.getSize();
				}

			} else if (child.isDirectory()) {
				ApfsDirectory convert = (ApfsDirectory) child;
				total += convert.getTotalSize();
			}
		}
		return total;
	}

	public LinkedList<ApfsDirectory> getSubDirectories() {
		LinkedList<ApfsDirectory> sub = new LinkedList<>();
		for (ApfsElement child : children) {
			if (child.isDirectory()) {
				sub.add((ApfsDirectory) child);
			}
		}
		return sub;
	}

	public void printChildName(LinkedList<ApfsElement> children) {
		for (ApfsElement child : children) {
			System.out.println(child.getName());
		}
	}

}
