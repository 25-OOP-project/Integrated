// package Attend2;

import java.util.ArrayList;
import java.util.List;

public class AttendanceManager {

    private List<AttendanceRecord> records = new ArrayList<>();
    private LectureManager lectureManager; // LectureManager 참조

    public AttendanceManager(LectureManager lectureManager) {
        this.lectureManager = lectureManager;
    }

    public void setRecords(List<AttendanceRecord> records) {
        this.records = records;
    }

    public List<AttendanceRecord> getRecords() {
        return records;
    }

    public void addRecord(AttendanceRecord record) {
        records.add(record);
    }

    // 강의별 출석률 계산 (출석 / 전체)
    public double getAttendanceRate(String lectureName) {
        int total = 0;
        int attended = 0;
        for (AttendanceRecord r : records) {
            if (r.getLectureName().equals(lectureName)) {
                total++;
                if ("출석".equals(r.getStatus())) {
                    attended++;
                }
            }
        }
        return total == 0 ? 0 : (double) attended / total;
    }

    // 강의별 출석 기록 반환
    public List<AttendanceRecord> getRecordsForLecture(String lectureName) {
        List<AttendanceRecord> result = new ArrayList<>();
        for (AttendanceRecord r : records) {
            if (r.getLectureName().equals(lectureName)) {
                result.add(r);
            }
        }
        return result;
    }
}
