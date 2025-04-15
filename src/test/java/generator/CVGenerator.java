package generator;

import cv.CV;

import java.util.*;

public class CVGenerator {
    private static final List<String> SKILLS_POOL = Arrays.asList(
            "Java", "Python", "SQL", "HTML", "CSS", "JavaScript", "React", "Spring", "Docker", "Kubernetes"
    );
    private static final List<String> UNIVERSITIES = Arrays.asList(
            "MIT", "Stanford", "Harvard", "UC Berkeley", "Oxford"
    );
    private static final List<String> JOB_TITLES = Arrays.asList(
            "Software Engineer", "Data Scientist", "Backend Developer", "Frontend Developer", "DevOps Engineer"
    );
    private static final List<String> CITIES = Arrays.asList(
            "Dublin", "London", "New York", "Berlin", "Toronto"
    );

    public static CV generateRandomCV(int id) {
        Random random = new Random();
        String name = "Applicant" + id;
        String email = "applicant" + id + "@mail.com";
        String phone = "08" + (10000000 + random.nextInt(90000000));
        String address = CITIES.get(random.nextInt(CITIES.size())) + " Street " + (random.nextInt(50) + 1);
        String experience = "Experience: " + (id % 10) + " years";

        List<String> skills = new ArrayList<>();
        Collections.shuffle(SKILLS_POOL);
        for (int i = 0; i < 3 + random.nextInt(3); i++) {
            skills.add(SKILLS_POOL.get(i));
        }

        String education = UNIVERSITIES.get(random.nextInt(UNIVERSITIES.size()));
        String jobTitle = JOB_TITLES.get(random.nextInt(JOB_TITLES.size()));

        return new CV(name, email, phone, address, experience, skills, education, jobTitle);
    }
}
