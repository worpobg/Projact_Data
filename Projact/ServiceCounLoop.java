package Projact;

/**
 * ServiceCounLoop - คลาสสำหรับจัดการเคาท์เตอร์แบบ Circular Linked List
 * ใช้ Circular Singly Linked List - โครงสร้างรายการเชื่อมเดี่ยวที่วนกลับมาหัว
 * node สุดท้ายจะชี้กลับไปยัง node แรก เช่นเดียวกับวงกลม
 * แต่ละครั้งที่เรียก nextCounter() จะหมุนไปเคาท์เตอร์ถัดไป
 */
class ServiceCounLoop {
    /**
     * Node - โหนดของ Circular Singly Linked List
     * เก็บชื่อเคาท์เตอร์และ pointer ชี้ไปยัง node ถัดไป
     */
    class Node {
        // ทำงานกับ: display (บรรทัด 53)
        String counterName;  // เก็บชื่อของเคาท์เตอร์
        
        // ทำงานกับ: addCounter (บรรทัด 39-45), nextCounter (บรรทัด 49)
        Node next;           // pointer ชี้ไปยัง node ถัดไป (หรือกลับไป head ในระบบ circular)

        Node(String name) {
            this.counterName = name;
        }
    }

    // ทำงานกับ: addCounter (บรรทัด 33-44), nextCounter (บรรทัด 48), display (บรรทัด 52)
    Node head;     // node แรก (หัวรายการ) - ชี้ไปยังเคาท์เตอร์แรก
    
    // ทำงานกับ: addCounter (บรรทัด 35-36), nextCounter (บรรทัด 48-49)
    Node current;  // node ปัจจุบันที่ชี้อยู่ (ใช้สำหรับ circular queue - หมุนต่อไป)

    /**
     * addCounter - เพิ่มเคาท์เตอร์เข้า Circular Linked List
     * ต่อท้ายรายการและทำให้ node สุดท้ายชี้กลับไปยัง head
     * @param name - ชื่อของเคาท์เตอร์
     * Time Complexity: O(n) - ต้องวนลูปหาส่วนท้าย
     */
    public void addCounter(String name) {
        Node newNode = new Node(name);
        if (head == null) {
            head = newNode;
            head.next = head;  // ชี้ไปยังตัวเอง (circular)
            current = head;
            return;
        }
        // วนลูปหา node สุดท้าย
        Node temp = head;
        while (temp.next != head) {  // วนลูปจนกว่า next จะชี้ไป head
            temp = temp.next;
        }
        temp.next = newNode;        // เชื่อม node ใหม่เข้า
        newNode.next = head;        // ให้ node ใหม่ชี้กลับไป head (circular)
    }

    /**
     * nextCounter - คืนเคาท์เตอร์ปัจจุบัน และเลื่อน pointer ไปที่ถัดไป
     * ใช้สำหรับเรียกเคาท์เตอร์หมุนเวียน (Round Robin)
     * @return ชื่อเคาท์เตอร์ปัจจุบัน
     * Time Complexity: O(1)
     */
    public String nextCounter() {
        if (current == null) return "No Counter";  // ถ้าไม่มีเคาท์เตอร์
        String name = current.counterName;  // เก็บชื่อเคาท์เตอร์ปัจจุบัน
        current = current.next;  // เลื่อน pointer ไปเคาท์เตอร์ถัดไป (circular)
        return name;
    }

    /**
     * display - แสดงเคาท์เตอร์หลายรอบในระบบ Circular Linked List
     * แสดงเคาท์เตอร์ซ้ำหลายครั้งเพื่อแสดงว่าเป็น circular
     * @param rounds - จำนวนรอบที่ต้องการแสดง
     * Time Complexity: O(rounds)
     */
    public void display(int rounds) {
        Node temp = head;
        for (int i = 0; i < rounds; i++) {  // วนลูปแสดง rounds ครั้ง
            System.out.println(temp.counterName);  // พิมพ์ชื่อเคาท์เตอร์
            temp = temp.next;  // เลื่อนไปเคาท์เตอร์ถัดไป (จะวนกลับมาหัวถ้า rounds > จำนวนเคาท์เตอร์)
        }
    }
}