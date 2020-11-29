package com.company.designpatterns;



// Based on the type you create the instance

class FactoryPattern {

    public static void main(String[] args) {
        var factory = new ShapeFactory();
        factory.getShape("CIRCLE");
    }

}

class ShapeFactory {

    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();

        } else if(shapeType.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();

        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }

        return null;
    }
}


class Square implements Shape {
    @Override
    public void draw() {}
}
class Rectangle implements Shape {
    @Override
    public void draw() {}
}
class Circle implements Shape {
    @Override
    public void draw() {}
}
interface Shape {
    void draw();
}

