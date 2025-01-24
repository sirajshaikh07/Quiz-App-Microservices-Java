package com.microservices.microservices.Quiz;

import lombok.Data;

@Data
public class Response
{

    int id;
    String response;
    Response(int id,String response)
    {
        this.id=id;
        this.response=response;
    }

}