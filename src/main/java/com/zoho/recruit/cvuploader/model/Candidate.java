package com.zoho.recruit.cvuploader.model;

/**
 * Created by burakdagli on 25.03.2018.
 */
public class Candidate {

    private String name;
    private String email;
    private String cvTitle;

    public Candidate() {
    }

    public Candidate(String name, String email, String cvTitle) {
        this.name = name;
        this.email = email;
        this.cvTitle = cvTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCvTitle() {
        return cvTitle;
    }

    public void setCvTitle(String cvTitle) {
        this.cvTitle = cvTitle;
    }

    public boolean isValid() {
        if((name != null && name.length()>0) && (email != null && email.contains("@")) && (cvTitle != null && cvTitle.length()>0) ){
            return true;
        } else {
            return  false;
        }
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cvTitle='" + cvTitle + '\'' +
                '}';
    }
}
