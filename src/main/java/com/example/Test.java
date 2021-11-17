//package com.example;
//
//import com.example.controller.FactController;
//import com.example.service.OpenAPIService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//
//public class Test {
//
//    @Autowired
//    static
//    OpenAPIService openAPIService = new OpenAPIService();
//
//    public static void main(String[] args) {
//        FactController factController = new FactController();
//
//        String response = new String();
//
//        {
//            try {
//                response = factController.sayCatFact(openAPIService.getApiRequest());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println(response);
//
//
//    }
//
//}
