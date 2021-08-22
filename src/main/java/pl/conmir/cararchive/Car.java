package pl.conmir.cararchive;

/*
    TODO:
        -registration number DONE
        -make - DONE
        -model - DONE
        -production year - DONE
        -original file

 */


import pl.conmir.cararchive.car.Make;
import pl.conmir.cararchive.car.Model;
import pl.conmir.cararchive.car.ProductionYear;
import pl.conmir.cararchive.car.RegistrationNumber;

public class Car {

    private RegistrationNumber registration;

    private Model model;

    private Make make;

    private ProductionYear year;

    private OriginalModificationFile originalFile;
}
