package org.launchcode.models.forms;

import org.launchcode.models.*;
import org.launchcode.models.data.JobData;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
public class JobForm {

    @NotNull
    @Size(min=1, message = "Name may not be empty")
    private String name;

    @NotNull
    private int employerId;

    /*
        TODO #3 - Included other fields needed to create a job,
        with correct validation attributes and display names.
        Don't forget to add getters and setters
        For tasks #3-5, you'll need to mimic the code that's in place for
        employerId and the employers list for the other job field types.
     */

    private static int nextEmployerId = 1;

    public JobForm() {                             //put this under the first JobForm() since no info in being passed in with either?
        employerId = nextEmployerId;
        nextEmployerId++;
    }

    public JobForm(String name) {
        this();                //what does this(); do ???????????????? calls on JobForm()
        // from notes: this(); has to be at top, calls default constructor for given class (JobForm())
        this.name = name;
    }

    public boolean contains(String name) {
        return this.name.toLowerCase().contains(name.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JobForm jobField = (JobForm) o;

        return employerId == jobField.getEmployerId();
    }

    private ArrayList<Employer> employers;
    private ArrayList<Location> locations;
    private ArrayList<CoreCompetency> coreCompetencies;
    private ArrayList<PositionType> positionTypes;

    public JobForm(ArrayList<Job> job) {  //ArrayList<Job> wasn't given, JobForm() was originally empty

        JobData jobData = JobData.getInstance();

        /*
            TODO #4 - populate the other ArrayList collections needed in the view
        */

        employers = jobData.getEmployers().findAll();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployerId() {
        return employerId;
    }

    private void setEmployerId(int employerId) {                    //I changed this to private, correct?
        this.employerId = employerId;
    }

    public ArrayList<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(ArrayList<Employer> employers) {
        this.employers = employers;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<CoreCompetency> getCoreCompetencies() {
        return coreCompetencies;
    }

    public void setCoreCompetencies(ArrayList<CoreCompetency> coreCompetencies) {
        this.coreCompetencies = coreCompetencies;
    }

    public ArrayList<PositionType> getPositionTypes() {
        return positionTypes;
    }

    public void setPositionTypes(ArrayList<PositionType> positionTypes) {
        this.positionTypes = positionTypes;
    }
}
