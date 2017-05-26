package com.woodpecker.util;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * 经纬度模型类
 *
 * @author Relaxier
 * @since 2017年05月26日
 */
public class LatLng implements Cloneable, Serializable {
    private static final DecimalFormat format = new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.US));
    private static final long serialVersionUID = 7733596940641620353L;
    // 纬度
    private double latitude;
    // 经度
    private double longitude;

    /**
     * 构造器
     */
    public LatLng(double paramDouble1, double paramDouble2) {
        this(paramDouble1, paramDouble2, true);
    }

    private LatLng(double paramDouble1, double paramDouble2, boolean paramBoolean) {
        if (paramBoolean) {
            if ((-180.0D <= paramDouble2) && (paramDouble2 < 180.0D)) {
                this.longitude = format(paramDouble2);
            } else {
                this.longitude = format(((paramDouble2 - 180.0D) % 360.0D + 360.0D) % 360.0D - 180.0D);
            }
            this.latitude = format(Math.max(-90.0D, Math.min(90.0D, paramDouble1)));
        } else {
            this.latitude = paramDouble1;
            this.longitude = paramDouble2;
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    private static double format(double paramDouble) {
        return Double.parseDouble(format.format(paramDouble));
    }

    @Override
    public LatLng clone() {
        return new LatLng(this.latitude, this.longitude);
    }

    @Override
    public int hashCode() {
        int i = 31;
        int j = 1;
        long l = Double.doubleToLongBits(this.latitude);
        j = i * j + (int) (l ^ l >>> 32);
        l = Double.doubleToLongBits(this.longitude);
        j = i * j + (int) (l ^ l >>> 32);
        return j;
    }

    @Override
    public boolean equals(Object paramObject) {
        if (this == paramObject) {
            return true;
        }
        if (!(paramObject instanceof LatLng)) {
            return false;
        }
        LatLng localLatLng = (LatLng) paramObject;

        return Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(localLatLng.longitude)
                && Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(localLatLng.latitude);
    }

    @Override
    public String toString() {
        return "lat/lng: (" + this.latitude + "," + this.longitude + ")";
    }

}
