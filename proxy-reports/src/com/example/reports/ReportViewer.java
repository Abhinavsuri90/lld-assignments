package com.example.reports;

/**
 * Client — depends only on the Report interface, unaware of proxy vs real.
 */
public class ReportViewer {

    public void open(Report report, User user) {
        report.display(user);
    }
}
