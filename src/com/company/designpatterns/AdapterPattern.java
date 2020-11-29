package com.company.designpatterns;

class AdapterPattern {

}


class RoundHole {

    public int radius;
    public final int gap = 2;

    RoundHole(int r) {
        this.radius = r + gap;
    }
}

class RoundPag {
    public int radius;

    RoundPag() {}
    RoundPag(int r) {
        this.radius = r;
    }

    public int getRadius() {
        return radius;
    }
}

class SquarePag {

    public int width;

    SquarePag(int x) {
        this.width = x;
    }
}

class SquarePagRoundAdapter extends RoundPag {

    SquarePag squarePag;

    SquarePagRoundAdapter(SquarePag squarePag) {
        this.squarePag = squarePag;
    }

    @Override
    public int getRadius() {

        // do the calculation and convert square to circle
        return super.getRadius();
    }
}
