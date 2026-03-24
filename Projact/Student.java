package Projact;

/**
 * Student - คลาสสำหรับเก็บข้อมูลนักเรียน
 * เก็บข้อมูล: รหัสประจำตัว (id) และชื่อ (name)
 */
public class Student {
    // ทำงานกับ: toString (บรรทัด 24), Main.findStudentById (บรรทัด 15-16 ใน Main)
    int id;       // เก็บรหัสประจำตัวของนักเรียน (ใช้สำหรับค้นหาและเปรียบเทียบ)
    
    // ทำงานกับ: toString (บรรทัด 24)
    String name;  // เก็บชื่อของนักเรียน

    /**
     * Constructor - สร้าง object Student ใหม่
     * @param id - รหัสประจำตัวของนักเรียน
     * @param name - ชื่อของนักเรียน
     */
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * toString - แสดงข้อมูลนักเรียนในรูปแบบข้อความ
     * @return ข้อความแสดง ID และชื่อของนักเรียน
     */
    public String toString() {
        return "ID: " + id + " Name: " + name;
    }
}