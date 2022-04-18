package teksystems.casestudy.formbean;

import javax.validation.constraints.NotBlank;

public class ClinicianSearchFormbean {

    @NotBlank(message="Name or language is required")
    private String searchEntry;

    private String searchType;
}
