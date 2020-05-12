package com.example.demo.controller;

import com.example.demo.pojo.Course;
import com.example.demo.pojo.Plant;
import com.example.demo.respone.Response;
import com.example.demo.service.PlantService;
import com.example.demo.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlantController {

    @Autowired
    PlantService plantService;


    @CrossOrigin
    @GetMapping("/api/findall/plant/")
    public List<Plant> getPlantList() {
        return plantService.getAllPlant();
    }

    @CrossOrigin
    @PostMapping("/api/update/plant/{id}")
    public Response updateCourseInfo(@RequestBody Plant requestPlant, @PathVariable("id") int id) {
        Plant plant = plantService.findById(id);
        plant.setName(requestPlant.getName());
        plant.setContentMd(requestPlant.getContentMd());
        plant.setContentHtml(requestPlant.getContentHtml());
        plant.setDescpiprion(requestPlant.getDescpiprion());
        plant.setPic(requestPlant.getPic());
//        plant.setDescpiprion(StrUtils.getMsg(requestPlant.getContentMd()));
        plantService.updatePlant(plant);
        return new Response(200, "成功", null);
    }

    @CrossOrigin
    @PostMapping("/api/add/plant/")
    public Response addNote(@RequestBody Plant requestPlant) {
        Plant plant = new Plant();
        plant.setName(requestPlant.getName());
        plant.setContentMd(requestPlant.getContentMd());
        plant.setContentHtml(requestPlant.getContentHtml());
        plant.setDescpiprion(requestPlant.getDescpiprion());
        plantService.updatePlant(plant);
        return new Response(200, "成功", null);
    }

    @CrossOrigin
    @GetMapping("/api/delete/plant/{id}")
    public Response deletePlant(@PathVariable("id") int id) {
        plantService.deletePlant(id);
        return new Response(200, "成功", null);
    }
}
