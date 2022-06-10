package com.orchestrator.resources;

import com.orchestrator.resources.dto.Dto;
import com.orchestrator.resources.dto.RequestDto;
import com.orchestrator.resources.interfaces.Client1;
import com.orchestrator.resources.interfaces.Client2;
import com.orchestrator.resources.interfaces.Client3;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/redirect")
public class RedirectResource {

    private final Client1 client1;
    private final Client2 client2;
    private final Client3 client3;

    public RedirectResource(Client1 client1, Client2 client2, Client3 client3) {
        this.client1 = client1;
        this.client2 = client2;
        this.client3 = client3;
    }

    @PostMapping
    public List<Integer> redirect(@RequestBody Dto dto) throws InterruptedException {

        int count = dto.array1().size() / 3;

        var request1 = new RequestDto(dto.array1().subList(0, count), dto.array2().subList(0, count));
        var request2 = new RequestDto(dto.array1().subList(count, count + count), dto.array2().subList(count, count + count));
        var request3 = new RequestDto(dto.array1().subList(count + count, dto.array1().size()), dto.array2().subList(count + count, dto.array2().size()));

        final List<Integer> list1 = new ArrayList<>();
        final List<Integer> list2 = new ArrayList<>();
        final List<Integer> list3 = new ArrayList<>();

        Thread thread1 = new Thread(() -> {
            var response = client1.send(request1);
            list1.addAll(response);
        });

        thread1.start();

        Thread thread2 = new Thread(() -> {
            var response = client2.send(request2);
            list2.addAll(response);
        } );

        thread2.start();

        Thread thread3 = new Thread(() -> {
            var response = client3.send(request3);
            list3.addAll(response);
        } );

        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        List<Integer> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);

        return list;
    }

}

