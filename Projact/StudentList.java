package Projact;

/**
 * StudentList - คลาสสำหรับจัดการรายชื่อนักเรียน
 * ใช้ Singly Linked List (SLL) - โครงสร้างข้อมูลรายการเชื่อมเดี่ยว
 * แต่ละ Node มี pointer ชี้ไปยัง Node ถัดไป (next)
 */
class StudentList {
    // Node - โหนดของ Singly Linked List
    class Node {
        // ทำงานกับ: add (บรรทัด 25-27), display (บรรทัด 32-33)
        Student data;    // เก็บข้อมูลนักเรียน
        
        // ทำงานกับ: add (บรรทัด 24-26), display (บรรทัด 32-35)
        Node next;       // pointer ชี้ไปยัง node ถัดไป

        Node(Student data) {
            this.data = data;
        }
    }

    // ทำงานกับ: add (บรรทัด 20-28), display (บรรทัด 31-36)
    Node head;  // ตัวชี้ไปยัง node แรก (หัวรายการ) - เชื่อมโยงรายชื่อนักเรียนทั้งหมด

    /**
     * add - เพิ่มนักเรียนเข้าท้ายรายการ
     * ใช้การวนลูปเพื่อหา node สุดท้าย แล้วเพิ่ม node ใหม่
     * Time Complexity: O(n)
     */
    public void add(Student s) {
        Node newNode = new Node(s);
        if (head == null) {
            head = newNode;  // ถ้ารายการว่าง node ใหม่เป็น head
            return;
        }
        Node cur = head;
        while (cur.next != null) {  // วนลูปจนถึง node สุดท้าย
            cur = cur.next;
        }
        cur.next = newNode;  // เชื่อม node ใหม่เข้าท้ายรายการ
    }

    /**
     * display - แสดงรายชื่อนักเรียนทั้งหมด
     * วนลูปจาก head ไปยัง tail และพิมพ์ข้อมูลแต่ละ node
     */
    public void display() {
        Node cur = head;
        while (cur != null) {  // วนลูปตราบจนี่สิ้นสุดรายการ
            System.out.println(cur.data);  // พิมพ์ข้อมูลนักเรียน
            cur = cur.next;  // เลื่อนไปยัง node ถัดไป
        }
    }
}