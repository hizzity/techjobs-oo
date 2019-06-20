package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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

    @RequestMapping(value = "add/{id}", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute Job newJob, @Valid JobForm jobForm,
                      Errors errors, @PathVariable int id) {
// I added @ModelAttribute so that Spring Boot will create a new Job object when it gets the
// the POST request from /add

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.

        //validate
        if (errors.hasErrors()) {
            return "new-job";
        } else {
            //create new Job object (did that with @ModelAttribute)
            //and add it to the data layer by calling jobData.add(newJob)
            //display the new job with job-detail
            jobData.add(newJob);

            model.addAttribute("title", "Job Listing");
            model.addAttribute("someJob", newJob);

            return "job-detail";
        }

    }

}
