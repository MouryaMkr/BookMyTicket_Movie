package com.example.demo.Utlity;

import com.example.demo.Model.CastRequest;
import com.example.demo.Model.StreamingDetailsRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util
{

    public List<CastRequest> Casts;
    public CastRequest avengersCast1;
    public CastRequest avengersCast2;

    public Util()
    {
        avengersCast1 = new CastRequest("actor-1","director");
        avengersCast2 = new CastRequest("actor-2","Hero");
        Casts = new ArrayList<>();
        Casts.add(avengersCast1);
        Casts.add(avengersCast2);
    }

    public List<CastRequest> getCastListUtil()
    {
        return  Casts;
    }

    public StreamingDetailsRequest getLinkUtil()
    {
        StreamingDetailsRequest link = new StreamingDetailsRequest();
        link.setLink("//https::www.mourya.com");

        return link;
    }

    public Date getDateUtil()
    {
        Date date = new Date();
        return date;
    }






}
