package Projact;

import java.util.Stack;

/**
 * CallStack - คลาสสำหรับจัดการสแตก (Stack)
 * ใช้ Stack Data Structure - Last In First Out (LIFO)
 * คนที่เข้าเข้ามาหลัง จะได้ออกมาเป็นคนแรก (เหมือนซ้อนจาน)
 * ใช้เพื่อเก็บประวัติการเรียกคิวไว้ สำหรับฟังก์ชัน Undo
 */
class CallStack {
    // ทำงานกับ: push (บรรทัด 18), pop (บรรทัด 29), display (บรรทัด 39)
    Stack<Student> stack = new Stack<>();  // ใช้ Stack จาก Java (เก็บประวัติการเรียกล่าสุด)

    /**
     * push - เพิ่มนักเรียนเข้าไปในสแตก (ที่ด้านบน)
     * LIFO: ผู้ที่เข้าหลังสุด จะออกเป็นคนแรก
     * Time Complexity: O(1)
     */
    public void push(Student s) {
        stack.push(s);  // เพิ่มนักเรียนลง Stack
    }

    /**
     * pop - ดึงนักเรียนออกจากด้านบนของสแตก
     * คำสั่ง pop() จะลบและคืนค่า element สุดท้ายที่เพิ่มเข้ามา
     * @return นักเรียนที่ถูกลบออก, null ถ้า Stack ว่าง
     * Time Complexity: O(1)
     */
    public Student pop() {
        if (!stack.isEmpty()) {
            return stack.pop();  // ลบและคืนค่าจากด้านบน
        }
        return null;  // ถ้า Stack ว่าง คืนค่า null
    }

    /**
     * display - แสดงสแตกทั้งหมด
     * วนลูปและพิมพ์ข้อมูลนักเรียนแต่ละคน
     */
    public void display() {
        for (Student s : stack) {  // วนลูปผ่านทุก element ใน Stack
            System.out.println(s);  // พิมพ์ข้อมูลนักเรียน
        }
    }
}