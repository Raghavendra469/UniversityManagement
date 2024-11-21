package com.codegnan.university.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.codegnan.university.exceptions.CourseNotFoundException;
import com.codegnan.university.exceptions.ProfessorNotFoundException;
import com.codegnan.university.exceptions.StudentNotFoundException;

public class UniversityManagement {
	
	private List<Student> students; // list to hold students
	private List<Professor> professors; // list to hold professors
	private List<Course> courses; // list to hold courses
	
	
	// constructor initilizes empty list for students, professors, courses
	public UniversityManagement() {
		students = new ArrayList<>();
		professors = new ArrayList<>();
		courses = new ArrayList<>();
	}
	
	//method to add a student
	public void addStudent(String name) {
		students.add(new Student(name));
	}
	
	//method to add a Professor
	public void addProfessor(String name) {
		professors.add(new Professor(name));
	}
	
	//method to add a Course
	public void addCourse(String name) {
		courses.add(new Course(name));
	}
	
	//method to enroll a student in course
	public void enrollStudentsInCOurse(String studentName, String courseName) 
			throws StudentNotFoundException, CourseNotFoundException {
		Student student = findStudentByName(studentName); //helper method to find studentName
		Course course = findCouseByTitle(courseName); //helper method to find course
		
		if(student == null) {
			throw new StudentNotFoundException("Student "+studentName+" not found");
		}
		if(course == null) {
			throw new CourseNotFoundException("Course "+courseName+" not found");
		}
		student.enrollInCourse(course);//enroll the student in course
	}
	
	
	public void assignCourseToProfessor(String professorName, String courseTittle)
	throws ProfessorNotFoundException, CourseNotFoundException{
		Professor professor = findProfessorByName(professorName); //helper method to find professorName
		Course course = findCouseByTitle(courseTittle); //helper method to find course
		
		if(professor == null) {
			throw new ProfessorNotFoundException("Professor "+professorName+" not found");
		}
		if(course == null) {
			throw new CourseNotFoundException("Course "+courseTittle+" not found");
		}
		professor.assignCourse(course);//assign the course to the professor
	}
	
	//method to list all students
	public void listStudents() {
		if(students.isEmpty()) {
			System.out.println("No students are avaliable");
		}
		else {
			System.out.println("List of students are");
			for(Student student : students) {
				System.out.println(student); //print each student name
			}
		}
	}
	
	//method to list all professors
	public void listProfessors() {
		if(professors.isEmpty()) {
			System.out.println("No professors are avaliable");
		}
		else {
			System.out.println("List of professors are");
			for(Professor professor : professors) {
				System.out.println(professor); //print each professor name
			}
		}
	}
	
	//method to list all Courses
	public void listCourses() {
		if(courses.isEmpty()) {
			System.out.println("No courses are avaliable");
		}
		else {
			System.out.println("List of courses are");
			for(Course course : courses) {
				System.out.println(course); //print each courses name
			}
		}
	}
	
	public void displayStudentCourses(String studentName) throws StudentNotFoundException{
		Student student = findStudentByName(studentName); //find student by name
		if(student == null) {
			throw new StudentNotFoundException("Student "+studentName+" not found");
		}
		System.out.println("Courses of Student : "+studentName +" are/is");
		for(Course course: student.getEnrolledCourses()){
			System.out.println(course);//print each enrolled course
		}
	}
	
	public void displayProfessorCourses(String professorName) throws ProfessorNotFoundException{
		Professor professor = findProfessorByName(professorName); //find professor by name
		if(professor == null) {
			throw new ProfessorNotFoundException("Student "+professorName+" not found");
		}
		System.out.println("Course Assigned for professor : "+professorName+" are/is");
		for(Course course: professor.getAssignedCourses()){
			System.out.println(course);//print each enrolled course
		}
	}
	
	//helper method of findStudentByName implementation
	public Student findStudentByName(String name) {
		for(Student student:students) {
			if(student.getName().equalsIgnoreCase(name)) {
				return student; //return student if found
			}
		}
		return null;
	}
	
	public Professor findProfessorByName(String name) {
		for(Professor professor:professors) {
			if(professor.getName().equalsIgnoreCase(name)) {
				return professor; //return professor if found
			}
		}
		return null;
	}
	
	public Course findCouseByTitle(String title) {
		for(Course course:courses) {
			if(course.getTitle().equalsIgnoreCase(title)) {
				return course; //return course if found
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UniversityManagement management = new UniversityManagement();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while(running) {
			System.out.println("||==============================================||");
			System.out.println("    University Management System   ");
			System.out.println("||==============================================||");
			System.out.println("||               1. Add Student                 ||");
			System.out.println("||               2. Add Professor               ||");
			System.out.println("||               3. Add Courses                 ||");
			System.out.println("||               4. Enroll Student in course    ||");
			System.out.println("||               5. Assign Courses to professor ||");
			System.out.println("||               6. List of Students            ||");
			System.out.println("||               7. List of professors          ||");
			System.out.println("||               8. List of Courses             ||");
			System.out.println("||               9. Display Student Courses     ||");
			System.out.println("||               10. Display professor Courses  ||");
			System.out.println("||               11. Exit                       ||");
			System.out.println("||==============================================||");
			System.out.println("Enter your option:");
			int choice = scanner.nextInt();
			scanner.nextLine();
			try {
				switch(choice) {
					case 1:
						System.out.print("Enter student Name:");
						String StudentName = scanner.nextLine();
						management.addStudent(StudentName);
						break;
					case 2:
						System.out.print("Enter professor Name:");
						String professorName = scanner.nextLine();
						management.addProfessor(professorName);
						break;
					case 3:
						System.out.print("Enter Course Title:");
						String courseTitle = scanner.nextLine();
						management.addCourse(courseTitle);
						break;
					case 4:
						System.out.print("Enter student Name:");
						String enrollStudentName = scanner.nextLine();
						System.out.print("Enter course title");
						String enrollCourse = scanner.nextLine();
						try {
							management.enrollStudentsInCOurse(enrollStudentName, enrollCourse);
						}
						catch(StudentNotFoundException | CourseNotFoundException e) {
							e.printStackTrace();
						}
						
						break;
					case 5:
						System.out.print("Enter professor Name:");
						String assignProfessor = scanner.nextLine();
						System.out.print("Enter course title");
						String assignedCourse = scanner.nextLine();
						try {
							management.assignCourseToProfessor(assignProfessor, assignedCourse);
						}
						catch(ProfessorNotFoundException | CourseNotFoundException e) {
							e.printStackTrace();
						}
						
						break;
					case 6:
//						System.out.print("Displaying List of Students");
						management.listStudents();
						break;
					case 7:
//						System.out.print("Displaying List of Professors");
						management.listProfessors();
						break;
					case 8:
//						System.out.print("Displaying List of Courses");
						management.listCourses();
						break;
					case 9:
						System.out.print("Enter student Name:");
						String displayStudentCourse = scanner.nextLine();		
						try {
							management.displayStudentCourses(displayStudentCourse);
						}
						catch(StudentNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case 10:
						System.out.print("Enter professor Name:");
						String displayProfessorCourse = scanner.nextLine();	
						try {
							management.displayProfessorCourses(displayProfessorCourse);
						}
						catch(ProfessorNotFoundException e) {
							e.printStackTrace();
						}
						break;
					case 11:
						System.out.println("You Choosed Exit option");
						System.out.println("Exitting...");
						System.out.println("Exitted.");
						running=false;
						break;
					default:
						System.out.println("Invalid option: Enter correct option");
					}
				}
			catch(Exception e) {
				e.fillInStackTrace();
			}
		}
	}

}
