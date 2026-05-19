package com.vut.calculator;

/**
 * VUT Student Grade Calculator
 * 
 * This class calculates student grades based on semester marks and exam marks.
 * 
 * ===== GRADING RULES (as per VUT policy) =====
 * Final Mark = (Semester Mark * 0.4) + (Exam Mark * 0.6)
 * 
 * Grade Boundaries:
 *   80-100  -> Distinction
 *   70-79   -> Merit
 *   60-69   -> Credit
 *   50-59   -> Pass
 *   0-49    -> Fail
 * 
 * A student needs a semester mark of at least 40 to gain exam admission.
 * ==============================================
 */
public class GradeCalculator {

    // Final mark calculation
    public double calculateFinalMark(double semesterMark, double examMark) {
        double finalMark = (semesterMark * 0.4) + (examMark * 0.6);
        return Math.round(finalMark * 100.0) / 100.0;
    }

    // Grade determination
    public String determineGrade(double finalMark) {
        if (finalMark >= 80) {
            return "Distinction";
        } else if (finalMark >= 70) {
            return "Merit";
        } else if (finalMark >= 60) {
            return "Credit";
        } else if (finalMark >= 50) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    // Exam admission
    public boolean hasExamAdmission(double semesterMark) {
        return semesterMark >= 40;
    }

    // Class average
    public double calculateClassAverage(double[] marks) {
        if (marks == null || marks.length == 0) {
            return 0.0;
        }

        double total = 0;

        for (double mark : marks) {
            total += mark;
        }

        return Math.round((total / marks.length) * 100.0) / 100.0;
    }

    // Pass rate
    public double calculatePassRate(double[] finalMarks) {
        if (finalMarks == null || finalMarks.length == 0) {
            return 0.0;
        }

        int passCount = 0;

        for (double mark : finalMarks) {
            if (mark >= 50) {
                passCount++;
            }
        }

        return Math.round(((double) passCount / finalMarks.length) * 100.0) / 100.0;
    }

    // Highest mark
    public double findHighestMark(double[] marks) {
        if (marks == null || marks.length == 0) {
            return 0.0;
        }

        double highest = marks[0];

        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > highest) {
                highest = marks[i];
            }
        }

        return highest;
    }

    // Supplementary eligibility
    public boolean qualifiesForSupplementary(double finalMark) {
        return finalMark >= 45 && finalMark <= 49;
    }

    // Mark validation
    public boolean isValidMark(double mark) {
        return mark >= 0 && mark <= 100;
    }

    // Student report
    public String generateStudentReport(String studentName, double semesterMark, double examMark) {

        StringBuilder report = new StringBuilder();

        report.append("=== Student Report ===\n");
        report.append("Name: ").append(studentName).append("\n");
        report.append("Semester Mark: ").append(semesterMark).append("\n");

        if (hasExamAdmission(semesterMark)) {

            report.append("Exam Admission: ADMITTED\n");
            report.append("Exam Mark: ").append(examMark).append("\n");

            double finalMark = calculateFinalMark(semesterMark, examMark);

            report.append("Final Mark: ").append(finalMark).append("\n");
            report.append("Grade: ").append(determineGrade(finalMark)).append("\n");

        } else {

            report.append("Exam Admission: DENIED\n");
            report.append("Status: Student did not meet minimum semester mark requirement.\n");
        }

        report.append("======================\n");

        return report.toString();
    }
}