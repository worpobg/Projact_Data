package Projact;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Service - คลาสสำหรับจัดการคิว (Queue)
 * ใช้ Queue Data Structure - First In First Out (FIFO)
 * ผู้ที่เข้าคิวเป็นคนแรก จะได้เรียกเป็นคนแรก
 * ใช้ LinkedList ของ Java ซึ่งเป็น Doubly Linked List
 */
class Service {
    // ทำงานกับ: enqueue (บรรทัด 18), dequeue (บรรทัด 26), isEmpty (บรรทัด 42), size (บรรทัด 49), 
    // display (บรรทัด 33), getQueuePosition (บรรทัด 56)
    Queue<Student> queue = new LinkedList<>();  // ใช้ LinkedList เพื่อจัดการ Queue (เก็บรายชื่อคนในคิว)

    /**
     * enqueue - เพิ่มนักเรียนเข้าท้ายคิว (Queue)
     * FIFO: ผู้ที่เข้าท้ายคิวเป็นคนแรก
     * Time Complexity: O(1)
     */
    public void enqueue(Student s) {
        queue.add(s);  // เพิ่มเข้า Queue จากด้านหลัง
    }

    /**
     * dequeue - ดึงนักเรียนออกจากหน้าคิว
     * คำสั่ง poll() จะลบและคืนค่าขอบหน้าของ Queue
     * Time Complexity: O(1)
     */
    public Student dequeue() {
        return queue.poll();  // ลบและคืนค่าคนแรกในคิว
    }

    /**
     * display - แสดงรายชื่อในคิวทั้งหมด
     * แสดงตำแหน่งลำดับคิว ID และชื่อของแต่ละคน
     */
    public void display() {
        if (queue.isEmpty()) {
            System.out.println("There is no queue.");  // ถ้าคิวว่าง แสดงข้อความว่าง
            return;
        }
        int position = 1;
        for (Student s : queue) {  // วนลูปผ่านทุกคนในคิว
            System.out.println("Queue: " + position + " ID: " + s.id + " Name: " + s.name);
            position++;
        }
    }

    /**
     * isEmpty - ตรวจสอบว่าคิวว่างหรือไม่
     * @return true ถ้าคิวว่าง, false ถ้ามีคนในคิว
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * size - ดึงจำนวนคนที่อยู่ในคิว
     * @return จำนวนนักเรียนในคิว
     */
    public int size() {
        return queue.size();
    }

    /**
     * getQueuePosition - หาตำแหน่งคิวของนักเรียนตามรหัส
     * วนลูปผ่านคิวจนหาคนที่มี ID ตรงกัน
     * @param student - นักเรียนที่ต้องการหา
     * @return ตำแหน่งในคิว, -1 ถ้าไม่พบ
     */
    public int getQueuePosition(Student student) {
        int position = 1;
        for (Student s : queue) {
            if (s.id == student.id) {
                return position;  // คืนตำแหน่งเมื่อพบ
            }
            position++;
        }
        return -1;  // ไม่พบในคิว
    }
}