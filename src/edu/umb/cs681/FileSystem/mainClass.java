package edu.umb.cs681.FileSystem;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

class ApfsDirectoryRunnable extends ApfsDirectory implements Runnable {

	public ApfsDirectoryRunnable(ApfsDirectory parent, String name, int size, String owner, LocalDateTime creationTime,
			LocalDateTime lastModified) {
		super(parent, name, size, owner, creationTime, lastModified);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Size: " + getSize());
		System.out.println("Name: " + getName());
		System.out.println();
		
		for (ApfsDirectory child : getSubDirectories()) {
			System.out.println(child.getName());
		}
		for (ApfsElement child : getFiles()) {
			System.out.println(child.getName());
		}
		System.out.println();
	}

}

class ApfsFileRunnable extends ApfsFile implements Runnable {

	public ApfsFileRunnable(ApfsDirectory parent, String name, int size, String owner, LocalDateTime creationTime,
			LocalDateTime lastModified) {
		super(parent, name, size, owner, creationTime, lastModified);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Size: " + getSize());
		System.out.println("Name: " + getName());
		System.out.println();
	}

}

class ApfsLinkRunnable extends ApfsLink implements Runnable {

	public ApfsLinkRunnable(ApfsDirectory parent, String name, int size, String owner, LocalDateTime creationTime,
			LocalDateTime lastModified, ApfsElement target) {
		super(parent, name, size, owner, creationTime, lastModified, target);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Size: " + getSize());
		System.out.println("Name: " + getName());
		System.out.println("Get target size: " + getTargetSize());
		System.out.println("Get target name: " + getTarget().getName());
		System.out.println();
	}

}

public class mainClass {

	public static void main(String[] args) {

		ApfsDirectoryRunnable root = new ApfsDirectoryRunnable(null, "root", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 8, 15), LocalTime.of(12, 00, 00)),
				LocalDateTime.of(LocalDate.of(2019, 8, 15), LocalTime.of(12, 00, 00)));

		ApfsDirectoryRunnable applications = new ApfsDirectoryRunnable(root, "applications", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 00, 40)),
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 00, 40)));

		ApfsDirectoryRunnable home = new ApfsDirectoryRunnable(root, "home", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(20, 30, 00)),
				LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(20, 30, 00)));
		ApfsDirectoryRunnable code = new ApfsDirectoryRunnable(home, "code", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 31, 00)),
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 31, 00)));

		ApfsFileRunnable a = new ApfsFileRunnable(applications, "a", 2048, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 01, 00)),
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 01, 00)));
		ApfsFileRunnable b = new ApfsFileRunnable(applications, "b", 1024, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 10, 00)),
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(13, 10, 00)));

		ApfsFileRunnable c = new ApfsFileRunnable(home, "c", 2048, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(21, 32, 45)),
				LocalDateTime.of(LocalDate.of(2019, 10, 15), LocalTime.of(21, 32, 45)));
		ApfsFileRunnable d = new ApfsFileRunnable(home, "d", 4096, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(21, 45, 18)),
				LocalDateTime.of(LocalDate.of(2019, 9, 15), LocalTime.of(21, 45, 18)));

		ApfsFileRunnable e = new ApfsFileRunnable(code, "e", 1024, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 40, 59)),
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 40, 59)));
		ApfsFileRunnable f = new ApfsFileRunnable(code, "f", 2048, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 45, 51)),
				LocalDateTime.of(LocalDate.of(2019, 11, 15), LocalTime.of(20, 45, 51)));
		// symbolic Link
		ApfsLinkRunnable x = new ApfsLinkRunnable(home, "x", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 11, 17), LocalTime.of(20, 46, 00)),
				LocalDateTime.of(LocalDate.of(2019, 11, 17), LocalTime.of(20, 46, 00)), applications);

		ApfsLinkRunnable y = new ApfsLinkRunnable(code, "y", 0, "Chau Ngo",
				LocalDateTime.of(LocalDate.of(2019, 9, 16), LocalTime.of(20, 39, 00)),
				LocalDateTime.of(LocalDate.of(2019, 9, 16), LocalTime.of(20, 39, 00)), b);
		
		applications.appendChild(a);
		applications.appendChild(b);

		root.appendChild(applications);
		root.appendChild(home);

		home.appendChild(code);
		home.appendChild(c);
		home.appendChild(d);
		home.appendChild(x);

		code.appendChild(e);
		code.appendChild(f);
		code.appendChild(y);
		
		//multi-threaded
		
		Thread thread1 = new Thread(root);
		Thread thread2 = new Thread(home);
		Thread thread3 = new Thread(a);
		Thread thread4 = new Thread(x);
		Thread thread5 = new Thread(applications);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		
		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
		}catch(InterruptedException ex) {
			ex.getMessage();
		}
		
		

	}

}
