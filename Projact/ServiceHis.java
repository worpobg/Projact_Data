package Projact;

/**
 * ServiceHis - คลาสสำหรับจัดการประวัติการเรียกคิว
 * ใช้ Doubly Linked List (DLL) - โครงสร้างข้อมูลรายการเชื่อมสองทาง
 * แต่ละ Node มี pointer สองตัว: prev (ชี้ไปข้างหน้า) และ next (ชี้ไปข้างหลัง)
 * เก็บข้อมูล: นักเรียนที่เรียกและหมายเลขเคาท์เตอร์ที่เรียกไป
 */
class ServiceHis {
    /**
     * Node - โหนดของ Doubly Linked List
     * เก็บข้อมูลนักเรียน เลขเคาท์เตอร์ และ pointer ไปข้างหน้าและข้างหลัง
     */
    class Node {
        // ทำงานกับ: displayForward (บรรทัด 65), displayBackward (บรรทัด 76), getHistoryCounter (บรรทัด 87)
        Student data;    // เก็บข้อมูลนักเรียน
        
        // ทำงานกับ: displayForward (บรรทัด 65), displayBackward (บรรทัด 76)
        String counter;  // เก็บชื่อเคาท์เตอร์ที่เรียก
        
        // ทำงานกับ: add (บรรทัด 52), displayBackward (บรรทัด 74)
        Node prev;       // pointer ชี้ไปข้างหน้า (ย้อนกลับในรายการ)
        
        // ทำงานกับ: add (บรรทัด 51), displayForward (บรรทัด 63), getHistoryCounter (บรรทัด 85)
        Node next;       // pointer ชี้ไปข้างหลัง (ไปข้างหน้าในรายการ)

        Node(Student data, String counter) {
            this.data = data;
            this.counter = counter;
        }
    }

    // ทำงานกับ: add (บรรทัด 47-53), displayForward (บรรทัด 61-67), getHistoryCounter (บรรทัด 83-93)
    Node head;  // node แรก (หัวรายการ) - ชี้ไปยังประวัติเรียกแรก
    
    // ทำงานกับ: add (บรรทัด 47-54), displayBackward (บรรทัด 73-79)
    Node tail;  // node สุดท้าย - ชี้ไปยังประวัติเรียกล่าสุด

    /**
     * add - เพิ่มประวัติการเรียกคิวเข้าท้ายรายการ
     * ตั้งค่า pointer prev และ next ให้เชื่อมต่อกัน
     * Time Complexity: O(1)
     */
    public void add(Student s, String counter) {
        Node newNode = new Node(s, counter);
        if (head == null) {
            head = tail = newNode;  // ถ้าว่าง node ใหม่เป็นทั้ง head และ tail
            return;
        }
        tail.next = newNode;       // เชื่อม tail ปัจจุบันไปยัง node ใหม่
        newNode.prev = tail;       // เชื่อม node ใหม่ย้อนกลับไปยัง tail เก่า
        tail = newNode;            // อัปเดต tail ให้เป็น node ใหม่
    }

    /**
     * displayForward - แสดงประวัติเรียงลำดับจากเก่าไปใหม่ (หน้าไปหลัง)
     * เริ่มจาก head วนลูปผ่าน next ไปจนสิ้นสุด
     * Time Complexity: O(n)
     */
    public void displayForward() {
        Node cur = head;
        while (cur != null) {  // วนลูปจาก head ไปยัง tail
            System.out.println("ID: " + cur.data.id + " Name: " + cur.data.name + " Counter: " + cur.counter);
            cur = cur.next;    // เลื่อนไปยัง node ถัดไป
        }
    }

    /**
     * displayBackward - แสดงประวัติเรียงลำดับจากใหม่ไปเก่า (หลังไปหน้า)
     * เริ่มจาก tail วนลูปผ่าน prev ย้อนกลับมา
     * ใช้สำหรับแสดงประวัติโดยเรียงจากรายการล่าสุด
     * Time Complexity: O(n)
     */
    public void displayBackward() {
        Node cur = tail;
        while (cur != null) {  // วนลูปจาก tail ไปยัง head
            System.out.println("ID: " + cur.data.id + " Name: " + cur.data.name + " Counter: " + cur.counter);
            cur = cur.prev;    // เลื่อนไปยัง node ก่อนหน้า (ย้อนกลับ)
        }
    }

    /**
     * getHistoryCounter - ค้นหาหมายเลขเคาท์เตอร์ของนักเรียนจากประวัติ
     * วนลูปจาก head จนหา node ที่มี ID ตรงกัน
     * @param student - นักเรียนที่ต้องการค้นหา
     * @return ชื่อเคาท์เตอร์ที่เรียก, null ถ้าไม่พบในประวัติ
     * Time Complexity: O(n)
     */
    public String getHistoryCounter(Student student) {
        Node cur = head;
        while (cur != null) {  // วนลูปผ่านทั้ง linked list
            if (cur.data.id == student.id) {  // เมื่อเจอ ID ที่ตรงกัน
                return cur.counter;  // คืนชื่อเคาท์เตอร์
            }
            cur = cur.next;  // เลื่อนไปยัง node ถัดไป
        }
        return null;
    }
}