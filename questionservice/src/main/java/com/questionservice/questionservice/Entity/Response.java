package com.questionservice.questionservice.Entity;

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