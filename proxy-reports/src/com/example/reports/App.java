package com.example.reports;

public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin   = new User("Kshitij", "ADMIN");

        // Proxies hold metadata only — nothing loaded yet
        Report publicReport  = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review",   "FACULTY");
        Report adminReport   = new ReportProxy("R-303", "Budget Audit",     "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===\n");

        // student opens public report — allowed, loads once
        System.out.println("-- student opens public report --");
        viewer.open(publicReport, student);
        System.out.println();

        // student tries faculty report — blocked before loading
        System.out.println("-- student tries faculty report --");
        viewer.open(facultyReport, student);
        System.out.println();

        // faculty opens faculty report — allowed, loads once
        System.out.println("-- faculty opens faculty report --");
        viewer.open(facultyReport, faculty);
        System.out.println();

        // admin opens admin report — allowed, loads once
        System.out.println("-- admin opens admin report --");
        viewer.open(adminReport, admin);
        System.out.println();

        // admin opens same report again — should use cache, no reload
        System.out.println("-- admin opens same admin report again --");
        viewer.open(adminReport, admin);
    }
}
