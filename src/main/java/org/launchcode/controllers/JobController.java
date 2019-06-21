package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view

        Job someJob = jobData.findById(id); //straight out of instructions
            model.addAttribute("someJob", someJob);
            model.addAttribute("title", ("Job Number " + id));
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm,
                      Errors errors) {
// I added @ModelAttribute so that Spring Boot will create a new Job object when it gets the
// the POST request from /add

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.




        //validate
        if (errors.hasErrors()) {
            return "new-job";
        } else {
            //create new Job object (did that with @ModelAttribute);
            //and add it to the data layer by calling jobData.add(newJob)
            //display the new job with job-detail
            Employer employer = jobData.getEmployers().findById(jobForm.getEmployerId());
            Location location = jobData.getLocations().findById(jobForm.getLocation());
            CoreCompetency coreCompetency = jobData.getCoreCompetencies().findById(jobForm.getCoreCompetency());
            PositionType positionType = jobData.getPositionTypes().findById(jobForm.getPositionType());

            Job newJob = new Job(jobForm.getName(), employer, location,
                    positionType, coreCompetency);

            jobData.add(newJob);


            model.addAttribute("title", "Job Listing");
            model.addAttribute("someJob", newJob);


            return "redirect:?id="+newJob.getId(); //passes the id to the address bar ..job/?id=
        }                                           //redirect takes you to base path, /job

    }

}
