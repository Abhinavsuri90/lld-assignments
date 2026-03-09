package com.example.reports;

/**
 * Proxy — sits in front of RealReport and handles:
 *   1. Access control  — denies unauthorized users before any loading happens
 *   2. Lazy loading    — RealReport is only created when a permitted user first calls display()
 *   3. Caching         — once loaded, the same RealReport is reused for every subsequent call
 */
public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    // null until a permitted user requests the report for the first time
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("[ACCESS DENIED] " + user.getName()
                    + " (" + user.getRole() + ") cannot view "
                    + classification + " report: " + title);
            return;
        }

        // lazy load — create RealReport only on first authorised access
        if (realReport == null) {
            System.out.println("[proxy] first access — loading real report...");
            realReport = new RealReport(reportId, title, classification);
        } else {
            System.out.println("[proxy] returning cached report (no reload)");
        }

        realReport.display(user);
    }
}
