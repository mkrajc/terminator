package org.mech.terminator.geometry;

public class QCircle extends Circle {
    private Double angle;

    public QCircle(final Position center, final int radius, final double angle) {
        super(center, radius);

        if (angle > 360.0) {
            throw new IllegalArgumentException("Radian can be defined up to 2PI");
        }
        this.angle = angle;

        recount();
    }

    @Override
    protected void recount() {
        if (angle != null) {
            super.recount();
        }
    }

    @Override
    protected boolean positionBelongInCircle(final int x, final int y) {
        final boolean positionBelongInCircle = super.positionBelongInCircle(x, y);

        if (positionBelongInCircle) {
            final double value = getCircleValue(x, y);
            final double left = normalizeDegree(angle + 45.0);
            final double right = normalizeDegree(angle - 45.0);

            if (left < right) {
                return value <= left || value >= right;
            } else {
                return value <= left && value >= right;
            }

        }

        return positionBelongInCircle;
    }

    public static void main(final String[] args) {
        final QCircle circle = new QCircle(Position.at(0, 0), 2, 0);

        System.out.println(circle.getCircleValue(1.0, 1.0));
        System.out.println(circle.getCircleValue(-1.0, 1.0));
        System.out.println(circle.getCircleValue(-1.0, -1.0));
        System.out.println(circle.getCircleValue(1.0, -1.0));

    }

    private double getCircleValue(final double x, final double y) {
        return normalizeDegree(Math.toDegrees(Math.atan2(y - getCenter().y, x - getCenter().x)));
    }

    private double normalizeDegree(final double degree) {
        return (degree + 360.0) % 360.0;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(final Double angle) {
        this.angle = angle;
        recount();
    }

}
