package stream.stepic.filtering_and_skipping;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class OnlineJobSystem {

    /**
     * You have been asked to develop a system for finding Java developer jobs.
     * The candidate enters a request and gets the number of suitable vacancies.
     */
    public static long calculateNumberOfVacancies(ApplicantRequest request, Collection<JobDescription> jobs) {
        return jobs.stream()
                .filter(jobDescription -> jobDescription.getCompany().equals(request.getCompany()))
                .filter(jobDescription -> jobDescription.getRequiredExperienceYears() <= request.getExperienceYears())
                .count();
    }

    /**
     * In the test example, the first line represents the request from an applicant
     * and all the following lines are all available job descriptions in this system.
     * <p>
     * Google|3
     * Java Developer|Google|3
     * Senior Java Developer|Google|7
     * Super Java Developer|CryptoStartup|3
     * Junior Java Developer|Google|1
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ApplicantRequest request = Optional.of(scanner.nextLine())
                .map(inputLine -> {
                    String[] parts = inputLine.split("\\|");
                    return new ApplicantRequest(parts[0], Integer.parseInt(parts[1]));
                }).get();

        List<JobDescription> jobs = Stream.iterate(1, i -> scanner.hasNextLine(), i -> i + 1)
                .map(i -> scanner.nextLine())
                .map(inputLine -> {
                    String[] parts = inputLine.split("\\|");
                    return new JobDescription(parts[0], parts[1], Integer.parseInt(parts[2]));
                })
                .collect(Collectors.toList());

        System.out.println(calculateNumberOfVacancies(request, jobs));
    }
}

class ApplicantRequest {
    private final String company;
    private final int experienceYears;

    ApplicantRequest(String company, int experienceYears) {
        this.company = company;
        this.experienceYears = experienceYears;
    }

    public String getCompany() {
        return company;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
}

class JobDescription {
    private final String title;
    private final String company;
    private final int requiredExperienceYears;

    JobDescription(String title, String company, int requiredExperienceYears) {
        this.title = title;
        this.company = company;
        this.requiredExperienceYears = requiredExperienceYears;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public int getRequiredExperienceYears() {
        return requiredExperienceYears;
    }
}