package Projact;

import java.util.Scanner;

/**
 * Main - โปรแกรมหลักสำหรับระบบเรียกคิวสโมสร (Smart Campus Queue System)
 * 
 * ใช้โครงสร้างข้อมูลต่างๆ:
 * - StudentList: Singly Linked List (SLL) - เก็บรายชื่อนักเรียนทั้งหมด
 * - Service: Queue (Linked List) - เก็บคิวของนักเรียนที่รอเรียก (FIFO)
 * - CallStack: Stack (LIFO) - เก็บประวัติการเรียกไว้สำหรับ Undo
 * - ServiceHis: Doubly Linked List (DLL) - เก็บประวัติการเรียกแบบสองทาง
 * - Finder: ArrayList Circular Queue - จัดการเคาท์เตอร์หมุนเวียน
 * 
 * ฟังก์ชันหลัก: เพิ่มนักเรียน, เรียกคิว, แสดงคิว, ย้อนกลับ (Undo), ค้นหา
 */
public class Main {
    /**
     * findStudentById - ค้นหานักเรียนตาม ID (Iterative)
     * วนลูปผ่าน StudentList (Singly Linked List) จนหานักเรียนที่มี ID ตรงกัน
     * @param list - StudentList ที่เก็บรายชื่อนักเรียน
     * @param id - รหัส ID ของนักเรียนที่ต้องการค้นหา
     * @return Student ที่พบ, null ถ้าไม่พบ
     * Time Complexity: O(n)
     */
    public static Student findStudentById(StudentList list, int id) {
        StudentList.Node cur = list.head;
        while (cur != null) {  // วนลูปผ่าน Linked List
            if (cur.data.id == id) {
                return cur.data;  // เจอ ID ตรงกัน คืนค่านักเรียน
            }
            cur = cur.next;  // เลื่อนไปยัง node ถัดไป
        }
        return null;  // ไม่พบ
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            // ประกาศตัวแปรเก็บโครงสร้างข้อมูลต่างๆ
            // ทำงานกับ: case 1 (บรรทัด 68), case 7 (บรรทัด 150)
            StudentList studentList = new StudentList();  // SLL - เก็บรายชื่อทั้งหมด
            
            // ทำงานกับ: case 1 (บรรทัด 73), case 2 (บรรทัด 82-89), case 3 (บรรทัด 94), case 5 (บรรทัด 116-122)
            Service queue = new Service();               // Queue - คิวรอเรียก (FIFO)
            
            // ทำงานกับ: case 2 (บรรทัด 91), case 5 (บรรทัด 114)
            CallStack stack = new CallStack();           // Stack - ประวัติเรียก (LIFO)
            
            // ทำงานกับ: case 2 (บรรทัด 92), case 4 (บรรทัด 101)
            ServiceHis history = new ServiceHis();       // DLL - ประวัติสองทาง
            
            // ทำงานกับ: __init__ (บรรทัด 55-57), case 2 (บรรทัด 87), case 6 (บรรทัด 124)
            Finder counters = new Finder();              // ArrayList - เคาท์เตอร์

            // เพิ่มเคาท์เตอร์เข้าระบบ
            counters.addCounter("Counter A");
            counters.addCounter("Counter B");
            counters.addCounter("Counter C");

            // เมนูโปรแกรม
            while (true) {
                System.out.println("\n=== SMART CAMPUS SYSTEM ===");
                System.out.println("1. Add Student and Take Queue");
                System.out.println("2. Call Next");
                System.out.println("3. Show Queue");
                System.out.println("4. Show History");
                System.out.println("5. Undo Last Call");
                System.out.println("6. Show Counters");
                System.out.println("7. Search Student (Recursive)");
                System.out.println("0. Exit");

                try {
                    int choice = sc.nextInt();

                    switch (choice) {
                        /**
                         * ตัวเลือก 1: เพิ่มนักเรียนและจัดคิว
                         * เพิ่มนักเรียนใหม่เข้า StudentList (SLL)
                         * เพิ่มนักเรียนลงคิวใน Service (Queue)
                         */
                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        Student newStudent = new Student(id, name);
                        studentList.add(newStudent);  // เพิ่มเข้า SLL
                        queue.enqueue(newStudent);   // เพิ่มลงคิว (ด้านท้าย)
                        System.out.println("Added student and taken queue: " + newStudent);
                        System.out.println("Queue number: " + queue.size());
                        break;
                    
                        /**
                         * ตัวเลือก 2: เรียกคนถัดไปจากคิว
                         * ใช้ dequeue ดึงจากหัวคิว (FIFO)
                         * เพิ่มลง CallStack (LIFO) สำหรับ Undo
                         * บันทึกประวัติในประวัติการเรียก (DLL)
                         */
                    case 2:
                        if (!queue.isEmpty()) {
                            Student student = queue.dequeue(); // ✅ ดึงคนแรก (FIFO)

                            String counter = counters.nextCounter();  // หมุนเสี่ยมเคาท์เตอร์
                            System.out.println("Calling: " + student);
                            System.out.println("Go to Counter: " + counter);

                            stack.push(student);  // เก็บไว้สำหรับ Undo (LIFO)
                            history.add(student, counter);  // บันทึกประวัติ (DLL)
                        } else {
                            System.out.println("Queue empty");
                        }
                        break;
                    
                        /**
                         * ตัวเลือก 3: แสดงคิวทั้งหมด
                         * แสดงรายชื่อและตำแหน่งในคิว
                         */
                    case 3:
                        queue.display();  // แสดงคิว (Queue)
                        break;

                        /**
                         * ตัวเลือก 4: แสดงประวัติการเรียก
                         * แสดงจากเก่าไปใหม่ (หางสอง DLL)
                         */
                    case 4:
                        history.displayForward();  // แสดงประวัติไปข้างหน้า (DLL)
                        break;

                        /**
                         * ตัวเลือก 5: ย้อนกลับการเรียกล่าสุด (Undo)
                         * ดึงจาก CallStack (LIFO)
                         * เพิ่มกลับเข้าคิว
                         */
                    case 5:
                        Student undo = stack.pop();  // ดึงคนสุดท้ายจาก Stack (LIFO)
                        if (undo != null) {
                            System.out.println("Undo: " + undo);
                            queue.enqueue(undo);  // เพิ่มกลับเข้าคิว
                        } else {
                            System.out.println("There is no previous queue.");
                        }
                        break;

                        /**
                         * ตัวเลือก 6: แสดงเคาท์เตอร์ที่มี
                         */
                    case 6:
                        counters.display(6);  // แสดงเคาท์เตอร์
                        break;

                        /**
                         * ตัวเลือก 7: ค้นหานักเรียนตาม ID
                         * Iterative search ใน StudentList (SLL)
                         * แสดงถ้าอยู่ในคิวหรือประวัติการเรียก
                         */
                    case 7:
                        System.out.print("Search ID: ");
                        int sid = sc.nextInt();
                        Student foundStudent = findStudentById(studentList, sid);  // ค้นหา
                        if (foundStudent != null) {
                            System.out.println("ID: " + foundStudent.id + " Name: " + foundStudent.name);
                            // ตรวจสอบถ้าอยู่ในคิว
                            int queuePos = queue.getQueuePosition(foundStudent);
                            if (queuePos > 0) {
                                System.out.println("Get the queue at: " + queuePos);
                            } else {
                                // ตรวจสอบประวัติการเรียก
                                String counter = history.getHistoryCounter(foundStudent);
                                if (counter != null) {
                                    System.out.println("Get that counter.: " + counter);
                                } else {
                                    System.out.println("Haven't called in the queue yet.");
                                }
                            }
                        } else {
                            System.out.println("Student not found");
                        }
                        break;

                        /**
                         * ตัวเลือก 0: ออกจากโปรแกรม
                         */
                    case 0:
                        System.out.println("goodbey");
                        return;
                }
                } catch (Exception e) {
                    System.out.println("Please enter the correct information.");
                    sc.nextLine();
                }
            }
        }
    }
}