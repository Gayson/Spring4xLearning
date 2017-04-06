package com.gayson.domain.fb;

import com.gayson.domain.Car;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by jixunzhen on 2017/4/5.
 */
public class CarFactoryBean implements FactoryBean<Car>{
    private String carInfo;

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }

    public Car getObject() throws Exception {
        Car car=new Car();
        String[] infos=carInfo.split(",");
        car.setBrand(infos[0]);
        car.setColor(infos[1]);
        car.setMaxSpeed(Integer.parseInt(infos[2]));
        return car;
    }

    public boolean isSingleton() {
        return false;
    }

    public Class<?> getObjectType() {
        return Car.class;
    }
}
