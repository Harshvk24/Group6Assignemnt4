// src/main/java/cv/CV.java
package cv;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class CV {
    private final String name;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String experience;
    private final List<String> skills;
    private final String education;
    private final String jobTitle;
    private final String uuid; // unique internal ID

    public CV(String name, String email, String phoneNumber, String address, String experience, List<String> skills, String education, String jobTitle) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.experience = experience;
        this.skills = skills;
        this.education = education;
        this.jobTitle = jobTitle;
        this.uuid = UUID.randomUUID().toString();
    }

    public String getHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String input = uuid + name + email + phoneNumber + address + experience + education + jobTitle + skills.toString();
            byte[] digest = md.digest(input.toLowerCase().getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber +
                ", Address: " + address + ", Experience: " + experience +
                ", Skills: " + skills + ", Education: " + education + ", Job Title: " + jobTitle;
    }
}
