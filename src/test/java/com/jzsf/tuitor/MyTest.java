package com.jzsf.tuitor;

import com.jzsf.tuitor.dao.RegisterRecordDao;
import com.jzsf.tuitor.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author by plain yuan
 * @since 2020/04/12
 */
@SpringBootTest
public class MyTest {
    @Autowired
    RegisterRecordDao registerRecordDao;
    @Autowired
    UserService userService;

    public static void main(String[] args) {
        List<Integer> arr1 = new ArrayList<>(Arrays.asList(1, 3, 44, 55, 66, 77));
        List<Integer> arr2 = new ArrayList<>(Arrays.asList(2, 4, 6, 8));
        System.out.println(merge(arr1, arr2));
    }

    public static List<Integer> merge(List<Integer> arr1, List<Integer> arr2) {
        int i = 0, j = 0;
        List<Integer> arr3 = new ArrayList<>();
        while (true) {
            if (arr1.get(i) <= arr2.get(j)) {
                arr3.add(arr1.get(i++));
            } else {
                arr3.add(arr2.get(j++));
            }
            if (i == arr1.size()) {
                arr3.addAll(arr2.subList(j, arr2.size()));
                break;
            }
            if (j == arr2.size()) {
                arr3.addAll(arr1.subList(j, arr1.size()));
                break;
            }
        }
        return arr3;
    }

    @Test
    public void quickTest() throws Exception {
        System.out.println(userService == null);
        System.out.println(userService.findAll());
        //String[] books = {"乱世佳人", "Java 编程思想", "JVM 虚拟机", null};
        //int sizePlus = books.length + 1;
        //LinkedList<String> formOneThrowException = new LinkedList<>(Arrays.asList(books));
        //LinkedList<String> formTwoReturnCode = new LinkedList<>(Arrays.asList(books));
        //
        //try {
        //    for (int i = 0; i < sizePlus; i++) {
        //        System.out.println(formOneThrowException.removeFirst());
        //    }
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        //
        //Thread.sleep(1000);
        //
        //for (int i = 0; i < sizePlus; i++) {
        //    System.out.println(formTwoReturnCode.poll());
        //}
        //
        //BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
        //System.out.println(TimeUnit.SECONDS);
        //ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(1);
        //System.out.println(blockingQueue.remainingCapacity() + " - " +arrayBlockingQueue.remainingCapacity());


    }


}
