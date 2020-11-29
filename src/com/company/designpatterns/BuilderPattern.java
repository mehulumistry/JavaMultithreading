package com.company.designpatterns;



interface ICarBuilder {
    ICarBuilder setTransmissionType(String type);
    ICarBuilder setNumberOfSeats(int seats);
    ICarBuilder setNoOfTires(int tires);
}

class CarBuilder implements ICarBuilder {

    private String transmissionType;
    private int numberOfSeats;
    private int numberOfTires;

    @Override
    public ICarBuilder setTransmissionType(String type) {
        return null;
    }

    @Override
    public ICarBuilder setNumberOfSeats(int seats) {
        return null;
    }

    @Override
    public ICarBuilder setNoOfTires(int tires) {
        return null;
    }
}



class BuilderPattern {

    public static void main(String[] args) {
        CarBuilder cb = new CarBuilder();

        cb.setNoOfTires(5)
                .setNumberOfSeats(5)
                .setTransmissionType("AUTO");
    }
}